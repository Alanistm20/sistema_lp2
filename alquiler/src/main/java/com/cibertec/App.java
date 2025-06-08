package com.cibertec;

import com.cibertec.sistema.util.JPAUtil;
import com.cibertec.sistema.vista.FormularioAlquiler;

import org.h2.tools.Server;

import javax.swing.*;

public class App {
    private static Server h2Server;

    private static Server iniciarServidorH2() {
        try {
            Server webServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
            System.out.println("H2 Web Console disponible en: http://localhost:8082");
            System.out.println("Conéctate con:");
            System.out.println("JDBC URL: jdbc:h2:mem:alquilerdb");
            System.out.println("Usuario: sa");
            System.out.println("Contraseña: (dejar vacío)");
            return webServer;
        } catch (java.sql.SQLException e) {
            System.err.println("Error al iniciar el servidor H2");
            e.printStackTrace();
            return null;
        }
    }

    private static void stopH2Server() {
        if (h2Server != null) {
            h2Server.stop();
        }
    }

    public static void main(String[] args) {
        h2Server = iniciarServidorH2();

        // Ejecutar el formulario Swing en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            FormularioAlquiler formulario = new FormularioAlquiler();
            formulario.setLocationRelativeTo(null); // Centrar la ventana
            formulario.setVisible(true);
        });

        // Hook de apagado para liberar recursos
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            stopH2Server();
            JPAUtil.shutdown();
            System.out.println(">>> APLICACIÓN FINALIZADA <<<");
        }));
    }
}

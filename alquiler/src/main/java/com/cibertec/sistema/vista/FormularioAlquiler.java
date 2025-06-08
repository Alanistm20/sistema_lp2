package com.cibertec.sistema.vista;

import com.cibertec.sistema.dao.ClienteDAO;
import com.cibertec.sistema.dao.PeliculaDAO;
import com.cibertec.sistema.dao.AlquilerDAO;
import com.cibertec.sistema.modelo.*;

import javax.swing.*;
import java.awt.*;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class FormularioAlquiler extends JFrame {
    private JComboBox<Cliente> comboClientes;
    private JComboBox<Pelicula> comboPeliculas;
    private JTextField txtCantidad;
    private JButton btnAgregar;
    private JButton btnGuardar;
    private JTextArea txtResumen;
    private JLabel lblTotal;

    private List<DetalleAlquiler> detalles = new ArrayList<>();
    private double total = 0.0;

    public FormularioAlquiler() {
        setTitle("Gestión de Alquiler");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 10, 10));

        comboClientes = new JComboBox<>(ClienteDAO.obtenerTodos().toArray(new Cliente[0]));
        comboPeliculas = new JComboBox<>(PeliculaDAO.obtenerTodos().toArray(new Pelicula[0]));
        txtCantidad = new JTextField();

        btnAgregar = new JButton("Agregar Película");
        btnGuardar = new JButton("Registrar Alquiler");

        txtResumen = new JTextArea();
        txtResumen.setEditable(false);
        lblTotal = new JLabel("Total: S/ 0.0");

        panelFormulario.add(new JLabel("Cliente:"));
        panelFormulario.add(comboClientes);
        panelFormulario.add(new JLabel("Película:"));
        panelFormulario.add(comboPeliculas);
        panelFormulario.add(new JLabel("Cantidad:"));
        panelFormulario.add(txtCantidad);
        panelFormulario.add(btnAgregar);
        panelFormulario.add(btnGuardar);

        add(panelFormulario, BorderLayout.NORTH);
        add(new JScrollPane(txtResumen), BorderLayout.CENTER);
        add(lblTotal, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> agregarPelicula());
        btnGuardar.addActionListener(e -> guardarAlquiler());
    }

    private void agregarPelicula() {
        Pelicula peli = (Pelicula) comboPeliculas.getSelectedItem();
        int cantidad;

        try {
            cantidad = Integer.parseInt(txtCantidad.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese una cantidad válida.");
            return;
        }

        if (cantidad <= 0 || cantidad > peli.getStock()) {
            JOptionPane.showMessageDialog(this, "Cantidad inválida o stock insuficiente.");
            return;
        }

        detalles.add(new DetalleAlquiler(peli, cantidad));
        double subtotal = cantidad * 10; // precio fijo
        total += subtotal;

        txtResumen.append("Película: " + peli.getTitulo() + " | Cantidad: " + cantidad + " | Subtotal: S/ " + subtotal + "\n");
        lblTotal.setText("Total: S/ " + total);
    }

    private void guardarAlquiler() {
        Cliente cliente = (Cliente) comboClientes.getSelectedItem();
        if (cliente == null || detalles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar cliente y agregar al menos una película.");
            return;
        }

        Alquiler alquiler = new Alquiler();
        alquiler.setCliente(cliente);
        alquiler.setFecha(LocalDate.now());
        alquiler.setEstado(EstadoAlquiler.ACTIVO);
        alquiler.setTotal(total);

        try {
            AlquilerDAO.guardarConDetalles(alquiler, detalles);
            JOptionPane.showMessageDialog(this, "Alquiler registrado exitosamente.");
            this.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar el alquiler.");
        }



    }
}

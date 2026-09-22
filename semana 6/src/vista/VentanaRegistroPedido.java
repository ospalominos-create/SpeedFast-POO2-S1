package vista;

import modelo.Pedido;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaRegistroPedido extends JFrame {

    private final JTextField txtId;
    private final JTextField txtDireccion;
    private final JComboBox<String> comboTipo;
    private final List<Pedido> listaPedidos;

    public VentanaRegistroPedido(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;

        setTitle("SpeedFast - Registro de Pedido");
        setSize(420, 260);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout(10, 10));

        Font fuenteTexto = VentanaPrincipal.obtenerFuente("Roboto", Font.PLAIN, 13);
        Font fuenteEtiquetas = VentanaPrincipal.obtenerFuente("Roboto", Font.BOLD, 13);

        JPanel panelForm = new JPanel(new GridLayout(3, 2, 10, 12));
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 25, 10, 25));

        JLabel lblId = new JLabel("ID Pedido:");
        lblId.setFont(fuenteEtiquetas);
        txtId = new JTextField();
        txtId.setFont(fuenteTexto);

        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setFont(fuenteEtiquetas);
        txtDireccion = new JTextField();
        txtDireccion.setFont(fuenteTexto);

        JLabel lblTipo = new JLabel("Tipo de Pedido:");
        lblTipo.setFont(fuenteEtiquetas);
        comboTipo = new JComboBox<>(new String[]{"COMIDA", "ENCOMIENDA", "EXPRESS"});
        comboTipo.setFont(fuenteTexto);

        panelForm.add(lblId);
        panelForm.add(txtId);
        panelForm.add(lblDireccion);
        panelForm.add(txtDireccion);
        panelForm.add(lblTipo);
        panelForm.add(comboTipo);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCerrar = new JButton("Cerrar");

        btnGuardar.setFont(fuenteTexto);
        btnCerrar.setFont(fuenteTexto);
        btnGuardar.setFocusPainted(false);
        btnCerrar.setFocusPainted(false);

        panelBotones.add(btnGuardar);
        panelBotones.add(btnCerrar);

        add(panelForm, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        btnGuardar.addActionListener(e -> guardarPedido());
        btnCerrar.addActionListener(e -> dispose());
    }

    private void guardarPedido() {
        String textoId = txtId.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String tipo = (String) comboTipo.getSelectedItem();

        if (textoId.isEmpty() || direccion.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Todos los campos son obligatorios.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int id;
        try {
            id = Integer.parseInt(textoId);
            if (id <= 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "El ID debe ser un número entero mayor a 0.",
                        "Validación",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "El ID debe ser exclusivamente numérico.",
                    "Error de Formato",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        for (Pedido p : listaPedidos) {
            if (p.getId() == id) {
                JOptionPane.showMessageDialog(
                        this,
                        "Ya existe un pedido con el ID #" + id + ".",
                        "ID Duplicado",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }
        }

        try {
            Pedido nuevo = new Pedido(id, direccion, tipo);
            listaPedidos.add(nuevo);

            JOptionPane.showMessageDialog(
                    this,
                    "Pedido #" + id + " registrado exitosamente.",
                    "Registro Exitoso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            txtId.setText("");
            txtDireccion.setText("");
            comboTipo.setSelectedIndex(0);
            txtId.requestFocus();

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}

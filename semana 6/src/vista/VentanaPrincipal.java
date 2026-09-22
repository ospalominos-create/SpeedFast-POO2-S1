package vista;

import modelo.Pedido;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaPrincipal extends JFrame {

    private final List<Pedido> listaPedidos;

    public VentanaPrincipal() {
        this.listaPedidos = new ArrayList<>();

        listaPedidos.add(new Pedido(101, "Av. Providencia 123", "COMIDA"));
        listaPedidos.add(new Pedido(102, "Av. Santa Rosa 567", "ENCOMIENDA"));
        listaPedidos.add(new Pedido(103, "Calle Los Alerces 890", "EXPRESS"));

        setTitle("SpeedFast - Gestión de Entregas");
        setSize(460, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout(15, 15));

        Font fuenteTitulo = obtenerFuente("Roboto", Font.BOLD, 18);
        Font fuenteBotones = obtenerFuente("Roboto", Font.PLAIN, 14);

        JLabel lblTitulo = new JLabel("Panel Principal SpeedFast", SwingConstants.CENTER);
        lblTitulo.setFont(fuenteTitulo);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 12, 12));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 45, 25, 45));

        JButton btnRegistrar = new JButton("1. Registrar Nuevo Pedido");
        JButton btnListar = new JButton("2. Listar Pedidos Registrados");
        JButton btnAsignar = new JButton("3. Iniciar / Simular Entrega");

        btnRegistrar.setFont(fuenteBotones);
        btnListar.setFont(fuenteBotones);
        btnAsignar.setFont(fuenteBotones);

        btnRegistrar.setFocusPainted(false);
        btnListar.setFocusPainted(false);
        btnAsignar.setFocusPainted(false);

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnListar);
        panelBotones.add(btnAsignar);

        add(panelBotones, BorderLayout.CENTER);

        btnRegistrar.addActionListener(e -> {
            VentanaRegistroPedido reg = new VentanaRegistroPedido(listaPedidos);
            reg.setVisible(true);
        });

        btnListar.addActionListener(e -> {
            VentanaListaPedidos list = new VentanaListaPedidos(listaPedidos);
            list.setVisible(true);
        });

        btnAsignar.addActionListener(e -> {
            if (listaPedidos.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "No hay pedidos registrados en el sistema.",
                        "Aviso",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Simulación iniciada: " + listaPedidos.size() + " pedidos listos para reparto.",
                        "SpeedFast Envíos",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
    }

    public static Font obtenerFuente(String nombrePreferido, int estilo, int tamano) {
        Font font = new Font(nombrePreferido, estilo, tamano);
        if (!font.getFamily().equalsIgnoreCase(nombrePreferido)) {
            font = new Font("Segoe UI", estilo, tamano);
        }
        if (!font.getFamily().equalsIgnoreCase("Segoe UI")) {
            font = new Font(Font.SANS_SERIF, estilo, tamano);
        }
        return font;
    }
}

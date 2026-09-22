package vista;

import modelo.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaListaPedidos extends JFrame {

    private final List<Pedido> listaPedidos;
    private final DefaultTableModel modeloTabla;

    public VentanaListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;

        setTitle("SpeedFast - Listado de Pedidos");
        setSize(560, 360);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        Font fuenteTexto = VentanaPrincipal.obtenerFuente("Roboto", Font.PLAIN, 13);
        Font fuenteEncabezado = VentanaPrincipal.obtenerFuente("Roboto", Font.BOLD, 13);

        String[] columnas = {"ID", "Dirección de Entrega", "Tipo"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable tabla = new JTable(modeloTabla);
        tabla.setFont(fuenteTexto);
        tabla.setRowHeight(22);
        tabla.getTableHeader().setFont(fuenteEncabezado);
        tabla.getTableHeader().setReorderingAllowed(false);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createEmptyBorder(12, 15, 5, 15));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        JButton btnRefrescar = new JButton("Refrescar");
        JButton btnCerrar = new JButton("Cerrar");

        btnRefrescar.setFont(fuenteTexto);
        btnCerrar.setFont(fuenteTexto);
        btnRefrescar.setFocusPainted(false);
        btnCerrar.setFocusPainted(false);

        panelBotones.add(btnRefrescar);
        panelBotones.add(btnCerrar);

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        btnRefrescar.addActionListener(e -> cargarDatos());
        btnCerrar.addActionListener(e -> dispose());

        cargarDatos();
    }

    public void cargarDatos() {
        modeloTabla.setRowCount(0);
        for (Pedido p : listaPedidos) {
            modeloTabla.addRow(new Object[]{
                    p.getId(),
                    p.getDireccionEntrega(),
                    p.getTipo()
            });
        }
    }
}

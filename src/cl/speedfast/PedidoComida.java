package cl.speedfast;

public class PedidoComida extends Pedido {

    public PedidoComida(int idPedido, String direccionEntrega) {
        super(idPedido, direccionEntrega, "Comida");
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("Pedido de comida #" + getIdPedido()
                + " → Se valida que el repartidor cuente con mochila térmica.");
    }

    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        asignarRepartidor();
        System.out.println("   → Repartidor '" + nombreRepartidor + "' asignado.");
    }
}

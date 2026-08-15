package cl.speedfast;

public class PedidoExpress extends Pedido {

    public PedidoExpress(int idPedido, String direccionEntrega) {
        super(idPedido, direccionEntrega, "Express");
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("Pedido express #" + getIdPedido()
                + " → Se asigna el repartidor más cercano con disponibilidad inmediata.");
    }

    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        asignarRepartidor();
        System.out.println("   → Repartidor '" + nombreRepartidor + "' asignado como el más cercano disponible.");
    }
}

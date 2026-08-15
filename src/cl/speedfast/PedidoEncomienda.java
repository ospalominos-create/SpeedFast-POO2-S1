package cl.speedfast;

public class PedidoEncomienda extends Pedido {

    public PedidoEncomienda(int idPedido, String direccionEntrega) {
        super(idPedido, direccionEntrega, "Encomienda");
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("Pedido de encomienda #" + getIdPedido()
                + " → Se valida peso y embalaje antes de asignar repartidor.");
    }

    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        asignarRepartidor();
        System.out.println("   → Repartidor '" + nombreRepartidor + "' asignado.");
    }
}

package cl.speedfast;

public class PedidoEncomienda extends Pedido {

    private static final int TIEMPO_BASE_MIN = 20;
    private static final double MIN_POR_KM = 1.5;

    public PedidoEncomienda(
            int idPedido,
            String direccionEntrega,
            double distanciaKm) {

        super(idPedido, direccionEntrega, distanciaKm);
    }

    @Override
    protected String obtenerTipoPedido() {
        return "Encomienda";
    }

    @Override
    public int calcularTiempoEntrega() {
        return Math.round((float) (
                TIEMPO_BASE_MIN
                        + MIN_POR_KM * getDistanciaKm()));
    }

    @Override
    public void asignarRepartidor() {
        asignarRepartidor("Repartidor genérico de encomiendas");
        registrarEvento(
                "Asignación automática: valida peso y embalaje."
        );
    }
}

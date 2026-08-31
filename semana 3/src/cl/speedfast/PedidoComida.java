package cl.speedfast;

public class PedidoComida extends Pedido {

    private static final int TIEMPO_BASE_MIN = 15;
    private static final int MIN_POR_KM = 2;

    public PedidoComida(
            int idPedido,
            String direccionEntrega,
            double distanciaKm) {

        super(idPedido, direccionEntrega, distanciaKm);
    }

    @Override
    protected String obtenerTipoPedido() {
        return "Comida";
    }

    @Override
    public int calcularTiempoEntrega() {
        return (int) (TIEMPO_BASE_MIN
                + MIN_POR_KM * getDistanciaKm());
    }

    @Override
    public void asignarRepartidor() {
        String repartidor = "Luis Díaz";
        asignarRepartidor(repartidor);

        registrarEvento(
                "Asignación automática: requiere mochila térmica.");
    }
}

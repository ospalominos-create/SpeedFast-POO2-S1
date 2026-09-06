package cl.speedfast;

public class PedidoExpress extends Pedido {

    private static final int TIEMPO_BASE_MIN = 10;
    private static final double UMBRAL_KM = 5.0;
    private static final int EXTRA_MIN = 5;

    public PedidoExpress(
            int idPedido,
            String direccionEntrega,
            double distanciaKm) {

        super(idPedido, direccionEntrega, distanciaKm);
    }

    @Override
    protected String obtenerTipoPedido() {
        return "Express";
    }

    @Override
    public int calcularTiempoEntrega() {
        int tiempo = TIEMPO_BASE_MIN;

        if (getDistanciaKm() > UMBRAL_KM) {
            tiempo += EXTRA_MIN;
        }

        return tiempo;
    }

    @Override
    public void asignarRepartidor() {
        asignarRepartidor("Repartidor genérico express");
        registrarEvento(
                "Asignación automática: repartidor cercano disponible."
        );
    }
}

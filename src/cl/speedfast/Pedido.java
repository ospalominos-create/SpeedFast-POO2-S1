package cl.speedfast;

public abstract class Pedido {

    private int idPedido;
    private String direccionEntrega;
    private double distanciaKm;

    public Pedido(int idPedido, String direccionEntrega, double distanciaKm) {
        if (idPedido <= 0) {
            throw new IllegalArgumentException("El idPedido debe ser mayor a 0.");
        }
        if (direccionEntrega == null || direccionEntrega.isBlank()) {
            throw new IllegalArgumentException("La dirección de entrega no puede estar vacía.");
        }
        if (distanciaKm < 0) {
            throw new IllegalArgumentException("La distancia no puede ser negativa.");
        }
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKm = distanciaKm;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void mostrarResumen() {
        System.out.println("Pedido #" + idPedido
                + " | Tipo: " + obtenerTipoPedido()
                + " | Dirección: " + direccionEntrega
                + " | Distancia: " + distanciaKm + " km");
    }

    protected abstract String obtenerTipoPedido();

    public abstract int calcularTiempoEntrega();
}

package cl.speedfast;

import java.util.ArrayList;
import java.util.List;

public abstract class Pedido
        implements Despachable, Cancelable, Rastreable {

    private int idPedido;
    private String direccionEntrega;
    private double distanciaKm;
    private String repartidorAsignado;
    private boolean cancelado;
    private boolean despachado;
    private final List<String> historial;

    public Pedido(int idPedido, String direccionEntrega, double distanciaKm) {
        if (idPedido <= 0) {
            throw new IllegalArgumentException(
                    "El idPedido debe ser mayor a 0.");
        }

        if (direccionEntrega == null || direccionEntrega.isBlank()) {
            throw new IllegalArgumentException(
                    "La dirección de entrega no puede estar vacía.");
        }

        if (distanciaKm < 0) {
            throw new IllegalArgumentException(
                    "La distancia no puede ser negativa.");
        }

        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKm = distanciaKm;
        this.historial = new ArrayList<>();
        this.historial.add("Pedido creado: #" + idPedido);
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

    public String getRepartidorAsignado() {
        return repartidorAsignado;
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public boolean isDespachado() {
        return despachado;
    }

    public void mostrarResumen() {
        System.out.println("Pedido #" + idPedido
                + " | Tipo: " + obtenerTipoPedido()
                + " | Dirección: " + direccionEntrega
                + " | Distancia: " + distanciaKm + " km");
    }

    public abstract int calcularTiempoEntrega();

    protected abstract String obtenerTipoPedido();

    public abstract void asignarRepartidor();

    public void asignarRepartidor(String nombreRepartidor) {
        if (nombreRepartidor == null || nombreRepartidor.isBlank()) {
            System.out.println("No se pudo asignar un repartidor.");
            return;
        }

        this.repartidorAsignado = nombreRepartidor;
        registrarEvento("Repartidor asignado manualmente: "
                + nombreRepartidor);

        System.out.println("Pedido #" + idPedido
                + " → Repartidor asignado: " + nombreRepartidor);
    }

    protected void registrarEvento(String evento) {
        historial.add(evento);
    }

    @Override
    public void despachar() {
        if (cancelado) {
            System.out.println("Pedido #" + idPedido
                    + " no puede despacharse porque está cancelado.");
            registrarEvento("Intento de despacho rechazado: pedido cancelado.");
            return;
        }

        if (repartidorAsignado == null) {
            System.out.println("Pedido #" + idPedido
                    + " no puede despacharse sin repartidor.");
            registrarEvento("Intento de despacho rechazado: sin repartidor.");
            return;
        }

        despachado = true;
        registrarEvento("Pedido despachado por " + repartidorAsignado);

        System.out.println("Pedido #" + idPedido
                + " despachado correctamente.");
    }

    @Override
    public void cancelar() {
        if (despachado) {
            System.out.println("Pedido #" + idPedido
                    + " no puede cancelarse porque ya fue despachado.");
            registrarEvento("Intento de cancelación rechazado: ya despachado.");
            return;
        }

        if (cancelado) {
            System.out.println("Pedido #" + idPedido
                    + " ya estaba cancelado.");
            return;
        }

        cancelado = true;
        registrarEvento("Pedido cancelado.");

        System.out.println("Pedido #" + idPedido
                + " cancelado exitosamente.");
    }

    @Override
    public void verHistorial() {
        System.out.println("Historial del pedido #"
                + idPedido + " (" + obtenerTipoPedido() + "):");

        for (String evento : historial) {
            System.out.println("- " + evento);
        }
    }
}

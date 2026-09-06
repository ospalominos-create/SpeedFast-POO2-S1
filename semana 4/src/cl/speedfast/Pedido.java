package cl.speedfast;

import java.util.ArrayList;
import java.util.List;

public abstract class Pedido
        implements Despachable, Cancelable, Rastreable {

    private final int idPedido;
    private final String direccionEntrega;
    private final double distanciaKm;

    private String repartidorAsignado;
    private boolean cancelado;
    private boolean despachado;

    private final List<String> historial;

    public Pedido(
            int idPedido,
            String direccionEntrega,
            double distanciaKm) {

        if (idPedido <= 0) {
            throw new IllegalArgumentException(
                    "El id del pedido debe ser mayor que cero."
            );
        }

        if (direccionEntrega == null || direccionEntrega.isBlank()) {
            throw new IllegalArgumentException(
                    "La dirección de entrega no puede estar vacía."
            );
        }

        if (distanciaKm < 0) {
            throw new IllegalArgumentException(
                    "La distancia no puede ser negativa."
            );
        }

        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKm = distanciaKm;
        this.historial = new ArrayList<>();

        registrarEvento("Pedido creado.");
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
        System.out.println(
                "Pedido #" + idPedido
                        + " | Tipo: " + obtenerTipoPedido()
                        + " | Dirección: " + direccionEntrega
                        + " | Distancia: " + distanciaKm + " km"
        );
    }

    public abstract int calcularTiempoEntrega();

    protected abstract String obtenerTipoPedido();

    public abstract void asignarRepartidor();

    public void asignarRepartidor(String nombreRepartidor) {
        if (nombreRepartidor == null || nombreRepartidor.isBlank()) {
            System.out.println(
                    "No se pudo asignar un repartidor al pedido #"
                            + idPedido + "."
            );

            registrarEvento(
                    "Intento de asignación rechazado: "
                            + "nombre de repartidor inválido."
            );

            return;
        }

        this.repartidorAsignado = nombreRepartidor;

        registrarEvento(
                "Repartidor asignado: " + nombreRepartidor + "."
        );

        System.out.println(
                "Pedido #" + idPedido
                        + " asignado al repartidor: "
                        + nombreRepartidor + "."
        );
    }

    @Override
    public void despachar() {
        if (cancelado) {
            System.out.println(
                    "Pedido #" + idPedido
                            + " no puede despacharse porque fue cancelado."
            );

            registrarEvento(
                    "Intento de despacho rechazado: "
                            + "el pedido está cancelado."
            );

            return;
        }

        if (despachado) {
            System.out.println(
                    "Pedido #" + idPedido
                            + " ya fue despachado."
            );

            registrarEvento(
                    "Intento de despacho rechazado: "
                            + "el pedido ya estaba despachado."
            );

            return;
        }

        if (repartidorAsignado == null) {
            System.out.println(
                    "Pedido #" + idPedido
                            + " no puede despacharse sin repartidor asignado."
            );

            registrarEvento(
                    "Intento de despacho rechazado: "
                            + "sin repartidor asignado."
            );

            return;
        }

        despachado = true;

        registrarEvento(
                "Pedido despachado por "
                        + repartidorAsignado + "."
        );

        System.out.println(
                "Pedido #" + idPedido
                        + " despachado correctamente por "
                        + repartidorAsignado + "."
        );
    }

    @Override
    public void cancelar() {
        if (despachado) {
            System.out.println(
                    "Pedido #" + idPedido
                            + " no puede cancelarse porque ya fue despachado."
            );

            registrarEvento(
                    "Intento de cancelación rechazado: "
                            + "el pedido ya fue despachado."
            );

            return;
        }

        if (cancelado) {
            System.out.println(
                    "Pedido #" + idPedido
                            + " ya estaba cancelado."
            );

            registrarEvento(
                    "Intento de cancelación rechazado: "
                            + "el pedido ya estaba cancelado."
            );

            return;
        }

        cancelado = true;

        registrarEvento("Pedido cancelado.");

        System.out.println(
                "Pedido #" + idPedido
                        + " cancelado correctamente."
        );
    }

    @Override
    public void verHistorial() {
        System.out.println(
                "Historial del pedido #"
                        + idPedido
                        + " (" + obtenerTipoPedido() + "):"
        );

        for (String evento : historial) {
            System.out.println("- " + evento);
        }
    }

    protected void registrarEvento(String evento) {
        historial.add(evento);
    }
}

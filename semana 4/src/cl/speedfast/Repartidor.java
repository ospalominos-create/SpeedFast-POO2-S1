package cl.speedfast;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Repartidor implements Runnable {

    private final String nombre;
    private final List<Pedido> pedidosAsignados;

    public Repartidor(
            String nombre,
            List<Pedido> pedidosAsignados) {

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre del repartidor no puede estar vacío."
            );
        }

        if (pedidosAsignados == null || pedidosAsignados.isEmpty()) {
            throw new IllegalArgumentException(
                    "El repartidor debe tener pedidos asignados."
            );
        }

        this.nombre = nombre;
        this.pedidosAsignados = pedidosAsignados;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pedido> getPedidosAsignados() {
        return pedidosAsignados;
    }

    @Override
    public void run() {
        System.out.println(
                "[Repartidor: " + nombre + "] "
                        + "Inició su jornada con "
                        + pedidosAsignados.size()
                        + " pedidos asignados."
        );

        for (Pedido pedido : pedidosAsignados) {
            entregarPedido(pedido);
        }

        System.out.println(
                "[Repartidor: " + nombre + "] "
                        + "Finalizó todas sus entregas."
        );
    }

    private void entregarPedido(Pedido pedido) {
        String tipoPedido = pedido.getClass().getSimpleName();

        System.out.println(
                "[Repartidor: " + nombre + "] "
                        + "Entregando " + tipoPedido
                        + " #" + pedido.getIdPedido()
                        + " en " + pedido.getDireccionEntrega()
                        + "."
        );

        try {
            int tiempoEntrega = ThreadLocalRandom.current()
                    .nextInt(700, 1801);

            Thread.sleep(tiempoEntrega);

            pedido.despachar();

            System.out.println(
                    "[Repartidor: " + nombre + "] "
                            + "Pedido #" + pedido.getIdPedido()
                            + " entregado en "
                            + tiempoEntrega + " ms."
            );

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

            System.out.println(
                    "[Repartidor: " + nombre + "] "
                            + "La entrega del pedido #"
                            + pedido.getIdPedido()
                            + " fue interrumpida."
            );
        }
    }
}

package cl.speedfast;

import java.util.concurrent.ThreadLocalRandom;

public class Repartidor implements Runnable {

    private final String nombre;
    private final ZonaDeCarga zonaDeCarga;

    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del repartidor no puede estar vacío.");
        }
        if (zonaDeCarga == null) {
            throw new IllegalArgumentException("La zona de carga no puede ser nula.");
        }
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        while (true) {
            Pedido pedido = zonaDeCarga.retirarPedido();

            if (pedido == null) {
                break;
            }

            entregarPedido(pedido);
        }
    }

    private void entregarPedido(Pedido pedido) {
        System.out.println("[Repartidor - " + nombre + "] Retirando pedido #" + pedido.getId() + "...");
        System.out.println("[Repartidor - " + nombre + "] Estado: " + pedido.getEstado());

        try {
            int tiempoSimulado = ThreadLocalRandom.current().nextInt(700, 1501);
            System.out.println("[Repartidor - " + nombre + "] Entregando pedido #" + pedido.getId() + "...");

            Thread.sleep(tiempoSimulado);

            pedido.setEstado(EstadoPedido.ENTREGADO);

            System.out.println("[Repartidor - " + nombre + "] Estado: " + pedido.getEstado());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("[Repartidor - " + nombre + "] Interrumpido en pedido #" + pedido.getId());
        }
    }
}

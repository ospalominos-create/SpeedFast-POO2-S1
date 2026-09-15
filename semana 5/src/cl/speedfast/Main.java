package cl.speedfast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        System.out.println("[Zona de carga inicializada]\n");

        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();

        zonaDeCarga.agregarPedido(new Pedido(1, "Santiago Centro"));
        zonaDeCarga.agregarPedido(new Pedido(2, "Providencia"));
        zonaDeCarga.agregarPedido(new Pedido(3, "Ñuñoa"));
        zonaDeCarga.agregarPedido(new Pedido(4, "Recoleta"));
        zonaDeCarga.agregarPedido(new Pedido(5, "Las Condes"));

        Repartidor juan = new Repartidor("Juan", zonaDeCarga);
        Repartidor camila = new Repartidor("Camila", zonaDeCarga);
        Repartidor pedro = new Repartidor("Pedro", zonaDeCarga);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        System.out.println();
        executor.submit(juan);
        executor.submit(camila);
        executor.submit(pedro);

        executor.shutdown();

        esperarFinalizacion(executor);

        System.out.println("\n--- Resumen final de entregas ---");
        for (Pedido p : zonaDeCarga.getPedidos()) {
            System.out.println(p);
        }

        if (zonaDeCarga.cantidadPendientes() == 0 
                && zonaDeCarga.cantidadEnReparto() == 0 
                && zonaDeCarga.cantidadEntregados() == 5) {
            System.out.println("\nTodos los pedidos han sido entregados correctamente.");
        } else {
            System.out.println("\nAdvertencia: Existen pedidos no completados.");
        }
    }

    private static void esperarFinalizacion(ExecutorService executor) {
        try {
            boolean terminado = executor.awaitTermination(1, TimeUnit.MINUTES);
            if (!terminado) {
                System.out.println("Tiempo de espera agotado. Forzando detención.");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
            System.out.println("Hilo principal interrumpido.");
        }
    }
}

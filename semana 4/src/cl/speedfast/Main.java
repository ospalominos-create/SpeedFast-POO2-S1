package cl.speedfast;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        System.out.println(
                "=== SPEEDFAST - ENTREGAS CONCURRENTES ===\n"
        );

        Pedido comida1 = new PedidoComida(
                101,
                "Av. Providencia 123",
                3.5
        );

        Pedido encomienda1 = new PedidoEncomienda(
                102,
                "Av. Santa Rosa 567",
                7.0
        );

        Pedido express1 = new PedidoExpress(
                103,
                "Calle Los Alerces 890",
                4.0
        );

        Pedido comida2 = new PedidoComida(
                104,
                "Av. Apoquindo 2450",
                5.2
        );

        Pedido encomienda2 = new PedidoEncomienda(
                105,
                "Av. Vicuña Mackenna 1330",
                8.4
        );

        Pedido express2 = new PedidoExpress(
                106,
                "Calle Holanda 320",
                2.8
        );

        Repartidor camila = new Repartidor(
                "Camila",
                List.of(comida1, encomienda1)
        );

        Repartidor luis = new Repartidor(
                "Luis",
                List.of(express1, comida2)
        );

        Repartidor sofia = new Repartidor(
                "Sofía",
                List.of(encomienda2, express2)
        );

        asignarNombreRepartidor(camila);
        asignarNombreRepartidor(luis);
        asignarNombreRepartidor(sofia);

        mostrarResumenPedidos(camila);
        mostrarResumenPedidos(luis);
        mostrarResumenPedidos(sofia);

        ExecutorService executor =
                Executors.newFixedThreadPool(3);

        System.out.println(
                "\n--- Iniciando entregas concurrentes ---\n"
        );

        executor.submit(camila);
        executor.submit(luis);
        executor.submit(sofia);

        executor.shutdown();

        esperarFinalizacion(executor);

        mostrarHistoriales(camila);
        mostrarHistoriales(luis);
        mostrarHistoriales(sofia);

        System.out.println(
                "\n=== TODAS LAS ENTREGAS HAN FINALIZADO ==="
        );
    }

    private static void asignarNombreRepartidor(
            Repartidor repartidor) {

        for (Pedido pedido : repartidor.getPedidosAsignados()) {
            pedido.asignarRepartidor(repartidor.getNombre());
        }
    }

    private static void mostrarResumenPedidos(
            Repartidor repartidor) {

        System.out.println(
                "\n--- Pedidos asignados a "
                        + repartidor.getNombre() + " ---"
        );

        for (Pedido pedido : repartidor.getPedidosAsignados()) {
            pedido.mostrarResumen();

            System.out.println(
                    "Tiempo estimado: "
                            + pedido.calcularTiempoEntrega()
                            + " minutos."
            );
        }
    }

    private static void esperarFinalizacion(
            ExecutorService executor) {

        try {
            boolean finalizo = executor.awaitTermination(
                    1,
                    TimeUnit.MINUTES
            );

            if (!finalizo) {
                System.out.println(
                        "Tiempo de espera agotado. "
                                + "Se forzará el cierre del executor."
                );

                executor.shutdownNow();
            }

        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();

            System.out.println(
                    "La espera del programa principal fue interrumpida."
            );
        }
    }

    private static void mostrarHistoriales(
            Repartidor repartidor) {

        System.out.println(
                "\n--- Historial de pedidos de "
                        + repartidor.getNombre() + " ---"
        );

        for (Pedido pedido : repartidor.getPedidosAsignados()) {
            pedido.verHistorial();
            System.out.println();
        }
    }
}

package cl.speedfast;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== SpeedFast: clases abstractas y jerarquía ===\n");

        Pedido[] pedidos = crearPedidos();

        demostrarResumenYTiempos(pedidos);
        demostrarComparativa(pedidos);
    }

    private static Pedido[] crearPedidos() {
        return new Pedido[] {
                new PedidoComida(1, "Av. Central 100", 4.0),
                new PedidoEncomienda(2, "Calle Norte 200", 8.0),
                new PedidoExpress(3, "Pasaje Sur 300", 6.5),
                new PedidoExpress(4, "Diagonal Poniente 400", 5.0)
        };
    }

    private static void demostrarResumenYTiempos(Pedido[] pedidos) {
        System.out.println("--- Resumen y tiempo estimado de cada pedido ---");
        for (Pedido pedido : pedidos) {
            pedido.mostrarResumen();
            System.out.println("Tiempo estimado: " + pedido.calcularTiempoEntrega() + " min\n");
        }
    }

    private static void demostrarComparativa(Pedido[] pedidos) {
        System.out.println("--- Comparativa de tiempos ---");
        for (Pedido pedido : pedidos) {
            System.out.println(pedido.getClass().getSimpleName()
                    + " (#" + pedido.getIdPedido() + "): "
                    + pedido.calcularTiempoEntrega() + " min");
        }
    }
}

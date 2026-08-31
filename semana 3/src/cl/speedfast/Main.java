package cl.speedfast;

public class Main {

    public static void main(String[] args) {

        System.out.println(
                "=== SPEEDFAST - ACTIVIDAD SUMATIVA 1 ===\n");

        PedidoComida comida = new PedidoComida(
                101,
                "Av. Providencia 123",
                4.0);

        PedidoEncomienda encomienda = new PedidoEncomienda(
                102,
                "Av. Santa Rosa 567",
                7.0);

        PedidoExpress express = new PedidoExpress(
                103,
                "Calle Los Alerces 890",
                6.0);

        Pedido[] pedidos = {
                comida,
                encomienda,
                express
        };

        ControladorDeEnvios controlador =
                new ControladorDeEnvios();

        for (Pedido pedido : pedidos) {
            controlador.registrarPedido(pedido);
        }

        mostrarAsignacionAutomatica(pedidos);
        mostrarAsignacionManual(encomienda);
        mostrarTiempos(pedidos);
        mostrarResumenes(pedidos);
        mostrarCancelacion(controlador, express);
        mostrarDespacho(controlador);
        mostrarHistorial(controlador);
    }

    private static void mostrarAsignacionAutomatica(
            Pedido[] pedidos) {

        System.out.println(
                "--- Asignación automática de repartidores ---");

        for (Pedido pedido : pedidos) {
            pedido.asignarRepartidor();
        }

        System.out.println();
    }

    private static void mostrarAsignacionManual(
            PedidoEncomienda encomienda) {

        System.out.println(
                "--- Asignación manual y sobrecarga ---");

        encomienda.asignarRepartidor("Daniela Tapia");

        System.out.println();
    }

    private static void mostrarTiempos(Pedido[] pedidos) {

        System.out.println(
                "--- Tiempos estimados de entrega ---");

        for (Pedido pedido : pedidos) {
            System.out.println(
                    pedido.getClass().getSimpleName()
                            + " #" + pedido.getIdPedido()
                            + ": "
                            + pedido.calcularTiempoEntrega()
                            + " minutos");
        }

        System.out.println();
    }

    private static void mostrarResumenes(Pedido[] pedidos) {

        System.out.println("--- Resumen de pedidos ---");

        for (Pedido pedido : pedidos) {
            pedido.mostrarResumen();
        }

        System.out.println();
    }

    private static void mostrarCancelacion(
            ControladorDeEnvios controlador,
            PedidoExpress express) {

        System.out.println("--- Cancelación ---");
        System.out.println(
                "Cancelando Pedido Express #"
                        + express.getIdPedido() + "...");

        controlador.cancelar(express);

        System.out.println();
    }

    private static void mostrarDespacho(
            ControladorDeEnvios controlador) {

        System.out.println("--- Despacho ---");

        controlador.despacharTodos();

        System.out.println();
    }

    private static void mostrarHistorial(
            ControladorDeEnvios controlador) {

        System.out.println("--- Historial de entregas ---");

        controlador.mostrarHistoriales();
    }
}

package cl.speedfast;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== SpeedFast: demostración de polimorfismo ===\n");

        Pedido[] pedidos = crearPedidos();

        demostrarSobrescritura(pedidos);
        demostrarSobrecarga(pedidos);
        demostrarToString(pedidos);
    }

    private static Pedido[] crearPedidos() {
        return new Pedido[] {
                new PedidoComida(1, "Av. Central 100"),
                new PedidoEncomienda(2, "Calle Norte 200"),
                new PedidoExpress(3, "Pasaje Sur 300")
        };
    }

    private static void demostrarSobrescritura(Pedido[] pedidos) {
        System.out.println("--- Asignación sin nombre de repartidor ---");
        for (Pedido pedido : pedidos) {
            pedido.asignarRepartidor();
        }
        System.out.println();
    }

    private static void demostrarSobrecarga(Pedido[] pedidos) {
        System.out.println("--- Asignación con nombre de repartidor ---");
        for (Pedido pedido : pedidos) {
            pedido.asignarRepartidor("Carlos");
            System.out.println();
        }
    }

    private static void demostrarToString(Pedido[] pedidos) {
        System.out.println("--- Detalle de cada pedido (toString) ---");
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
        }
    }
}

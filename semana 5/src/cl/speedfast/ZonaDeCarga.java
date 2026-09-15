package cl.speedfast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ZonaDeCarga {

    private final List<Pedido> pedidos;

    public ZonaDeCarga() {
        this.pedidos = new ArrayList<>();
    }

    public synchronized void agregarPedido(Pedido pedido) {
        if (pedido == null) {
            System.out.println("[ZonaDeCarga] Error: no se puede agregar un pedido nulo.");
            return;
        }
        pedidos.add(pedido);
        System.out.println("Pedido #" + pedido.getId() + " agregado. Destino: " + pedido.getDireccionEntrega());
    }

    public synchronized Pedido retirarPedido() {
        for (Pedido pedido : pedidos) {
            if (pedido.getEstado() == EstadoPedido.PENDIENTE) {
                pedido.setEstado(EstadoPedido.EN_REPARTO);
                return pedido;
            }
        }
        return null;
    }

    public synchronized int cantidadPendientes() {
        int count = 0;
        for (Pedido p : pedidos) {
            if (p.getEstado() == EstadoPedido.PENDIENTE) {
                count++;
            }
        }
        return count;
    }

    public synchronized int cantidadEnReparto() {
        int count = 0;
        for (Pedido p : pedidos) {
            if (p.getEstado() == EstadoPedido.EN_REPARTO) {
                count++;
            }
        }
        return count;
    }

    public synchronized int cantidadEntregados() {
        int count = 0;
        for (Pedido p : pedidos) {
            if (p.getEstado() == EstadoPedido.ENTREGADO) {
                count++;
            }
        }
        return count;
    }

    public synchronized List<Pedido> getPedidos() {
        return Collections.unmodifiableList(new ArrayList<>(pedidos));
    }
}

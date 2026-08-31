package cl.speedfast;

import java.util.ArrayList;
import java.util.List;

public class ControladorDeEnvios {

    private final List<Despachable> despachables;
    private final List<Cancelable> cancelables;
    private final List<Rastreable> rastreables;

    public ControladorDeEnvios() {
        despachables = new ArrayList<>();
        cancelables = new ArrayList<>();
        rastreables = new ArrayList<>();
    }

    public void registrarPedido(Pedido pedido) {
        despachables.add(pedido);
        cancelables.add(pedido);
        rastreables.add(pedido);
    }

    public void despacharTodos() {
        for (Despachable despachable : despachables) {
            despachable.despachar();
        }
    }

    public void cancelar(Cancelable cancelable) {
        cancelable.cancelar();
    }

    public void mostrarHistoriales() {
        for (Rastreable rastreable : rastreables) {
            rastreable.verHistorial();
            System.out.println();
        }
    }
}

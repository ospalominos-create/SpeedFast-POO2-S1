package cl.speedfast;

public class Pedido {

    private int idPedido;
    private String direccionEntrega;
    private String tipoPedido;

    public Pedido(int idPedido, String direccionEntrega, String tipoPedido) {
        if (idPedido <= 0) {
            throw new IllegalArgumentException("El idPedido debe ser mayor a 0.");
        }
        if (direccionEntrega == null || direccionEntrega.isBlank()) {
            throw new IllegalArgumentException("La dirección de entrega no puede estar vacía.");
        }
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.tipoPedido = tipoPedido;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public String getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(String tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public void asignarRepartidor() {
        System.out.println("Asignación genérica de repartidor para pedido " + idPedido);
    }

    public void asignarRepartidor(String nombreRepartidor) {
        asignarRepartidor();
        System.out.println("   → Repartidor asignado: " + nombreRepartidor);
    }

    @Override
    public String toString() {
        return "Pedido{id=" + idPedido
                + ", tipo='" + tipoPedido + "'"
                + ", direccion='" + direccionEntrega + "'}";
    }
}

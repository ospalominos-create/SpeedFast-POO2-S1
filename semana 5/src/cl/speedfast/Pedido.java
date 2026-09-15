package cl.speedfast;

public class Pedido {

    private int id;
    private String direccionEntrega;
    private double distanciaKm;
    private EstadoPedido estado;

    public Pedido(int id, String direccionEntrega) {
        this(id, direccionEntrega, 0.0);
    }

    public Pedido(int id, String direccionEntrega, double distanciaKm) {
        if (id <= 0) {
            throw new IllegalArgumentException("El id debe ser mayor a 0.");
        }
        if (direccionEntrega == null || direccionEntrega.isBlank()) {
            throw new IllegalArgumentException("La dirección de entrega no puede estar vacía.");
        }
        this.id = id;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKm = Math.max(0.0, distanciaKm);
        this.estado = EstadoPedido.PENDIENTE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public void setEstado(String nuevoEstado) {
        if (nuevoEstado != null) {
            this.estado = EstadoPedido.valueOf(nuevoEstado.trim().toUpperCase());
        }
    }

    @Override
    public String toString() {
        return "Pedido #" + id + " | Dirección: " + direccionEntrega + " | Estado: " + estado;
    }
}

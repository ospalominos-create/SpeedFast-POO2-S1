package modelo;

public class Pedido {

    private int id;
    private String direccionEntrega;
    private String tipo;

    public Pedido(int id, String direccionEntrega, String tipo) {
        setId(id);
        setDireccionEntrega(direccionEntrega);
        setTipo(tipo);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor a 0.");
        }
        this.id = id;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        if (direccionEntrega == null || direccionEntrega.isBlank()) {
            throw new IllegalArgumentException("La dirección no puede estar vacía.");
        }
        this.direccionEntrega = direccionEntrega.trim();
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if (tipo == null || tipo.isBlank()) {
            throw new IllegalArgumentException("El tipo de pedido es obligatorio.");
        }
        this.tipo = tipo.trim();
    }

    @Override
    public String toString() {
        return "Pedido #" + id + " [" + tipo + "] - " + direccionEntrega;
    }
}

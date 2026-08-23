# SpeedFast - POO II (PRY2203)

Proyecto de la empresa SpeedFast: sistema de gestión de pedidos con jerarquía de clases en Java.

## Progreso del proyecto

- **Semana 1**: polimorfismo mediante sobrecarga y sobrescritura de métodos.
- **Semana 2**: `Pedido` evoluciona a clase abstracta, incorporando cálculo de tiempo de entrega.

## Caso

SpeedFast es una empresa de reparto con 3 tipos de pedido, cada uno con su propia lógica de tiempo de entrega y asignación de repartidor.

## Estructura (Semana 2)

```
Pedido (clase abstracta)
├── mostrarResumen()          → concreto, común a todos
├── obtenerTipoPedido()       → abstracto
└── calcularTiempoEntrega()   → abstracto

├── PedidoComida       → 15 min + 2 min/km
├── PedidoEncomienda    → 20 min + 1.5 min/km (redondeado)
└── PedidoExpress       → 10 min base (+5 min si distancia > 5 km)
```

## Conceptos aplicados

- **Clase abstracta**: `Pedido` no puede instanciarse directamente.
- **Método concreto**: `mostrarResumen()`, reutilizado sin cambios por las subclases.
- **Método abstracto**: `calcularTiempoEntrega()` y `obtenerTipoPedido()`, cada subclase con su propia lógica.
- **Herencia**: las 3 subclases extienden `Pedido`.
- **Polimorfismo**: `Main` usa referencias `Pedido` para ejecutar comportamientos distintos.

## Ejecutar

Abrir el proyecto en IntelliJ IDEA y ejecutar `Main.java`.

## Autor

Oscar Palominos - DUOC UC

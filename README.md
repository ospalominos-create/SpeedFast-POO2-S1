# SpeedFast - POO II (PRY2203)

Actividad Semana 1: polimorfismo mediante sobrecarga y sobrescritura de métodos.

## Caso

SpeedFast es una empresa de reparto con 3 tipos de pedido, cada uno con su propia lógica de asignación de repartidor.

## Estructura

```
Pedido (clase base)
├── PedidoComida       → valida mochila térmica
├── PedidoEncomienda    → valida peso y embalaje
└── PedidoExpress       → asigna repartidor más cercano
```

## Conceptos aplicados

- **Herencia**: las 3 subclases extienden `Pedido`.
- **Sobrescritura**: cada subclase redefine `asignarRepartidor()`.
- **Sobrecarga**: `asignarRepartidor(String nombreRepartidor)`.
- **Polimorfismo**: `Main` usa referencias `Pedido` para ejecutar comportamientos distintos.

## Ejecutar

Abrir el proyecto en IntelliJ IDEA y ejecutar `Main.java`.

## Autor

Oscar Palominos - DUOC UC

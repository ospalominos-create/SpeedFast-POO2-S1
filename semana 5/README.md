# Semana 5 - Sincronización y Recursos Compartidos (Sumativa 2)

## Caso SpeedFast: Optimización y Coordinación de Entregas
Este módulo implementa la solución a la **Actividad Sumativa 2** de la asignatura **Desarrollo Orientado a Objetos II (PRY2203)**.

### Características del Sistema:
1. **Recurso Compartido (`ZonaDeCarga`):** Administra la colección de pedidos de forma sincronizada mediante `synchronized` en `retirarPedido()`, garantizando exclusión mutua para evitar condiciones de carrera.
2. **Estados Controlados (`EstadoPedido`):** Enum con `PENDIENTE`, `EN_REPARTO` y `ENTREGADO`.
3. **Flujo Concurrente (`Repartidor implements Runnable`):** 3 repartidores (Juan, Camila, Pedro) retiran pedidos concurrentemente de la misma zona compartida.
4. **Coordinación con `ExecutorService`:** Pool de 3 hilos con cierre seguro (`shutdown` + `awaitTermination`).

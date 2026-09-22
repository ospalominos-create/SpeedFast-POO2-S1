# Semana 6 - Interfaz Gráfica con Java Swing (Formativa 4)

## Caso SpeedFast: Sistema de Gestión de Entregas
Implementación de interfaz gráfica de usuario (GUI) con Java Swing para la gestión de pedidos en memoria.

### Estructura de Paquetes:
- `modelo`: Entidad `Pedido` con validaciones en setters y encapsulación estricta.
- `vista`: Formularios y tablas visuales (`VentanaPrincipal`, `VentanaRegistroPedido`, `VentanaListaPedidos`).
- `main`: Inicialización segura con `SwingUtilities.invokeLater` en el Event Dispatch Thread (EDT).

### Características Técnicas:
1. **Navegación e Integración:** `VentanaPrincipal` coordina la apertura de ventanas secundarias compartiendo la misma lista en memoria (`List<Pedido>`).
2. **Validación:** Control de campos obligatorios, IDs enteros positivos, detección de IDs duplicados y diálogos `JOptionPane`.
3. **JTable & DefaultTableModel:** Listado con `JScrollPane`, columnas bloqueadas para edición y refresco limpio mediante `setRowCount(0)`.

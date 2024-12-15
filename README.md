# Gestión de Tickets con Patrón Prototipo

Este proyecto implementa un sistema de gestión de tickets utilizando el **Patrón de Diseño Prototipo**. Los usuarios pueden crear, clonar y gestionar tickets de eventos mediante una interfaz gráfica interactiva.

---

## 📋 Características

- Crear tickets personalizados ingresando datos como:
  - **Nombre del evento**
  - **Categoría del asiento**
  - **Precio**
- Clonar tickets seleccionados con un número configurable de copias.
- Visualizar todos los tickets (originales y clonados) en una lista interactiva.
- Interfaz gráfica sencilla y fácil de usar basada en **Swing**.

---

## 🔨 Tecnologías Utilizadas

- **Java SE**
- **Swing** para la interfaz gráfica
- Principios **SOLID** en el diseño del código
- Patrón de diseño **Prototipo**

---

## 📂 Estructura del Proyecto

```plaintext
src/
├── main/
│   ├── models/              # Clases relacionadas con el modelo de datos
│   │   ├── Ticket.java      # Interfaz para todos los tickets
│   │   ├── EventTicket.java # Implementación concreta de un ticket de evento
│   ├── services/            # Lógica de negocio y servicios
│   │   ├── TicketManager.java   # Gestor de tickets y clonado
│   │   ├── CloneableService.java # Interfaz para servicios de clonado
│   ├── ui/                  # Interfaz gráfica
│   │   ├── TicketManagerUI.java # Ventana principal
│   └── Main.java            # Punto de entrada del programa
```

---

## 🖥️ Uso del Programa

### 1. Crear un Ticket
- Presiona el botón **"Añadir Ticket"**.
- Completa los campos solicitados:
  - **Nombre del Evento:** Nombre descriptivo (por ejemplo, "Concierto de Rock").
  - **Categoría del Asiento:** Clasificación como "VIP" o "General".
  - **Precio:** Costo en formato numérico.
- Presiona **OK** para añadir el ticket.

### 2. Clonar un Ticket
- Selecciona un ticket de la lista.
- Presiona el botón **"Clonar Ticket"**.
- Ajusta el número de clones utilizando el spinner (botones `+` y `-`) o escribiendo directamente un número.
- Presiona **OK** para generar los clones.

### 3. Visualizar los Tickets
- La lista muestra todos los tickets creados, incluyendo clones, con información detallada:
  - **Evento**
  - **Categoría**
  - **Precio**

---

## 🧩 Principios de Diseño Aplicados

- **Patrón Prototipo:** Permite clonar objetos de manera eficiente sin depender de su implementación concreta.
- **Principios SOLID:**
  - **Responsabilidad Única (SRP):** Cada clase tiene una función específica.
  - **Abierto/Cerrado (OCP):** Es fácil añadir nuevos tipos de tickets.
  - **Sustitución de Liskov (LSP):** Las clases derivadas (`EventTicket`) son intercambiables con su base (`Ticket`).
  - **Segregación de Interfaces (ISP):** La interfaz `CloneableService` separa responsabilidades de clonado.
  - **Inversión de Dependencias (DIP):** `TicketManager` trabaja con la abstracción `Ticket`, no con implementaciones concretas.

---


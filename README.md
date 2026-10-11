# 🔬 Mini Científicos - App Android

## 📌 Descripción del Proyecto

<table>
  <tr>
    <td valign="middle" width="65%">
      <strong>Mini Científicos</strong> es una aplicación nativa para Android desarrollada en <b>Java</b>, diseñada para trasladar la experiencia de la plataforma web oficial a una interfaz móvil interactiva, colorida y optimizada. Su objetivo es acercar la ciencia a niños, padres de familia y colegios mediante talleres, experimentos en vivo, espectáculos y kits educativos.<br><br>
      El sistema destaca por su enfoque científico, con componentes visuales, un e-commerce completamente funcional mediante manejo de estado dinámico e integración con WhatsApp.
    </td>
    <td align="center" valign="middle" width="35%">
      <picture>
        <source srcset="https://github.com/mrpeak-1/MiniCientificos-Android/blob/main/.github-assets/android.gif?raw=true">
        <img src="https://github.com/mrpeak-1/MiniCientificos-Android/blob/main/.github-assets/android.gif?raw=true" width="200" alt="Mini Científicos Demo">
      </picture>
    </td>
  </tr>
</table>


---

## 📱 Interfaz de Usuario Principal


| Inicio | Talleres | Tienda | Galería | Contacto |
| :---: | :---: | :---: | :---: | :---: |
| <img src=".github-assets/inicio.png" width="180" alt="Inicio"> | <img src=".github-assets/talleres.png" width="180" alt="Talleres"> | <img src=".github-assets/tienda.png" width="180" alt="Tienda"> | <img src=".github-assets/galeria.png" width="180" alt="Galería"> | <img src=".github-assets/contacto.png" width="180" alt="Contacto"> |



---

## 🛍️ Módulos de E-commerce, Detalles e Interacciones


| Personajes | Detalle de Taller | Detalle de Producto | Carrito de Compras | Pedido WhatsApp |
| :---: | :---: | :---: | :---: | :---: |
| <img src=".github-assets/personajes.png" width="180" alt="Detalle Personajes"> | <img src=".github-assets/taller_detalle.png" width="180" alt="Detalle Taller"> | <img src=".github-assets/producto_detalle.png" width="180" alt="Detalle Producto"> | <img src=".github-assets/carrito.png" width="180" alt="Carrito de Compras"> | <img src=".github-assets/whatsapp.jpg" width="180" alt="Generación de Pedido"> |
---

## 🛠️ Tecnologías y Stack Utilizado

* **Lenguaje:** Java 100% (Desarrollo nativo).
* **Diseño UI/UX:** Material Design 3 (M3) adaptado para un público infantil/educativo.
* **Vinculación de Vistas:** `ViewBinding` para un acceso directo, seguro y eficiente a las vistas en XML.
* **Tipografías:** Integración de Google Fonts (`Fredoka`, `Nunito`, `Kalam`) mediante *Downloadable Fonts*.
* **Navegación y Arquitectura:**
  * Patrón Single Activity + Multiple Fragments.
  * `BottomNavigationView` para la conmutación entre las 5 secciones principales.
  * `FragmentContainerView` gestionado dinámicamente desde `MainActivity`.
  * `BottomSheetDialogFragment` implementado para pre-visualizar datos en paneles emergentes interactivos (Detalles y Carrito).
  * Patrón Singleton (`CartManager`) para centralizar y persistir temporalmente el estado del carrito en memoria.
* **Componentes Interactivos:**
  * `ViewPager2` + `TabLayoutMediator` para el carrusel promocional (Hero Slider).
  * `RecyclerView` con adaptadores avanzados (`ListAdapter` / `ViewHolder`), manejando filtrados, notificaciones y callbacks reactivos.
  * `TextWatcher` y `ChipGroup` combinados para búsqueda y filtrado de listas en tiempo real.
  * `FloatingActionButton` (FAB) anclado dinámicamente con actualizaciones asíncronas para el contador (Badge).

---

## 📁 Estructura del Proyecto

El código está organizado de manera limpia y modular (MVC/UI-Driven) en el paquete principal `com.icsm.minicientificos_android`:

```text
app/src/main/java/com/icsm/minicientificos_android/
├── 📂 models/       # Clases POJO (Taller, Producto, CartItem) y Repositorios simulados (CatalogData)
├── 📂 adapters/     # Adaptadores de RecyclerView (TallerAdapter, ProductoAdapter, CartAdapter)
├── 📂 cart/         # Lógica central del E-commerce (CartManager Singleton, interfaces de estado)
├── 📂 utils/        # Herramientas globales (WhatsAppHelper para formatear URIs y pedidos)
├── 📂 ui/           # Fragmentos de vistas, BottomSheets y Helpers (CartBadgeHelper)
└── 📄 MainActivity.java # Host principal que escucha eventos del carrito
```

---

## 🌟 Módulos y Funcionalidades Principales

1. 🏠 **Inicio (Home):**
   * Carrusel promocional dinámico con indicadores circulares (Swiper.js nativo).
   * Vista previa de la oferta de servicios y presentación lúdica de los personajes (**Titan** y **Tesla**).

2. 🧪 **Talleres y Experimentos:**
   * Lista interactiva de talleres educativos con buscador reactivo.
   * Filtros mediante `ChipGroup` (Presencial, Virtual, Híbrido, etc.).
   * **`TallerDetalleBottomSheet`**: Panel modal interactivo con desglose temático, control de vacantes y reserva directa por WhatsApp usando `WhatsAppHelper`.

3. 🛒 **Tienda Científica (E-commerce):**
   * Catálogo de kits y juguetes con imágenes y precios. Búsqueda y filtrado instantáneo en memoria.
   * **`ProductoDetalleBottomSheet`**: Modal que incluye un selector reactivo de cantidades (`[ - 1 + ]`) validado contra el stock disponible.
   * **`CartManager`**: Gestor global del carrito de compras que notifica a la UI sobre cambios.
   * **`CartBottomSheetDialog`**: Pantalla de *Checkout* donde se resumen las compras, se recalculan totales en tiempo real, se eliminan ítems y se procesa el pago a través de un mensaje automatizado de WhatsApp.

4. 🖼️ **Galería:**
   * Cuadrícula fotográfica (`GridLayoutManager`) de eventos reales y actividades.

5. 📞 **Contacto Espacial:**
   * Formulario e integración para contrataciones.


---

## 🚀 Cómo Ejecutar el Proyecto

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/mrpeak-1/MiniCientificos-Android.git
   ```
2. **Abrir en Android Studio:**
   * Selecciona `Open an Existing Project` y elige la carpeta del proyecto.
3. **Sincronizar Gradle:**
   * Espera a que Android Studio descargue las dependencias y construya el proyecto.
4. **Ejecutar:**
   * Conecta un dispositivo físico o inicia un emulador y presiona **Run** (`Shift + F10`).

---

## 👨‍💻 Desarrollado por

| Miguel Córdova | Mateo Vicente | Oscar Quille |
| :---: | :---: | :---: |
| 🚀 Android Developer | 🚀 Android Developer | 🚀 Android Developer |
| <picture><img src="https://github.com/mrpeak-1/MiniCientificos-Android/blob/main/.github-assets/programmer.gif?raw=true" width="200" alt="James"></picture> | <picture><img src="https://github.com/mrpeak-1/MiniCientificos-Android/blob/main/.github-assets/programmer.gif?raw=true" width="200" alt="Mateo"></picture> | <picture><img src="https://github.com/mrpeak-1/MiniCientificos-Android/blob/main/.github-assets/programmer.gif?raw=true" width="200" alt="Oscar"></picture> |

<br>

<p align="center">
  <strong>© 2026 MiniCientíficos.</strong>
</p>

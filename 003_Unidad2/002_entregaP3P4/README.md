# Práctica de Spring Boot

¡Hola! Este es el README de la práctica. Aquí explico un poco por encima cómo funciona el proyecto, cómo se ejecuta y las cosas que pide el enunciado.

---

## 🚀 ¿Cómo se ejecuta el proyecto?
Para ponerlo en marcha en el ordenador, hay que hacer lo siguiente:
1. Abrir el proyecto en IntelliJ IDEA.
2. Ir a la clase principal: `src/main/java/com/example/entregaP3P4/EntregaP3P4Application.java`.
3. Hacer clic derecho y darle a **Run** (o darle al play verde).
4. Cuando arranque, abrir el navegador y entrar en la dirección: `http://localhost:8080/`

---

## 📦 Ámbito (Scope) de las variables y propiedades
En el código hemos usado varios tipos de variables según su ámbito:
* **Propiedades del DTO (`SolicitudPrestamoForm`):** Son los campos del formulario (nombre, correo, fechas, etc.). Su ámbito es el objeto que se crea cuando el usuario interactúa con el formulario y viaja hasta el controlador para validarse.
* **Variables locales:** Son las que declaramos dentro de los métodos del controlador (por ejemplo, los arrays o los mapas donde calculamos los días). Solo existen y se pueden usar dentro de ese método mientras se está ejecutando.
* **Parámetros de método:** Son los datos que recibe el controlador desde las peticiones web, como el `@PathVariable` para coger el código del material o el `@ModelAttribute`. Solo viven durante el tiempo que dura esa petición concreta.

---

## 👁️ Cómo se usan los atributos del modelo en la vista (Thymeleaf)
Desde el controlador mandamos datos a los HTML usando `model.addAttribute(...)`. En las vistas los pintamos con Thymeleaf así:
* **`${...}`:** Lo usamos para leer variables que vienen del modelo. Por ejemplo, `${catalogo}` para rellenar el desplegable con los materiales, o `${solicitud.nombre}` para mostrar los datos en la página de resultados.
* **`*{...}`:** Se usa dentro del formulario (porque en la etiqueta `<form>` pusimos `th:object="${solicitudPrestamoForm}"`). Sirve para acortar y pillar los campos del DTO directamente, por ejemplo `th:field="*{correo}"` o `th:field="*{motivo}"`.
* **`@{...}`:** Lo usamos para poner las rutas de los enlaces y los archivos de diseño, como `th:href="@{/css/style.css}"` para que coja bien los estilos.

---

## 🔗 Rutas principales de la aplicación
* `GET /` -> Es la página principal donde sale el catálogo de materiales.
* `GET /materiales/{codigo}` -> Muestra la ficha de un material en concreto (por ejemplo, `/materiales/PORTATIL`).
* `GET /solicitud/nueva` -> Abre el formulario vacío para hacer una solicitud.
* `POST /solicitudes` -> Es a donde se mandan los datos del formulario para validarlos y ver si todo está bien.
* Páginas de error (`404`, `4xx`, `5xx`) -> Salen automáticamente si intentamos entrar a una página que no existe o si hay algún fallo raro en el servidor.

---

## ✔️ Comprobaciones que hemos hecho
Para asegurarnos de que funciona, hemos probado lo siguiente:
1. Que el formulario avise en rojo si dejas campos en blanco, pones un correo falso o te pasas de unidades.
2. Que si pones una fecha de devolución anterior a la de recogida, salte un error de validación cruzada.
3. Que los estilos CSS carguen bien en todas las páginas (incluso en las de error).
4. Que al escribir una URL que no existe, aparezca la página de error personalizada en lugar de la típica pantalla fea de Spring.
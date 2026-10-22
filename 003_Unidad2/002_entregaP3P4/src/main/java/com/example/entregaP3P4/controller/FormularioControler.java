package com.example.entregaP3P4.controller;


import com.example.entregaP3P4.dto.SolicitudPrestamoForm;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class FormularioControler {

    //Catálogo de materiales en memoria
    private static final String[][] materiales = {
            {"PORTATIL", "Ordenador Portátil", "Ordenador portátil; incluye un cargador y una funda."},
            {"MICRO", "Kit de microcontrolador", "Kit de microcontrolador; incluye una placa y un cable USB."},
            {"CAMARA", "Cámara de documentación", "Cámara de documentación; incluye un soporte de mesa."}
    };

    /**
     * Método que busca el material por sú código y devuelve el material o null sino existe
      * @param codigo
     * @return Material o null si no existe
     */
   private String[] buscarMaterialCodigo(String codigo) {
        for (String[] material : materiales) {
            if (material[0].equalsIgnoreCase(codigo)) {
                return material;
            }
        }
        return null;
    }

    /**
     * Método GetMapping que nos diriguirá al HTML principal de la página a la raiz (/)
     * @param model
     * @return html
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("catalogo", materiales);
        return "catalogo";
    }

    /**
     * Método que nos mostrará la ficha de un producto en concreto buscandolo por el código que deberemos pasarle por parámetro
     * @param codigo del material
     * @param turno
     * @param model
     * @return
     */
    @GetMapping("/materiales/{codigo}")
    public String recibirFormulario(
            @PathVariable("codigo") String codigo, //Sirve para extraer el valor directamente del sector concreto donde se coloca del html
            @RequestParam(value = "turno", defaultValue = "general") String turno, Model model) {

        String[] material = buscarMaterialCodigo(codigo);

        //Si el buscarMaterialCodigo no encuentra el material por el código del PathVariable, mandará un error.
        if(material == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Material no encontrado");
        }

        //Caja del sastre de donde el html sacará toda la información que necesita
        model.addAttribute("material", material);
        model.addAttribute("turno", turno);
        return "ficha";
    }

    /**
     * Método GET que muestra el formulario vacío para una nueva solicitud de préstamo.
     *
     * @param model Objeto Model para pasar datos a la vista
     * @return El nombre de la plantilla HTML del formulario ("formulario")
     */
    @GetMapping("/solicitud/nueva")
    public String solicitud(Model model) {
        model.addAttribute("solicitudPrestamoForm", new SolicitudPrestamoForm());
        model.addAttribute("catalogo", materiales);
        return "formulario";
    }

    /**
     * Método auxiliar para calcular los días del préstamo y los días-unidad.
     *
     * @param form Objeto DTO con las fechas y unidades ya validadas.
     * @return Un mapa con los resultados calculados.
     */
    private java.util.Map<String, Long> calcularMetricasPrestamo(SolicitudPrestamoForm form) {
        // ChronoUnit.DAYS calcula la diferencia exacta de días entre dos LocalDate.
        // Se suma +1 porque el enunciado exige contar tanto el día de recogida como el de devolución.
        long diasPrestamo = java.time.temporal.ChronoUnit.DAYS.between(form.getFechaRecogida(), form.getFechaDevolucion()) + 1;

        // Multiplicamos los días totales del préstamo por el número de unidades solicitadas.
        long diasUnidad = diasPrestamo * form.getUnidades();

        // Guardamos los resultados en una estructura clave-valor para enviarlos al modelo.
        java.util.Map<String, Long> metricas = new java.util.HashMap<>();
        metricas.put("diasPrestamo", diasPrestamo);
        metricas.put("diasUnidad", diasUnidad);
        return metricas;
    }

    /**
     * Método POST que procesa el envío del formulario de préstamo.
     * Recibe los datos enviados por el usuario, aplica validaciones automáticas y de negocio,
     * y decide si muestra el resumen del préstamo o devuelve el formulario con los errores.
     *
     * @param form Objeto DTO que captura automáticamente los campos del formulario HTML.
     * @param bindingResult Objeto obligatorio que almacena el resultado de las validaciones. DEBE IR JUSTO DESPUÉS DEL DTO.
     * @param model Objeto para inyectar datos y enviarlos hacia la plantilla HTML (Thymeleaf).
     * @return El nombre de la plantilla HTML a renderizar ("resultado" si todo va bien, o "formulario" si hay errores).
     */
    @PostMapping("/solicitudes")
    public String procesarSolicitud(
            @Valid @ModelAttribute("solicitudPrestamoForm") SolicitudPrestamoForm form,
            BindingResult bindingResult,
            Model model) {

        // PASO 1: Validación de campos, compara dos campos entre sí
        //Comprobamos que ninguna de las dos fechas sean nulas, en el caso de que sean nulas no entra
        if (form.getFechaRecogida() != null && form.getFechaDevolucion() != null) {
            //Comprobamos que la fecha de Devolución no sea anterior a la fecha de recogida, en el caso de que sea anterior devolvemos un error
            if (form.getFechaDevolucion().isBefore(form.getFechaRecogida())) {
                bindingResult.rejectValue("fechaDevolucion", "error.fechaDevolucion",
                        "La fecha de devolución no puede ser anterior a la fecha de recogida.");
            }
        }

        // PASO 2: Control de errores de validación
        if (bindingResult.hasErrors()) {
            //En el caso de que las validaciones hechas en SolicitudPrestamoForm.java, den alguna fallo, devolvemos el model de
            //el catálogo de materiales ya que es un Array dentro de esta clase y no aparece de por si en el formulario y devolvemos el formulario
            model.addAttribute("catalogo", materiales);
            return "formulario";
        }

        // PASO 3: Validación del material seleccionado
        //Buscamos la selección de material que ha hecho el usuario en el formulario, en el caso de que sea null, es decir, no haya hecho selección
        //o no sea la adecuada, devolverá de nuevo el forulario con el error perteneciente a este campo.
        String[] materialEncontrado = buscarMaterialCodigo(form.getMateriales());
        if (materialEncontrado == null) {
            bindingResult.rejectValue("materiales", "error.materiales",
                    "El material seleccionado no es válido");
            model.addAttribute("catalogo", materiales);
            return "formulario";
        }

        // PASO 4: Procesamiento de datos válidos para la devolución del prestamo
        //Calculamos la duración del prestamo con el método que creamos ante
        java.util.Map<String, Long> metricas = calcularMetricasPrestamo(form);

        //Añadimos el cajón del sastre donde guardaremos todos los datos para enviarlos al resultado.html
        model.addAttribute("solicitud", form);
        model.addAttribute("material", materialEncontrado);
        model.addAttribute("diasPrestamo", metricas.get("diasPrestamo"));
        model.addAttribute("diasUnidad", metricas.get("diasUnidad"));

        //Redirigimos a la plantilla de los resultados al usuario cuando todo esté perfectamente modificado
        return "resultado";
    }

}

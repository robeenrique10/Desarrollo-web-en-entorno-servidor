package com.example.proyectoCvPortfolio.controller;

import com.example.proyectoCvPortfolio.model.*;
import com.example.proyectoCvPortfolio.model.dto.PortfolioForm;
import com.example.proyectoCvPortfolio.model.dto.ProyectoForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Controller
public class CurriculumControler {

    // Configuración fija leída al arrancar, no un dato de la petición
    @Value("${cv.nombre}")
    private String nombre;

    /**
     * Método para crear el objeto curriculum
     * @param nombre
     * @return
     */
    private Curriculum miCurriculum(String nombre) {
        List<Formacion> formaciones = List.of(
                new Formacion("cv.formacion.1.nombre", "cv.formacion.1.centro", 2025),
                new Formacion("cv.formacion.2.nombre", "cv.formacion.2.centro", null));

        List<Experiencia> experiencias = List.of(
                new Experiencia("cv.experiencia.1.puesto", "cv.experiencia.1.organizacion", "cv.experiencia.1.periodo"));

        List<Habilidad> habilidades = List.of(
                new Habilidad("cv.habilidad.1.nombre", "medio"),
                new Habilidad("cv.habilidad.2.nombre", "medio"),
                new Habilidad("cv.habilidad.3.nombre", "medio"),
                new Habilidad("cv.habilidad.4.nombre", "medio"),
                new Habilidad("cv.habilidad.5.nombre", "medio"),
                new Habilidad("cv.habilidad.6.nombre", "alto"),
                new Habilidad("cv.habilidad.7.nombre", "medio"),
                new Habilidad("cv.habilidad.8.nombre", "medio"),
                new Habilidad("cv.habilidad.9.nombre", "medio"),
                new Habilidad("cv.habilidad.10.nombre", "alto"),
                new Habilidad("cv.habilidad.11.nombre", "alto"),
                new Habilidad("cv.habilidad.12.nombre", "alto"),
                new Habilidad("cv.habilidad.13.nombre", "alto"));

        List<Idioma> idiomas = List.of(
                new Idioma("cv.idioma.1", "alto"),
                new Idioma("cv.idioma.2", "medio"),
                new Idioma("cv.idioma.3", "bajo"));

        return new Curriculum(
                1,
                nombre,                       // ya no va escrito a mano
                "cv.titularProfesional",
                "cv.presentacion",
                "robeenrique10@gmail.com",
                formaciones, experiencias, habilidades, idiomas);
    }

    /**
     * Método GetMapping que sirve para dirigir la información de los models al html index.html, este tendrá dirección / o /curriculum
     * @param imprimir haciendo uso de Ctrl + P podremos imprimir el proyecto
     * @param model
     * @return
     */
    @GetMapping({"/", "/curriculum"})
    public String index(@RequestParam(defaultValue = "false") boolean imprimir, Model model) {
        Curriculum cv = miCurriculum("José Enrique Rodríguez Benítez");
        model.addAttribute("miCurriculum", cv);
        model.addAttribute("imprimir", imprimir);
        return "index";
    }

    /**
     * Método para imprimir el proyecto de forma más compacta que la anterior eliminando el header y el footer de la vista
     * Ampliación 2: plantilla de impresión compacta
     * @param model
     * @return Curriculum completo, pero sin el header y el footer
     */
    @GetMapping("/curriculum/imprimir")
    public String curriculumImprimir(Model model) {
        model.addAttribute("miCurriculum", miCurriculum("José Enrique Rodríguez Benítez"));
        return "curriculumImprimir";
    }

    /**
     * Creación de una lista de proyectos
     * @return lista de proyectos
     */
    private List<Proyecto> listaProyectos() {
        return List.of(
                new Proyecto(1, "proyecto.1.titulo", 2026, "sitios-web", "proyecto.1.descripcion", "En curso",
                        List.of("Java", "Spring Boot", "Spring MVC", "Thymeleaf", "HTML", "CSS"), null, null),
                new Proyecto(2, "proyecto.2.titulo", 2026, "sitios-web", "proyecto.2.descripcion", "En curso",
                        List.of("Java", "HTML", "CSS", "Bootstrap", "JavaScript", "SQL"), null, null),
                new Proyecto(3, "proyecto.3.titulo", 2026, "diseno", "proyecto.3.descripcion", "Terminado",
                        List.of("HTML", "CSS", "Bootstrap", "JavaScript"), null, "/images/AdminPageMigas.png"));
    }

    /**
     * Método GetMapping que nos dirigirá al portfolio (/portfolio) donde se encontrarán todos los proyectos de la lista creada
     * anteriormente
     * @param categoria parámetro de los proyectos con el que posteriormente filtraremos los proyectos
     * @param anio parámetro de los proyectos con el que posteriormente filtraremos los proyectos
     * @param model
     * @return Vista de portfolio con todos los proyectos
     */
    @GetMapping("/portfolio")
    public String portfolio(@RequestParam(defaultValue = "todas") String categoria,
                            @RequestParam(defaultValue = "") String anio,
                            Model model) {

        List<Proyecto> misProyectos = listaProyectos();

        //Se usa para convertir el año que inicialmente es un String en un Integer, para que pueda seguir siendo null, si en su caso lo es
        Integer anioFiltro = null;
        boolean anioInvalido = false;
        if (!anio.isBlank()) {
            try {
                anioFiltro = Integer.valueOf(anio.trim());
            } catch (NumberFormatException e) {
                anioInvalido = true;
            }
        }

        //Lista que devuelve la lista de proyectos ya filtrada
        List<Proyecto> resultado = filtrarProyectos(misProyectos, categoria, anioFiltro);

        model.addAttribute("proyectos", resultado);
        model.addAttribute("total", resultado.size());
        model.addAttribute("categoriaSel", categoria);
        model.addAttribute("anioSel", anio);
        model.addAttribute("anioInvalido", anioInvalido);
        return "portfolio";
    }

    /**
     * Método GetMapping que nos dirigirá a una nueva ventana con los detalles de un solo proyecto, dependerá del id del proyecto
     * Ampliación 1: detalle de un proyecto con ruta construida a partir de su id
     * @param id identificador del proyecto
     * @param model
     * @return Página de detalle del proyecto
     */
    @GetMapping("/portfolio/proyecto/{id}")
    public String detalleProyecto(@PathVariable Integer id, Model model) {
        //Con stream convertiremos el array en una secuencia de elementos sobre la que podemos encadenar las siguientes operaciones
        Proyecto proyecto = listaProyectos().stream()
                //Con filter filtraremos los proyectos que realmente tenemos cargador en memoria
                .filter(p -> p.id().equals(id))
                .findFirst()
                // Si un id no existe, lanzará la excepción 404
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("proyecto", proyecto);
        return "detalleProyecto";
    }

    /**
     * Método que nos devuelve la lista de proyectos con los filtros establecidos desde la vista
     * @param proyectos
     * @param categoria
     * @param anio
     * @return Lista de proyectos con los proyectos que cumplan las condiciones pasadas por parámetro
     */
    private List<Proyecto> filtrarProyectos(List<Proyecto> proyectos, String categoria, Integer anio) {
        List<Proyecto> resultado = new ArrayList<>();

        for (Proyecto p : proyectos) {
            boolean categoriaOk = categoria.equals("todas") || p.categoria().equals(categoria);
            boolean anioOk = anio == null || p.anio().equals(anio);

            if (categoriaOk && anioOk) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    /**
     * Inserta en maxProyectos el valor que se ha establecido en application.properties sobre el valor
     * portfolio.max-proyectos
     */
    @Value("${portfolio.max-proyectos}")
    private int maxProyectos;

    /**
     * Método que nos dirigirá hacia el formulario que rellenará el usuario para su propio curriculum
     * @param model
     * @return
     */
    @GetMapping("/portfolio/editar")
    public String portfolioEditar(Model model) {
        PortfolioForm form = new PortfolioForm();
        form.getProyectos().add(new ProyectoForm());
        model.addAttribute("portfolioForm", form);
        model.addAttribute("maxProyectos", maxProyectos);
        return "editar";
    }

    /**
     * Método PostMapping que nos validará los campos introducidos en el formulario, y donde además controlaremos
     * el añadir o eliminar campos de proyectos para que el usuario pueda añadir o eliminar los proyectos que quiere que se aprecien
     * en su curriculum
     * @param form
     * @param result
     * @param accion
     * @param quitar
     * @param model
     * @return vista del curriculum con los proyectos del usuario
     */
    @PostMapping("/portfolio/editar")
    public String procesar( @ModelAttribute("portfolioForm") PortfolioForm form,
                           BindingResult result,
                           @RequestParam(required = false) String accion,
                           @RequestParam(required = false) Integer quitar,
                           Model model) {

        model.addAttribute("maxProyectos", maxProyectos);

        //Añade o elimina filas de ProyectoForm, sin validar simplemente añadiendo o quitando
        if (quitar != null || "anadir".equals(accion)) {
            modificarFilas(form, accion, quitar);
            return "editar";
        }

        //Busca que no haya errores antes de mandar a la vista previa en el caso de que haya errores, volverá a redirigirnos al form.
        validar(form, result);
        if (result.hasErrors()) {
            return "editar";
        }

        //Creamos una lista de proyectos que luego pasaremos a la vista
        List<Proyecto> listaProyectosVista = new ArrayList<>();
        for (ProyectoForm pf : form.getProyectos()) {
            //Si el proyecto creado no está vacío creará el objeto y lo añadirá a la lista que veremos en la vista
            if (!estaVacia(pf)) {
                // Convertimos el ProyectoForm a Proyecto (o lo adaptamos según tu modelo)
                // Dependiendo de cómo tengas tu clase Proyecto, aquí mapeas los campos:
                Proyecto p = new Proyecto(
                        null, // id si no tiene
                        pf.getTitulo(),
                        Integer.valueOf(pf.getAnio().trim()),
                        pf.getCategoria(),
                        pf.getDescripcion(),
                        pf.getEstado(),
                        null, // tecnologías si las usas
                        null, // enlace
                        null  // imagen
                );
                listaProyectosVista.add(p);
            }
        }

        // Añadimos los datos al modelo para que la vista previa los muestre
        model.addAttribute("proyectos", listaProyectosVista);

        //Cuando no existan errores en el formulario nos redirigirá a la vista previa.
        return "vistaPrevia";
    }

    /**
     * Método con el que controlaremos la modificación de las filas de proyectos, es decir, añadir o eliminar filas de proyectos
     * @param form
     * @param accion
     * @param quitar
     */
    private void modificarFilas(PortfolioForm form,String accion, Integer quitar) {
        List<ProyectoForm> filas = form.getProyectos();

        //Solo se podrá quitar si el índide de filas es mayor a 1
        if(quitar != null && quitar >= 0 && quitar < filas.size()) {
            filas.remove(quitar.intValue());    //Con intValue(), lo que haremos será quitar la posición
        }

        //Añadimos filas de proyecto si el número de proyectos es menor al límite establecido en application.properties
        if ("anadir".equals(accion) && filas.size() < maxProyectos) {
            filas.add(new ProyectoForm());
        }

        //En el caso de que se eliminen todas las filas, crearemos siempre una fila de proyectos
        if (filas.isEmpty()) {
            filas.add(new ProyectoForm());
        }
    }

    /**
     * Método de validación completa dentro del controlador, donde además utilizaremos dos métodos más
     * textoVacío y estáVacío
     * @param form
     * @param result
     */
    private void validar(PortfolioForm form, BindingResult result) {

        //Comprobación de los datos obligatorios donde daremos el código de error el cual es una clave i18n y un mensaje por defecto
        if(textoVacio(form.getNombre())) {
            result.rejectValue("nombre", "error.obligatorio", "Campo obligatorio");
        }

        if (textoVacio(form.getTitular())) {
            result.rejectValue("titular", "error.obligatorio", "Campo obligatorio");
        }

        if (textoVacio(form.getPresentacion())) {
            result.rejectValue("presentacion", "error.obligatorio", "Campo obligatorio");
        }

        //Si el campo correo está relleno, comprobará si tiene un formato válido
        if (!textoVacio(form.getCorreo()) && !Pattern.matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+", form.getCorreo().trim())) {
            result.rejectValue("correo", "error.correo.formato", "Correo no válido");
        }

        //Lista donde se almacenarán los proyectos que el usuario ha introducido en el formulario para validarlos posteriormente
        List<ProyectoForm> proyectos = form.getProyectos();

        //Comprobamos el límite de proyectos
        if(proyectos.size() > maxProyectos) {
            result.reject("error.limite", "Has superado el límite de proyectos");
        }

        //Comprobamos todos los campos de proyectos como pueden ser más de uno tendremos que comprobarlos todos
        //Obtenemos el número total de proyectos con proyectos.size()
        for (int i = 0; i < proyectos.size(); i++) {
            ProyectoForm proyectoForm = proyectos.get(i);

            //En el caso de que el objeto proyectoForm esté vacío, continuaremos y no lo validaremos
            if (estaVacia(proyectoForm)) {
                continue;
            }

            //Simple encadenación de texto que mostrará (proyecto[posición dentro del array])
            String campo = "proyectos [" + i + "]";

            //Validación de los campos con un código de error i18n, y un mensaje por defecto para que se muestre en el caso de
            //que el código de error i18n no funcionase
            if (textoVacio(proyectoForm.getTitulo())) {
                result.rejectValue(campo + "titulo", "error.obligatorio", "Campo obligatorio");
            }

            if (textoVacio(proyectoForm.getDescripcion())) {
                result.rejectValue(campo + "descripcion", "error.obligatorio", "Campo obligatorio");
            }

            //Intentará tratar el campo Anio como un String, en el caso que no funcione lo convertirá en un Integer y lo tratará como tal
            if (textoVacio(proyectoForm.getAnio())) {
                result.rejectValue(campo + "anio", "error.obligatorio", "Campo obligatorio");
            } else {
                try {
                    Integer.valueOf(proyectoForm.getAnio().trim());
                } catch (NumberFormatException e) {
                    result.rejectValue(campo + "anio", "error.anio", "Año no válido");
                }
            }

            // Categoría y estado donde solo dejará que se elijan entre las opciones
            if (textoVacio(proyectoForm.getCategoria())) {
                result.rejectValue(campo + "categoria", "error.obligatorio", "Campo obligatorio");
            } else if (!List.of("aplicaciones", "sitios-web", "diseno").contains(proyectoForm.getCategoria())) {
                result.rejectValue(campo + "categoria", "error.obligatorio", "Categoría no válida");
            }

            if (textoVacio(proyectoForm.getEstado())) {
                result.rejectValue(campo + "estado", "error.obligatorio", "Campo obligatorio");
            } else if (!List.of("En curso", "Terminado").contains(proyectoForm.getEstado())) {
                result.rejectValue(campo + "estado", "error.obligatorio", "Estado no válido");
            }
        }
    }

    /**
     * Método que comprueba si un texto es null, no tiene nada o si es blanco, no tiene texto
     * @param texto cadena de texto
     * @return true si el texto es null o está en blanco, en el caso de que solo haya espacios lo considera como blanco
     */
    private boolean textoVacio(String texto) {
        return texto == null || texto.isBlank();
    }

    /**
     * Método que compruba si alguno de los campos del formulario de proyectos está vacío
     * @param projectForm objeto proyecto formulario
     * @return true en el caso de que alguno de los campos del formulario de proyectos está vacío, false si todos están completos
     */
    private boolean estaVacia(ProyectoForm projectForm) {
        return textoVacio(projectForm.getTitulo()) && textoVacio(projectForm.getAnio()) && textoVacio(projectForm.getCategoria())
                && textoVacio(projectForm.getEstado()) && textoVacio(projectForm.getDescripcion());
    }
}
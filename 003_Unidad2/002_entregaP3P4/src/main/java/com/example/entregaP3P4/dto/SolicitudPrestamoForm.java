package com.example.entregaP3P4.dto;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class SolicitudPrestamoForm {

    @NotBlank(message = "Nombre del solicitante.")
    @Size(max = 60, message = "El nombre no puede superar los 60 caracteres.")
    private String nombre;

    @NotBlank(message = "Correo electrónico.")
    @Email(message = "Introduce una dirección de correo válida.")
    @Size(max = 120, message = "El correo no puede superar los 120 caracteres.")
    private String correo;

    @NotBlank(message = "Selecciona un material.")
    @Pattern(regexp = "PORTATIL|MICRO|CAMARA",
            message = "Selecciones uno de los materiales.")
    private String materiales;

    @NotNull(message = "Seleccione las unidades que requiera.")
    @Min(value = 1, message = "Las unidades mínimas son 1.")
    @Max(value = 3, message = "Las unidades máximas son 3.")
    private Integer unidades;

    @NotNull(message = "La fecha de recogida es obligatoria.")
    @FutureOrPresent(message = "La fecha de recogida debe ser hoy o una fecha futura.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaRecogida;

    @NotNull(message = "La fecha de devolución es oblicatoria.")
    @FutureOrPresent(message = "La fecha de devolución debe ser hoy o una fecha futura.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaDevolucion;

    @NotBlank(message = "El motivo de prestamo es obligatorio.")
    @Size(min = 30, max = 400, message = "Este campo deberá tener entre 30 y 400 carácteres.")
    private String motivo;

    @AssertTrue(message = "Debe aceptar las normas de la solicitud.")
    private boolean acepta;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getMateriales() {
        return materiales;
    }

    public void setMateriales(String materiales) {
        this.materiales = materiales;
    }

    public Integer getUnidades() {
        return unidades;
    }

    public void setUnidades(Integer unidades) {
        this.unidades = unidades;
    }

    public LocalDate getFechaRecogida() {
        return fechaRecogida;
    }

    public void setFechaRecogida(LocalDate fechaRecogida) {
        this.fechaRecogida = fechaRecogida;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public boolean isAcepta() {
        return acepta;
    }

    public void setAcepta(boolean acepta) {
        this.acepta = acepta;
    }
}

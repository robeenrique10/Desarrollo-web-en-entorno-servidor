package com.example.u4p3.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class NuevaActividadForm {

    @NotBlank(message = "Escribe el título de la actividad.")
    @Size(max = 80, message = "El título no puede superar los 80 caracteres.")
    private String titulo;

    @Size(max = 300, message = "La descripción no puede superar los 300 caracteres.")
    private String descripcion;

    @NotNull(message = "Elija la fecha en la que se desarrollará la actividad.")
    private LocalDate fecha;

    @Min(value = 15, message = "La duración como mínimo deberá ser de 15 minutos.")
    @Max(value = 480, message = "La duración como máximo deberá ser de 480 minutos.")
    private int duracionMinutos;

    @DecimalMin(value = "0.00", message = "El valor mínimo de la entrada deberá de ser de 0.00 euros.")
    @DecimalMax(value = "999.99", message = "El valor máximo de la entrada deberá de ser de 999.99 euros.")
    private BigDecimal precio;

    @Min(value = 1, message = "El valor mínimo del aforo deberá de ser de 1 persona.")
    @Max(value = 200, message = "El valor máximo del aforo deberá de ser de 200 personas.")
    private int aforo;

    @NotNull(message = "Debes indicar si la inscripción está abierta.")
    private boolean inscripcionAbierta;
}

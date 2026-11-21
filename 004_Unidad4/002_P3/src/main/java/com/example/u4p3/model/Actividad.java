package com.example.u4p3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "actividad")
@Setter
@Getter
@NoArgsConstructor
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo",nullable = false, length = 80)
    private String titulo;

    @Column(name = "descripcion", nullable = true, length = 300)
    private String descripcion;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "duracionMinutos",nullable = true)
    @Min(value = 15, message ="La duración tiene que ser de 15 minutos como mínimo.")
    @Max(value = 480, message ="La duración tiene que ser de 480 minutos como máximo.")
    private int duracionMinutos;

    @Column(name = "precio", nullable = true, precision = 5, scale = 2)
    @DecimalMin(value = "0.00", message ="El precio mínimo es de 0 euros.")
    @DecimalMax(value = "999.99", message = "El precio máximo es de 999.99 euros.")
    private BigDecimal precio;

    @Column(name = "aforo", nullable = true)
    @Min(value = 0, message ="El valor mínimo del aforo deberá ser de 1.")
    @Max(value = 200, message ="El valor máximo del aforo deberá de ser de 200.")
    private int aforo;

    @Column(name = "inscripcionAbierta", nullable = false)
    private boolean inscripcionAbierta;
}

package com.example.u4p2.apartado1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "espacio")
@Getter
@Setter
@NoArgsConstructor
public class Espacio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 8)
    private String codigo_espacio;

    @Column(nullable = false, length = 60)
    private String nombre_espacio;

    @Column(nullable = false)
    private Integer aforo;

    @Column(nullable = false)
    private Integer planta;

    @Column(nullable = false)
    private boolean accesible;

}

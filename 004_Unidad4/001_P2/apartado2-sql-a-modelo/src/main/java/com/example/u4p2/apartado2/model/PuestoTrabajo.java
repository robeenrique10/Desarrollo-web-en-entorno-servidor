package com.example.u4p2.apartado2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Entity
@Table(name = "puesto_trabajo")
@Getter
@Setter
@NoArgsConstructor
public class PuestoTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(nullable = false,length = 10)
    private String codigo_puesto;

    @Column(nullable = false, length = 60)
    private String denominacion;

    @Column(nullable = false)
    private Integer planta;

    @Column(nullable = false)
    private boolean tiene_electricidad;

}

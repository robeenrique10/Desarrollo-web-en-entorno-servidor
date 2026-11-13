package com.example.u4p2.apartado2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.type.descriptor.jdbc.SmallIntJdbcType;

import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "material_aula")
@Getter
@Setter
@NoArgsConstructor
public class MaterialAula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(nullable = false, unique = true, length = 12)
    private String referencia;

    @Column(nullable = false, length = 80)
    private String nombre_material;

    @Column(nullable = true, length = 250)
    private String descripcion;

    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal coste_reposicion;

    @Column(nullable = false)
    private short unidades;
}

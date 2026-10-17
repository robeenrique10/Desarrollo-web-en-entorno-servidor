package org.example.maquinaria;

import org.example.personal.Mecanico;

public class Locomotora {

    private String matricula;
    private double potenciaMotor;
    private int anoFabricacion;
    private Mecanico mecanico;

    public Locomotora(String matricula, double potenciaMotor, int anoFabricacion, Mecanico mecanico) {
        this.matricula = matricula;
        this.potenciaMotor = potenciaMotor;
        this.anoFabricacion = anoFabricacion;
        this.mecanico = mecanico;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public double getPotenciaMotor() {
        return potenciaMotor;
    }

    public void setPotenciaMotor(double potenciaMotor) {
        this.potenciaMotor = potenciaMotor;
    }

    public int getAnoFabricacion() {
        return anoFabricacion;
    }

    public void setAnoFabricacion(int anoFabricacion) {
        this.anoFabricacion = anoFabricacion;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

    public void setMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
    }

    @Override
    public String toString() {
        return "Locomotora{" +
                "matricula='" + matricula + '\'' +
                ", potenciaMotor=" + potenciaMotor +
                ", anoFabricacion=" + anoFabricacion +
                ", mecanico=" + mecanico +
                '}';
    }
}

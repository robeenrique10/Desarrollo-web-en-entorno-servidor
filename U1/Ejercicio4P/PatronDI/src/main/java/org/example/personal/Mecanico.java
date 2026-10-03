package org.example.personal;

public class Mecanico {

    private String nombreCompleto;
    private String tlfno;
    private String especialidad;

    public Mecanico(String nombreCompleto, String tlfno, String especialidad) {
        this.nombreCompleto = nombreCompleto;
        this.tlfno = tlfno;
        this.especialidad = especialidad;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTlfno() {
        return tlfno;
    }

    public void setTlfno(String tlfno) {
        this.tlfno = tlfno;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        if (especialidad.equals("frenos") || especialidad.equals("hidraulica")) {
            this.especialidad = especialidad;
        } else {
            this.especialidad = "";
        }
    }

    @Override
    public String toString() {
        return "Mecanicos{" +
                "nombreCompleto='" + nombreCompleto + '\'' +
                ", tlfno='" + tlfno + '\'' +
                ", especialidad='" + especialidad + '\'' +
                '}';
    }
}

package cr.ac.ucenfotec.Tarea3.bl.entidades;

import cr.ac.ucenfotec.Tarea3.bl.tipos.TipoContrato;

import java.util.Date;

public class Profesor extends Usuario {

    private int numeroCedula;
    private TipoContrato contrato;
    private Date fechaContratacion;

    public int getNumeroCedula() {
        return numeroCedula;
    }

    public void setNumeroCedula(int numeroCedula) {
        this.numeroCedula = numeroCedula;
    }

    public TipoContrato getContrato() {
        return contrato;
    }

    public void setContrato(TipoContrato contrato) {
        this.contrato = contrato;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public Profesor() {

    }

    public Profesor(String nombre, String apellido, int numeroCedula, TipoContrato contrato, Date fechaContratacion) {
        super(nombre, apellido);
        this.numeroCedula = numeroCedula;
        this.contrato = contrato;
        this.fechaContratacion = fechaContratacion;
    }

    public Profesor (String sourceLines){
        String[] datos = sourceLines.split(",");
        this.nombre = datos[0];
        this.apellido = datos[1];
        this.numeroCedula = Integer.parseInt(datos[2]);
        this.contrato = TipoContrato.valueOf(datos[3]);
        this.fechaContratacion = new Date(datos[4]);
    }

    @Override
    public String toCSVLine() {
        return this.nombre + "," + this.apellido + "," + this.numeroCedula + "," + this.contrato + "," + this.fechaContratacion + ",";
    }


}

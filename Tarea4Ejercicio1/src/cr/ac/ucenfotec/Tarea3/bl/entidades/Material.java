package cr.ac.ucenfotec.Tarea3.bl.entidades;

import cr.ac.ucenfotec.Tarea3.bl.interfaces.SerializacionCSV;

import java.util.Date;

public class Material implements SerializacionCSV {

    protected String signatura;
    protected Date fechaCompra;
    protected boolean restringido;
    protected String tema;

    public String getSignatura() {
        return signatura;
    }

    public void setSignatura(String signatura) {
        this.signatura = signatura;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public boolean isRestringido() {
        return restringido;
    }

    public void setRestringido(boolean restringido) {
        this.restringido = restringido;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Material() {
    }

    public Material(String signatura, Date fechaCompra, boolean restringido, String tema) {
        this.signatura = signatura;
        this.fechaCompra = fechaCompra;
        this.restringido = restringido;
        this.tema = tema;
    }

    @Override
    public String toCSVLine() {
        return this.signatura + "," + this.fechaCompra + "," + this.restringido + "," + this.tema;
    }
}
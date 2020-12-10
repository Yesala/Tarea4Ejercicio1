package cr.ac.ucenfotec.Tarea3.bl.entidades;

import java.util.Date;

public class Otro extends Material {

    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Otro() {
    }

    public Otro(String signatura, Date fechaCompra, boolean restringido, String tema, String descripcion) {
        super(signatura, fechaCompra, restringido, tema);
        this.descripcion = descripcion;
    }

    public Otro (String sourceLines){
        String[] datos = sourceLines.split(",");
        this.signatura = datos[0];
        this.fechaCompra = new Date(datos[1]);
        this.restringido = Boolean.parseBoolean(datos[2]);
        this.tema = datos[3];
        this.descripcion = datos[4];
    }

    @Override
    public String toCSVLine() {
        return this.signatura + "," + this.fechaCompra + "," + this.restringido + "," + this.tema + "," + this.descripcion + ",";
    }

}

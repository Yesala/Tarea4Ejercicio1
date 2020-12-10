package cr.ac.ucenfotec.Tarea3.bl.entidades;

import cr.ac.ucenfotec.Tarea3.bl.tipos.TipoNombramiento;

public class Administrativo extends Usuario {

    private int numeroCedula;
    private float horasSemAsignadas;
    private TipoNombramiento nombramiento;

    public int getNumeroCedula() {
        return numeroCedula;
    }

    public void setNumeroCedula(int numeroCedula) {
        this.numeroCedula = numeroCedula;
    }

    public float getHorasSemAsignadas() {
        return horasSemAsignadas;
    }

    public void setHorasSemAsignadas(float horasSemAsignadas) {
        this.horasSemAsignadas = horasSemAsignadas;
    }

    public TipoNombramiento getNombramiento() {
        return nombramiento;
    }

    public void setNombramiento(TipoNombramiento tipoNombramiento) {
        this.nombramiento = tipoNombramiento;
    }

    public Administrativo() {

    }

    public Administrativo(String nombre, String apellido, int numeroCedula, TipoNombramiento nombramiento, float horasSemAsignadas) {
        super(nombre, apellido);
        this.numeroCedula = numeroCedula;
        this.nombramiento = nombramiento;
        this.horasSemAsignadas = horasSemAsignadas;
    }

    public Administrativo (String sourceLines){
        String[] datos = sourceLines.split(",");
        this.nombre = datos[0];
        this.apellido = datos[1];
        this.numeroCedula = Integer.parseInt(datos[2]);
        this.nombramiento = TipoNombramiento.valueOf(datos[3]);
        this.horasSemAsignadas = Float.parseFloat(datos[4]);
    }

    @Override
    public String toCSVLine() {
        return this.nombre + "," + this.apellido + "," + this.numeroCedula + "," +  this.nombramiento + "," + this.horasSemAsignadas + ",";
    }

}

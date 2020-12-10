package cr.ac.ucenfotec.Tarea3.bl.entidades;

public class Estudiante extends Usuario {

    private int numeroCarnet;
    private String carrera;
    private int creditos;

    public int getNumeroCarnet() {
        return numeroCarnet;
    }

    public void setNumeroCarnet(int numeroCarnet) {
        this.numeroCarnet = numeroCarnet;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public Estudiante() {

    }

    public Estudiante(String nombre, String apellido, int numeroCarnet, String carrera, int creditos) {
        super(nombre, apellido);
        this.numeroCarnet = numeroCarnet;
        this.carrera = carrera;
        this.creditos = creditos;
    }

    public Estudiante (String sourceLines){
        String[] datos = sourceLines.split(",");
        this.nombre = datos[0];
        this.apellido = datos[1];
        this.numeroCarnet = Integer.parseInt(datos[2]);
        this.carrera = datos[3];
        this.creditos = Integer.parseInt(datos[4]);
    }

    @Override
    public String toCSVLine() {
        return this.nombre + "," + this.apellido + "," + this.numeroCarnet + "," + this.carrera + "," + this.creditos + ",";
    }
}

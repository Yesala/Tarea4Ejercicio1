package cr.ac.ucenfotec.Tarea3.bl.entidades;

import java.time.LocalDate;

public class Prestamo {

    private String usuario;
    private int cantidadMateriales;
    private LocalDate fechaDevolucion;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getCantidadMateriales() {
        return cantidadMateriales;
    }

    public void setCantidadMateriales(int cantidadMateriales) {
        this.cantidadMateriales = cantidadMateriales;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Prestamo() {
    }

    public Prestamo(String usuario, int cantidadMateriales, LocalDate fechaDevolucion) {
        this.usuario = usuario;
        this.cantidadMateriales = cantidadMateriales;
        this.fechaDevolucion = fechaDevolucion;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "usuario='" + usuario + '\'' +
                ", cantidadMateriales=" + cantidadMateriales +
                ", fechaDevolucion=" + fechaDevolucion +
                '}';
    }
}

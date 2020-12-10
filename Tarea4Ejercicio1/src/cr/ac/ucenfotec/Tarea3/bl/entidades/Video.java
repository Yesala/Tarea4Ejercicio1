package cr.ac.ucenfotec.Tarea3.bl.entidades;

import cr.ac.ucenfotec.Tarea3.bl.tipos.TipoVideo;

import java.util.Date;

public class Video extends Material {

    private float duracion;
    private String idioma;
    private String director;
    private TipoVideo formato;

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public TipoVideo getFormato() {
        return formato;
    }

    public void setFormato(TipoVideo formato) {
        this.formato = formato;
    }

    public Video() {

    }

    public Video(String signatura, Date fechaCompra, boolean restringido, String tema, TipoVideo formato, float duracion, String idioma, String director) {
        super(signatura, fechaCompra, restringido, tema);
        this.duracion = duracion;
        this.idioma = idioma;
        this.director = director;
        this.formato = formato;
    }

    public Video (String sourceLines){
        String[] datos = sourceLines.split(",");
        this.signatura = datos[0];
        this.fechaCompra = new Date(datos[1]);
        this.restringido = Boolean.parseBoolean(datos[2]);
        this.tema = datos[3];
        this.formato = TipoVideo.valueOf(datos[4]);
        this.duracion = Float.parseFloat(datos[5]);
        this.idioma = datos[6];
        this.director = datos[7];
    }

    @Override
    public String toCSVLine() {
        return this.signatura + "," + this.fechaCompra + "," + this.restringido + "," + this.tema + "," + this.formato + "," + this.duracion + "," + this.idioma + "," + this.director + ",";
    }

}

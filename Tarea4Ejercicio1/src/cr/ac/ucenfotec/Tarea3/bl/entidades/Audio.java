package cr.ac.ucenfotec.Tarea3.bl.entidades;

import cr.ac.ucenfotec.Tarea3.bl.tipos.TipoAudio;

import java.util.Date;

public class Audio extends Material {

    private float duracion;
    private String idioma;
    private TipoAudio formato;

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

    public TipoAudio getFormato() {
        return formato;
    }

    public void setFormato(TipoAudio formato) {
        this.formato = formato;
    }

    public Audio() {
    }

    public Audio(String signatura, Date fechaCompra, boolean restringido, String tema, TipoAudio formato, float duracion, String idioma) {
        super(signatura, fechaCompra, restringido, tema);
        this.formato = formato;
        this.duracion = duracion;
        this.idioma = idioma;
    }

    public Audio (String sourceLines){
        String[] datos = sourceLines.split(",");
        this.signatura = datos[0];
        this.fechaCompra = new Date(datos[1]);
        this.restringido = Boolean.parseBoolean(datos[2]);
        this.tema = datos[3];
        this.formato = TipoAudio.valueOf(datos[4]);
        this.duracion = Float.parseFloat(datos[5]);
        this.idioma = datos[6];
    }

    @Override
    public String toCSVLine() {
        return this.signatura + "," + this.fechaCompra + "," + this.restringido + "," + this.tema + "," + this.formato + "," + this.duracion + "," + this.idioma + ",";
    }
}

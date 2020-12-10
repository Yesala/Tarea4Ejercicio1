package cr.ac.ucenfotec.Tarea3.bl.dao;

import cr.ac.ucenfotec.Tarea3.bl.entidades.Material;
import cr.ac.ucenfotec.Tarea3.bl.entidades.Video;
import cr.ac.ucenfotec.Tarea3.bl.tipos.TipoVideo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VideoDAO {

    private final String TEMPLATE_CMD_INSERTAR = "insert into TVideo(signaturaVideo,fechaCompra,restringido,tema,tipoVideo,duracion,idioma,director) " +
            "values (?,?,?,?,?,?,?,?)";
    private final String TEMPLATE_QRY_VIDEO = "select * from TVideo";

    private Connection cnx;
    private PreparedStatement cmdInsertar;
    private PreparedStatement queryVIDEO;

    public VideoDAO(Connection conexion){
        this.cnx = conexion;
        try {
            this.cmdInsertar = cnx.prepareStatement(TEMPLATE_CMD_INSERTAR);
            this.queryVIDEO = cnx.prepareStatement(TEMPLATE_QRY_VIDEO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveVideo(Video nuevo) {
        if (this.cmdInsertar != null) {
            try {
                this.cmdInsertar.setString(1, nuevo.getSignatura());
                this.cmdInsertar.setDate(2, convertToSqlDate(nuevo.getFechaCompra()));
                this.cmdInsertar.setBoolean(3, nuevo.isRestringido());
                this.cmdInsertar.setString(4, nuevo.getTema());
                this.cmdInsertar.setString(5, String.valueOf(nuevo.getFormato()));
                this.cmdInsertar.setFloat(6, nuevo.getDuracion());
                this.cmdInsertar.setString(7, nuevo.getIdioma());
                this.cmdInsertar.setString(8, nuevo.getDirector());
                this.cmdInsertar.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private java.sql.Date convertToSqlDate(java.util.Date fechaAConvertir){
        return new java.sql.Date(fechaAConvertir.getTime());
    }

    public List<Material> findAll(){
        ArrayList<Material> result = new ArrayList<>();
        ResultSet resultado = null;
        try {
            resultado = this.queryVIDEO.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                if (!resultado.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Material unMaterial = new Video();
            try {
                unMaterial.setSignatura(resultado.getString("signatura"));
                unMaterial.setFechaCompra(resultado.getDate("fechaCompra"));
                unMaterial.setRestringido(resultado.getBoolean("restringido"));
                unMaterial.setTema(resultado.getString("tema"));
                ((Video)unMaterial).setFormato(TipoVideo.valueOf(resultado.getString("tipoVideo")));
                ((Video)unMaterial).setDuracion(Float.parseFloat(resultado.getString("duracion")));
                ((Video)unMaterial).setIdioma(resultado.getString("idioma"));
                ((Video)unMaterial).setDirector(resultado.getString("director"));

            } catch (SQLException e) {
                e.printStackTrace();
            }
            result.add(unMaterial);
        }
        return result;
    }
}

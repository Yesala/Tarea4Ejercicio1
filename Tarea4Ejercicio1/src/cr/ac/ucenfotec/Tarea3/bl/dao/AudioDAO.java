package cr.ac.ucenfotec.Tarea3.bl.dao;

import cr.ac.ucenfotec.Tarea3.bl.entidades.Audio;
import cr.ac.ucenfotec.Tarea3.bl.entidades.Material;
import cr.ac.ucenfotec.Tarea3.bl.tipos.TipoAudio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AudioDAO {

    private final String TEMPLATE_CMD_INSERTAR = "insert into TAudio(signaturaAudio,fechaCompra,restringido,tema,tipoAudio,duracion,idioma) " +
            "values (?,?,?,?,?,?,?)";
    private final String TEMPLATE_QRY_AUDIO = "select * from TAudio";

    private Connection cnx;
    private PreparedStatement cmdInsertar;
    private PreparedStatement queryAUDIO;

    public AudioDAO(Connection conexion){
        this.cnx = conexion;
        try {
            this.cmdInsertar = cnx.prepareStatement(TEMPLATE_CMD_INSERTAR);
            this.queryAUDIO = cnx.prepareStatement(TEMPLATE_QRY_AUDIO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveAudio(Audio nuevo) {
        if (this.cmdInsertar != null) {
            try {
                this.cmdInsertar.setString(1, nuevo.getSignatura());
                this.cmdInsertar.setDate(2, convertToSqlDate(nuevo.getFechaCompra()));
                this.cmdInsertar.setBoolean(3, nuevo.isRestringido());
                this.cmdInsertar.setString(4, nuevo.getTema());
                this.cmdInsertar.setString(5, String.valueOf(nuevo.getFormato()));
                this.cmdInsertar.setFloat(6, nuevo.getDuracion());
                this.cmdInsertar.setString(7, nuevo.getIdioma());
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
            resultado = this.queryAUDIO.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                if (!resultado.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Material unMaterial = new Audio();
            try {
                unMaterial.setSignatura(resultado.getString("signatura"));
                unMaterial.setFechaCompra(resultado.getDate("fechaCompra"));
                unMaterial.setRestringido(resultado.getBoolean("restringido"));
                unMaterial.setTema(resultado.getString("tema"));
                ((Audio)unMaterial).setFormato(TipoAudio.valueOf(resultado.getString("tipoAudio")));
                ((Audio)unMaterial).setDuracion(Float.parseFloat(resultado.getString("duracion")));
                ((Audio)unMaterial).setIdioma(resultado.getString("idioma"));

            } catch (SQLException e) {
                e.printStackTrace();
            }
            result.add(unMaterial);
        }
        return result;
    }

}

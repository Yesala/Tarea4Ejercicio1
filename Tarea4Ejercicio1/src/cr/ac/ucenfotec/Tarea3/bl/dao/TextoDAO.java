package cr.ac.ucenfotec.Tarea3.bl.dao;

import cr.ac.ucenfotec.Tarea3.bl.entidades.Material;
import cr.ac.ucenfotec.Tarea3.bl.entidades.Texto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TextoDAO {

    private final String TEMPLATE_CMD_INSERTAR = "insert into TTexto(signaturaTexto,fechaCompra,restringido,tema,titulo,nombreAutor,fechaPublicacion,numeroPaginas,idioma) " +
            "values (?,?,?,?,?,?,?,?,?)";
    private final String TEMPLATE_QRY_TEXTO = "select * from TTexto";

    private Connection cnx;
    private PreparedStatement cmdInsertar;
    private PreparedStatement queryTEXTO;

    public TextoDAO(Connection conexion){
        this.cnx = conexion;
        try {
            this.cmdInsertar = cnx.prepareStatement(TEMPLATE_CMD_INSERTAR);
            this.queryTEXTO = cnx.prepareStatement(TEMPLATE_QRY_TEXTO);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveTexto(Texto nuevo) {
        if (this.cmdInsertar != null) {
            try {
                this.cmdInsertar.setString(1, nuevo.getSignatura());
                this.cmdInsertar.setDate(2, convertToSqlDate(nuevo.getFechaCompra()));
                this.cmdInsertar.setBoolean(3, nuevo.isRestringido());
                this.cmdInsertar.setString(4, nuevo.getTema());
                this.cmdInsertar.setString(5, nuevo.getTitulo());
                this.cmdInsertar.setString(6, nuevo.getNombreAutor());
                this.cmdInsertar.setDate(7, convertToSqlDate(nuevo.getFechaPublicacion()));
                this.cmdInsertar.setInt(8, nuevo.getNumeroPaginas());
                this.cmdInsertar.setString(9, nuevo.getIdioma());
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
            resultado = this.queryTEXTO.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                if (!resultado.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Material unMaterial = new Texto();
            try {
                unMaterial.setSignatura(resultado.getString("signatura"));
                unMaterial.setFechaCompra(resultado.getDate("fechaCompra"));
                unMaterial.setRestringido(resultado.getBoolean("restringido"));
                unMaterial.setTema(resultado.getString("tema"));
                ((Texto)unMaterial).setTitulo(resultado.getString("titulo"));
                ((Texto)unMaterial).setNombreAutor(resultado.getString("nombreAutor"));
                ((Texto)unMaterial).setFechaPublicacion(resultado.getDate("fechaPublicacion"));
                ((Texto)unMaterial).setNumeroPaginas(resultado.getInt("numeroPaginas"));
                ((Texto)unMaterial).setIdioma(resultado.getString("idioma"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            result.add(unMaterial);
        }
        return result;
    }


}

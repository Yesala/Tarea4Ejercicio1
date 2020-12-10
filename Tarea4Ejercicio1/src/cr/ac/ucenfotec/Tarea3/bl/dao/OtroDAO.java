package cr.ac.ucenfotec.Tarea3.bl.dao;

import cr.ac.ucenfotec.Tarea3.bl.entidades.Material;
import cr.ac.ucenfotec.Tarea3.bl.entidades.Otro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OtroDAO {

    private final String TEMPLATE_CMD_INSERTAR = "insert into TOtro(signaturaOtro,fechaCompra,restringido,tema,descripcion) " +
            "values (?,?,?,?,?)";
    private final String TEMPLATE_QRY_OTRO = "select * from TOtro";

    private Connection cnx;
    private PreparedStatement cmdInsertar;
    private PreparedStatement queryOTRO;

    public OtroDAO(Connection conexion){
        this.cnx = conexion;
        try {
            this.cmdInsertar = cnx.prepareStatement(TEMPLATE_CMD_INSERTAR);
            this.queryOTRO = cnx.prepareStatement(TEMPLATE_QRY_OTRO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveOtro(Otro nuevo)  {
        if (this.cmdInsertar != null) {
            try {
                this.cmdInsertar.setString(1, nuevo.getSignatura());
                this.cmdInsertar.setDate(2, convertToSqlDate(nuevo.getFechaCompra()));
                this.cmdInsertar.setBoolean(3, nuevo.isRestringido());
                this.cmdInsertar.setString(4, nuevo.getTema());
                this.cmdInsertar.setString(5, nuevo.getDescripcion());
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
            resultado = this.queryOTRO.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                if (!resultado.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Material unMaterial = new Otro();
            try {
                unMaterial.setSignatura(resultado.getString("signatura"));
                unMaterial.setFechaCompra(resultado.getDate("fechaCompra"));
                unMaterial.setRestringido(resultado.getBoolean("restringido"));
                unMaterial.setTema(resultado.getString("tema"));
                ((Otro)unMaterial).setDescripcion(resultado.getString("descripcion"));

            } catch (SQLException e) {
                e.printStackTrace();
            }
            result.add(unMaterial);
        }
        return result;
    }

}

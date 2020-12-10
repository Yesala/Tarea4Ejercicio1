package cr.ac.ucenfotec.Tarea3.bl.dao;

import cr.ac.ucenfotec.Tarea3.bl.entidades.Profesor;
import cr.ac.ucenfotec.Tarea3.bl.entidades.Usuario;
import cr.ac.ucenfotec.Tarea3.bl.tipos.TipoContrato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDAO {

    private final String TEMPLATE_CMD_INSERTAR = "insert into TProfesor(nombre,apellido,numeroCedula,tipoContrato,fechaContratacion) " +
            "values (?,?,?,?,?)";
    private final String TEMPLATE_QRY_PROFESOR = "select * from TProfesor";

    private Connection cnx;
    private PreparedStatement cmdInsertar;
    private PreparedStatement queryPROFESOR;

    public ProfesorDAO(Connection conexion){
        this.cnx = conexion;
        try {
            this.cmdInsertar = cnx.prepareStatement(TEMPLATE_CMD_INSERTAR);
            this.queryPROFESOR = cnx.prepareStatement(TEMPLATE_QRY_PROFESOR);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveProfesor(Profesor nuevo) {
        if (this.cmdInsertar != null) {
            try {
                this.cmdInsertar.setString(1, nuevo.getNombre());
                this.cmdInsertar.setString(2, nuevo.getApellido());
                this.cmdInsertar.setInt(3, nuevo.getNumeroCedula());
                this.cmdInsertar.setString(4, String.valueOf(nuevo.getContrato()));
                this.cmdInsertar.setDate(5, convertToSqlDate(nuevo.getFechaContratacion()));
                this.cmdInsertar.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private java.sql.Date convertToSqlDate(java.util.Date fechaAConvertir){
        return new java.sql.Date(fechaAConvertir.getTime());
    }

    public List<Usuario> findAll(){
        ArrayList<Usuario> result = new ArrayList<>();
        ResultSet resultado = null;
        try {
            resultado = this.queryPROFESOR.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                if (!resultado.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Usuario unUsuario = new Profesor();
            try {
                unUsuario.setNombre(resultado.getString("nombre"));
                unUsuario.setApellido(resultado.getString("apellido"));
                ((Profesor)unUsuario).setNumeroCedula(resultado.getInt("numeroCedula"));
                ((Profesor)unUsuario).setContrato(TipoContrato.valueOf(resultado.getString("tipoContrato")));
                ((Profesor)unUsuario).setFechaContratacion(resultado.getDate("fechaContratacion"));
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
            result.add(unUsuario);
        }
        return result;
    }

}

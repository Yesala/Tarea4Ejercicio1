package cr.ac.ucenfotec.Tarea3.bl.dao;

import cr.ac.ucenfotec.Tarea3.bl.entidades.Estudiante;
import cr.ac.ucenfotec.Tarea3.bl.entidades.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {

    private final String TEMPLATE_CMD_INSERTAR = "insert into TEstudiante(nombre,apellido,numeroCarnet,carrera,creditos) " +
            "values (?,?,?,?,?)";
    private final String TEMPLATE_QRY_ESTUDIANTE = "select * from TEstudiante";

    private Connection cnx;
    private PreparedStatement cmdInsertar;
    private PreparedStatement queryESTUDIANTE;

    public EstudianteDAO(Connection conexion){
        this.cnx = conexion;
        try {
            this.cmdInsertar = cnx.prepareStatement(TEMPLATE_CMD_INSERTAR);
            this.queryESTUDIANTE = cnx.prepareStatement(TEMPLATE_QRY_ESTUDIANTE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean saveEstudiante(Estudiante nuevo) {
        boolean resultado = true;
        if (this.cmdInsertar != null) {
            try {
                this.cmdInsertar.setString(1, nuevo.getNombre());
                this.cmdInsertar.setString(2, nuevo.getApellido());
                this.cmdInsertar.setInt(3, nuevo.getNumeroCarnet());
                this.cmdInsertar.setString(4, nuevo.getCarrera());
                this.cmdInsertar.setInt(5, nuevo.getCreditos());
                this.cmdInsertar.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }return resultado;
    }

    public List<Usuario> findAll(){
        ArrayList<Usuario> result = new ArrayList<>();
        ResultSet resultado = null;
        try {
            resultado = this.queryESTUDIANTE.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                if (!resultado.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Usuario unUsuario = new Estudiante();
            try {
                unUsuario.setNombre(resultado.getString("nombre"));
                unUsuario.setApellido(resultado.getString("apellido"));
                ((Estudiante)unUsuario).setNumeroCarnet(resultado.getInt("numeroCarnet"));
                ((Estudiante)unUsuario).setCarrera(resultado.getString("carrera"));
                ((Estudiante)unUsuario).setCreditos(resultado.getInt("creditos"));

            } catch (SQLException e) {
                e.printStackTrace();
            }
            result.add(unUsuario);
        }
        return result;
    }


}

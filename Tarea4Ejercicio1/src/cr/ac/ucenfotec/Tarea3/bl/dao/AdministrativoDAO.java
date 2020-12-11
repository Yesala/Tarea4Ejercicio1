package cr.ac.ucenfotec.Tarea3.bl.dao;

import cr.ac.ucenfotec.Tarea3.bl.entidades.Administrativo;
import cr.ac.ucenfotec.Tarea3.bl.entidades.Usuario;
import cr.ac.ucenfotec.Tarea3.bl.tipos.TipoNombramiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdministrativoDAO {

    private final String TEMPLATE_CMD_INSERTAR = "insert into TAdministrativo(nombre,apellido,numeroCedula,tipoNombramiento,horasSemAsignadas) " +
            "values (?,?,?,?,?)";
    private final String TEMPLATE_QRY_ADMINISTRATIVO = "select * from TAdministrativo";

    private Connection cnx;
    private PreparedStatement cmdInsertar;
    private PreparedStatement queryADMINISTRATIVO;

    public AdministrativoDAO(Connection conexion){
        this.cnx = conexion;
        try {
            this.cmdInsertar = cnx.prepareStatement(TEMPLATE_CMD_INSERTAR);
            this.queryADMINISTRATIVO = cnx.prepareStatement(TEMPLATE_QRY_ADMINISTRATIVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveAdmiistrativo(Administrativo nuevo) {
        if (this.cmdInsertar != null) {
            try {
                this.cmdInsertar.setString(1, nuevo.getNombre());
                this.cmdInsertar.setString(2, nuevo.getApellido());
                this.cmdInsertar.setInt(3, nuevo.getNumeroCedula());
                this.cmdInsertar.setString(4, String.valueOf(nuevo.getNombramiento()));
                this.cmdInsertar.setFloat(5, nuevo.getHorasSemAsignadas());
                this.cmdInsertar.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public List<Usuario> findAll(){
        ArrayList<Usuario> result = new ArrayList<>();
        ResultSet resultado = null;
        try {
            resultado = this.queryADMINISTRATIVO.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                if (!resultado.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Usuario unUsuario = new Administrativo();
            try {
                unUsuario.setNombre(resultado.getString("nombre"));
                unUsuario.setApellido(resultado.getString("apellido"));
                ((Administrativo)unUsuario).setNumeroCedula(resultado.getInt("numeroCedula"));
                ((Administrativo)unUsuario).setNombramiento(TipoNombramiento.valueOf(resultado.getString("tipoNombramiento")));
                ((Administrativo)unUsuario).setHorasSemAsignadas(Float.parseFloat(resultado.getString("horasSemAsignadas")));


            } catch (SQLException e) {
                e.printStackTrace();
            }
            result.add(unUsuario);
        }
        return result;
    }

}

package cr.ac.ucenfotec.Tarea3.gestor;

import cr.ac.ucenfotec.Tarea3.bl.entidades.Administrativo;
import cr.ac.ucenfotec.Tarea3.bl.entidades.Estudiante;
import cr.ac.ucenfotec.Tarea3.bl.entidades.Profesor;
import cr.ac.ucenfotec.Tarea3.bl.entidades.Usuario;
import cr.ac.ucenfotec.Tarea3.bl.fao.AdministrativoFAO;
import cr.ac.ucenfotec.Tarea3.bl.fao.EstudianteFAO;
import cr.ac.ucenfotec.Tarea3.bl.fao.ProfesorFAO;
import cr.ac.ucenfotec.Tarea3.bl.fao.UsuarioFAO;

import java.util.ArrayList;
import java.util.List;

public class GestorUsuarios {

    private EstudianteFAO estudianteFAO = new EstudianteFAO();
    private ProfesorFAO profesorFAO = new ProfesorFAO();
    private AdministrativoFAO administrativoFAO = new AdministrativoFAO();

    public boolean save(Usuario nuevoUsuario){
        try{
            UsuarioFAO objPersistente = determinarObjetoFAO(nuevoUsuario);
            return objPersistente.save(nuevoUsuario);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    private UsuarioFAO determinarObjetoFAO(Usuario nuevoUsuario) throws Exception {
        if(nuevoUsuario instanceof Estudiante){
            return this.estudianteFAO;
        }
        if(nuevoUsuario instanceof Profesor){
            return this.profesorFAO;
        }
        if(nuevoUsuario instanceof Administrativo){
            return this.administrativoFAO;
        }
        throw new Exception("Usuario Desconocido");
    }


    public List<Usuario> listAll(Usuario tipoUsuario){
        try{
            UsuarioFAO objPersistente = determinarObjetoFAO(tipoUsuario);
            return objPersistente.findAll();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new ArrayList<Usuario>();
    }

}

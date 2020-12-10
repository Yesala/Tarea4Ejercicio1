package cr.ac.ucenfotec.Tarea3.bl.fao;

import cr.ac.ucenfotec.Tarea3.bl.entidades.Usuario;

import java.util.List;

public abstract class UsuarioFAO {

        public abstract boolean save(Usuario newUsuario);
        public abstract List<Usuario> findAll();

    }



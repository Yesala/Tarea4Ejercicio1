package cr.ac.ucenfotec.Tarea3.gestor;

import cr.ac.ucenfotec.Tarea3.bl.dao.*;
import cr.ac.ucenfotec.Tarea3.bl.entidades.*;

import java.sql.Connection;
import java.sql.DriverManager;

    public class GestorDAO {

    Connection connection;

    AdministrativoDAO administrativoDAO;
    ProfesorDAO profesorDAO;
    EstudianteDAO estudianteDAO;
    TextoDAO textoDAO;
    AudioDAO audioDAO;
    VideoDAO videoDAO;
    OtroDAO otroDAO;

    public GestorDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdbiblioteca"
                    , "root", "Yesala1104");

            this.administrativoDAO = new AdministrativoDAO(this.connection);
            this.profesorDAO = new ProfesorDAO(this.connection);
            this.estudianteDAO = new EstudianteDAO(this.connection);
            this.textoDAO = new TextoDAO(this.connection);
            this.audioDAO = new AudioDAO(this.connection);
            this.videoDAO = new VideoDAO(this.connection);
            this.otroDAO = new OtroDAO(this.connection);

        } catch (Exception e) {
            System.out.println("Cant connect to db");
            System.out.println(e.getMessage());
        }
    }


        public void guardarProfesor(Profesor profesor) {
            this.profesorDAO.saveProfesor(profesor);
        }

        public void guardarEstudiante(Estudiante estudiante) {
            this.estudianteDAO.saveEstudiante(estudiante);
        }

        public void guardarAdministrativo(Administrativo administrativo) {
            this.administrativoDAO.saveAdmiistrativo(administrativo);
        }

        public void guardarTexto(Texto texto) {
            this.textoDAO.saveTexto(texto);
        }

        public void guardarAudio(Audio audio) {
            this.audioDAO.saveAudio(audio);
        }

        public void guardarVideo(Video video) {
            this.videoDAO.saveVideo(video);
        }

        public void guardarOtro(Otro otro) {
            this.otroDAO.saveOtro(otro);
        }
    }

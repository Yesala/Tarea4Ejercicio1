package cr.ac.ucenfotec.Tarea3.controlador;

import cr.ac.ucenfotec.Tarea3.bl.entidades.*;
import cr.ac.ucenfotec.Tarea3.bl.tipos.TipoAudio;
import cr.ac.ucenfotec.Tarea3.bl.tipos.TipoContrato;
import cr.ac.ucenfotec.Tarea3.bl.tipos.TipoNombramiento;
import cr.ac.ucenfotec.Tarea3.bl.tipos.TipoVideo;
import cr.ac.ucenfotec.Tarea3.gestor.GestorDAO;
import cr.ac.ucenfotec.Tarea3.gestor.GestorMateriales;
import cr.ac.ucenfotec.Tarea3.gestor.GestorUsuarios;
import cr.ac.ucenfotec.Tarea3.iu.IU;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Controlador {

    private IU iu = new IU();
    private GestorMateriales gestorMateriales = new GestorMateriales();
    private GestorUsuarios gestorUsuarios = new GestorUsuarios();
    private GestorDAO gestorDAO = new GestorDAO();

    public void ejecutarPrograma() {
        int opcion = 0;
        do {
            iu.mostrarMenu();
            opcion = iu.leerOpcion();
            try {
                procesarOpcion(opcion);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } while (opcion != 10);
    }

    private void procesarOpcion(int opcion) throws ParseException {
        switch (opcion) {
            case 1:
                registroEstudiante();
                break;
            case 2:
                registroProfesor();
                break;
            case 3:
                registroAdministrativo();
                break;
            case 4:
               listarUsuarios();
                break;
            case 5:
                registroTextos();
                break;
            case 6:
                registroAudio();
                break;
            case 7:
                registroVideo();
                break;
            case 8:
                registroOtros();
                break;
            case 9:
                listarMateriales();
                break;
            case 10:
                break;
            default:
                iu.imprimirMensaje("Opcion desconocida");
         }
    }

    private void registroEstudiante() {
        iu.imprimirMensaje("Nombre del estudiante: ");
        String nombre = iu.leerTexto();
        iu.imprimirMensaje("Apellido del estudiante: ");
        String apellido = iu.leerTexto();
        iu.imprimirMensaje("Numero de carnet: ");
        int numeroCarne = iu.leerEntero();
        iu.imprimirMensaje("Carrera que cursa: ");
        String carrera = iu.leerTexto();
        iu.imprimirMensaje("Creditos de la carrera: ");
        int creditos = iu.leerEntero();
        Estudiante estudiante = new Estudiante(nombre, apellido, numeroCarne,carrera,creditos);
        this.gestorUsuarios.save(estudiante);
        this.gestorDAO.guardarEstudiante(estudiante);
    }

    private void registroProfesor() {
        iu.imprimirMensaje("Nombre del profesor: ");
        String nombre = iu.leerTexto();
        iu.imprimirMensaje("Apellido del profesor ");
        String apellido = iu.leerTexto();
        iu.imprimirMensaje("Numero de cedula ");
        int numeroCedula = iu.leerEntero();
        iu.imprimirMensaje("Tipo de Contrato: TIEMPO_COMPLETO, MEDIO_TIEMPO");
        String tipoContrato = iu.leerTexto();
        TipoContrato contratoProfesor = TipoContrato.valueOf(tipoContrato);
        iu.imprimirMensaje("Fecha de Contratación (Formato yyyy-MM-dd):");
        Date fechaContratacion = null;
        try {
            fechaContratacion = new SimpleDateFormat("yyyy-MM-dd").parse(iu.leerTexto());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Profesor profesor = new Profesor(nombre,apellido,numeroCedula,contratoProfesor,fechaContratacion);
        this.gestorUsuarios.save(profesor);
        this.gestorDAO.guardarProfesor(profesor);
    }

    private void registroAdministrativo() {
        iu.imprimirMensaje("Nombre del colaborador: ");
        String nombre = iu.leerTexto();
        iu.imprimirMensaje("Apellido del colaborador: ");
        String apellido = iu.leerTexto();
        iu.imprimirMensaje("Numero de cedula ");
        int numeroCedula = iu.leerEntero();
        iu.imprimirMensaje("Tipo de Nombramiento: A, B, C");
        String tipoNombramiento = iu.leerTexto();
        TipoNombramiento contratoAdministrativo = TipoNombramiento.valueOf(tipoNombramiento);
        iu.imprimirMensaje("Cantidad de horas semanales asignadas ");
        float horasSemAsignadas = iu.leerEntero();
        Administrativo administrativo = new Administrativo(nombre,apellido,numeroCedula,contratoAdministrativo,horasSemAsignadas);
        this.gestorUsuarios.save(administrativo);
        this.gestorDAO.guardarAdministrativo(administrativo);
    }

    private void listarUsuarios() {
        iu.imprimirMensaje("Elija la lista a imprimir: 1. Estudiantes, 2. Profesores, 3, Administradores");
        Usuario tipoUsuario = new Usuario();
        int escogencia = iu.leerEntero();
        if (escogencia == 1) {
            tipoUsuario = new Estudiante();
        }
        else if (escogencia == 2) {
            tipoUsuario = new Profesor();
        }
        else if (escogencia == 3) {
            tipoUsuario = new Administrativo();
        }
        List<Usuario> usuarios = gestorUsuarios.listAll(tipoUsuario);
        for (Usuario unUsuario: usuarios) {
            iu.imprimirMensaje(unUsuario.toCSVLine());
        }
    }

    private void registroTextos() throws ParseException {
        boolean False = true;
        iu.imprimirMensaje("Signatura del texto: ");
        String signatura = iu.leerTexto();
        iu.imprimirMensaje("Fecha de Compra (Formato yyyy-MM-dd):");
        Date fechaCompra = null;
        try {
            fechaCompra = new SimpleDateFormat("yyyy-MM-dd").parse(iu.leerTexto());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        iu.imprimirMensaje("Disponibilidad del texto: ");
        boolean restringido = False;
        iu.imprimirMensaje("Tema del texto:");
        String tema= iu.leerTexto();
        iu.imprimirMensaje("Titulo:");
        String titulo = iu.leerTexto();
        iu.imprimirMensaje("Nombre del autor:");
        String nombreAutor = iu.leerTexto();
        iu.imprimirMensaje("Fecha de Publicacion (Formato yyyy-MM-dd):");
        Date fechaPublicacion = null;
        try {
            fechaPublicacion = new SimpleDateFormat("yyyy-MM-dd").parse(iu.leerTexto());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        iu.imprimirMensaje("Numero de páginas del texto: ");
        int numeroPagina = iu.leerEntero();
        iu.imprimirMensaje("Idioma:");
        String idioma = iu.leerTexto();
        Texto texto = new Texto(signatura,fechaCompra,restringido,tema,titulo,nombreAutor,fechaPublicacion,numeroPagina,idioma);
        this.gestorMateriales.save(texto);
        this.gestorDAO.guardarTexto(texto);
    }

    private void registroAudio() {
        boolean False = true;
        iu.imprimirMensaje("Signatura del audio: ");
        String signatura = iu.leerTexto();
        iu.imprimirMensaje("Fecha de Compra (Formato yyyy-MM-dd):");
        Date fechaCompra = null;
        try {
            fechaCompra = new SimpleDateFormat("yyyy-MM-dd").parse(iu.leerTexto());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        iu.imprimirMensaje("Disponibilidad del audio: ");
        boolean restringido = False;
        iu.imprimirMensaje("Tema del audio:");
        String tema= iu.leerTexto();
        iu.imprimirMensaje("Tipo de Audio: CASSETTE, CD, DVD");
        String formatoAudio  = iu.leerTexto();
        TipoAudio tipoAudio = TipoAudio.valueOf(formatoAudio);
        iu.imprimirMensaje("Duracion del audio");
        float duracion = iu.leerFloat();
        iu.imprimirMensaje("Idioma del audio");
        String idioma = iu.leerTexto();
        Audio audio = new Audio(signatura,fechaCompra,restringido,tema,tipoAudio,duracion,idioma);
        this.gestorMateriales.save(audio);
        this.gestorDAO.guardarAudio(audio);
    }

    private void registroVideo() {
        boolean False = true;
        iu.imprimirMensaje("Signatura del video: ");
        String signatura = iu.leerTexto();
        iu.imprimirMensaje("Fecha de Compra (Formato yyyy-MM-dd):");
        Date fechaCompra = null;
        try {
            fechaCompra = new SimpleDateFormat("yyyy-MM-dd").parse(iu.leerTexto());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        iu.imprimirMensaje("Disponibilidad del video: ");
        boolean restringido = False;
        iu.imprimirMensaje("Tema del video:");
        String tema= iu.leerTexto();
        iu.imprimirMensaje("Tipo de Video: VHS, VCD, DVD");
        String formatoVideo = iu.leerTexto();
        TipoVideo tipoVideo = TipoVideo.valueOf(formatoVideo);
        iu.imprimirMensaje("Duracion del video");
        float duracion = iu.leerFloat();
        iu.imprimirMensaje("Idioma del video");
        String idioma = iu.leerTexto();
        iu.imprimirMensaje("Nombre del director del video");
        String director = iu.leerTexto();
        Video video = new Video(signatura,fechaCompra,restringido,tema,tipoVideo,duracion,idioma,director);
        this.gestorMateriales.save(video);
        this.gestorDAO.guardarVideo(video);
    }

    private void registroOtros() {
        boolean False = true;
        iu.imprimirMensaje("Signatura del material: ");
        String signatura = iu.leerTexto();
        iu.imprimirMensaje("Fecha de Compra (Formato yyyy-MM-dd):");
        Date fechaCompra = null;
        try {
            fechaCompra = new SimpleDateFormat("yyyy-MM-dd").parse(iu.leerTexto());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        iu.imprimirMensaje("Disponibilidad del material: ");
        boolean restringido = False;
        iu.imprimirMensaje("Tema del material:");
        String tema= iu.leerTexto();
        iu.imprimirMensaje("Descripcion del material: ");
        String descripcion = iu.leerTexto();
        Otro otro = new Otro(signatura,fechaCompra,restringido,tema,descripcion);
        this.gestorMateriales.save(otro);
        this.gestorDAO.guardarOtro(otro);
    }

    private void listarMateriales() {
        iu.imprimirMensaje("Elija la lista a imprimir: 1. Textos, 2. Audio, 3, Video, 4. Otros Materiales");
        Material tipoMaterial= new Material();
        int escogencia = iu.leerEntero();
        if (escogencia == 1) {
            tipoMaterial = new Texto();
        }
        else if (escogencia == 2) {
            tipoMaterial = new Audio();
        }
        else if (escogencia == 3) {
            tipoMaterial = new Video();
        }
        else if (escogencia == 4) {
            tipoMaterial = new Otro();
        }
        List<Material> materiales = gestorMateriales.listAll(tipoMaterial);
        for (Material unMaterial: materiales) {
            iu.imprimirMensaje(unMaterial.toCSVLine());
        }
    }
}

package cr.ac.ucenfotec.Tarea3.iu;

import java.io.PrintStream;
import java.util.Scanner;

public class IU {

    private PrintStream output = new PrintStream(System.out);
    private Scanner input = new Scanner(System.in).useDelimiter("\n");

    public void mostrarMenu() {
        output.println("Bienvenido a la Biblioteca Bookless");
        output.println("Registro de Usuarios y Materiales");
        output.println("1. Registro de Estudiantes ");
        output.println("2. Registro de Profesores");
        output.println("3. Registro de personal Administrativo");
        output.println("4. Imprimir Listas de Usuarios");
        output.println("5. Registro de Textos");
        output.println("6. Registro de Audios");
        output.println("7. Registro de Videos");
        output.println("8. Registro de otros materiales");
        output.println("9. Imprimir Listas de Materiales");
        output.println("10. Salir");
    }

    public int leerOpcion() {
        output.println("Opción seleccionada: ");
        return input.nextInt();
    }

    public void imprimirMensaje(String mensaje) {
        output.println(mensaje);
    }

    public String leerTexto() {
        return input.next();
    }

    public int leerEntero() {
        return input.nextInt();
    }

    public float leerFloat() {
        return input.nextFloat();
    }
}

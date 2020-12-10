package cr.ac.ucenfotec.Tarea3.bl.fao;

import cr.ac.ucenfotec.Tarea3.bl.entidades.Administrativo;
import cr.ac.ucenfotec.Tarea3.bl.entidades.Usuario;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class AdministrativoFAO extends UsuarioFAO {

    @Override
    public boolean save(Usuario newUsuario) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add(newUsuario.toCSVLine());
        try {
            Files.write(Paths.get("/Users/macbook/Dev/listOfAdministrativos.csv"),lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public List<Usuario> findAll() {
        ArrayList<Usuario> result = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("/Users/macbook/Dev/listOfAdministrativos.csv"));
            String currentLine = reader.readLine();
            while (currentLine != null) {
                result.add(new Administrativo(currentLine) {
                });
                currentLine = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


}



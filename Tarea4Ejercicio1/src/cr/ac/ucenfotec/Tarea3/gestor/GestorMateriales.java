package cr.ac.ucenfotec.Tarea3.gestor;

import cr.ac.ucenfotec.Tarea3.bl.entidades.*;
import cr.ac.ucenfotec.Tarea3.bl.fao.*;

import java.util.ArrayList;
import java.util.List;

public class GestorMateriales {

    private AudioFAO audioFAO = new AudioFAO();
    private TextoFAO textoFAO = new TextoFAO();
    private VideoFAO videoFAO = new VideoFAO();
    private OtrosFAO otrosFAO = new OtrosFAO();

    public boolean save(Material nuevoMaterial){
        try{
            MaterialFAO objPersistente = determinarObjetoFAO(nuevoMaterial);
            return objPersistente.save(nuevoMaterial);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    private MaterialFAO determinarObjetoFAO(Material nuevoMaterial) throws Exception {
        if(nuevoMaterial instanceof Texto){
            return this.textoFAO;
        }
        if(nuevoMaterial instanceof Audio){
            return this.audioFAO;
        }
        if(nuevoMaterial instanceof Video){
            return this.videoFAO;
        }
        if(nuevoMaterial instanceof Otro){
            return this.otrosFAO;
        }
        throw new Exception("Material Desconocido");
    }

    public List<Material> listAll(Material tipoMaterial){
        try{
            MaterialFAO objPersistente = determinarObjetoFAO(tipoMaterial);
            return objPersistente.findAll();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new ArrayList<Material>();
    }

}






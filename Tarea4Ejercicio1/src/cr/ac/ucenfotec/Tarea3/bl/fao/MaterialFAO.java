package cr.ac.ucenfotec.Tarea3.bl.fao;

import cr.ac.ucenfotec.Tarea3.bl.entidades.Material;

import java.util.List;

public abstract class MaterialFAO {

    public abstract boolean save(Material newMaterial);
    public abstract List<Material> findAll();

}


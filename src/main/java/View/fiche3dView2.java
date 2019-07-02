package View;

import Controller.FicheController;
import Observable.FicheObservable;
import Observer.FicheObserver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import Enum.RaceEnum;

import java.io.IOException;

public class fiche3dView2 implements FicheObserver {

    FicheController ficheCon;
    Group fiches;
    Group fiche;
    boolean initialize = true;

    public fiche3dView2(FicheController ficheCon, Group fiches){
        this.ficheCon = ficheCon;
        this.fiches = fiches;
        ficheCon.registerObserver(this);
        loadFiche();
    }

    public void loadFiche(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(this.getClass().getResource("/3dObjects/raceFiche.fxml"));
            fiche = fxmlLoader.load();
            fiche.setScaleX(10);
            fiche.setScaleY(10);
            fiche.setScaleZ(10);
            fiches.getChildren().add(fiche);
        }
        catch (IOException e) {
            // exception handling
        }
    }


    @Override
    public void update(FicheObservable fo) {
        fiche.setTranslateX(fo.getPosition().getX());
        fiche.setTranslateY(fo.getPosition().getY());
        fiche.setTranslateZ(fo.getPosition().getZ());

        if(initialize){
            MeshView mesh = (MeshView)fiche.getChildren().get(0);
            PhongMaterial material = new PhongMaterial();
            material.setDiffuseColor(RaceEnum.valueOf(fo.getRace()).getRace().getRaceColor());
            mesh.setMaterial(material);
            initialize = false;
        }
    }
}

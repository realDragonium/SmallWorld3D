package View;

import Controller.FicheController;
import Enums.GameViewEnum;
import Objects.NormalFXMLLoader;
import Observable.FicheObservable;
import Observer.FicheObserver;
import javafx.scene.Group;
import Enums.RaceEnum;

import Enums.View3DEnum;
import javafx.scene.layout.Pane;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;

public class Fiche3DView implements FicheObserver {

    private FicheController ficheCon;
    private Group fiches;
    private Group fiche;
    private boolean initialize = true;

    public Fiche3DView(FicheController ficheCon){
        this.ficheCon = ficheCon;
        this.fiches = View3DEnum.FICHES.getGroup();
        ficheCon.registerObserver(this);
        loadFiche();
    }

    public void loadFiche(){
        fiche = new NormalFXMLLoader("/3dObjects/raceFiche.fxml").loadGroup();
        fiche.setScaleX(10);
        fiche.setScaleY(10);
        fiche.setScaleZ(10);
        fiches.getChildren().add(fiche);
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

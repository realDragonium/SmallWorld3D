package View;

import Controller.FicheController;
import Objects.NormalFXMLLoader;
import Observable.FicheObservable;
import Observer.FicheObserver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;

import java.io.IOException;
import Enum.View3DEnum;
public class Fiche3DView implements FicheObserver {

    private FicheController ficheCon;
    private Group fiches;
    private Group fiche;

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
    }
}

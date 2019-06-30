package View;

import Controller.FicheController;
import Observable.FicheObservable;
import Observer.FicheObserver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;

import java.io.IOException;

public class fiche3dView implements FicheObserver {

    FicheController ficheCon;
    Group fiches;
    Group fiche;

    public fiche3dView(FicheController ficheCon, Group fiches){
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
    }
}

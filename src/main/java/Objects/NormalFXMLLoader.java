package Objects;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.shape.MeshView;

import java.io.IOException;

public class NormalFXMLLoader {

    private String url;

    public NormalFXMLLoader(String url) {
        this.url = url;
    }

    public Group loadGroup() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(this.getClass().getResource(url));
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("INLADEN FXML NIET GELUKT! URL: " + url);
        return null;
    }

    public MeshView loadMeshView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(this.getClass().getResource(url));
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("INLADEN FXML NIET GELUKT! URL: " + url);
        return null;
    }

}

package Objects;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Callable;

public class SpecialFXMLLoader {

    public void loader(String path, Callable<?> callable) {
        javafx.fxml.FXMLLoader fxmlLoader = new javafx.fxml.FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(path));
        fxmlLoader.setControllerFactory(param -> {
            try {
                return callable.call();
            } catch (Exception ex) {
                throw new IllegalStateException(ex);
            }
        });
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

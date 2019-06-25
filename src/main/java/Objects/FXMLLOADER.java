package Objects;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Callable;

public class FXMLLOADER {

    public void loader(String path, Map<Class, Callable<?>> creators) {
        javafx.fxml.FXMLLoader fxmlLoader = new javafx.fxml.FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(path));
        fxmlLoader.setControllerFactory(param -> {
            Callable<?> callable = creators.get(param);
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

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Scene escena = new Scene(
                FXMLLoader.load(
                        getClass().getResource("/vista/calculadora.fxml"))
        );

        stage.setTitle("Cliente UDP - Calculadora");
        stage.setScene(escena);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
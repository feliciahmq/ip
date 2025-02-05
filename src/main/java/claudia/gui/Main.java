package claudia.gui;

import java.io.IOException;

import claudia.exception.ClaudiaException;
import claudia.ui.Claudia;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Claudia using FXML.
 */
public class Main extends Application {

    private Claudia claudia = new Claudia();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setClaudia(claudia);  // inject the Claudia instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

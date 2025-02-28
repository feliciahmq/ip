package claudia.gui;

import claudia.ui.Claudia;
import claudia.ui.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Claudia claudia;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image claudiaImage = new Image(this.getClass().getResourceAsStream("/images/DaClaudia.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Claudia instance */
    public void setClaudia(Claudia c) {
        claudia = c;
        String welcome = Ui.showWelcome();
        dialogContainer.getChildren().add(
                DialogBox.getClaudiaDialog(welcome, claudiaImage)
        );

        this.claudia.guiStart();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);

        String response = claudia.getResponse(input);
        DialogBox claudiaDialog = DialogBox.getClaudiaDialog(response, claudiaImage);

        if (response.startsWith("NOOOOOO")) {
            claudiaDialog.getLabel().getStyleClass().add("error-dialog");
        }

        dialogContainer.getChildren().addAll(userDialog, claudiaDialog);
        userInput.clear();

    }
}

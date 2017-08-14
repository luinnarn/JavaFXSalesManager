package controller;

import entities.Sale;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.HibernateUtil;
import org.hibernate.Session;

/**
 * FXML Controller class
 *
 * @author Nikola Dragić
 */
public class AddContractController {

    @FXML
    private ChoiceBox<String> speedChoice;

    @FXML
    private ChoiceBox<String> thresholdChoice;

    @FXML
    private ChoiceBox<String> durationChoice;

    @FXML
    private TextField nameField;

    @FXML
    private TextField addressField;

    @FXML
    private Button okBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private Label msgLabel;

    @FXML
    private void addContract() {

        if (speedChoice.getValue() == null || thresholdChoice.getValue() == null || durationChoice.getValue() == null || nameField.getText().isEmpty() || addressField.getText().isEmpty()) {
            msgLabel.setText("Please fill in all fields");
        } else {

            Sale sale = new Sale();
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            sale.setSpeed(Integer.parseInt(speedChoice.getValue().split(" ")[0]));
            sale.setThreshold(thresholdChoice.getValue());
            sale.setDuration(Integer.parseInt(durationChoice.getValue().split(" ")[0]));
            sale.setName(nameField.getText());
            sale.setAddress(addressField.getText());
            session.save(sale);
            session.getTransaction().commit();

            try {

                Stage stage;
                Parent root;

                stage = (Stage) okBtn.getScene().getWindow();

                root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
            }
        }
    }

    @FXML
    private void cancel() {
        try {

            Stage stage;
            Parent root;

            stage = (Stage) cancelBtn.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }
    }

    /**
     * Initializes the controller class.
     */
    public void initialize() {

    }

}

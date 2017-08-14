package controller;

import entities.Sale;
import java.io.IOException;
import java.util.Optional;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * FXML Controller class
 *
 * @author Nikola Dragić
 */
public class MainWindowController {

    @FXML
    private TableView<Sale> contractSalesTable;

    @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private TableColumn idColumn;

    @FXML
    private TableColumn speedColumn;

    @FXML
    private TableColumn thresholdColumn;

    @FXML
    private TableColumn durationColumn;

    @FXML
    private TableColumn nameColumn;

    @FXML
    private TableColumn addressColumn;

    public void fillTable() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query query = s.createQuery("from Sale");
        ObservableList<Sale> sales = FXCollections.observableArrayList();
        sales.addAll(query.list());

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        speedColumn.setCellValueFactory(new PropertyValueFactory<>("speed"));
        thresholdColumn.setCellValueFactory(new PropertyValueFactory<>("threshold"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        contractSalesTable.setItems(sales);
        contractSalesTable.getColumns().clear();
        contractSalesTable.getColumns().addAll(idColumn, speedColumn, thresholdColumn, durationColumn, nameColumn, addressColumn);
        contractSalesTable.refresh();
    }

    public void initialize() {
        fillTable();
    }

    @FXML
    private void openAddContractForm() {
        try {

            Stage stage;
            Parent root;

            stage = (Stage) addBtn.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource("/fxml/addContract.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }
    }

    @FXML
    private void deleteContract() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/fxml/style.css").toExternalForm());
        dialogPane.getStyleClass().add("dialog");
        alert.initStyle(StageStyle.UNDECORATED);
        alert.initOwner(deleteBtn.getScene().getWindow());

        alert.setHeaderText("Delete contract");
        alert.setContentText("Are you sure you want to delete the selected contract?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Sale sale = contractSalesTable.getSelectionModel().getSelectedItem();

            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            session.delete(sale);

            session.getTransaction().commit();
            fillTable();
        } else {
        }
    }

    @FXML
    private void closeApp() throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/fxml/style.css").toExternalForm());
        dialogPane.getStyleClass().add("dialog");
        alert.initStyle(StageStyle.UNDECORATED);
        alert.initOwner(exitBtn.getScene().getWindow());

        alert.setHeaderText("Exit");
        alert.setContentText("Are you sure you want to exit?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
            System.exit(0);
        } else {
        }

    }
}

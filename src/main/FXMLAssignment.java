package main;

import entities.Sale;
import java.net.URL;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Nikola Dragić
 */
public class FXMLAssignment extends Application {
    
    double dragOffsetX;
    double dragOffsetY;
        

    public static void main(String[] args) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query query = s.createQuery("from Sale");
        ArrayList<Sale> sales = (ArrayList<Sale>)query.list();
        
        Application.launch();
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        URL fxmlUrl = getClass().getClassLoader().getResource("fxml/main.fxml");
        AnchorPane root = (AnchorPane) FXMLLoader.load(fxmlUrl);
      
       
        Scene mainWindow = new Scene(root);
        primaryStage.setScene(mainWindow);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        
        mainWindow.setOnMousePressed((MouseEvent event) -> {
            dragOffsetX = event.getScreenX() - primaryStage.getX();
            dragOffsetY = event.getScreenY() - primaryStage.getY();
        });
        mainWindow.setOnMouseDragged((MouseEvent event) -> {
            primaryStage.setX(event.getScreenX() - dragOffsetX);
            primaryStage.setY(event.getScreenY() - dragOffsetY);
        });
        
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
    }
    
}

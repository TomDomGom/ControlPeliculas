/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlpeliculas;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Tomas
 */
public class MainView extends Application {
    
    private EntityManagerFactory emf;
    private EntityManager em;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        StackPane rootMain = new StackPane();
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PeliculasView.fxml"));
        Parent root = fxmlLoader.load();
        
        rootMain.getChildren().add(root);
        
        emf = Persistence.createEntityManagerFactory("ControlPeliculasPU");
        em = emf.createEntityManager();
        
        PeliculasViewController peliculasViewController = (PeliculasViewController) fxmlLoader.getController();
        peliculasViewController.setEntityManager(em);
        peliculasViewController.cargarPeliculas();
        
        Scene scene = new Scene(rootMain, 800, 800);
        
        primaryStage.setTitle("Cine Maestro Francisco Fatou");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlpeliculas;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
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
    // Arrancamos la aplicación y mostramos los paneles montados con SceneBuilder.
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        StackPane rootMain = new StackPane();
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PeliculasView.fxml"));
        Pane rootPeliculasView = fxmlLoader.load();
        rootMain.getChildren().add(rootPeliculasView);
        
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
    
   // Metodo para parar la conexión con la base de datos.
    @Override
    public void stop() throws Exception {
        em.close(); 
        emf.close(); 
        try { 
            DriverManager.getConnection("jdbc:derby:BDPeliculas;shutdown=true"); 
        } catch (SQLException ex) { 
        }        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

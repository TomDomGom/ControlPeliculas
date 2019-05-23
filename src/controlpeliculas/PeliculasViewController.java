/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlpeliculas;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author Tomas
 */
public class PeliculasViewController implements Initializable {

    private EntityManager entityManager;
    private Datospeliculas peliculasSeleccionadas;
    
    @FXML
    private AnchorPane peliculasView;
    @FXML
    private Pane panePeliculasView;
    @FXML
    private TableView<?> tablaPeliculasView;
    @FXML
    private TableColumn<Datospeliculas, String> tituloView;
    @FXML
    private TableColumn<Datospeliculas, String> directorView;
    @FXML
    private TableColumn<Datospeliculas, Date> fecEstrenoView;
    @FXML
    private TableColumn<Datospeliculas, String> productoraView;
    @FXML
    private TableColumn<Datospeliculas, String> cartelView;
    @FXML
    private TableColumn<Datospeliculas, Integer> categoriaView;
    @FXML
    private TableColumn<Datospeliculas, BigDecimal> recaudacionView;
    @FXML
    private TableColumn<Datospeliculas, Boolean> proyectadaView;
    @FXML
    private Button addPelicula;
    @FXML
    private Button editPelicula;
    @FXML
    private Button deletePelicula;
    @FXML
    private Button btnBuscar;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tituloView.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        directorView.setCellValueFactory(new PropertyValueFactory<>("director"));
        fecEstrenoView.setCellValueFactory(new PropertyValueFactory<>("estreno"));
        productoraView.setCellValueFactory(new PropertyValueFactory<>("productora"));
        cartelView.setCellValueFactory(new PropertyValueFactory<>("cartel"));
        categoriaView.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        recaudacionView.setCellValueFactory(new PropertyValueFactory<>("recaudacion"));
        proyectadaView.setCellValueFactory(new PropertyValueFactory<>("proyectada"));
        
    }    

    @FXML
    private void addPeliculaClick(MouseEvent event) {
    }

    @FXML
    private void editPeliculaClick(MouseEvent event) {
    }

    @FXML
    private void deletePeliculaClick(MouseEvent event) {
    }

    @FXML
    private void buscarPeliculaClick(MouseEvent event) {
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cargarPeliculas() {
    Query queryProductosFindAll = entityManager.createNamedQuery("Datospeliculas.findAll");
    List<Datospeliculas> listPeliculas = queryProductosFindAll.getResultList();
    tablaPeliculasView.setItems(FXCollections.observableArrayList(listPeliculas));
    }
    
}
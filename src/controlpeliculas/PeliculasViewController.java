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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
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
    private TableView<Datospeliculas> tablaPeliculasView;
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
    @FXML
    private Button btnPeliculasView;
    @FXML
    private Button btnCategoriasView;
    @FXML
    private Button btnRegistrosView;
    @FXML
    private TextField buscador;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tituloView.setCellValueFactory(new PropertyValueFactory<>("TITULO"));
        directorView.setCellValueFactory(new PropertyValueFactory<>("DIRECTOR"));
        fecEstrenoView.setCellValueFactory(new PropertyValueFactory<>("FECHAESTRENO"));
        productoraView.setCellValueFactory(new PropertyValueFactory<>("PRODUCTORA"));
        cartelView.setCellValueFactory(new PropertyValueFactory<>("CARTEL"));
        categoriaView.setCellValueFactory(new PropertyValueFactory<>("CATEGORIA"));
        recaudacionView.setCellValueFactory(new PropertyValueFactory<>("RECAUDACION"));
        proyectadaView.setCellValueFactory(new PropertyValueFactory<>("PROYECTADA"));
        
        tablaPeliculasView.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
            peliculasSeleccionadas = newValue;
             if (peliculasSeleccionadas != null) {
                buscador.setText(peliculasSeleccionadas.getTitulo());
             }
             else {
                 buscador.setText("Pelicula no registrada.");
             }
        });
        
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
    Query queryDatospeliculasFindAll = entityManager.createNamedQuery("Datospeliculas.findAll");
    List<Datospeliculas> listPeliculas = queryDatospeliculasFindAll.getResultList();
    tablaPeliculasView.setItems(FXCollections.observableArrayList(listPeliculas));
    }

    @FXML
    private void clickPeliculas(MouseEvent event) {
    }

    @FXML
    private void clickCategorias(MouseEvent event) {
    }

    @FXML
    private void clickRegistros(MouseEvent event) {
    }
    
}
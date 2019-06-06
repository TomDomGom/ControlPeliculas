/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlpeliculas;

import controlpeliculas.entities.Datospeliculas;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
    private TableColumn<Datospeliculas, String> categoriaView;
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
    private TextField buscador;
    @FXML
    private AnchorPane rootPeliculasView;

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
        fecEstrenoView.setCellValueFactory(new PropertyValueFactory<>("fechaestreno"));
        productoraView.setCellValueFactory(new PropertyValueFactory<>("productora"));
        cartelView.setCellValueFactory(new PropertyValueFactory<>("cartel"));
        categoriaView.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        recaudacionView.setCellValueFactory(new PropertyValueFactory<>("recaudacion"));
        proyectadaView.setCellValueFactory(new PropertyValueFactory<>("proyectada"));
        categoriaView.setCellValueFactory(
            cellData -> {
                SimpleStringProperty property = new SimpleStringProperty();
                if (cellData.getValue().getCategoria()!= null) {
                    property.setValue(cellData.getValue().getCategoria().getNombre());
                }
                return property;
            });
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
    
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    // Creamos el metodo para crear la lista con todos los datos de las peliculas.
    public void cargarPeliculas() {
        Query queryDatospeliculasFindAll = entityManager.createNamedQuery("Datospeliculas.findAll");
        List<Datospeliculas> listPeliculas = queryDatospeliculasFindAll.getResultList();
        tablaPeliculasView.setItems(FXCollections.observableArrayList(listPeliculas));
    
    }
    // Creamos el evento del raton para que cuando hagamos click, nos lleve al formulario de registro.
    @FXML
    private void addPeliculaClick(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegistroView.fxml"));
            Parent rootRegistroView = fxmlLoader.load();
            
            rootPeliculasView.setVisible(false);
            
            StackPane rootMain = (StackPane)rootPeliculasView.getScene().getRoot();
            rootMain.getChildren().add(rootRegistroView);
            
            RegistroViewController registroViewController = (RegistroViewController) fxmlLoader.getController();
            registroViewController.setRootFormViewMain(rootPeliculasView);
            registroViewController.cargarForm(tablaPeliculasView);
            
            peliculasSeleccionadas = new Datospeliculas();
            registroViewController.setPelicula(entityManager, peliculasSeleccionadas, true);
            registroViewController.mostrarDatos();
                //Funciones que cargara para que interactue con la tabla 
            
        } catch (IOException ex) {
            Logger.getLogger(PeliculasViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /* Igual que con el evento de añadir, con la peculiaridad de que cuando cliquemos sobre una pelicula, 
    podamos seleccionar todos sus campos y pintarlos sobre el formulario para así editar. */
    @FXML
    private void editPeliculaClick(MouseEvent event) {
        if(peliculasSeleccionadas != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegistroView.fxml"));
                Parent rootDetalleView = fxmlLoader.load();
                
                rootPeliculasView.setVisible(false);
                
                StackPane rootMain = (StackPane)rootPeliculasView.getScene().getRoot();
                rootMain.getChildren().add(rootDetalleView);
                
                RegistroViewController registroViewController = (RegistroViewController) fxmlLoader.getController();
                registroViewController.setRootFormViewMain(rootPeliculasView);
                registroViewController.cargarForm(tablaPeliculasView);

                registroViewController.setPelicula(entityManager, peliculasSeleccionadas, false);
                registroViewController.mostrarDatos();
            } catch (IOException ex) {
                Logger.getLogger(RegistroViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // Mensaje de alerta en el caso que demos click en editar y no hayamos seleccionado ninguna pelicula.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atención");
            alert.setHeaderText("Selecciona un registro para editar");
            alert.showAndWait();
        }
    }
    // Evento para eliminar un registro de la base de datos seleccionado. con mensaje de confirmación.
    @FXML
    private void deletePeliculaClick(MouseEvent event) {
    if(peliculasSeleccionadas != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar");
            alert.setHeaderText("¿Desea eliminar la Película?");
            alert.setContentText(peliculasSeleccionadas.getTitulo()+ " "
                    + " De: " + peliculasSeleccionadas.getDirector());
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                entityManager.getTransaction().begin();
                entityManager.remove(peliculasSeleccionadas);
                entityManager.getTransaction().commit();
                tablaPeliculasView.getItems().remove(peliculasSeleccionadas);
                tablaPeliculasView.getFocusModel().focus(null);
                tablaPeliculasView.requestFocus();
            } else {
                int numFilaSeleccionada = tablaPeliculasView.getSelectionModel().getSelectedIndex();
                tablaPeliculasView.getItems().set(numFilaSeleccionada, peliculasSeleccionadas);
                TablePosition pos = new TablePosition(tablaPeliculasView, numFilaSeleccionada, null);
                tablaPeliculasView.getFocusModel().focus(pos);
                tablaPeliculasView.requestFocus();            
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atención");
            alert.setHeaderText("Selecciona un registro para eliminar");
            alert.showAndWait();
        }
    }
    // Buscador de registros dentro de la base de datos.
    @FXML
    private void buscarPeliculaClick(MouseEvent event) {
    }
}
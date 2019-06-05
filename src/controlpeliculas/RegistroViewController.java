/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlpeliculas;

import controlpeliculas.entities.Datospeliculas;
import controlpeliculas.entities.Datoscategoria;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.RollbackException;


/**
 * FXML Controller class
 *
 * @author Tomas
 */
public class RegistroViewController implements Initializable {
    
    private EntityManager entityManager;
    private Datospeliculas peliculas;
    private Datoscategoria categorias;
    private Pane rootPeliculasView;
    private TableView tableViewForm;
    private boolean nuevaPelicula;
    
    public static final String Carteles = "Carteles";
    
    @FXML
    private AnchorPane rootTregistroView;
    @FXML
    private Pane paneRegistroView;
    @FXML
    private GridPane gridPaneFormPelicula;
    @FXML
    private ComboBox<Datoscategoria> categoriaFormView;
    @FXML
    private CheckBox checkFormView;
    @FXML
    private DatePicker estrenoFormView;
    @FXML
    private ImageView imgCartelView;
    @FXML
    private Button addPeliculaFormView;
    @FXML
    private Button btnPeliculasView;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField tituloFormView;
    @FXML
    private TextField directorFormView;
    @FXML
    private TextField productoraFormView;
    @FXML
    private TextField calificacionFormView;
    @FXML
    private TextField recaudacionFormView;
    
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setRootFormViewMain (Pane rootPeliculasView) {
        this.rootPeliculasView = rootPeliculasView;
    }

    public void cargarForm (TableView tableViewForm){
       this.tableViewForm = tableViewForm;
    }
    
    public void setPelicula(EntityManager entityManager, Datospeliculas peliculas, boolean nuevaPelicula) {
        this.entityManager = entityManager;
        entityManager.getTransaction().begin();
        if (!nuevaPelicula) {
            this.peliculas = entityManager.find(Datospeliculas.class, peliculas.getId());
        } else {
            this.peliculas = peliculas;
        }
        
        this.nuevaPelicula = nuevaPelicula;
    }
    
     public void mostrarDatos() {
        tituloFormView.setText(peliculas.getTitulo());
        directorFormView.setText(peliculas.getDirector());
        productoraFormView.setText(peliculas.getProductora());
        calificacionFormView.setText(peliculas.getCalificacion());

        if (peliculas.getFechaestreno()!= null) {
            Date date = peliculas.getFechaestreno();
            Instant instant = date.toInstant();
            ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
            LocalDate localDate = zdt.toLocalDate();
            estrenoFormView.setValue(localDate);
        }
        if (peliculas.getRecaudacion()!= null) {
            recaudacionFormView.setText(peliculas.getRecaudacion().toString());
        }
        
        if (peliculas.getProyectada()!= null) {
            checkFormView.setSelected(peliculas.getProyectada());
        }

        Query queryDatosCategoriaFindAll = entityManager.createNamedQuery("Datoscategoria.findAll");
        List listCategoria = queryDatosCategoriaFindAll.getResultList();

        categoriaFormView.setItems(FXCollections.observableList(listCategoria));
        if (peliculas.getCategoria() != null) {
            categoriaFormView.setValue(peliculas.getCategoria());
        }
        categoriaFormView.setCellFactory((ListView<Datoscategoria> l) -> new ListCell<Datoscategoria>() {
            @Override
            protected void updateItem(Datoscategoria datoscategoria, boolean empty) {
                super.updateItem(datoscategoria, empty);
                if (datoscategoria == null || empty) {
                    setText("");
                } else {
                    setText(datoscategoria.getCodigo()+ " - " + datoscategoria.getNombre());
                }
            }
        });
        //Formato para el valor mostrado actualmente como seleccionado
        categoriaFormView.setConverter(new StringConverter<Datoscategoria>() {
            @Override
            public String toString(Datoscategoria datoscategoria) {
                if (datoscategoria == null) {
                    return null;
                } else {
                    return datoscategoria.getId() + "-" + datoscategoria.getNombre();
                }
            }

            @Override
            public Datoscategoria fromString(String userId) {
                return null;
            }
        });
        
        // Subir cartel.
        if (peliculas.getCartel() != null) {
            String imageFileName = peliculas.getCartel();
            File file = new File(Carteles + "/" + imageFileName);
            if (file.exists()) {
                Image image = new Image(file.toURI().toString());
                imgCartelView.setImage(image);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "No se encuentra el Cartel");
                alert.showAndWait();
            }
        }

    }

    @FXML
    private void addPeliculaClick(MouseEvent event) {
            int numFilaSeleccionada;
        boolean errorFormato = false;

        peliculas.setTitulo(tituloFormView.getText());
        peliculas.setDirector(directorFormView.getText());
        peliculas.setProductora(productoraFormView.getText());
        peliculas.setCalificacion(calificacionFormView.getText());

        if (!recaudacionFormView.getText().isEmpty()) {
            try {
                peliculas.setRecaudacion(BigDecimal.valueOf(Double.valueOf(recaudacionFormView.getText()).doubleValue()));
            } catch (NumberFormatException ex) {
                errorFormato = true;
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Salario no válido");
                alert.showAndWait();
                recaudacionFormView.requestFocus();
            }
        }

        peliculas.setProyectada(checkFormView.isSelected());

        if (estrenoFormView.getValue() != null) {
            LocalDate localDate = estrenoFormView.getValue();
            ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
            Instant instant = zonedDateTime.toInstant();
            Date date = Date.from(instant);
            peliculas.setFechaestreno(date);
        } else {
            peliculas.setFechaestreno(null);
        }

        if (categoriaFormView.getValue() != null) {
            peliculas.setCategoria(categoriaFormView.getValue());
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Debe indicar una provincia");
            alert.showAndWait();
            errorFormato = true;
        }

        if (!errorFormato) {
            try {
                if (nuevaPelicula) {
                    entityManager.persist(peliculas);
                } else {
                    entityManager.merge(peliculas);
                }
                entityManager.getTransaction().commit();

                StackPane rootMain = (StackPane) rootTregistroView.getScene().getRoot();
                rootMain.getChildren().remove(rootTregistroView);
                rootPeliculasView.setVisible(true);

                if (nuevaPelicula) {
                    tableViewForm.getItems().add(peliculas);
                    numFilaSeleccionada = tableViewForm.getItems().size() - 1;
                    tableViewForm.getSelectionModel().select(numFilaSeleccionada);
                    tableViewForm.scrollTo(numFilaSeleccionada);
                } else {
                    numFilaSeleccionada = tableViewForm.getSelectionModel().getSelectedIndex();
                    tableViewForm.getItems().set(numFilaSeleccionada, peliculas);
                }
                TablePosition pos = new TablePosition(tableViewForm, numFilaSeleccionada, null);
                tableViewForm.getFocusModel().focus(pos);
                tableViewForm.requestFocus();
            } catch (RollbackException ex) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("No se han podido guardar los cambios. "
                        + "Compruebe que los datos cumplen los requisitos");
                alert.setContentText(ex.getLocalizedMessage());
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void clickPeliculas(MouseEvent event) {
        //        entityManager.refresh(persona);
        entityManager.getTransaction().rollback();

        StackPane rootMain = (StackPane) rootTregistroView.getScene().getRoot();
        rootMain.getChildren().remove(rootTregistroView);

        rootPeliculasView.setVisible(true);

        int numFilaSeleccionada = tableViewForm.getSelectionModel().getSelectedIndex();
        TablePosition pos = new TablePosition(tableViewForm, numFilaSeleccionada, null);
        tableViewForm.getFocusModel().focus(pos);
        tableViewForm.requestFocus();
    }

    @FXML
    private void clickCancelar(MouseEvent event) {
    //        entityManager.refresh(persona);
        entityManager.getTransaction().rollback();

        StackPane rootMain = (StackPane) rootTregistroView.getScene().getRoot();
        rootMain.getChildren().remove(rootTregistroView);

        rootPeliculasView.setVisible(true);

        int numFilaSeleccionada = tableViewForm.getSelectionModel().getSelectedIndex();
        TablePosition pos = new TablePosition(tableViewForm, numFilaSeleccionada, null);
        tableViewForm.getFocusModel().focus(pos);
        tableViewForm.requestFocus();
    }
    
    @FXML
    private void onActionButtonExaminar(ActionEvent event) {
     File carpetaFotos = new File(Carteles);
        if (!carpetaFotos.exists()) {
            carpetaFotos.mkdir();
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes (jpg, png)", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("Todos los archivos", "*.*")
        );
        File file = fileChooser.showOpenDialog(rootTregistroView.getScene().getWindow());
        if (file != null) {
            try {
                Files.copy(file.toPath(), new File(Carteles + "/" + file.getName()).toPath());
                peliculas.setCartel(file.getName());
                Image image = new Image(file.toURI().toString());
                imgCartelView.setImage(image);
            } catch (FileAlreadyExistsException ex) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Nombre de archivo duplicado");
                alert.showAndWait();
            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "No se ha podido guardar la imagen");
                alert.showAndWait();
            }
        }
    }
    
    @FXML
    private void onActionSuprimirFoto(ActionEvent event) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar supresión de imagen");
        alert.setHeaderText("¿Desea SUPRIMIR el archivo asociado a la imagen, \n"
                + "quitar la foto pero MANTENER el archivo, \no CANCELAR la operación?");
        alert.setContentText("Elija la opción deseada:");

        ButtonType buttonTypeEliminar = new ButtonType("Suprimir");
        ButtonType buttonTypeMantener = new ButtonType("Mantener");
        ButtonType buttonTypeCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeEliminar, buttonTypeMantener, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeEliminar) {
            String imageFileName = peliculas.getCartel();
            File file = new File(Carteles + "/" + imageFileName);
            if (file.exists()) {
                file.delete();
            }
            peliculas.setCartel(null);
            imgCartelView.setImage(null);
        } else if (result.get() == buttonTypeMantener) {
            peliculas.setCartel(null);
            imgCartelView.setImage(null);
        }
    }

}

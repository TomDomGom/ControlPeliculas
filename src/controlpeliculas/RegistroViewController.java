/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlpeliculas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Tomas
 */
public class RegistroViewController implements Initializable {

    @FXML
    private Pane paneRegistroView;
    @FXML
    private CheckBox checkFormView;
    @FXML
    private DatePicker estrenoFormView;
    @FXML
    private Button addPeliculaFormView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addPeliculaClick(MouseEvent event) {
    }

    
}

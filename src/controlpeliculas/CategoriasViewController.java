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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Tomas
 */
public class CategoriasViewController implements Initializable {

    @FXML
    private TableColumn<?, ?> idCatView;
    @FXML
    private TableColumn<?, ?> nombreCatView;
    @FXML
    private TableColumn<?, ?> descCatView;
    @FXML
    private TextArea descripCatView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addCatClick(MouseEvent event) {
    }
    
}

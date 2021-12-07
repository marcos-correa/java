/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controles;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author dinha
 */
public class TelaPrincipalController implements Initializable {

    @FXML
    private MenuItem mnTab;
    @FXML
    private MenuItem mnGraf;
    @FXML
    private MenuItem mnImp;
    @FXML
    private MenuItem mnGst;
    @FXML
    private MenuItem mnTpGst;
    @FXML
    private MenuItem mnCrt;
    @FXML
    private AnchorPane scImg;
    @FXML
    private AnchorPane scCont;
    @FXML
    private BorderPane scTodo;
    @FXML
    private HBox scMeio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void abrirTab(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/telas/TelaTabelaPrincipal.fxml"));   
            
        Parent parent = fxmlLoader.load(); 
        scTodo.setCenter(parent);
    }

    @FXML
    private void abrirGraf(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/telas/TelaGrafico.fxml"));   
            
        Parent parent = fxmlLoader.load(); 
        scTodo.setCenter(parent);
    }

    @FXML
    private void imprimir(ActionEvent event) {
    }

    @FXML
    private void alterarGasto(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/telas/TelaGasto.fxml"));   
            
        Parent parent = fxmlLoader.load(); 
        scTodo.setCenter(parent);
    }

    @FXML
    private void alterarTipoGasto(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/telas/TelaTipoGasto.fxml"));   
            
        Parent parent = fxmlLoader.load(); 
        scTodo.setCenter(parent);
    }

    @FXML
    private void alterarCartao(ActionEvent event) {
    }
    
}

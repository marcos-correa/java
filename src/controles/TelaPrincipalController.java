/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controles;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.JobSettings;
import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

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
    private MenuItem mnGst;
    @FXML
    private MenuItem mnTpGst;
    @FXML
    private MenuItem mnCrt;
    private AnchorPane scImg;
    @FXML
    private AnchorPane scCont;
    @FXML
    private BorderPane scTodo;
    private Label txPrincipal;
    @FXML
    private Button btnInicial;
    @FXML
    private Label mensagem;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            entrarHome();
        } catch (IOException ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            mensagem.setText("Erro do sistema.");
        }
    }    

    private void entrarHome() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/telas/TelaHome.fxml"));   
            
        Parent parent = fxmlLoader.load(); 
        scTodo.setCenter(parent);
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
    private void alterarCartao(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/telas/TelaCartao.fxml"));   
            
        Parent parent = fxmlLoader.load(); 
        scTodo.setCenter(parent);
    }

    @FXML
    private void voltarInicial(ActionEvent event) throws IOException {
        entrarHome();
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controles;

import bancoDados.Gasto;
import bancoDados.TipoGasto;
import classesDao.GastoDao;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author dinha
 */
public class TelaTabelaPrincipalController implements Initializable {

    @FXML
    private AnchorPane scTabela;
    @FXML
    private TableView<Gasto> tabela;
    @FXML
    private TableColumn<Gasto, Date> colData;
    @FXML
    private TableColumn<Gasto, TipoGasto> colGasto;
    @FXML
    private TableColumn<Gasto, Float> colValor;
    @FXML
    private TableColumn<Gasto, String> colPag;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        List<Gasto> lista = new GastoDao().listar();
        //SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
           
        colData.setCellValueFactory(new PropertyValueFactory("data"));
        colGasto.setCellValueFactory(new PropertyValueFactory("idTipoGasto"));
        colPag.setCellValueFactory(new PropertyValueFactory("idFormaPagamento"));
        colValor.setCellValueFactory(new PropertyValueFactory("valor"));
        
       
        tabela.setItems(FXCollections.observableArrayList(lista));
    }    
    
}

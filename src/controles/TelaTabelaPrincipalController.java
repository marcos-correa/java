/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controles;

import bancoDados.Gasto;
import bancoDados.TipoGasto;
import classesDao.GastoDao;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private TableColumn<Gasto, String> colData;
    @FXML
    private TableColumn<Gasto, TipoGasto> colGasto;
    @FXML
    private TableColumn<Gasto, Float> colValor;
    @FXML
    private TableColumn<Gasto, String> colPag;
    @FXML
    private Button btnImprimir;
    @FXML
    private Label lbImprimir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        List<Gasto> lista = new GastoDao().listar();
        //SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
           
        colData.setCellValueFactory(new PropertyValueFactory("DataString"));//
        colGasto.setCellValueFactory(new PropertyValueFactory("idTipoGasto"));
        colPag.setCellValueFactory(new PropertyValueFactory("idFormaPagamento"));
        colValor.setCellValueFactory(new PropertyValueFactory("valor"));
        
       
        tabela.setItems(FXCollections.observableArrayList(lista));
    }    

    @FXML
    private void imprimir(ActionEvent event) {
        impressao(tabela);
    }
    
    
    private void impressao(Node node){
        
        lbImprimir.textProperty().unbind();
        lbImprimir.setText("Criando o printer job...");

        PrinterJob job = PrinterJob.createPrinterJob();
        job.showPageSetupDialog(null);
        if (job != null) {
            lbImprimir.textProperty().bind(job.jobStatusProperty().asString());
            boolean printed = job.printPage(node);
            if (printed) {
                job.endJob();
            }
            else {
            lbImprimir.textProperty().unbind();
            lbImprimir.setText("A impressão falhou.");
            }
         }
        else {
      lbImprimir.setText("Não foi possível criar o printer job.");
    }
    }}

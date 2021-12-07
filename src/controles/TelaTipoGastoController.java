/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controles;

import bancoDados.TipoGasto;
import classesDao.TipoGastoDao;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import jpaControles.exceptions.IllegalOrphanException;
import jpaControles.exceptions.NonexistentEntityException;

/**
 * FXML Controller class
 *
 * @author dinha
 */
public class TelaTipoGastoController implements Initializable {

    @FXML
    private BorderPane scTpGst;
    @FXML
    private Button btVslTudo;
    @FXML
    private TextField txTpGst;
    @FXML
    private ToggleButton btCnt;
    @FXML
    private ToggleButton btNv;
    @FXML
    private HBox scCtn;
    @FXML
    private AnchorPane scTab;
    @FXML
    private AnchorPane scCad;
    @FXML
    private Label lbCdg;
    @FXML
    private Label lbDsc;
    @FXML
    private TextField txCdg;
    @FXML
    private TextField txDsc;
    @FXML
    private Button btGrv;
    @FXML
    private Button btExc;
    @FXML
    private Button btSair;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void VisualizarTudo(ActionEvent event) {
    }

    @FXML
    private void consultar(ActionEvent event) {
        try {
            //pegar código do textfield  e enviar para o método de consulta do banco
            TipoGasto tipo = (TipoGasto) new TipoGastoDao().consultar(Integer.parseInt(txTpGst.getText()));
            //se existir copiar dados do banco para tela e exibir painel
     
            
            txCdg.setText(String.valueOf(tipo.getIdTipoGasto()));
            txDsc.setText(tipo.getDescricaoTipo());
            scCad.setVisible(true);
        }catch (NonexistentEntityException ex) {
             System.out.println(ex.getMessage());
        }
         catch (NumberFormatException e) {
            System.out.println("Código deve ser um número inteiro " + txTpGst.getText());
        } 
    }

    @FXML
    private void gravar(ActionEvent event) {
        try {
            TipoGasto tipo = new TipoGasto();
            
            tipo.setIdTipoGasto(Integer.parseInt(txCdg.getText()));
            tipo.setDescricaoTipo(txDsc.getText());
            if (btNv.isSelected()) {
                try {
                    new TipoGastoDao().inserir(tipo);
                    System.out.println("Inserção concluída com sucesso!!");
                } catch (Exception ex) {
                    System.out.println("Erro na inserção: " + ex.getMessage());
                }
            } else {
                try {
                    new TipoGastoDao().editar(tipo);
                } catch (Exception ex) {
                    System.out.println("Erro na alteração: " + ex.getMessage());
                }
            }
        } catch (Exception ex) {
           System.out.println("Formato de entrada incorreto: " + ex.getMessage());
        }
        
    }

    @FXML
    private void excluir(ActionEvent event) {
        try {
            new TipoGastoDao().excluir(Integer.parseInt(txCdg.getText()));
            System.out.println("Exclusão com sucesso");
        } catch (NonexistentEntityException ex) {
            System.out.println("Erro na exclusão: " + ex.getMessage());
        } catch (IllegalOrphanException ex) {
           System.out.println("Erro na exclusão: " + ex.getMessage());
        }
    }

    @FXML
    private void sair(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void inserir(ActionEvent event) {
        scCad.setVisible(true);
    }
    
}

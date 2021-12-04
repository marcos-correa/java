/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controles;

import java.util.Date;
import bancoDados.Cartao;
import bancoDados.FormasPagamento;
import bancoDados.Gasto;
import bancoDados.TipoGasto;
import classesDao.CartaoDao;
import classesDao.GastoDao;
import classesDao.TipoGastoDao;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.converter.StringConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
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
public class TelaGastoController implements Initializable {

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
    private Button btGrv;
    @FXML
    private Button btExc;
    @FXML
    private Button btSair;
    @FXML
    private ComboBox <TipoGasto> cbBoxGt;
    @FXML
    private TextField txValor;
    @FXML
    private DatePicker cxData;
    @FXML
    private ComboBox <FormasPagamento> chBoxPgt;
   
    @FXML
    private TextField txCrt;
    
    private TipoGasto opTipoGasto;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<TipoGasto> lista = new TipoGastoDao().listar();
        cbBoxGt.setItems(FXCollections.observableArrayList(lista));
        
        FormasPagamento[] list = FormasPagamento.values();
        chBoxPgt.setItems( FXCollections.observableArrayList(list));
        
    }    

    @FXML
    private void VisualizarTudo(ActionEvent event) {
    }

    @FXML
    private void consultar(ActionEvent event) {
        try {
            //pegar código do textfield  e enviar para o método de consulta do banco
            Gasto gasto = (Gasto) new GastoDao().consultar(Integer.parseInt(txTpGst.getText()));
            //se existir copiar dados do banco para tela e exibir painel
     
            
            txCdg.setText((String.valueOf(gasto.getIdGasto()))); //String.valueOf
            
            cbBoxGt.getSelectionModel().select(gasto.getTipoGasto());
            
            Date data = gasto.getData();      
            cxData.setValue(data.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
            
            txValor.setText(String.valueOf(gasto.getValor()));
            
            chBoxPgt.getSelectionModel().select(FormasPagamento.valueOf(gasto.getIdFormaPagamento().toUpperCase()));
            
            
            if(gasto.getIdFormaPagamento().equals(FormasPagamento.CARTAO)){
                txCrt.setText((gasto.getIdCartao().getIdCartao()));
            }
            
           
            scCad.setVisible(true);
        }catch (NonexistentEntityException ex) {
             System.out.println(ex.getMessage());
        }
         catch (NumberFormatException e) {
            System.out.println("Código deve ser um número inteiro " + txTpGst.getText());
        } 
    }

    @FXML
    private void inserir(ActionEvent event) {
        scCad.setVisible(true);
        
    }

    @FXML
    private void gravar(ActionEvent event) {
        
    
        try {
            Gasto gasto = new Gasto();
           
            gasto.setIdGasto(Integer.parseInt(txCdg.getText()));
            gasto.setTipoGasto(opTipoGasto);
            gasto.setValor((float) Double.parseDouble(txValor.getText()));
            LocalDate ld = cxData.getValue();
            Calendar c =  Calendar.getInstance();
            c.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
            Date date = c.getTime();
            gasto.setData(date);
            gasto.setIdFormaPagamento(chBoxPgt.getSelectionModel().getSelectedItem().getDescricao());
            System.out.println(chBoxPgt.getSelectionModel().getSelectedItem().getDescricao());
  
             if(chBoxPgt.getSelectionModel().getSelectedItem().equals(FormasPagamento.CARTAO)){
    
                Cartao cartao = (Cartao) new CartaoDao().consultar(txCrt.getText());
                gasto.setIdCartao(cartao);
            }
            
            
            if (btNv.isSelected()) {
                try {
                    new GastoDao().inserir(gasto);
                    System.out.println("Inserção concluída com sucesso!!");
                } catch (Exception ex) {
                    System.out.println("Erro na inserção: " + ex.getMessage());
                }
            } else {
                try {
                    new GastoDao().editar(gasto);
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
            new GastoDao().excluir(Integer.parseInt(txCdg.getText()));
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
    private void selecionaTipoGasto(ActionEvent event) {
        opTipoGasto = cbBoxGt.getSelectionModel().getSelectedItem();
    }
    

    @FXML
    private void selecionaPagamento(ActionEvent event) {
        if(chBoxPgt.getSelectionModel().getSelectedItem().equals(FormasPagamento.CARTAO)){
                txCrt.setEditable(true);
                System.out.println("entrou");
                
            }
    }

    @FXML
    private void clickLbCartao(ActionEvent event) {
        
    }
    
}

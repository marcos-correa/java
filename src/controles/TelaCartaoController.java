/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controles;

import bancoDados.Bandeira;
import bancoDados.Cartao;
<<<<<<< HEAD
<<<<<<< HEAD
import bancoDados.FormasPagamento;
import classesDao.CartaoDao;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
=======
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
>>>>>>> 12bbfe8 (Tela cartão e mudanças)
=======
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
>>>>>>> 12bbfe8927e49f2b2b60d0acfe300a84bafdcad8
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
<<<<<<< HEAD
<<<<<<< HEAD
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import jpaControles.exceptions.IllegalOrphanException;
import jpaControles.exceptions.NonexistentEntityException;
=======
import javafx.scene.control.TextField;
>>>>>>> 12bbfe8 (Tela cartão e mudanças)
=======
import javafx.scene.control.TextField;
>>>>>>> 12bbfe8927e49f2b2b60d0acfe300a84bafdcad8

/**
 * FXML Controller class
 *
 * @author marcos
 */
public class TelaCartaoController implements Initializable {

    @FXML
    private TextField nmrCrt;
   
    @FXML
    private TextField nmCrt;

    @FXML
    private DatePicker dtVldCrt;

    @FXML
<<<<<<< HEAD
    private TextField cnsCrt;
    
    @FXML
    private ToggleButton btCnsltr;
     
    @FXML
    private ToggleButton btNv;
    
    @FXML
    private ComboBox<Bandeira> bdrCrt;

    Bandeira[] listBandeira;
    @FXML
    private Label msgCrt;
    @FXML
    private Label msgCrtSuccess;
    @FXML
    private AnchorPane scCartao;
   
=======
    private ComboBox<Bandeira> bdrCartao;
>>>>>>> 12bbfe8927e49f2b2b60d0acfe300a84bafdcad8

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
<<<<<<< HEAD
        listBandeira = Bandeira.values();
        bdrCrt.setItems( FXCollections.observableArrayList(listBandeira));
        msgCrt.setStyle("");
=======
        Bandeira[] list = Bandeira.values();
        bdrCartao.setItems( FXCollections.observableArrayList( list));
        System.out.println("iu");
>>>>>>> 12bbfe8927e49f2b2b60d0acfe300a84bafdcad8
    }    

    @FXML
    private void VisualizarTudo(ActionEvent event) {
    }

    @FXML
    private void consultar(ActionEvent event) {
<<<<<<< HEAD
        resetMessage();
        try{
            Cartao cartao = (Cartao) new CartaoDao().consultar(cnsCrt.getText());
            
            nmrCrt.setText((String.valueOf(cartao.getIdCartao())));
            nmCrt.setText(String.valueOf(cartao.getNome()));
            Date data = cartao.getDataValidade();
            dtVldCrt.setValue(data.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
            //bdrCrt.getSelectionModel().select(Bandeira.valueOf(cartao.getBandeira()).ordinal());
            bdrCrt.getSelectionModel().select(Bandeira.valueOf(cartao.getBandeira().toUpperCase()));
            scCartao.setVisible(true);
        }catch (NonexistentEntityException ex) {
             System.out.println(ex.getMessage());
             msgCrt.setText("Código não encontrado na base " + nmrCrt.getText());
        }
         catch (NumberFormatException e) {
            msgCrt.setText("Código deve ser um número inteiro " + nmrCrt.getText());
        }    
    }

    @FXML
    private void inserir(ActionEvent event) {
        scCartao.setVisible(true);
    }

    @FXML
    private void gravar(ActionEvent event)throws AlphaNumException {
        resetMessage();
        try {
            Cartao cartao = new Cartao();

            // Bandeira
            String bandeira = bdrCrt.getValue().getDescricao();
            cartao.setBandeira(bandeira);

            // Validade
            LocalDate dataLocal = dtVldCrt.getValue();
            Date dataValidade = Date.from(dataLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
            cartao.setDataValidade(dataValidade);

            // Nome
            cartao.setNome(nmCrt.getText());

            // Número
            String idCartao = nmrCrt.getText();
            boolean idCartaoIsNumeric =  idCartao.matches("[+-]?\\d*(\\.\\d+)?");
            
            if(!idCartaoIsNumeric){
                throw new AlphaNumException(idCartao);
            }
           
            
         
            cartao.setIdCartao(idCartao);


            if (btNv.isSelected()) {
                try {
                    new CartaoDao().inserir(cartao);
                     msgCrtSuccess.setText("Inserção concluída com sucesso!!");
                } catch (Exception ex) {
                     msgCrt.setText("Erro na inserção do cartão: " + ex.getMessage());
                }
            } else {
                try {
                    new CartaoDao().editar(cartao);
                } catch (Exception ex) {
                    msgCrt.setText("Erro na alteração do cartão: " + ex.getMessage());
                }
            }
        }catch (AlphaNumException ex){
                    msgCrt.setText(ex.getMessage());
     
        } catch (Exception  ex) {            
            System.out.println("Formato de entrada incorreto: " + ex.getMessage());
        }
    }

    @FXML
    private void excluir(ActionEvent event) throws IllegalOrphanException {
        resetMessage();
         try {
            new CartaoDao().excluir(nmrCrt.getText());
             msgCrtSuccess.setText("Exclusão com sucesso");
        } catch (NonexistentEntityException ex) {
             msgCrt.setText("Erro na exclusão: " + ex.getMessage());
        } catch (IllegalOrphanException ex) {
             msgCrt.setText("Erro na exclusão: " + ex.getMessage());
        }
=======
//        bdrCartao.getSelectionModel().select(2);
//        System.out.println("selecionado? ->"+bdrCartao.getSelectionModel());
    }

//    getIndexOfList(String str){
//        for
//                
//            return 1
//    }

    @FXML
    private void inserir(ActionEvent event) {
    }

    @FXML
    private void gravar(ActionEvent event) {
        
        Cartao cartao = new Cartao();

        cartao.setBandeira(bdrCartao.getValue().getDescricao());
        String bandeira = bdrCartao.getValue().getDescricao();
//        try {
//            Cartao cartao = new Cartao();
//            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//            bandeira = testarEnumBandeira(bdCrt.getValue());
//            cartao.setBandeira(bandeira);
//            cartao.setDataValidade(formato.parse(txValidade.getText()));
//            cartao.setNome(txNome.getText());
//            if (btNovo.isSelected()) {
//                try {
//                    new CartaoDao().inserir(cartao);
//                    System.out.println("Inserção concluída com sucesso!!");
//                } catch (Exception ex) {
//                    System.out.println("Erro na inserção: " + ex.getMessage());
//                }
//            } else {
//                try {
//                    new CartaoDao().editar(cartao);
//                } catch (Exception ex) {
//                    System.out.println("Erro na alteração: " + ex.getMessage());
//                }
//            }
//        } catch (ParseException  ex) {
//            // chamar uma tela
//           
//            //apresentar o erro
//            
//            // ok de fechar
//
//            
//            System.out.println("Formato de entrada incorreto: " + ex.getMessage());
//        } catch (Exception ex) {
//           System.out.println("Formato de entrada incorreto: " + ex.getMessage());
//        }
    }

    @FXML
    private void excluir(ActionEvent event) {
>>>>>>> 12bbfe8927e49f2b2b60d0acfe300a84bafdcad8
    }

    @FXML
    private void sair(ActionEvent event) {
<<<<<<< HEAD
        resetMessage();
         Platform.exit();
=======
>>>>>>> 12bbfe8927e49f2b2b60d0acfe300a84bafdcad8
    }

    @FXML
    private void setDateCard(ActionEvent event) {
        
    }

    @FXML
    private void setBandeira(ActionEvent event) {
<<<<<<< HEAD
        bdrCrt.getValue();       
    }
    
    private void resetMessage(){
        msgCrt.setText("");
        msgCrtSuccess.setText("");
    }
    
    
=======
        bdrCartao.getValue();       
    }
    
>>>>>>> 12bbfe8927e49f2b2b60d0acfe300a84bafdcad8
}

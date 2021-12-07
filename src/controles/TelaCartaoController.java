/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controles;

import bancoDados.Bandeira;
import bancoDados.Cartao;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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
    private ComboBox<Bandeira> bdrCartao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Bandeira[] list = Bandeira.values();
        bdrCartao.setItems( FXCollections.observableArrayList( list));
        System.out.println("iu");
    }    

    @FXML
    private void VisualizarTudo(ActionEvent event) {
    }

    @FXML
    private void consultar(ActionEvent event) {
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
    }

    @FXML
    private void sair(ActionEvent event) {
    }

    @FXML
    private void setDateCard(ActionEvent event) {
        
    }

    @FXML
    private void setBandeira(ActionEvent event) {
        bdrCartao.getValue();       
    }
    
}

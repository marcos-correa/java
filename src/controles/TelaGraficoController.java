/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controles;

import bancoDados.Gasto;
import classesDao.GastoDao;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author dinha
 */
public class TelaGraficoController implements Initializable {
    private ObservableList<GastoDia> diario;
    @FXML
    private LineChart grafLinha;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         List<Gasto> lista = new GastoDao().listar();
         lista.sort((o1,o2) -> o1.getData().compareTo(o2.getData()));
         tratarDados(lista);
         
         
         final CategoryAxis xAxis = new CategoryAxis();
         final NumberAxis yAxis = new NumberAxis();
         xAxis.setLabel("Dia");
         XYChart.Series series = new XYChart.Series();
         series.setName("Gasto diário");
       
        for(GastoDia objeto: diario){
           series.getData().add(new XYChart.Data(objeto.getData(), objeto.getValor()));
       }
       
        grafLinha.getData().add(series); 
    }    
    
     public void tratarDados( List<Gasto> lista){
        diario =  FXCollections.observableArrayList();
        
        for(Gasto objeto:lista){
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String data = dateFormat.format(objeto.getData());
            GastoDia vd =  new GastoDia(data,objeto.getValor());
            Integer index = vd.iguais(diario);
            if (index != -1)
            {
                System.out.println(data);
                diario.get(index).setValorAcumulado(objeto.getValor());
            }
            else{
               
                diario.add(vd);
            }
            
        }
  
     }
        
        public class GastoDia{
            private String data;
            private Float valor;
        
        public GastoDia(String data,Float valor ){
            this.data = data;
            this.valor = valor;
        }

        @Override
        public String toString() {
            return data + " - " + valor;
        }

        public void setData(String data) {
            this.data = data;
        }

        public void setValor(Float valor) {
            this.valor = valor;
        }
        
        public void setValorAcumulado(Float valorAdicional) {
            this.valor = this.valor + valorAdicional;
        }

        public String getData() {
            return data;
        }

        public Float getValor() {
            return valor;
        }
        
        public Integer iguais(ObservableList<GastoDia> list) {
            for(GastoDia object: list){
                if ((this.data.equals(object.data))) {
                    return list.indexOf(object);
            }
            }
            
            return -1;
    }

       
    }
        

}

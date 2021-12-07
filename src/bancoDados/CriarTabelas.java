/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancoDados;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
/**
 *
 * @author dinha
 */
public class CriarTabelas {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/GastosDiarios";
        String senha = "root";
        String user = "root";
        try{
        
           BufferedReader arq = new BufferedReader(new FileReader("gasto.txt")); 
           Connection conexao = DriverManager.getConnection(url, user, senha);
           Statement sessao = conexao.createStatement();
         
          
           String linha;
           String sql="";
            while((linha = arq.readLine())!= null){
                sql = sql + linha;
                if(linha.contains(";")){
                   //System.out.println(sql);
                   //System.out.println(sql.getClass().getSimpleName());
                   try{
                        sessao.executeUpdate(sql);
                        System.out.println("Tabela criada");
                   }catch(SQLException e){
                        System.out.println("Erro para gerar tabela: " + e.getMessage());
                    }
                   
                   sql="";
                }
             }
            
            
           
        }catch(IOException | SQLException e){
            System.out.println("Erro " + e.getMessage());
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancoDados;
import java.sql.*;

/**
 *
 * @author dinha
 */
public class CriarBase {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost";
        String senha = "clarindo";
        String user = "root";
        String baseBD = "GastosDiarios";
        String sql = "CREATE DATABASE " + baseBD;
        try {
            Connection conexao = DriverManager.getConnection(url, user, senha);
            Statement sessao = conexao.createStatement();
            sessao.executeUpdate(sql);
            System.out.println("Database " + baseBD + " criado com successo.");
        } catch (SQLException e) {
            System.out.println("Não foi possível gerar a base pelo seguinte erro: " + e);
        }}
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancoDados;

/**
 *
 * @author dinha
 */
public enum Bandeira {
    MASTERCARD("mastercard"),
    VISA ("visa"),
    AMEX ("amex");
    
    private String descricao;
    
    Bandeira(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
}

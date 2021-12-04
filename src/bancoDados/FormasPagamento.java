/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancoDados;

/**
 *
 * @author dinha
 */
public enum FormasPagamento {
    DINHEIRO("dinheiro"),
    CHEQUE ("cheque"),
    CARTAO ("cartao"),
    CHEQUEPRE ("chequepre");
    
    private String descricao;
    
    FormasPagamento(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
    
    
    
}

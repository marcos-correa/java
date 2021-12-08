/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controles;

/**
 *
 * @author dinha
 */
public class AlphaNumException extends Exception{
    private String numeroCartao;
    public AlphaNumException(String numeroCartao) {
		this.numeroCartao = numeroCartao;
    }
    public String getMessage() {
	return "O número de cartão " + numeroCartao + " não é válido";
    }
    
}

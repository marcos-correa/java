/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classesDao;

import jpaControles.exceptions.IllegalOrphanException;
import jpaControles.exceptions.NonexistentEntityException;
import bancoDados.Cartao;// importar a class
import jpaControles.CartaoJpaController;

/**
 *
 * @author dinha
 */
public class CartaoDao extends ClasseDaoAbstract{
    CartaoJpaController objetoJPA;
    
    public CartaoDao(){
         objetoJPA = new CartaoJpaController(emf);
    }
    @Override
    public void inserir(Object objeto) throws Exception {
         objetoJPA.create((Cartao) objeto);
    }

    @Override
    public void editar(Object objeto) throws Exception {
        objetoJPA.edit((Cartao)objeto);
    }

    
    public void excluir(String chave) throws NonexistentEntityException, IllegalOrphanException {
        objetoJPA.destroy(chave);
    }

    
    public Object consultar(String chave) throws NonexistentEntityException {
        Cartao objeto = objetoJPA.findCartao(chave);
        if(objeto == null){
            throw new NonexistentEntityException("Cartão "+chave+" não cadastrado");
        }
        return objeto;
    }
   
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classesDao;

import bancoDados.Gasto;
import jpaControles.GastoJpaController;
import jpaControles.exceptions.IllegalOrphanException;
import jpaControles.exceptions.NonexistentEntityException;

/**
 *
 * @author dinha
 */
public class GastoDao extends ClasseDaoAbstract{
    
    GastoJpaController objetoJPA;
    
    public GastoDao(){
         objetoJPA = new GastoJpaController(emf);
    }

    @Override
    public void inserir(Object objeto) throws Exception {
        objetoJPA.create((Gasto) objeto);
    }

    @Override
    public void editar(Object objeto) throws Exception {
        objetoJPA.edit((Gasto) objeto);
    }
    
    public void excluir(Integer chave) throws NonexistentEntityException, IllegalOrphanException {
        objetoJPA.destroy(chave);
    }

    
    public Object consultar(Integer chave) throws NonexistentEntityException {
        Gasto objeto = objetoJPA.findGasto(chave);
        if(objeto == null){
            throw new NonexistentEntityException("Gasto "+chave+" não cadastrado");
        }
        return objeto;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classesDao;

import bancoDados.TipoGasto;
import jpaControles.TipoGastoJpaController;
import jpaControles.exceptions.IllegalOrphanException;
import jpaControles.exceptions.NonexistentEntityException;

/**
 *
 * @author dinha
 */
public class TipoGastoDao extends ClasseDaoAbstract{
    
    TipoGastoJpaController objetoJPA;
    
    public TipoGastoDao(){
         objetoJPA = new TipoGastoJpaController(emf);
    }

    @Override
    public void inserir(Object objeto) throws Exception {
        objetoJPA.create((TipoGasto) objeto);
    }

    @Override
    public void editar(Object objeto) throws Exception {
        objetoJPA.edit((TipoGasto) objeto);
    }
    
    public void excluir(Integer chave) throws NonexistentEntityException, IllegalOrphanException {
        objetoJPA.destroy(chave);
    }

    
    public Object consultar(Integer chave) throws NonexistentEntityException {
        TipoGasto objeto = objetoJPA.findTipoGasto(chave);
        if(objeto == null){
            throw new NonexistentEntityException("Tipo de Gasto "+chave+" não cadastrado");
        }
        return objeto;
    }
}

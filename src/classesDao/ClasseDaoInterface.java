/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classesDao;

import jpaControles.exceptions.IllegalOrphanException;
import jpaControles.exceptions.NonexistentEntityException;




/**
 *
 * @author dinha
 */
public interface ClasseDaoInterface <T1>{
    public void inserir(T1 objeto)throws Exception;
    public void editar(T1 objeto)throws Exception;
    //public void excluir(Integer chave)throws NonexistentEntityException, IllegalOrphanException;
    //public T1 consultar(Integer chave)throws NonexistentEntityException;
    
}

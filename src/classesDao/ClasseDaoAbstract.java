/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classesDao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpaControles.CartaoJpaController;
import jpaControles.exceptions.IllegalOrphanException;
import jpaControles.exceptions.NonexistentEntityException;

/**
 *
 * @author dinha
 */
public abstract class ClasseDaoAbstract implements ClasseDaoInterface{
    EntityManagerFactory emf;
    //T2 objetoJPA;
    
    public ClasseDaoAbstract(){
        emf = Persistence.createEntityManagerFactory("GastosDiariosPU");//nome da persistence.xml
        //objetoJPA = (T2) new CartaoJpaController(emf);
    }
    
   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jsf;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author utfpr
 */
@ManagedBean
public class JsfUsuario {

    /**
     * Creates a new instance of JsfUsuario
     */
    public JsfUsuario() {
    }
    
    public Collection<br.model.Usuario> getAll(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CarentPU");
        
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Usuario.findAll").getResultList();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);

            return null;
        } finally {
           
            em.close();
        }
    }
}

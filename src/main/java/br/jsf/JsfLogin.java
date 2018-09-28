/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jsf;

import br.dao.DaoGenerico;
import br.dao.UsuarioDAO;
import br.model.Usuario;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author andre
 */
@ManagedBean(eager = true)
@SessionScoped
public class JsfLogin {

    private String email;
    private String senha;

    private Usuario us;

    public JsfLogin() {
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Usuario getUs() {
        return us;
    }

    public void logar() {
        List<br.model.Usuario> lst = getUsuario();

        if (lst.size() > 0) {
            us = lst.get(0);
        } else {
            us = null;
        }

    }

    public List<br.model.Usuario> getUsuario() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CarentPU");
        EntityManager em = emf.createEntityManager();
        try {
            List<br.model.Usuario> lst = em.createNamedQuery("Usuario.findByEmailSenha").setParameter("email", email).setParameter("senha", senha).getResultList();
            return lst;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);

            return null;
        } finally {
            em.close();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jsf;

import br.dao.DaoGenerico;
import br.model.Usuario;
import br.model.Usuario2;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author andre
 */
@ManagedBean()
@RequestScoped
public class JsfEditarConta {

    private Usuario2 usuario;

    /**
     * Creates a new instance of JsfEditarConta
     */
    public JsfEditarConta() {
        HttpSession session = SessionUtils.getSession();
        if (session.getAttribute("user") != null) {
            usuario = (Usuario2) session.getAttribute("user");
        }
    }

    public Usuario2 getUsuario() {
        return this.usuario;
    }

    public String editar() {
        DaoGenerico<Usuario2> dao = new DaoGenerico<Usuario2>();
        dao.saveOrUpdate(usuario);
        return "/editar-conta.xhtml?faces-redirect=true";
    }

}

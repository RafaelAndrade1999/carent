/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jsf;

import br.dao.DaoGenerico;
import br.model.Usuario2;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.CloseEvent;

@ManagedBean
public class DialogView {

    public void remover() {
        try {
            HttpSession session = SessionUtils.getSession();
            DaoGenerico<Usuario2> dao = new DaoGenerico<Usuario2>();
            Usuario2 us = (Usuario2) session.getAttribute("user");
            dao.remove(Usuario2.class, us.getId());
            session.invalidate();
        } catch (Exception err) {
            addMessage("System Error", "Please try again later. " + err.getMessage());
        }

    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}

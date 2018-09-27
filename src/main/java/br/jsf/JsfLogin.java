/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jsf;

import br.dao.DaoGenerico;
import br.dao.UsuarioDAO;
import br.model.Usuario;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author andre
 */
@ManagedBean(eager = true)
@SessionScoped
public class JsfLogin {

    private String email;
    private String senha;

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

    public void logar() {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario us =  dao.getUsuario(email, senha);
        if (us != null) {
            System.out.println("sucesso");
        } else {
            System.out.println("falhou");
        }

    }
}

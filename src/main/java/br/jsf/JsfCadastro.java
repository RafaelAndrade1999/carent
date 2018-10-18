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

/**
 *
 * @author andre
 */
@ManagedBean(eager = true)
@RequestScoped
public class JsfCadastro {

    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private String confirmaSenha;
    private String CPF;

    /**
     * Creates a new instance of JsfCadastro
     */
    public JsfCadastro() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    private boolean dadosCorretos() {

        if(nome.isEmpty()){
            return false;
        }
        if(email.isEmpty()){
            return false;
        }
        if(senha.isEmpty() || senha.length() <= 5){
            return false;
        }
        if(senha.equals(confirmaSenha) == false){
            return false;
        }
        return true;
    }

    public String cadastrar() {
        Usuario2 us = new Usuario2();
        us.setNome(nome + " " + sobrenome);
        us.setEmail(email);
        us.setSenha(senha);
        if (dadosCorretos()) {
            DaoGenerico<Usuario2> dao = new DaoGenerico<Usuario2>();
            dao.saveOrUpdate(us);
            return "/index.xhtml?faces-redirect=true";
        }else{
            return "/cadastro.xhtml?faces-redirect=true";
        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jsf;

import br.dao.DaoGenerico;
import br.model.Usuario;
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
    public void setConfirmaSenha(String confirmaSenha){
        this.confirmaSenha = confirmaSenha;
    }
    public String getConfirmaSenha(){
        return confirmaSenha;
    }
    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
    
    public void cadastrar(){
        Usuario us = new Usuario();
        us.setNome(nome+" "+sobrenome);
        us.setEmail(email);
        us.setSenha(senha);
        us.setCPF(CPF);
        DaoGenerico<Usuario> dao = new DaoGenerico<Usuario>();
        dao.saveOrUpdate(us);
    }
    
    
}

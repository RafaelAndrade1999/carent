/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jsf;

import br.dao.DaoGenerico;
import br.model.Filial;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Rafael Andrade
 */
@ManagedBean(eager = true)
@RequestScoped
public class JsfCadastroFilial {

    private String nome;
    private String endereco;
    private String cnpj;
    private String telefone;
    private String email;
    private String cidade;

    /**
     * Creates a new instance of JsfCadastro
     */
    public JsfCadastroFilial() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    private boolean dadosCorretos() {
        if(nome.isEmpty()){
            return false;
        }
        if(endereco.isEmpty()){
            return false;
        }
        if(cnpj.isEmpty()){
            return false;
        }
        if(telefone.isEmpty()){
            return false;
        }
        if(email.isEmpty()){
            return false;
        }

        return true;
    }

    public String cadastrar() {
        Filial fi = new Filial();
        fi.setNome(nome);
        fi.setEndereco(endereco);
        fi.setCnpj(cnpj);
        fi.setTelefone(telefone);
        fi.setEmail(email);
        //fi.setCidade(cidade);

        if (dadosCorretos()) {
            DaoGenerico<Filial> dao = new DaoGenerico<Filial>();
            dao.saveOrUpdate(fi);

        }
        return "/cadastrar-filial.xhtml?faces-redirect=true";
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.dao;

import br.model.Usuario;

/**
 *
 * @author andre
 */
public class UsuarioDAO extends DaoGenerico<Usuario> {
    public UsuarioDAO(){
        super();
    }
    
    public Usuario getUsuario(String email, String senha) {
        try {
            return (Usuario)manager.createNamedQuery("Usuario.findByEmailSenha").setParameter("email", email).setParameter("senha", senha).getResultList().get(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

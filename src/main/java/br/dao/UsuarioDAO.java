/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.dao;

import br.model.Usuario;
import java.util.ArrayList;
import java.util.List;

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
            System.out.println("Desgraça: "+manager);
            Usuario u = findById(Usuario.class, 1);
            return u;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

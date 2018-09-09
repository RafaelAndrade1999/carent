/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jsf;

import br.model.Carro;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author andre
 */
@ManagedBean
@RequestScoped
public class JsfCarro {

    /**
     * Creates a new instance of JsfCarro
     */
    public JsfCarro() {
    }
    /*
    public List<Carro> getAll(){
        return new Carro().getAll();
    }
*/
}

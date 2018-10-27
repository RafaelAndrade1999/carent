/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jsf;

import br.connection.ConnectionFactory;
import br.dao.DaoGenerico;
import br.model.Fabricante;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author andre
 */
@ManagedBean()
@RequestScoped
public class JsfFabricante {

    private Fabricante fabricante;

    public JsfFabricante() {
        fabricante = new Fabricante();
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public void cadastrar() {
        DaoGenerico<Fabricante> dao = new DaoGenerico<>();
        dao.saveOrUpdate(fabricante);
    }
    public void editar(Fabricante cor){
        this.fabricante.setId(cor.getId());
        this.fabricante.setNome(cor.getNome());
    }
    public void remover(Fabricante fabricante){
        DaoGenerico<Fabricante> dao = new DaoGenerico<>();
        dao.remove(Fabricante.class,fabricante.getId());
    }
    public List<Fabricante> getAll(){
        EntityManagerFactory factory = ConnectionFactory.getEntityManagerFactory();
        EntityManager em = factory.createEntityManager();
        try {
            List<br.model.Fabricante> lst = em.createNamedQuery("Fabricante.findAll").getResultList();
            return lst;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);

            return null;
        } finally {
            em.close();
        }
        
    }
}

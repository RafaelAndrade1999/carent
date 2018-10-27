/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jsf;

import br.connection.ConnectionFactory;
import br.dao.DaoGenerico;
import br.model.Fabricante;
import br.model.Modelo;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author andre
 */
@ManagedBean()
@ViewScoped
public class JsfModelo {

    private Modelo modelo;
    private List<SelectItem> fabricantes;
    
    private int fabricanteId;

    /**
     * Creates a new instance of JsfModelo
     */
    public JsfModelo() {
        modelo = new Modelo();
    }

    public void cadastrar() {
        DaoGenerico<Modelo> dao = new DaoGenerico<>();
        dao.saveOrUpdate(modelo);
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public int getFabricanteId() {
        System.out.println(fabricanteId);
        return fabricanteId;
    }

    public void setFabricanteId(int fabricanteId) {
        this.fabricanteId = fabricanteId;
    }

    public void changeFabricante() {
        DaoGenerico<Fabricante> dao = new DaoGenerico<>();
        this.modelo.setFabricante(dao.findById(Fabricante.class, this.fabricanteId));
    }

    public List<SelectItem> getAllFabricantes() {
        
        EntityManagerFactory factory = ConnectionFactory.getEntityManagerFactory();
        EntityManager em = factory.createEntityManager();
        try {
            List<br.model.Fabricante> lst = em.createNamedQuery("Fabricante.findAll").getResultList();
            fabricantes = new ArrayList<>();
            for (Fabricante f : lst) {
                fabricantes.add(new SelectItem(f.getId(), f.getNome()));
            }
            return fabricantes;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);

            return null;
        } finally {
            em.close();
        }
         
    }

    public List<Modelo> getAll() {
        EntityManagerFactory factory = ConnectionFactory.getEntityManagerFactory();
        EntityManager em = factory.createEntityManager();
        try {
            List<br.model.Modelo> lst = em.createNamedQuery("Modelo.findAll").getResultList();
            return lst;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);

            return null;
        } finally {
            em.close();
        }

    }

    public void changeListener(ValueChangeEvent event) {
        Object ob = event.getNewValue();
        this.fabricanteId = Integer.parseInt(event.getNewValue().toString());
        
    }
}

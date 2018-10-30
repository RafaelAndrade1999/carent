/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jsf;

import br.connection.ConnectionFactory;
import br.dao.DaoGenerico;
import br.model.Cor;
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
public class JsfCor {

    private Cor cor;

    public JsfCor() {
        cor = new Cor();
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public void cadastrar() {
        DaoGenerico<Cor> dao = new DaoGenerico<>();
        dao.saveOrUpdate(cor);
    }
    public void editar(Cor cor){
        this.cor.setId(cor.getId());
        this.cor.setNome(cor.getNome());
    }
    public void remover(Cor cor){
        DaoGenerico<Cor> dao = new DaoGenerico<>();
        dao.remove(Cor.class,cor.getId());
    }
    public List<Cor> getAll(){
        EntityManagerFactory factory = ConnectionFactory.getEntityManagerFactory();
        EntityManager em = factory.createEntityManager();
        try {
            List<br.model.Cor> lst = em.createNamedQuery("Cor.findAll").getResultList();
            return lst;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);

            return null;
        } finally {
            em.close();
        }
        
    }
}

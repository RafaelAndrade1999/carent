/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.dao;

import br.connection.ConnectionFactory;
import br.model.EntidadeBase;
import javax.persistence.EntityManager;

/**
 *
 * @author andre
 * @param <T>
 */
public class DaoGenerico<T extends EntidadeBase> {

    protected EntityManager manager;

    public DaoGenerico (){
        manager = ConnectionFactory.getEntityManager();
    }
    public T findById(Class<T> clazz, int id) {
        return manager.find(clazz, id);
    }
    
    
    public void saveOrUpdate(T obj) {
        try {
            manager.getTransaction().begin();
            if (obj.getId() == 0) {
                manager.persist(obj);
            } else {
                manager.merge(obj);
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
    }

    public void remove(Class<T> clazz, int id) {
        T t = findById(clazz, id);
        try {
            manager.getTransaction().begin();
            manager.remove(t);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
    }
}

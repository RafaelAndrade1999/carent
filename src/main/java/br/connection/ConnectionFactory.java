/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {
	
	private static EntityManagerFactory factory ;
	
	public static EntityManagerFactory getEntityManagerFactory(){
            if(factory != null)
                factory = Persistence.createEntityManagerFactory("CarentPU");
            return factory;
	}
	
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jsf;

import br.connection.ConnectionFactory;
import br.dao.DaoGenerico;
import br.model.Carro;
import br.model.Fabricante;
import br.model.Modelo;
import java.io.*;
import java.io.IOException;
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
import org.primefaces.model.UploadedFile;
import com.cloudinary.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author andre
 */
@ManagedBean()
@ViewScoped
public class JsfCarro {

    private Carro carro;
    private int modeloId;
    private List<SelectItem> modelos;
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
    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public int getModeloId() {
        return modeloId;
    }

    public void setModeloId(int modeloId) {
        this.modeloId = modeloId;
    }
    
    public List<SelectItem> getAllModelos(){
        return new ArrayList<SelectItem>();
    }
}

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
public class JsfModelo {

    private Modelo modelo;
    private List<SelectItem> fabricantes;
    private UploadedFile file1;
    private UploadedFile file2;
    private UploadedFile file3;
    private int fabricanteId;

    /**
     * Creates a new instance of JsfModelo
     */
    public JsfModelo() {
        modelo = new Modelo();
    }

    public void cadastrar() {
        DaoGenerico<Modelo> dao = new DaoGenerico<>();
        try {
            if (file1 != null) {
                String imagem1Url = salvaCloudinary(file1);
                modelo.setImagem1(imagem1Url);
            }
            if (file2 != null) {
                String imagem2Url = salvaCloudinary(file2);
                modelo.setImagem2(imagem2Url);
            }

            if (file3 != null) {
                String imagem3Url = salvaCloudinary(file3);
                modelo.setImagem3(imagem3Url);
            }
        } catch (Exception ex) {
            Logger.getLogger(JsfModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        dao.saveOrUpdate(modelo);
    }

    //Retorna a url da imagem
    private String salvaCloudinary(UploadedFile uploadedFile) throws Exception {
        try {
            DaoGenerico<Modelo> dao = new DaoGenerico<>();
            HashMap<String, String> hmap = new HashMap<String, String>();
            HashMap<String, String> config = new HashMap<String, String>();

            hmap.put("cloud_name", "carent-images");
            hmap.put("api_key", "533355469548965");
            hmap.put("api_secret", "clRtiRGm9_q3OZYOZaHYZa5k6zo");

            config.put("resource_type", "auto");
            Cloudinary cloudinary = new Cloudinary(hmap);

            Map uploadResult = null;
            String name = uploadedFile.getFileName();
            String images_path = SessionUtils.getRequest().getRealPath("./login");

            InputStream in = uploadedFile.getInputstream();
            images_path = images_path.replace('\\', '/');

            String extensao = retornaFormato(uploadedFile.getContentType());
            Files.copy(in, Paths.get(images_path + "/" + name + extensao), StandardCopyOption.REPLACE_EXISTING);
            File fileForCloudinary = new File(images_path + "/" + name + ".jpg");
            uploadResult = cloudinary.uploader().upload(fileForCloudinary, uploadResult);
            return uploadResult.get("url").toString();
        } catch (Exception ex) {
            Logger.getLogger(JsfModelo.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }
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

    public UploadedFile getFile1() {
        return file1;
    }

    public void setFile1(UploadedFile file) {
        this.file1 = file;
    }

    public UploadedFile getFile2() {
        return file2;
    }

    public void setFile2(UploadedFile file2) {
        this.file2 = file2;
    }

    public UploadedFile getFile3() {
        return file3;
    }

    public void setFile3(UploadedFile file3) {
        this.file3 = file3;
    }

    public void changeFabricante() {
        DaoGenerico<Fabricante> dao = new DaoGenerico<>();
        this.modelo.setFabricante(dao.findById(Fabricante.class, this.fabricanteId));
    }
    
    public void editar(Modelo modelo){
        this.modelo.setId(modelo.getId());
        this.modelo.setModelo(modelo.getModelo());
        this.fabricanteId = modelo.getFabricante().getId();
    }
    
    public void remover(Modelo modelo){
        DaoGenerico<Modelo> dao = new DaoGenerico<>();
        dao.remove(Modelo.class, modelo.getId());
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

    private String[][] formatosSuportados() {
        String[][] format = {
            {"image/png", ".png"},
            {"image/jpeg", ".jpg"}
        };
        return format;
    }

    private String retornaFormato(String mimeType) {
        String[][] format = formatosSuportados();
        for (int i = 0; i < format.length; i++) {
            for (int j = 0; j < format[i].length; j++) {
                if (mimeType.equals(format[i][0])) {
                    return format[i][1];
                }
            }
        }
        return null;
    }
}

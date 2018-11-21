/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.model;

import br.dao.DaoGenerico;
import br.jsf.JsfModelo;
import br.jsf.SessionUtils;
import com.cloudinary.Cloudinary;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author andre
 */
public class Util {

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

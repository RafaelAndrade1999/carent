package br.jsf;
 
import br.connection.ConnectionFactory;
import br.jsf.CarService;
import br.model.Carro;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
 
@ManagedBean
@ViewScoped
public class DataGridView implements Serializable {
     
    private List<Carro> cars;
     
    private Carro selectedCar;
     
    @ManagedProperty("#{carService}")
    private CarService service;
     
    @PostConstruct
    public void init() {
        cars = service.createCars(48);
    }
 
     public List<Carro> getAll() {
        EntityManagerFactory factory = ConnectionFactory.getEntityManagerFactory();
        EntityManager em = factory.createEntityManager();
        try {
            List<br.model.Carro> lst = em.createNamedQuery("Carro.findAll").getResultList();
            return lst;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);

            return null;
        } finally {
            em.close();
        }

    }
    public void setService(CarService service) {
        this.service = service;
    }
 
    public Carro getSelectedCar() {
        return selectedCar;
    }
 
    public void setSelectedCar(Carro selectedCar) {
        this.selectedCar = selectedCar;
    }
}
package br.jsf;
 
import br.jsf.CarService;
import br.model.Carro;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
 
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
 
    public List<Carro> getCars() {
        return cars;
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
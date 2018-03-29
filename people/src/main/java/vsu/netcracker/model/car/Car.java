package vsu.netcracker.model.car;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Objects;

@XmlRootElement(name = "car")
public class Car implements Serializable{
    private String licensePlate;
    private String brand;
    private String model;
    private int yearOfIssue;
    private transient Logger log = LogManager.getLogger(this.getClass());

    public String getLicensePlate() {
        return licensePlate;
    }

    @XmlAttribute
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    @XmlElement
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    @XmlElement
    public void setModel(String model) {
        this.model = model;
    }

    public int getYearOfIssue() {
        return yearOfIssue;
    }

    @XmlElement
    public void setYearOfIssue(int yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(licensePlate, car.licensePlate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(licensePlate);
    }

    public Car(){
    }

    public Car(String licensePlate, String brand, String model, int yearOfIssue){
        setLicensePlate(licensePlate);
        setBrand(brand);
        setModel(model);
        setYearOfIssue(yearOfIssue);
        log.debug("New car created {}", this.toString());
    }
}

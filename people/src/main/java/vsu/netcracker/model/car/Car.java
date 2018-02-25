package vsu.netcracker.model.car;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Car {
    private String licensePlate;
    private String brand;
    private String model;
    private int yearOfIssue;
    private Logger log = LogManager.getLogger(this.getClass());

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(int yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
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

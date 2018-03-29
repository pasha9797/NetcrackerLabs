package vsu.netcracker.model.car;

import vsu.netcracker.injection.Injector;
import vsu.netcracker.model.inteface.RepositoryFactory;

import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;


public class CarRepositoryFactory implements RepositoryFactory<CarRepository>{
    public CarRepository newInstance(){
        return (CarRepository)(new Injector()).inject(new CarRepository());
    }
}

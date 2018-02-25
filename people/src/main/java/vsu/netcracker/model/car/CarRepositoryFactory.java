package vsu.netcracker.model.car;

import vsu.netcracker.injection.Injector;

public class CarRepositoryFactory {
    public static CarRepository newInstance(){
        return (CarRepository)(new Injector()).inject(new CarRepository());
    }
}

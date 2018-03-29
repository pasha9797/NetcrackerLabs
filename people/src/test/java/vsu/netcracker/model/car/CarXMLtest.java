package vsu.netcracker.model.car;

import org.junit.Assert;
import org.junit.Test;
import vsu.netcracker.model.AbstractRepository;
import vsu.netcracker.xml.RepositoryParser;

public class CarXMLtest {
    @Test
    public void MarshallUnmarshall() {
        CarRepositoryFactory factory = new CarRepositoryFactory();

        AbstractRepository repository = factory.newInstance();
        Car c1 = new Car("M346KA", "Toyota", "Celica", 2001);
        Car c2 = new Car("H538PX", "Lada", "Priora", 2008);
        Car c3 = new Car("Y104BO", "Subaru", "Impreza", 1999);
        repository.add(c1);
        repository.add(c2);
        repository.add(c3);

        RepositoryParser.marshall(repository);
        AbstractRepository copy = RepositoryParser.unmarshall();
        Assert.assertEquals(repository, copy);
    }
}

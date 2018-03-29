package vsu.netcracker.model.car;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vsu.netcracker.model.AbstractRepository;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Comparator;

@XmlRootElement(name = "car-repository")
public class CarRepository extends AbstractRepository<Car> {
    private transient Logger log = LogManager.getLogger(this.getClass());

    public Car[] getElements() {
        return elements;
    }

    @XmlElementWrapper()
    @XmlElement(name = "car")
    public void setElements(Car[] elements) {
        this.elements = elements;
    }

    @Override
    protected Class getClassDef() {
        return Car.class;
    }

    /**
     * delete car with certain licence plate number
     *
     * @param licensePlate
     */
    public void deleteByLicensePlate(String licensePlate) {
        delete(get(licensePlate));
    }

    public void sortByLicensePlate() {
        sort(Comparator.comparing(Car::getLicensePlate));
    }

    public void sortByBrand() {
        sort(Comparator.comparing(Car::getBrand));
    }

    public void sortByModel() {
        sort(Comparator.comparing(Car::getModel));
    }

    public void sortByYearOfIssue() {
        sort(Comparator.comparing(Car::getYearOfIssue));
    }

    private Car get(String licensePlate) {
        log.debug("Getting car by license plate");
        for (int i = 0; i < elements.length; i++) {
            if (elements[i].getLicensePlate().equals(licensePlate)) {
                return elements[i];
            }
        }
        log.error("Car with plate '{}' has not been found", licensePlate);
        return null;
    }


}

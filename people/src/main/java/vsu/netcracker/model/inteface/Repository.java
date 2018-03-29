package vsu.netcracker.model.inteface;

import vsu.netcracker.model.AbstractRepository;
import vsu.netcracker.model.car.CarRepository;
import vsu.netcracker.model.person.Person;
import vsu.netcracker.sorters.Sorter;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.Serializable;
import java.util.Comparator;
import java.util.function.Predicate;

public interface Repository<T> extends Iterable<T>, Serializable{
    /**
     * add element to repository
     * @param element Element to be added
     */
    void add(T element);

    /**
     * get element by ID
     * @param id ID of an element
     * @return element with certain ID
     */
    T get(int id);

    /**
     * delete element by ID
     * @param id ID of an element
     */
    void delete(int id);

    /**
     * delete element from repository
     * @param element element to be deleted
     */
    void delete(T element);

    /**
     * set sorter to change sort method
     * @param sorter sorter to be set
     */
    void setSorter(Sorter<T> sorter);

    /**
     * sort elements with specified comparator
     * @param comparator comparator to compare elements
     */
    void sort(Comparator<T> comparator);

    /**
     * Search elements that match the predicate
     * @param predicate predicate to test elements
     * @return new repository with all found elements
     */
    Repository<T> search(Predicate<T> predicate);

}

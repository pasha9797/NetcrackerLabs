package vsu.netcracker.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vsu.netcracker.injection.AutoInjectable;
import vsu.netcracker.model.car.CarRepository;
import vsu.netcracker.model.inteface.Repository;
import vsu.netcracker.model.person.Person;
import vsu.netcracker.model.person.PersonRepository;
import vsu.netcracker.sorters.Sorter;

import javax.xml.bind.annotation.*;
import java.beans.Transient;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

@XmlRootElement
@XmlSeeAlso({CarRepository.class, PersonRepository.class})
@XmlTransient
public abstract class AbstractRepository<T> implements Repository<T> {

    protected T[] elements;

    @AutoInjectable
    protected transient Sorter<T> sorter;

    private transient Logger log = LogManager.getLogger(this.getClass());

    @Override
    public void add(T element) {
        T[] newArr = (T[]) Array.newInstance(getClassDef(), elements.length + 1);
        System.arraycopy(elements, 0, newArr, 0, elements.length);
        elements = newArr;
        elements[elements.length - 1] = element;
        log.info("Adding element '{}'", element.toString());
    }

    @Override
    public T get(int id) {
        log.debug("Getting element at index {}: {}", id, elements[id].toString());
        if (id < 0 || id >= elements.length) {
            String msg = String.format("(Get) Element index (%d) out of bounds (max %d)", id, elements.length);
            log.error(msg);
            throw new IndexOutOfBoundsException(msg);
        }
        return elements[id];
    }

    @Override
    public void delete(int id) {
        log.info("Deleting element at index {}: {}", id, elements[id].toString());
        if (id < 0 || id >= elements.length) {
            String msg = String.format("(Delete) Element index (%d) out of bounds (max %d)", id, elements.length);
            log.error(msg);
            throw new IndexOutOfBoundsException(msg);
        }
        T[] newArr = (T[]) Array.newInstance(getClassDef(), elements.length - 1);
        int index = 0;
        for (int i = 0; i < elements.length; i++) {
            if (i != id) {
                newArr[index] = elements[i];
                index++;
            }
        }
        elements = newArr;
    }

    @Override
    public void delete(T element) {
        if (element == null) {
            throw new NullPointerException("Element is null");
        }

        for (int i = 0; i < elements.length; i++) {
            if (elements[i].equals(element)) {
                delete(i);
                return;
            }
        }
    }

    @Override
    public void setSorter(Sorter<T> sorter) {
        this.sorter = sorter;
        log.debug("Sorter changed to {}", this.sorter.toString());
    }

    @Override
    public void sort(Comparator<T> comparator) {
        log.info("Sort has been called");
        sorter.sort(elements, comparator);
    }

    @Override
    public Repository<T> search(Predicate<T> predicate) {
        try {
            log.debug("Search is started");
            Repository<T> newRepository = (AbstractRepository) this.getClass().newInstance();
            for (T element : elements) {
                if (predicate.test(element))
                    newRepository.add(element);
            }
            return newRepository;
        } catch (Exception e) {
            String msg = "Failed to create new repository to execute search method";
            log.error(msg);
            System.out.println(msg);
            return null;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int id = 0;

            @Override
            public boolean hasNext() {
                return (id < elements.length);
            }

            @Override
            public T next() {
                if (hasNext())
                    return elements[id++];
                else
                    return null;
            }
        };
    }

    protected abstract Class getClassDef();

    public AbstractRepository() {
        elements = (T[]) Array.newInstance(getClassDef(), 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractRepository<?> that = (AbstractRepository<?>) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(elements);
    }
}

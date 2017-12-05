package vsu.netcracker.model;

import vsu.netcracker.conf.Configurator;
import vsu.netcracker.sorters.Sorter;

import java.util.Comparator;
import java.util.function.Predicate;

public class Repository {
    private Person[] people;
    private Sorter<Person> sorter;

    /**
     * Get whole array of people
     *
     * @return array of people
     */
    public Person[] getPeople() {
        return people;
    }

    /**
     * Set sorter to change sort type
     * @param sorter sorter to be set
     */
    void setSorter(Sorter<Person> sorter){
        this.sorter = sorter;
    }

    /**
     * Add person to repository
     *
     * @param person person to be added
     * @return true in case of success, false in case of failure
     */
    public boolean add(Person person) {
        if (get(person.getId()) != null)
            return false;

        Person[] newArr = new Person[people.length + 1];
        System.arraycopy(people, 0, newArr, 0, people.length);
        people = newArr;
        people[people.length - 1] = person;
        return true;
    }

    /**
     * Delete person from repository
     *
     * @param person Person to be deleted
     * @return true in case of success, false in case of failure
     */
    public boolean delete(Person person) {
        if (person == null) {
            return false;
        }
        Person pers = get(person.getId());
        if (pers == null) {
            return false;
        }

        Person[] newArr = new Person[people.length - 1];
        int index = 0;
        for (int i = 0; i < people.length; i++) {
            if (!people[i].equals(pers)) {
                newArr[index] = people[i];
                index++;
            }
        }
        people = newArr;
        return true;
    }

    /**
     * Delete person from repository by ID
     *
     * @param id ID of person to be deleted
     * @return true in case of success, false in case of failure
     */
    public boolean delete(long id) {
        Person person = get(id);
        return delete(person);
    }

    /**
     * Delete person from repository by name
     *
     * @param fullName name of person to be deleted
     * @return true in case of success, false in case of failure
     */
    public boolean delete(String fullName) {
        Person person = get(fullName);
        return delete(person);
    }

    /**
     * Sort people using comparator
     * @param comparator comparator to compare people
     */
    public void sort(Comparator<Person> comparator) {
                sorter.sort(people, comparator);
    }

    public void sortByID(){
        sort(Comparator.comparing(Person::getId));
    }

    public void sortByName(){
        sort(Comparator.comparing(Person::getFullName));
    }

    public void sortByAge(){
        sort(Comparator.comparing(Person::getAge));
    }

    /**
     * Find all people ho match the predicate
     * @param predicate predicate to test people
     * @return new repository with found people
     */
    public Repository search(Predicate<Person> predicate) {
        Repository newRepository = new Repository();
        for (Person p : people) {
            if (predicate.test(p))
                newRepository.add(p);
        }
        return newRepository;
    }

    private Person get(long id) {
        for (int i = 0; i < people.length; i++) {
            if (people[i].getId() == id) {
                return people[i];
            }
        }
        return null;
    }

    private Person get(String fullName) {
        for (int i = 0; i < people.length; i++) {
            if (people[i].getFullName().equals(fullName)) {
                return people[i];
            }
        }
        return null;
    }

    public Repository() {
        people = new Person[0];
        sorter = Configurator.getSorter();
    }
}

package vsu.netcracker.model.person;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vsu.netcracker.model.AbstractRepository;

import java.util.Comparator;

public class PersonRepository extends AbstractRepository<Person>{
    private Logger log = LogManager.getLogger(this.getClass());

    /**
     * Delete person from repository by name
     *
     * @param fullName name of person to be deleted
     */
    public void deleteByFullName(String fullName) {
        Person person = get(fullName);
        delete(person);
    }

    public void sortByPassportID(){
        sort(Comparator.comparing(Person::getPassportID));
    }

    public void sortByName(){
        sort(Comparator.comparing(Person::getFullName));
    }

    public void sortByAge(){
        sort(Comparator.comparing(Person::getAge));
    }

    private Person get(String fullName) {
        log.debug("Getting person by full name");
        for (int i = 0; i < elements.length; i++) {
            if (elements[i].getFullName().equals(fullName)) {
                return elements[i];
            }
        }
        log.error("Person with name {} has not been found", fullName);
        return null;
    }

    @Override
    protected Class getClassDef() {
        return Person.class;
    }
}
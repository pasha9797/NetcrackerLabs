package vsu.netcracker.model.person;

import org.junit.Assert;
import org.junit.Test;
import vsu.netcracker.model.AbstractRepository;
import vsu.netcracker.model.inteface.Repository;
import vsu.netcracker.xml.RepositoryParser;

public class PersonXMLtest {

    @Test
    public void MarshallUnmarshall() {
        PersonRepositoryFactory factory = new PersonRepositoryFactory();

        AbstractRepository repository = factory.newInstance();
        Person p1 = new Person(0, "Steve", "12-01-1945");
        Person p2 = new Person(3, "Michael", "2-02-1993");
        Person p3 = new Person(2, "Paul", "1-05-1995");
        repository.add(p1);
        repository.add(p2);
        repository.add(p3);

        RepositoryParser.marshall(repository);
        AbstractRepository copy = RepositoryParser.unmarshall();
        Assert.assertEquals(repository, copy);
    }
}

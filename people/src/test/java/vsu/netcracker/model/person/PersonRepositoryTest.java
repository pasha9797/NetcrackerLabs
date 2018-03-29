package vsu.netcracker.model.person;

import org.junit.*;
import vsu.netcracker.sorters.impl.*;

public class PersonRepositoryTest {
    private PersonRepository personRepository, expected;
    private Person p1, p2, p3;
    private PersonRepositoryFactory factory;

    @Before
    public void init() {
        p1 = new Person(0, "Steve", "12-01-1945");
        p2 = new Person(3, "Michael", "2-02-1993");
        p3 = new Person(2, "Paul", "1-05-1995");
        factory = new PersonRepositoryFactory();
    }

    @After
    public void tearDown() {
        personRepository = null;
        expected = null;

        p1 = null;
        p2 = null;
        p3 = null;

        factory = null;
    }

    @Test
    public void testSortByPassportID() {
        personRepository = factory.newInstance();
        expected = factory.newInstance();

        personRepository.setSorter(new BubbleSorter<>());

        personRepository.add(p1);
        personRepository.add(p2);
        personRepository.add(p3);

        expected.add(p1);
        expected.add(p3);
        expected.add(p2);

        personRepository.sortByPassportID();

        Assert.assertEquals(expected, personRepository);
    }

    @Test
    public void testSortByAge() {
        personRepository = factory.newInstance();
        expected = factory.newInstance();

        personRepository.setSorter(new InsertionSorter<>());

        personRepository.add(p1);
        personRepository.add(p2);
        personRepository.add(p3);

        expected.add(p3);
        expected.add(p2);
        expected.add(p1);

        personRepository.sortByAge();

        Assert.assertEquals(expected, personRepository);
    }

    @Test
    public void testSortByName() {
        personRepository = factory.newInstance();
        expected = factory.newInstance();

        personRepository.setSorter(new QuickSorter<>());

        personRepository.add(p1);
        personRepository.add(p2);
        personRepository.add(p3);

        expected.add(p2);
        expected.add(p3);
        expected.add(p1);

        personRepository.sortByName();

        Assert.assertEquals(expected, personRepository);
    }

    @Test
    public void testSearch() {
        personRepository = factory.newInstance();
        expected = factory.newInstance();

        personRepository.add(p1);
        personRepository.add(p2);
        personRepository.add(p3);

        expected.add(p3);

        personRepository = (PersonRepository) personRepository.search(a -> a.getAge() < 23);

        Assert.assertEquals(expected, personRepository);
    }

    @Test
    public void testDelete() {
        personRepository = factory.newInstance();
        expected = factory.newInstance();

        personRepository.add(p1);
        personRepository.add(p2);
        personRepository.add(p3);

        expected.add(p1);
        expected.add(p3);

        personRepository.deleteByFullName("Michael");

        Assert.assertEquals(expected, personRepository);
    }

    @Test
    public void testIterator() {
        personRepository = factory.newInstance();

        personRepository.add(p1);
        personRepository.add(p2);
        personRepository.add(p3);

        for (Person p : personRepository) {
            System.out.println(p);
        }
    }
}

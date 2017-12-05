package vsu.netcracker.model;

import org.junit.*;
import vsu.netcracker.sorters.impl.*;

public class RepositoryTest {
    private Repository repository, expected;
    private Person p1, p2, p3;

    @Before
    public void init() {
        p1 = new Person(0, "Steve", "12-01-1945");
        p2 = new Person(3, "Michael", "2-02-1993");
        p3 = new Person(2, "Paul", "1-05-1995");
    }

    @After
    public void tearDown() {
        repository = null;
        expected = null;

        p1=null;
        p2=null;
        p3=null;
    }

    @Test
    public void testSortByID() {
        repository = new Repository();
        expected = new Repository();

        repository.setSorter(new BubbleSorter<>());

        repository.add(p1);
        repository.add(p2);
        repository.add(p3);

        expected.add(p1);
        expected.add(p3);
        expected.add(p2);

        repository.sortByID();

        Assert.assertArrayEquals(expected.getPeople(), repository.getPeople());
    }

    @Test
    public void testSortByAge() {
        repository = new Repository();
        expected = new Repository();

        repository.setSorter(new InsertionSorter<>());

        repository.add(p1);
        repository.add(p2);
        repository.add(p3);

        expected.add(p3);
        expected.add(p2);
        expected.add(p1);

        repository.sortByAge();

        Assert.assertArrayEquals(expected.getPeople(), repository.getPeople());
    }

    @Test
    public void testSortByName() {
        repository = new Repository();
        expected = new Repository();

        repository.setSorter(new QuickSorter<>());

        repository.add(p1);
        repository.add(p2);
        repository.add(p3);

        expected.add(p2);
        expected.add(p3);
        expected.add(p1);

        repository.sortByName();

        Assert.assertArrayEquals(expected.getPeople(), repository.getPeople());
    }

    @Test
    public void testSearch() {
        repository = new Repository();
        expected = new Repository();

        repository.add(p1);
        repository.add(p2);
        repository.add(p3);

        expected.add(p3);

        repository=repository.search(a->a.getAge()<23);

        Assert.assertArrayEquals(expected.getPeople(), repository.getPeople());
    }

    @Test
    public void testDelete() {
        repository = new Repository();
        expected = new Repository();

        repository.add(p1);
        repository.add(p2);
        repository.add(p3);

        expected.add(p1);
        expected.add(p3);

        repository.delete(3);

        Assert.assertArrayEquals(expected.getPeople(), repository.getPeople());
    }
}

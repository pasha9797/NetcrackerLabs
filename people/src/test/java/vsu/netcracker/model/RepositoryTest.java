package vsu.netcracker.model;
import org.junit.*;
import vsu.netcracker.util.Sorts;

import java.util.Arrays;
import java.util.Comparator;

public class RepositoryTest {
    private Repository original, actual, expected;

    @Before
    public void init() {
        original = new Repository();
        original.add(new Person(0, "Steve", "12-01-1995"));
        original.add(new Person(3, "Michael", "2-02-1993"));
        original.add(new Person(2, "Paul", "1-05-1945"));
        original.add(new Person(1, "Alex", "6-11-1955"));
        original.add(new Person(4, "Andrew", "20-09-1999"));
        original.add(new Person(5, "John", "7-12-1997"));

    }

    @After
    public void tearDown() {
        original = null;
        actual = null;
        expected = null;
    }

    private void initializeTwoCopies(){
        expected=new Repository(original);
        actual=new Repository(original);
    }

    @Test
    public void testBubbleSort(){
        initializeTwoCopies();
        Comparator<Person> c = (o1,o2) -> (int)(o1.getId()-o2.getId());

        Arrays.sort(expected.getPeople(), c);
        actual.sort(c, Sorts.SortTypes.BUBBLE);

        Assert.assertArrayEquals(expected.getPeople(), actual.getPeople());
    }

    @Test
    public void testQuickSort(){
        initializeTwoCopies();
        Comparator<Person> c = (o1,o2) -> (int)(o1.getId()-o2.getId());

        Arrays.sort(expected.getPeople(), c);
        actual.sort(c, Sorts.SortTypes.QUICK);

        Assert.assertArrayEquals(expected.getPeople(), actual.getPeople());
    }

    @Test
    public void testInsertionSort(){
        initializeTwoCopies();
        Comparator<Person> c = (o1,o2) -> (int)(o1.getId()-o2.getId());

        Arrays.sort(expected.getPeople(), c);
        actual.sort(c, Sorts.SortTypes.INSERTION);

        Assert.assertArrayEquals(expected.getPeople(), actual.getPeople());
    }

    @Test
    public void testSearch(){
        actual = new Repository();
        actual.add(new Person(0, "A", "1-1-1991"));
        actual.add(new Person(1, "B", "1-1-1995"));
        actual.add(new Person(2, "C", "1-1-1999"));
        actual.add(new Person(3, "D", "1-1-1990"));

        expected= new Repository();
        expected.add(new Person(1, "B", "1-1-1995"));
        expected.add(new Person(2, "C", "1-1-1999"));

        actual = actual.search((a)->a.getAge()<25);

        Assert.assertArrayEquals(expected.getPeople(), actual.getPeople());
    }
}

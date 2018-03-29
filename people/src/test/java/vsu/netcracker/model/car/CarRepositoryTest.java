package vsu.netcracker.model.car;

import org.junit.*;
import vsu.netcracker.sorters.impl.*;

public class CarRepositoryTest {
    private CarRepository carRepository, expected;
    private Car c1, c2, c3;
    private CarRepositoryFactory factory;

    @Before
    public void init() {
        c1 = new Car("M346KA", "Toyota", "Celica", 2001);
        c2 = new Car("H538PX", "Lada", "Priora", 2008);
        c3 = new Car("Y104BO", "Subaru", "Impreza", 1999);
        factory = new CarRepositoryFactory();
    }

    @After
    public void tearDown() {
        carRepository = null;
        expected = null;

        c1 = null;
        c2 = null;
        c3 = null;

        factory=null;
    }

    @Test
    public void testSortByLicencePlate() {
        carRepository = factory.newInstance();
        expected = factory.newInstance();

        carRepository.setSorter(new BubbleSorter<>());

        carRepository.add(c1);
        carRepository.add(c2);
        carRepository.add(c3);

        expected.add(c2);
        expected.add(c1);
        expected.add(c3);

        carRepository.sortByLicensePlate();

        Assert.assertEquals(expected, carRepository);
    }

    @Test
    public void testSortByBrand() {
        carRepository = factory.newInstance();
        expected = factory.newInstance();

        carRepository.setSorter(new InsertionSorter<>());

        carRepository.add(c1);
        carRepository.add(c2);
        carRepository.add(c3);

        expected.add(c2);
        expected.add(c3);
        expected.add(c1);

        carRepository.sortByBrand();

        Assert.assertEquals(expected, carRepository);
    }

    @Test
    public void testSortByModel() {
        carRepository = factory.newInstance();
        expected = factory.newInstance();

        carRepository.setSorter(new QuickSorter<>());

        carRepository.add(c1);
        carRepository.add(c2);
        carRepository.add(c3);

        expected.add(c1);
        expected.add(c3);
        expected.add(c2);

        carRepository.sortByModel();

        Assert.assertEquals(expected, carRepository);
    }

    @Test
    public void testSortByYearOfIssue() {
        carRepository = factory.newInstance();
        expected = factory.newInstance();

        carRepository.setSorter(new QuickSorter<>());

        carRepository.add(c1);
        carRepository.add(c2);
        carRepository.add(c3);

        expected.add(c3);
        expected.add(c1);
        expected.add(c2);

        carRepository.sortByYearOfIssue();

        Assert.assertEquals(expected, carRepository);
    }


    @Test
    public void testSearch() {
        carRepository = factory.newInstance();
        expected = factory.newInstance();

        carRepository.add(c1);
        carRepository.add(c2);
        carRepository.add(c3);

        expected.add(c3);

        carRepository = (CarRepository) carRepository.search(a -> a.getYearOfIssue() < 2000);

        Assert.assertEquals(expected, carRepository);
    }

    @Test
    public void testDelete() {
        carRepository = factory.newInstance();
        expected = factory.newInstance();

        carRepository.add(c1);
        carRepository.add(c2);
        carRepository.add(c3);

        expected.add(c1);
        expected.add(c3);

        carRepository.deleteByLicensePlate("H538PX");

        Assert.assertEquals(expected, carRepository);
    }

    @Test
    public void testIterator() {
        carRepository = factory.newInstance();

        carRepository.add(c1);
        carRepository.add(c2);
        carRepository.add(c3);

        for (Car c : carRepository) {
            System.out.println(c);
        }
    }
}

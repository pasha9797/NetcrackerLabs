package vsu.netcracker.model.person;

import org.junit.*;

public class PersonTest {
    private Person person1, person2;

    @Before
    public void init() {
        person1 = new Person(0, "Donald Trump", "12-08-1997");

        person2 = new Person();
        person2.setFullName("Mickey Mouse");
        person2.setPassportID(1);
        person2.setBirthDate("31-12-1997");
    }

    @After
    public void tearDown() {
        person1 = null;
        person2 = null;
    }

    @Test
    public void testGetAge() {
        Assert.assertEquals(20, person1.getAge());
    }

    @Test
    public void testGetAge2() {
        Assert.assertEquals(19, person2.getAge());
    }

    @Test
    public void testGetters() {
        String str = person1.getPassportID() + " " + person1.getFullName();
        Assert.assertEquals("0 Donald Trump", str);
    }
    @Test
    public void testEquals(){
        Person p1 = new Person(0, "Donald Trump", "12-08-1997");
        Person p2 = new Person(1, "Donald Trump", "12-08-1997");

        Assert.assertTrue(p1.equals(person1));
        Assert.assertFalse(p2.equals(person1));
    }
}

package vsu.netcracker.model;

import org.junit.*;

public class PersonTest {
    private Person person;

    @Before
    public void init(){
        person = new Person(0,"Donald Trump", "12-08-1997");
    }

    @After
    public void tearDown(){
        person = null;
    }

    @Test
    public void testGetAge(){
        Assert.assertEquals(20, person.getAge());
    }
}

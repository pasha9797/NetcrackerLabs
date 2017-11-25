package vsu.netcracker.model;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

/**
 * Person is a class that contains description of person
 */
public class Person {
    private long id;
    private String fullName;
    private LocalDate birthDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Set the birth date from string
     * @param birthDate string containing birth date
     */
    public void setBirthDate(String birthDate){
            this.birthDate = LocalDate.parse(birthDate, DateTimeFormat.forPattern("d-M-YYYY"));
    }

    /**
     * Method to get person's age in years
     * @return age in years
     */
    public int getAge(){
        LocalDate now=LocalDate.now();
        int age = now.getYear() - birthDate.getYear();
        if(now.getDayOfYear() <birthDate.getDayOfYear())
            age--;
        return age;
    }


    public Person(){

    }

    public Person(long id, String fullName, String birthDate){
        setId(id);
        setFullName(fullName);
        setBirthDate(birthDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return id == person.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", full name='" + fullName + '\'' +
                ", birth date='" + birthDate.toString(DateTimeFormat.forPattern("d MMM YYYY")) +
                "'}";
    }
}

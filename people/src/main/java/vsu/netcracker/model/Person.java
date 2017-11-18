package vsu.netcracker.model;

import org.joda.time.LocalDate;

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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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
                ", birth date=" + birthDate +
                '}';
    }
}

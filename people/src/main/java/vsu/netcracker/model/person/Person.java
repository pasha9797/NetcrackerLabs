package vsu.netcracker.model.person;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * Person is a class that contains description of person
 */
@XmlRootElement(name = "person")
public class Person implements Serializable{
    private long passportID;
    private String fullName;
    private LocalDate birthDate;
    private transient Logger log = LogManager.getLogger(this.getClass());

    public long getPassportID() {
        return passportID;
    }

    @XmlAttribute
    public void setPassportID(long passportID) {
        this.passportID = passportID;
    }

    public String getFullName() {
        return fullName;
    }

    @XmlElement
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Set the birth date from string
     *
     * @param birthDate string containing birth date
     */
    @XmlElement
    public void setBirthDate(String birthDate) {
        this.birthDate = LocalDate.parse(birthDate, DateTimeFormat.forPattern("d-M-YYYY"));
    }

    public String getBirthDate(){
        return this.birthDate.toString();
    }

    /**
     * Method to get person's age in years
     *
     * @return age in years
     */
    public int getAge() {
        LocalDate now = LocalDate.now();
        int age = now.getYear() - birthDate.getYear();
        if (now.getDayOfYear() < birthDate.getDayOfYear())
            age--;
        return age;
    }


    public Person() {
    }

    public Person(long passportID, String fullName, String birthDate) {
        setPassportID(passportID);
        setFullName(fullName);
        setBirthDate(birthDate);
        log.debug("New person created {}", this.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return passportID == person.passportID;
    }

    @Override
    public int hashCode() {
        return (int) (passportID ^ (passportID >>> 32));
    }

    @Override
    public String toString() {
        return "Person{" +
                "passportID=" + passportID +
                ", full name='" + fullName + '\'' +
                ", birth date='" + birthDate.toString(DateTimeFormat.forPattern("d MMM YYYY")) +
                "'}";
    }
}

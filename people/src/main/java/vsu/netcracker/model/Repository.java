package vsu.netcracker.model;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import vsu.netcracker.model.Person;

import java.util.Arrays;

public class Repository {
    private Person[] people;

    /**
     * Get whole array of people
     * @return array of people
     */
    public Person[] getPeople() {
        return people;
    }

    /**
     * Add person to repository
     * @param person person to be added
     * @return true in case of success, false in case of failure
     */
    public boolean add(Person person){
        if(get(person.getId())!=null)
            return false;

        Person[] newArr=new Person[people.length+1];
        System.arraycopy(people,0,newArr,0,people.length);
        people=newArr;
        people[people.length-1]=person;
        return true;
    }

    /**
     * Delete person from repository
     * @param person Person to be deleted
     * @return true in case of success, false in case of failure
     */
    public boolean delete(Person person){
        if(person==null){
            return false;
        }
        Person pers = get(person.getId());
        if(pers==null){
            return false;
        }

        Person[] newArr = new Person[people.length-1];
        int index=0;
        for(int i=0;i<people.length;i++){
            if(!people[i].equals(pers)){
                newArr[index]=people[i];
                index++;
            }
        }
        people=newArr;
        return true;
    }

    /**
     * Delete person from repository by ID
     * @param id ID of person to be deleted
     * @return true in case of success, false in case of failure
     */
    public boolean delete(long id){
        Person person = get(id);
        return delete(person);
    }

    /**
     * Delete person from repository by name
     * @param fullName name of person to be deleted
     * @return true in case of success, false in case of failure
     */
    public boolean delete(String fullName){
        Person person = get(fullName);
        return delete(person);
    }

    private Person get(long id){
        for(int i=0;i<people.length;i++){
            if(people[i].getId()==id){
                return people[i];
            }
        }
        return null;
    }
    private Person get(String fullName){
        for(int i=0;i<people.length;i++){
            if(people[i].getFullName().equals(fullName)){
                return people[i];
            }
        }
        return null;
    }

    public Repository(){
        people = new Person[0];
    }
}

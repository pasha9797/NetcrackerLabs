package vsu.netcracker.model.person;

import vsu.netcracker.injection.Injector;

public class PersonRepositoryFactory {
    public static PersonRepository newInstance(){
        return (PersonRepository)(new Injector()).inject(new PersonRepository());
    }
}

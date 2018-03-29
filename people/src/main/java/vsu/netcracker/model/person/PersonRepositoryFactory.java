package vsu.netcracker.model.person;

import vsu.netcracker.injection.Injector;
import vsu.netcracker.model.inteface.RepositoryFactory;

import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

public class PersonRepositoryFactory implements RepositoryFactory<PersonRepository>{
    public PersonRepository newInstance(){
        return (new Injector()).inject(new PersonRepository());
    }
}

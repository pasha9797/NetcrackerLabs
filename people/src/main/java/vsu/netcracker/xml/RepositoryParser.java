package vsu.netcracker.xml;

import vsu.netcracker.model.AbstractRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class RepositoryParser {
    private static final String path = "repos.xml";

    public static void marshall(AbstractRepository repository) {
        try {
            JAXBContext context = JAXBContext.newInstance(repository.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(repository, new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AbstractRepository unmarshall() {
        try {
            JAXBContext context = JAXBContext.newInstance(AbstractRepository.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Object o = unmarshaller.unmarshal(new File(path));
            return (AbstractRepository) o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

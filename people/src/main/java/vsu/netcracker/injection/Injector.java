package vsu.netcracker.injection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;

public class Injector {
    private static Logger log = LogManager.getLogger(Injector.class);

    public <T> T inject(T o) {
        try {
            Properties prop = getProperties();
            for (Field field : getAllFields(o.getClass())) {
                //if field has 'injectable' annotation
                if (field.getAnnotation(AutoInjectable.class) != null) {
                    //create new comparator
                    Class<?> clazz = Class.forName(prop.getProperty(field.getType().getName()));
                    if (field.getType().isAssignableFrom(clazz)) {
                        field.setAccessible(true);
                        Constructor<?> ctor = clazz.getConstructor();
                        Object object = ctor.newInstance();
                        //set new comparator
                        field.set(o, object);
                        log.debug("Successfully injected {} to field {} in class {}", clazz.getSimpleName(), field.getName(), o.getClass().getSimpleName());
                    } else throw new Exception("Specified class is not assignable for field type");
                }
            }
            return o;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;

        }
    }

    private Properties getProperties() {
        InputStream input = null;
        try {
            log.debug("Trying to load config file...");
            Properties prop = new Properties();

            String filename = "config.properties";
            input = this.getClass().getClassLoader().getResourceAsStream(filename);
            log.debug("Config file loaded");
            prop.load(input);
            input.close();
            log.debug("Config file closed");
            return prop;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Field[] getAllFields(Class klass) {
        List<Field> fields = new ArrayList<Field>();
        fields.addAll(Arrays.asList(klass.getDeclaredFields()));
        if (klass.getSuperclass() != null) {
            fields.addAll(Arrays.asList(getAllFields(klass.getSuperclass())));
        }
        return fields.toArray(new Field[] {});
    }
}

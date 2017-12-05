package vsu.netcracker.conf;

import vsu.netcracker.sorters.Sorter;
import vsu.netcracker.Client;
import vsu.netcracker.sorters.impl.BubbleSorter;
import vsu.netcracker.sorters.impl.InsertionSorter;
import vsu.netcracker.sorters.impl.QuickSorter;

import java.io.*;
import java.util.Properties;

public class Configurator {
    public static Sorter getSorter() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            String filename = "config.properties";
            input = Client.class.getClassLoader().getResourceAsStream(filename);
            prop.load(input);

            String sortType = prop.getProperty("sorter");
            switch (sortType) {
                case "bubble":
                    return new BubbleSorter();
                case "insertion":
                    return new InsertionSorter();
                case "quick":
                    return new QuickSorter();
                default:
                    throw new Exception("Wrong sort type: " + sortType);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
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
}

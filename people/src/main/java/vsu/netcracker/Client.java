package vsu.netcracker;


public class Client {
/*    private static Scanner in = new Scanner(System.in);
    private static PersonRepository repository;

    public static void main(String[] args) {
        repository = new PersonRepository();
        char key = 'c';

        while (key != 'q') {
            printPeople();

            System.out.println("Press one of these symbols:\n a - add item\n d - delete item\n n - delete item by name\n q - quit");
            String s = in.nextLine();
            if (s.length() == 1) {
                key = s.charAt(0);
            }
            switch (key) {
                case 'a':
                    parseAdd();
                    break;
                case 'd':
                    parseDelete();
                    break;
                case 'n':
                    parseDeleteByName();
                    break;
                case 'q':
                    break;
                default:
                    System.out.println("Error: Unknown character.");
            }
        }
    }

    *//**
     * Prints all people in repository
     **//*
    private static void printPeople() {
        for (int i = 0; i < repository.getPeople().length; i++) {
            System.out.println(repository.getPeople()[i].toString() + ", Age: " + repository.getPeople()[i].getAge() + " years");
        }
        System.out.println();
    }

    *//**
     * Handles add command via console
     *//*
    private static void parseAdd() {
        System.out.println("Write id, full name (first name and last name) and a birth date (format day-month-year)");
        String str;

        str = in.nextLine();
        String[] parts = str.split(" ");
        if (parts.length != 4) {
            System.out.println("Error: Too much or not enough data.");
            return;
        }

        Person person = new Person();
        try {
            person.setPassportID(Integer.decode(parts[0]));
            person.setFullName(parts[1] + " " + parts[2]);
            person.setBirthDate(parts[3]);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        boolean result = repository.add(person);
        if (!result)
            System.out.println("Error in input information: This ID already exists.");
    }

    *//**
     * Handles delete command via console
     *//*
    private static void parseDelete() {
        int id;
        System.out.println("Write abonent ID");
        try {
            id = in.nextInt();
        } catch (Exception e) {
            System.out.println("Error: Write a digit.");
            return;
        } finally {
            in.nextLine();
        }
        boolean result = repository.delete(id);
        if (!result) {
            System.out.println("Error: No such person in repository.");
            return;
        }
    }

    *//**
     * Handles delete by name command via console
     *//*
    private static void parseDeleteByName() {
        String name;
        System.out.println("Write name.");
        name = in.nextLine();
        boolean result = repository.delete(name);
        if (!result) {
            System.out.println("Error: No such person in repository.");
            return;
        }
    }*/
}

package vsu.netcracker;

import org.joda.time.LocalDate;
import vsu.netcracker.model.Person;
import vsu.netcracker.model.Repository;

import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    private static Scanner in=new Scanner(System.in);
    private static Repository repository;

    public static void main(String[] args) {
        repository=new Repository();
        char key='c';

        while(key!='q') {
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

    private static void printPeople(){
        for(int i=0;i<repository.getPeople().length;i++){
            System.out.println(repository.getPeople()[i].toString());
        }
        System.out.println();
    }

    private static void parseAdd(){
        System.out.println("Write id, full name (first name and last name) and a birth date (format year-month-day)");
        int id;
        LocalDate birthDate;
        String fullName,str;

        str=in.nextLine();
        String[] parts = str.split(" ");
        if(parts.length!=4) {
            System.out.println("Error: Too much or not enough data.");
            return;
        }
        id=Integer.decode(parts[0]);
        fullName=parts[1]+" "+parts[2];
        try {
            birthDate = LocalDate.parse(parts[3]);
        }
        catch(Exception e){
            System.out.println("Error: Wrong birth date format.");
            return;
        }
        Person person = new Person();
        person.setId(id);
        person.setFullName(fullName);
        person.setBirthDate(birthDate);
        boolean result=repository.add(person);
        if(!result)
            System.out.println("Error: This ID already exists.");
    }
    private static void parseDelete(){
        int id;
        System.out.println("Write abonent ID");
        try{
            id=in.nextInt();
        }
        catch(Exception e){
            System.out.println("Error: Write a digit.");
            return;
        }
        finally {
            in.nextLine();
        }
        boolean result=repository.delete(id);
        if(!result) {
            System.out.println("Error: No such person in repository.");
            return;
        }
    }
    private static void parseDeleteByName(){
        String name;
        System.out.println("Write name.");
        name = in.nextLine();
        boolean result=repository.delete(name);
        if(!result) {
            System.out.println("Error: No such person in repository.");
            return;
        }
    }
}

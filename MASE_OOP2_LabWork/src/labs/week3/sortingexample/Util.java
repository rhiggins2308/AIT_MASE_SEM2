package labs.week3.sortingexample;

import java.util.*;
 
public class Util {
    public static List<Employee> getEmployees() {
        List<Employee> coll = new ArrayList<>();
 
        coll.add(new Employee(5, "Frank", 28));
        coll.add(new Employee(1, "Jorge", 19));
        coll.add(new Employee(6, "Bill", 34));
        coll.add(new Employee(3, "Michel", 10));
        coll.add(new Employee(7, "Simpson", 8));
        coll.add(new Employee(4, "Clerk",16 ));
        coll.add(new Employee(8, "Lee", 40));
        coll.add(new Employee(2, "Mark", 40));
 
        return coll;
    }
}
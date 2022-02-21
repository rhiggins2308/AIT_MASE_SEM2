package labs.week3.sortingexample;

import java.util.*;
 
public class TestEmployeeSort {
 
    public static void main(String[] args) {     
        List<Employee> coll = Util.getEmployees();
        Collections.sort(coll); // sort method
        printList(coll);
        Collections.sort(coll, new EmpSortByName());
        printList(coll);
        Collections.sort(coll, new EmpSortByAge());
        printList(coll);
    }
 
    private static void printList(List<Employee> list) {
        System.out.println("EmpId\tName\tAge");
        for (Employee e: list) {
            System.out.println(e.getEmpId() + "\t" + e.getName() + "\t" + e.getAge());
        }
    }
}
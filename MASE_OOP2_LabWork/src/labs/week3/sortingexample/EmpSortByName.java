package labs.week3.sortingexample;

import java.util.Comparator;

public class EmpSortByName implements Comparator<Employee>{
 
    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
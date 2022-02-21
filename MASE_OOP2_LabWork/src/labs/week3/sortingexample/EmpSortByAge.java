package labs.week3.sortingexample;

import java.util.Comparator;

public class EmpSortByAge implements Comparator<Employee>{
 
    @Override
    public int compare(Employee o1, Employee o2) {
        // As 'age' is an int (and not Integer) I cannot use "compareTo"
        // descending order
        return o2.getAge() - o1.getAge();
    }
}
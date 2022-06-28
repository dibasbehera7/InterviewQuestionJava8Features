package com.dibasb;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class EmployeesMainApp {

    private static List<Employee> employeeList = new ArrayList<Employee>();

    public static void loadEmpRecords() {
        employeeList.add(new Employee(111, "Jennifer Flores", 32, "Female", "HR", 2011, 25000.0));
        employeeList.add(new Employee(122, "Mr. Jacob Parker", 25, "Male", "Sales And Marketing", 2015, 13500.0));
        employeeList.add(new Employee(133, "Tony Williams", 29, "Male", "Infrastructure", 2012, 18000.0));
        employeeList.add(new Employee(144, "David King", 28, "Male", "Product Development", 2014, 32500.0));
        employeeList.add(new Employee(155, "Christina Baker", 27, "Female", "HR", 2013, 22700.0));
        employeeList.add(new Employee(166, "John Murphy", 43, "Male", "Security And Transport", 2016, 10500.0));
        employeeList.add(new Employee(177, "Daniel Jackson", 35, "Male", "Account And Finance", 2010, 27000.0));
        employeeList.add(new Employee(188, "William Foster", 31, "Male", "Product Development", 2015, 34500.0));
        employeeList.add(new Employee(199, "Linda Li", 24, "Female", "Sales And Marketing", 2016, 11500.0));
        employeeList.add(new Employee(200, "Justin Ward", 38, "Male", "Security And Transport", 2015, 11000.5));
        employeeList.add(new Employee(211, "Lisa Aguilar", 27, "Female", "Infrastructure", 2014, 15700.0));
        employeeList.add(new Employee(222, "Daniel Morris", 25, "Male", "Product Development", 2016, 28200.0));
        employeeList.add(new Employee(233, "Elizabeth Moody", 27, "Female", "Account And Finance", 2013, 21300.0));
        employeeList.add(new Employee(244, "William Williams", 24, "Male", "Sales And Marketing", 2017, 10700.5));
        employeeList.add(new Employee(255, "Dr. John Obrien", 23, "Male", "Infrastructure", 2018, 12700.0));
        employeeList.add(new Employee(266, "Sara Farrell", 26, "Female", "Product Development", 2015, 28900.0));
        employeeList.add(new Employee(277, "Eric Smith", 31, "Male", "Product Development", 2012, 35700.0));
    }

    public static void main(String[] args) {
        loadEmpRecords();
        System.out.println(employeeList);

        System.err.println("1. How many male and female employees are there in the organization?");
        Map<String, Long> noOfMaleAndFemaleEmployees = employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println(noOfMaleAndFemaleEmployees);

        System.err.println("2. Print the name of all departments in the organization?");
        employeeList.stream().map(Employee::getDepartment).distinct().forEach(System.out::println);

        System.err.println("3. What is the average age of male and female employees?");
        Map<String, Double> averageAgeOfMaleAndFemaleEmp = employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
        System.out.println(averageAgeOfMaleAndFemaleEmp);

        System.err.println("4.  Get the details of highest paid employee in the organization?");
        Optional<Employee> maxSal = employeeList.stream().collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)));
        System.out.println(maxSal.get());



        System.err.println("5. Get the names of all employees who have joined after 2015?");
        employeeList.stream().filter(emp -> emp.getYearOfJoining() > 2015).map(Employee::getName).forEach(System.out::println);

        System.err.println("6. Count the number of employees in each department?");
        Map<String, Long> noEmployeesInDept = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        Set<Map.Entry<String, Long>> entrySet = noEmployeesInDept.entrySet();
        for (Map.Entry<String, Long> entry: entrySet){
            System.out.println(entry.getKey() + " : "+entry.getValue());
        }

        System.err.println("7. What is the average salary of each department?");
        Map<String, Double > avgSalDept = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
        Set<Map.Entry<String, Double>> entries = avgSalDept.entrySet();
        for (Map.Entry<String, Double> entry : entries)
        {
            System.out.println(entry.getKey() +" : "+ entry.getValue());
        }

        System.err.println("8. Get the details of youngest male employee in the product development department?");
        Optional<Employee> youngestEmployee = employeeList.stream()
                .filter(emp -> emp.getGender().equals("Male") && emp.getDepartment().equals("Product Development"))
                .min(Comparator.comparing(Employee::getAge));
        System.out.println(youngestEmployee);

        System.err.println("9. Who has the most working experience in the organization?");
        Optional<Employee> mostWorkingExpEmp = employeeList.stream().sorted(Comparator.comparing(Employee::getYearOfJoining)).findFirst();
        System.out.println(mostWorkingExpEmp.get());

        System.err.println("10. How many male and female employees are there in the sales and marketing team?");
        Map<String, Long> maleAndFemaleInSaleMarketTeam = employeeList.stream()
                .filter(emp -> emp.getDepartment().equals("Sales And Marketing"))
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println(maleAndFemaleInSaleMarketTeam);

        System.err.println("11.  What is the average salary of male and female employees?");
        Map<String, Double> avgSalaryOfMaleAndFemale = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(avgSalaryOfMaleAndFemale);

        System.err.println("12. List down the names of all employees in each department?");
        Map<String, List<Employee>> employeesDepartmentList = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        Set<Map.Entry<String, List<Employee>>> entriess = employeesDepartmentList.entrySet();
        for(Map.Entry<String, List<Employee>> entry : entriess)
        {

            System.out.println("Department : "+ entry.getKey());
            System.out.println("------------------------------------");
            for(Employee emp : entry.getValue())
            {
                System.out.println(emp.getName());
            }
        }


        System.err.println("13. What is the average salary and total salary of the whole organization?");
        DoubleSummaryStatistics employeeLStats = employeeList.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println("Average Salary : "+employeeLStats.getAverage());
        System.out.println("Total Salary : "+employeeLStats.getSum());

        System.err.println("14. Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years.");
        Map<Boolean, List<Employee>> partitionEmployees = employeeList.stream()
                .collect(Collectors.partitioningBy( emp -> emp.getAge() > 25));

        Set<Map.Entry<Boolean, List<Employee>>> entriesss = partitionEmployees.entrySet();
        for (Map.Entry<Boolean, List<Employee>> entry: entriesss)
        {
            if(entry.getKey())
            {
                System.out.println("Employee older than 25 years");
            }else {
                System.out.println("Employee younger than or equals to 25 years");
            }
            System.out.println("------------------------------------------------");
            for(Employee emp : entry.getValue())
            {
                System.out.println(emp.getName());
            }
        }


        System.err.println("15. Who is the oldest employee in the organization? What is his age and which department he belongs to?");
        Optional<Employee> oldEmployee = employeeList.stream().max(Comparator.comparing(Employee::getAge));
        System.out.println("Name: "+oldEmployee.get().getName());
        System.out.println("Age: "+oldEmployee.get().getAge());
        System.out.println("Department: "+oldEmployee.get().getDepartment());

    }

}

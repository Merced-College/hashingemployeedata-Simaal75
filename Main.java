//Simaal Belgaumi
//CPSC 39
//Hash Table Lab

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ChainingHashTable<String, Employee> table = new ChainingHashTable<>(11);

        // make an ArrayList to store duplicate Employee objects
        ArrayList<Employee> duplicates = new ArrayList<>();

        // counters to keep track of total employees and duplicates
        int totalLoaded = 0;
        int duplicatesFound = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("Employee_data.csv"))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] cols = line.split(",", -1);
                if (cols.length < 6) continue;

                // Create the Employee object from the CSV
                Employee emp = new Employee(
                        cols[0].trim(),  // LAST NAME
                        cols[1].trim(),  // FIRST NAME
                        cols[2].trim(),  // JOB TITLE
                        cols[3].trim(),  // DEPARTMENT
                        parseMoney(cols[4]),
                        parseMoney(cols[5])
                );

                // increment your total counter
                totalLoaded++;

                // Create the hash key using last + first name
                String key = (emp.lastName + emp.firstName).toLowerCase();

                // use table.get(key) to see if an employee already exists
                Employee existing = table.get(key);
                if (existing != null) {
                    // if it exists, and it’s the same department, treat it as a duplicate
                    if (existing.department != null && emp.department != null &&
                            existing.department.equalsIgnoreCase(emp.department)) {
                        duplicates.add(emp);
                        duplicatesFound++;
                    } else {
                        // different department, still insert into the hash table
                        table.insert(key, emp);
                    }
                } else {
                    // key not in table yet, insert normally
                    table.insert(key, emp);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // print total employees, duplicates found, and duplicate list
        System.out.println("Total employees loaded: " + totalLoaded);
        System.out.println("Duplicates found: " + duplicatesFound);
        System.out.println();

        if (!duplicates.isEmpty()) {
            System.out.println("Duplicate employees (same name + department):");
            for (Employee dup : duplicates) {
                System.out.println(dup);
            }
        } else {
            System.out.println("No duplicates found.");
        }

        // --------------------------------------------------
        // Demonstrate CRUD Operations on the hash table
        // --------------------------------------------------
        System.out.println("\n--- CRUD Demo ---");

        // CREATE / INSERT
        Employee demoEmp = new Employee(
                "Doe",
                "John",
                "Developer",
                "IT",
                90000.0,
                90000.0
        );
        String demoKey = (demoEmp.lastName + demoEmp.firstName).toLowerCase();
        table.insert(demoKey, demoEmp);
        System.out.println("Inserted demo employee: " + demoEmp);

        // READ / GET
        Employee found = table.get(demoKey);
        if (found != null) {
            System.out.println("Found employee with key '" + demoKey + "': " + found);
        } else {
            System.out.println("Could not find employee with key '" + demoKey + "'");
        }

        // DELETE / REMOVE
        boolean removed = table.remove(demoKey);
        System.out.println("Removed employee with key '" + demoKey + "': " + removed);

        // VERIFY REMOVE
        Employee afterRemove = table.get(demoKey);
        if (afterRemove == null) {
            System.out.println("Confirmed: employee with key '" + demoKey + "' is no longer in the table.");
        } else {
            System.out.println("Employee still present after remove (something’s off): " + afterRemove);
        }
    }

    // helper for cleaning up salary strings
    private static double parseMoney(String s) {
        if (s == null || s.isBlank()) return 0.0;
        try {
            return Double.parseDouble(s.replace("$", "").replace(",", "").trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}

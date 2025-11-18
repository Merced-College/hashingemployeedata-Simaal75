public class Employee {

    public String lastName;
    public String firstName;
    public String jobTitle;
    public String department;
    public double annualSalary;
    public double estimatedAnnualMinusFurloughs;

    // No-argument constructor (required)
    public Employee() {
    }

    // Full constructor
    public Employee(String lastName, String firstName, String jobTitle,
                    String department, double annualSalary,
                    double estimatedAnnualMinusFurloughs) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.jobTitle = jobTitle;
        this.department = department;
        this.annualSalary = annualSalary;
        this.estimatedAnnualMinusFurloughs = estimatedAnnualMinusFurloughs;
    }

    // Getters
    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getDepartment() {
        return department;
    }

    public double getAnnualSalary() {
        return annualSalary;
    }

    public double getEstimatedAnnualMinusFurloughs() {
        return estimatedAnnualMinusFurloughs;
    }

    // Setters
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setAnnualSalary(double annualSalary) {
        this.annualSalary = annualSalary;
    }

    public void setEstimatedAnnualMinusFurloughs(double estimatedAnnualMinusFurloughs) {
        this.estimatedAnnualMinusFurloughs = estimatedAnnualMinusFurloughs;
    }

    // equals() based on lastName, firstName, department
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Employee other = (Employee) obj;

        if (lastName == null) {
            if (other.lastName != null) return false;
        } else if (!lastName.equals(other.lastName)) {
            return false;
        }

        if (firstName == null) {
            if (other.firstName != null) return false;
        } else if (!firstName.equals(other.firstName)) {
            return false;
        }

        if (department == null) {
            if (other.department != null) return false;
        } else if (!department.equals(other.department)) {
            return false;
        }

        return true;
    }

    // hashCode() based on lastName, firstName, department
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (lastName == null ? 0 : lastName.hashCode());
        result = 31 * result + (firstName == null ? 0 : firstName.hashCode());
        result = 31 * result + (department == null ? 0 : department.hashCode());
        return result;
    }

    // Optional: toString()
    @Override
    public String toString() {
        return firstName + " " + lastName +
               " | " + jobTitle +
               " | " + department +
               " | Salary: " + annualSalary +
               " | After furloughs: " + estimatedAnnualMinusFurloughs;
    }
}

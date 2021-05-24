package objects;

public class Employee 
{
    private String lastname;
    private String firstname;
    private String nationality;
    private int age;

    public Employee()
    {
        
    }

    public Employee(String lastname, String firstname)
    {
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public Employee(String lastname, String firstname, String nationality)
    {
        this.lastname = lastname;
        this.firstname = firstname;
        this.nationality = nationality;  
    }

    public Employee(String lastname, String firstname, String nationality, int age)
    {
        this.lastname = lastname;
        this.firstname = firstname;
        this.nationality = nationality;  
        this.age = age;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastName(String lastname)
    {
        this.lastname = lastname;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstName(String firstname)
    {
        this.firstname = firstname;
    }

    public String getNationality()
    {
        return nationality;
    }

    public void setNationality(String nationality)
    {
        this.nationality = nationality;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }
}

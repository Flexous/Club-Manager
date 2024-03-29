package objects;

public class Employee 
{
    private int id;

    private String lastname;
    private String firstname;
    private String nationality;
    private int age;

    private Club club;

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

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
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

    public Club getClub()
    {
        return club;
    }

    public void setClub(Club club)
    {
        this.club = club;
    }
}

package objects;

public class Player 
{
    private int id;

    private String firstname;
    private String lastname;
    private int number;
    private String dateOfBirth;
    private String imgSrc;
    private String nationality;

    private Club club;

    public Player()
    {
        
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Player(String firstname)
    {
        this.firstname = firstname;
    }
    
    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String name)
    {
        this.firstname = name;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String name)
    {
        this.lastname = name;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public String getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public String getImgSrc()
    {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc)
    {
        this.imgSrc = imgSrc;
    }

    public String getNationality()
    {
        return nationality;
    }

    public void setNationality(String nationality)
    {
        this.nationality = nationality;
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

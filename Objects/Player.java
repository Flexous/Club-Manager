package Objects;

public class Player 
{
    private String name;
    private String imgSrc="";
    private String nationality;
    private int age;

    public Player(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getImgSrc()
    {
        return this.imgSrc;
    }

    public void setImgSrc(String imgSrc)
    {
        this.imgSrc = imgSrc;
    }

    public String getNationality()
    {
        return this.nationality;
    }

    public void setNationality(String nationality)
    {
        this.nationality = nationality;
    }

    public int getAge()
    {
        return this.age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }
}
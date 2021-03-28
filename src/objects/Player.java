package objects;

import java.util.ArrayList;

public class Player 
{
    private String name;
    private int number;
    private String imgSrc="";
    private String nationality;
    private int age;

    private ArrayList<Property> properties = new ArrayList<>();

    public Player()
    {
        
    }

    public Player(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
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

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public ArrayList<Property> getProperties()
    {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties)
    {
        this.properties = properties;
    }
}

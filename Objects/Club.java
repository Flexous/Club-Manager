package Objects;

import java.awt.Color;
import java.util.ArrayList;

public class Club 
{
    private String name;
    private Color color1;
    private Color color2;
    private String logo="";
    
    private ArrayList<Property> properties = new ArrayList<>();

    //Stuff for later
    private ArrayList<Player>players = new ArrayList<>();

    public Club()
    {
        
    }

    public Club(String name)
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

    public Color getColor1()
    {
        return color1;
    }

    public void setColor1(Color color)
    {
        color1 = color;
    }

    public Color getColor2()
    {
        return color2;
    }

    public void setColor2(Color color)
    {
        color2 = color;
    }  

    public String getLogo()
    {
        return logo;
    }

    public void setLogo(String logo)
    {
        this.logo = logo;
    }

    public ArrayList<Property> getProperties()
    {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties)
    {
        this.properties = properties;
    }

    public void addPlayer(Player player)
    {
        players.add(player);
    }

    public void removePlayer(String playerName)
    {
        for (Player player : players)
        {
            if (player.getName().equals(playerName))
            {
                players.remove(player);
                return;
            }
        }
    }

    public void removeAllPlayers()
    {
        players.clear();
    }

    public void removePlayer(int playerIndex)
    {
        for (int i = 0; i < players.size(); i++)
        {
            if (i == playerIndex)
            {
                players.remove(playerIndex);
                return;
            }
        }
    }
}

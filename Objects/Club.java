package Objects;

import java.util.ArrayList;

public class Club 
{
    private String name;
    private String logoSrc="";
    private ArrayList<Player>players = new ArrayList<>();

    public Club(String name)
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

    public String getLogoSrc()
    {
        return this.logoSrc;
    }

    public void setLogoSrc(String logoSrc)
    {
        this.logoSrc = logoSrc;
    }

    public void addPlayer(Player player)
    {
        this.players.add(player);
    }

    public void removePlayer(String playerName)
    {
        for (Player player : this.players)
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
        this.players.clear();
    }

    public void removePlayer(int playerIndex)
    {
        for (int i = 0; i < this.players.size(); i++)
        {
            if (i == playerIndex)
            {
                players.remove(playerIndex);
                return;
            }
        }
    }
}

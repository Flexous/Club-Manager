package objects;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Club 
{
    private String appDataPath;

    private String name;
    private Color color1;
    private Color color2;
    private String logo;
    
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

    public String getAppDataPath()
    {
        return appDataPath;
    }

    public void setAppDataPath(String appDataPath)
    {
        this.appDataPath = appDataPath;
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

    public void setColor1(Color color1)
    {
        this.color1 = color1;
    }

    public Color getColor2()
    {
        return color2;
    }

    public void setColor2(Color color2)
    {
        this.color2 = color2;
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

    public void findAllPlayers()
    {
        File playerPath = new File(getAppDataPath()+"/Players");

        File [] playerFolders = playerPath.listFiles();

        for (File playerFolder : playerFolders)
        {
            String [] identifierParts = playerFolder.getName().split("- ");

            File playerFile = new File(playerFolder+"/"+identifierParts[1]+".player");

            try 
            {
                BufferedReader fileReader = new BufferedReader(new FileReader(playerFile));

                String line = "";

                Player player = new Player();

                while ((line=fileReader.readLine()) != null)
                {
                    String [] parts = line.split("=");

                    if (parts.length == 2)
                    {
                        if (parts[0].equals("Id"))
                        {
                            player.setId(Integer.parseInt(parts[1]));
                        }
                        else if (parts[0].equals("Name"))
                        {
                            player.setName(parts[1]);
                        }
                        else if (parts[0].equals("Number"))
                        {
                            player.setNumber(Integer.parseInt(parts[1]));
                        }
                    }
                }
                getPlayers().add(player);
                fileReader.close();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }

    public void addPlayer(Player player)
    {
        players.add(player);
    }

    public ArrayList<Player> getPlayers()
    {
        return players;
    }

    public void updatePlayer(Player updatedPlayer)
    {
        for (int i = 0; i < players.size(); i++)
        {
            if (players.get(i).getId() == updatedPlayer.getId())
            {
                players.set(i, updatedPlayer);
            }
        }
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

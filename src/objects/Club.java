package objects;

import java.awt.Color;
import java.util.ArrayList;

public class Club 
{
    private String name = "";
    private Color color1;
    private Color color2;
    private Color color3;
    private String logo = "";
    
    private String lastUpdateTime;
    private ArrayList<Property> properties = new ArrayList<>();

    //Stuff for later
    private ArrayList<Player>players = new ArrayList<>();

    private Manager manager;

    private ArrayList<Employee>employees = new ArrayList<>();

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
    
    public Color getColor3()
    {
        return color3;
    }

    public void setColor3(Color color3)
    {
        this.color3 = color3;
    } 

    public String getLogo()
    {
        return logo;
    }

    public void setLogo(String logo)
    {
        this.logo = logo;
    }

    public String getLastUpdateTime()
    {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime)
    {
        this.lastUpdateTime = lastUpdateTime;
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

    }

    public void addEmployee(Employee employee)
    {
        employees.add(employee);
    }

    public ArrayList<Employee> getEmployees()
    {
        return employees;
    }

    public void updateEmployee(Employee updateEmployee)
    {
        for (int i = 0; i < players.size(); i++)
        {
            if (employees.get(i).getId() == updateEmployee.getId())
            {
                employees.set(i, updateEmployee);
            }
        }
    }

    public void removeEmployee(String employeeName)
    {
        for (Employee employee : employees)
        {
            if (employee.getFirstname().equals(employeeName))
            {
                employees.remove(employee);
                return;
            }
        }
    }

    public void removeAllEmployees()
    {
        employees.clear();
    }

    public void removeEmployee(int employeeIndex)
    {
        for (int i = 0; i < employees.size(); i++)
        {
            if (i == employeeIndex)
            {
                employees.remove(employeeIndex);
                return;
            }
        }
    }

    public void setManager(Manager manager)
    {
        this.manager = manager;
    }

    public Manager getManager()
    {
        return manager;
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
            if (player.getFirstname().equals(playerName))
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

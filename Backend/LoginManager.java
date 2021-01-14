package Backend;

public class LoginManager 
{
    private boolean loginCompleted;

    public void checkLastLogin()
    {
        loginCompleted = true;
    } 

    public boolean isLoginCompleted()
    {
        return loginCompleted;
    }
}

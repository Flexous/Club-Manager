package Backend.Language;

public class Language 
{
    public static String NoClubCreated;
    public static String NameOfClub;
    public static String Color1;
    public static String Color2;
    public static String ColorSelection;
    public static String BadgeOfClub; 
    public static String Images;
    public static String CreateClub;
    public static String YourClub;
    public static String AddPlayer;
    public static String ChangeLanguage;
    public static String AppInfo; 
    public static String DoYouWantToSave;
    public static String Yes;
    public static String No;

    public static void applyLanguage(String currentLangauge)
    {
        if (currentLangauge != null)
        {
            if (currentLangauge.equals("GER"))
            {
                NoClubCreated = "Du hast noch keinen Club erstellt.";
                NameOfClub = "Name des Clubs";
                Color1 = "Farbe 1";
                Color2 = "Farbe 2";
                ColorSelection = "Farbauswahl";
                BadgeOfClub = "Logo des Clubs";
                Images = "Bilder";
                CreateClub = "Club erstellen";
                YourClub = "Dein Verein: ";
                AddPlayer = "Spieler hinzufügen";
                ChangeLanguage = "Sprache ändern";
                AppInfo = "Info";
                DoYouWantToSave = "Möchtest du speichern?";
                Yes = "Ja";
                No = "Nein";
            }
            else if (currentLangauge.equals("ENG"))
            {
                NoClubCreated = "You haven't created a club yet.";
                NameOfClub = "Name of club";
                Color1 = "Color 1";
                Color2 = "Color 2";
                ColorSelection = "Color selection";
                BadgeOfClub = "Badge of club";
                Images = "Images";
                CreateClub = "Create club";
                YourClub = "Your club: ";
                AddPlayer = "Add player";
                ChangeLanguage = "Switch language";
                AppInfo = "Info";
                DoYouWantToSave = "Do you want to save?";
                Yes = "Yes";
                No = "No";
            }
        }
    }
}

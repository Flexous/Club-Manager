package backend;

import backend.language.Language;

public class ClubManagerConstraints 
{
    public static final Application APP = new Application();
    public static final Language LANGUAGE = new Language();

    public static final String CLUBFILEPATH = APP.getAppDataPath()+"/Club Manager/Clubs/";
    public static final String PROPERTYFILEPATH = APP.getAppDataPath()+"/Club Manager/ClubManager.properties";
    
    public static final String DEFAULTLOGOPATH = "files/img/Default.png";
    public static final String APPFONT = "Arial";
}

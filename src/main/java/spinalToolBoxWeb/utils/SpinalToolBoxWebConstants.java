package spinalToolBoxWeb.utils;

/**
 * Created by Laobien Jehiel KY - kjehiel@gmail.com on 2014-09-28.
 */


public class SpinalToolBoxWebConstants {

    /*UPLOAD*/
   public static final String UPLOAD_FILE_PATH = "/home/adminer/upload";

   // path sur MAC : à modifier dans le futur
    //public static final String UPLOAD_FILE_PATH = "/usr/local/apache-tomcat-8/webapps/uploadedFiles";

    //public static final String UPLOAD_FILE_PATH = "C:/Users/Joe Kh/Desktop/INF4990/INF4990_MASTER_1/inf4990-02/spinal/src/main/resources/viewRessouces/uploadedFile";
    //public static final String UPLOAD_FILE_PATH_TOMCAT = "resources/viewRessouces/uploadedFile";

    //public static final String UPLOAD_FILE_PATH = "C:/INF4990_upload";

    public static final String ERROR_OCCURED = "error_occured";
    public static final String NO_ERROR_OCCURED = "no_error_occured";
    public static final String BACK_END_ERROR = "BackEnd execution ERROR !";

    /*VIEWS*/
    public static final String VIEW_HOME = "index";
    public static final String VIEW_VOLUME = "volumeViewerTest";
    public static final String VIEW_UPLOADED_FILE = "index";
    public static final String VIEW_ABOUT = "about";
    public static String VIEW_COMPARE_RESULT ="compareResult";
    public static final String VIEW_COMMAND ="command";

    public static final String COMMAND_VIOLATION = "Command Violation: cannot be null";
    public static boolean FIRST_UPLOAD = true;
    public static String APP_TOKEN;

}

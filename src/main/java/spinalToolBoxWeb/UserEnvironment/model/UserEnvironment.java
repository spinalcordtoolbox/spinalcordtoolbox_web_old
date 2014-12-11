package spinalToolBoxWeb.UserEnvironment.model;

import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.Serializable;

/**
 * Created by Jehiel KY - kjehiel@gmail.com on 07/11/14.
 */


public class UserEnvironment implements Serializable {

    private static final long serialVersionUID = 1L;
    private static String token;
    private static String userUploadPath;
    private static String userUploadPathWithEndSeparator;
    private File lastModifiedFile = null;

    @Value("${uploadPath}")
    private String userUploadBasePath;



    public File getLastModifiedFile() {
        return this.lastModifiedFile;
    }

    public void setLastModifiedFile(File lastModifiedFile) {
        this.lastModifiedFile = lastModifiedFile;
    }



    public UserEnvironment() {
        System.out.println("UserEnvironment::UserEnvironment model created " + this);
    }

    public static String getUserUploadPathWithEndSeparator() {
        return userUploadPathWithEndSeparator;
    }

    public static void setUserUploadPathWithEndSeparator(String userUploadPathWithEndSeparator) {
        UserEnvironment.userUploadPathWithEndSeparator = userUploadPathWithEndSeparator;
    }



    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        UserEnvironment.token = token;
    }


    public static String getUserUploadPath() {
        return userUploadPath;
    }

    public static void setUserUploadPath(String uploadPath) {
        UserEnvironment.userUploadPath = uploadPath;
    }


    public String getUserUploadBasePath() {
        return userUploadBasePath;
    }

    public void setUserUploadBasePath(String userUploadBasePath) {
        this.userUploadBasePath = userUploadBasePath;
    }


}

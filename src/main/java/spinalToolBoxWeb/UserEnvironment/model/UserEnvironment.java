package spinalToolBoxWeb.UserEnvironment.model;

import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

/**
 * Created by Jehiel KY - kjehiel@gmail.com on 07/11/14.
 */

//This class is a singleton. I will let Spring handle the bean singleton instead of using singleton design patter


public class UserEnvironment {
//    private static final long serialVersionUID = 1L;
    private static boolean isFirstUpload = true;
    private static String token;
    private static String userUploadPath;
    private static String userUploadPathWithEndSeparator;
    @Value("${uploadPath}")
    private String userUploadBasePath;

    public UserEnvironment() {
        System.out.println("UserEnvironment::UserEnvironment model created " + this);
    }

    public static String getUserUploadPathWithEndSeparator() {
        return userUploadPathWithEndSeparator;
    }

    public static void setUserUploadPathWithEndSeparator(String userUploadPathWithEndSeparator) {
        UserEnvironment.userUploadPathWithEndSeparator = userUploadPathWithEndSeparator;
    }

    public static boolean isIsFirstUpload() {
        return isFirstUpload;
    }

    public static void setIsFirstUpload(boolean isFirstUpload) {
        UserEnvironment.isFirstUpload = isFirstUpload;
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

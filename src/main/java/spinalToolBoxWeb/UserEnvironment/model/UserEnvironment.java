package spinalToolBoxWeb.UserEnvironment.model;

/**
 * Created by Jehiel KY - kjehiel@gmail.com on 07/11/14.
 */

//This class is a singleton. I will let Spring handle the bean singleton instead of using singleton design patter

public class UserEnvironment {

    private static boolean isFirstUpload = true;
    private static String token;
    private static String userUploadPath;
    private static String userUploadPathWithEndSeparator;



    public UserEnvironment() {

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


}

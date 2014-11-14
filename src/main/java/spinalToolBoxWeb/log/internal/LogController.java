package spinalToolBoxWeb.log.internal;

/**
 * LogController.java
 * This singleton is responsible for logging everything that happens
 * Created by Laobien Jehiel KY - kjehiel@gmail.com on 2014-09-28.
 */

public class LogController {
    private static LogController instance = new LogController();

    //TODO:Java log

    public static LogController getInstance(){
        return instance;
    }


}

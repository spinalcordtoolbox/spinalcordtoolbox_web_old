package spinalToolBoxWeb.UserEnvironment.internal;

import org.springframework.beans.factory.annotation.Autowired;
import spinalToolBoxWeb.UserEnvironment.model.UserEnvironment;

/**
 * Created by root on 10/11/14.
 */
public class UserEnvironmentController {
    @Autowired
    UserEnvironment userEnvironment;

    public UserEnvironmentController() {
        System.out.println("UserEnvironmentController:: UserEnvironmentController created " + this);
    }

    public UserEnvironment getUserEnvironment() {
        System.out.println("UserEnvironmentController::getUserEnvironment userEnvironment= " + userEnvironment);
        return userEnvironment;
    }


    public void setUserEnvironment(UserEnvironment userEnvironment) {
        this.userEnvironment = userEnvironment;
    }
}

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
    }

    public UserEnvironment getUserEnvironment() {
        return userEnvironment;
    }


    public void setUserEnvironment(UserEnvironment userEnvironment) {
        this.userEnvironment = userEnvironment;
    }
}

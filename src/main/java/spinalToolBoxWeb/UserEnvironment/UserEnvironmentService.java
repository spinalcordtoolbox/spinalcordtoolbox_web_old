package spinalToolBoxWeb.UserEnvironment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import spinalToolBoxWeb.UserEnvironment.internal.UserEnvironmentController;
import spinalToolBoxWeb.UserEnvironment.model.UserEnvironment;

/**
 * Created by root on 07/11/14.
 */
@Service
@Scope("request")
public class UserEnvironmentService {

    @Autowired
    UserEnvironmentController userEnvironmentController;

    public UserEnvironmentService() {
        System.out.println("UserEnvironmentService:: env service created" + this);
    }

    public UserEnvironment getUSerEnvironment(){
        System.out.println("UserEnvironmentService::getUSerEnvironment userEnvironmentController =" + userEnvironmentController);
        return userEnvironmentController.getUserEnvironment();
    }
}

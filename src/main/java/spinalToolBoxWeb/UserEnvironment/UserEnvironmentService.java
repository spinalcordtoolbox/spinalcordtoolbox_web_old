package spinalToolBoxWeb.UserEnvironment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spinalToolBoxWeb.UserEnvironment.internal.UserEnvironmentController;
import spinalToolBoxWeb.UserEnvironment.model.UserEnvironment;

/**
 * Created by root on 07/11/14.
 */
@Service
public class UserEnvironmentService {

    @Autowired
    UserEnvironmentController userEnvironmentController;

    public UserEnvironmentService() {
    }

    public UserEnvironment getUSerEnvironment(){
        return userEnvironmentController.getUserEnvironment();
    }
}

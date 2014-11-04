package spinalToolBoxWeb.log;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import spinalToolBoxWeb.log.internal.LogController;

/**
 * Created by Laobien Jehiel KY - kjehiel@gmail.com on 2014-09-28.
 */
@Service
public class LogService {


    @Autowired
    private LogController logController;

    public LogService() {};
}

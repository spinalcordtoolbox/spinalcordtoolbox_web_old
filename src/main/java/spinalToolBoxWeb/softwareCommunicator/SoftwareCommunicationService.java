package spinalToolBoxWeb.softwareCommunicator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spinalToolBoxWeb.softwareCommunicator.internal.SoftwareCommunicationController;

import java.io.IOException;

/**
 * Created by Laobien Jehiel KY - kjehiel@gmail.com on 2014-09-26.
 */

@Service
public class SoftwareCommunicationService
{

    @Autowired
    private SoftwareCommunicationController softwareCommunicationController;

    public SoftwareCommunicationService(){}

    public String executeProcess(String command) throws IOException
    {
        return softwareCommunicationController.executeProcess(command);
    }

}

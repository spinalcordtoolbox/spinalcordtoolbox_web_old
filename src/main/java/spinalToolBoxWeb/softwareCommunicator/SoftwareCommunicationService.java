package spinalToolBoxWeb.softwareCommunicator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spinalToolBoxWeb.softwareCommunicator.internal.SoftwareCommunicationController;

import java.io.File;
import java.io.IOException;

/**
 * Created by Laobien Jehiel KY - kjehiel@gmail.com on 2014-09-26.
 */

@Service
@Scope("request")
public class SoftwareCommunicationService
{

    @Autowired
    private SoftwareCommunicationController softwareCommunicationController ;

    public SoftwareCommunicationService(){
        System.out.println("SoftwareCommunicationService:: SoftwareCommunicationService created " + this);


    }

    public String runNConsecutiveCommands(String... commands) throws IOException
    {
        return softwareCommunicationController.runNConsecutiveCommands(commands);
    }

    public String generateRawAndHeader(MultipartFile file) throws IOException
    {
        return softwareCommunicationController.generateRawAndHeader(file);
    }

    public String generateRawAndHeader(File file) throws IOException
    {
        return softwareCommunicationController.generateRawAndHeader(file);
    }

    public String runCommandInPath(String path, String command) throws IOException
    {
        System.out.println("SoftwareCommunicationService::runCommandInPath using SoftwareCommunicationController " + softwareCommunicationController);
        return softwareCommunicationController.runCommandInPath(path, command);
    }

    public File getLastModifiedFile(String path) throws IOException
    {
        return softwareCommunicationController.getLastModifiedFile(path);
    }

}

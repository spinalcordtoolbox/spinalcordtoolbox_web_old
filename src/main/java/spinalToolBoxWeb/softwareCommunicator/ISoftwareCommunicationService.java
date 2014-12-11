package spinalToolBoxWeb.softwareCommunicator;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by Laobien Jehiel KY - kjehiel@gmail.com on 2014-09-26.
 */


public interface ISoftwareCommunicationService
{
    public String executeCommand(String command) throws IOException ;

    public String runNConsecutiveCommands(String... commands) throws IOException;

    public String generateRawAndHeader(MultipartFile file) throws IOException;

    public String generateRawAndHeader(File file) throws IOException;

    public String runCommandInPath(String path, String command) throws IOException;

    public File getLastModifiedFile(String path);

    public LinkedList<File> getLastModifiedFiles(String path);
}
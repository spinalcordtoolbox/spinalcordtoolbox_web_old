package spinalToolBoxWeb.softwareCommunicator.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import spinalToolBoxWeb.UserEnvironment.UserEnvironmentService;
import spinalToolBoxWeb.command.model.CommandUtils;
import spinalToolBoxWeb.utils.SpinalToolBoxWebConstants;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Laobien Jehiel KY - kjehiel@gmail.com on 2014-09-26.
 */

public class SoftwareCommunicationController
{

    @Autowired
    private UserEnvironmentService userEnvironmentService;

    @Value("${script_generating_raw_header}") private String script_generating_rawHeader;

    public SoftwareCommunicationController() {
        System.out.println("SoftwareCommunicationController:: SoftwareCommunicationController created " + this);

    }


    public String executeCommand(String command) throws IOException {

        if(command != null) {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec(command);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line, consoleOutMessage = "";
            while ((line = bfr.readLine()) != null) {
                consoleOutMessage = consoleOutMessage + line;
                System.out.println(line);
            }
            return consoleOutMessage;
        }
        else
            return SpinalToolBoxWebConstants.COMMAND_VIOLATION;
    }

    /**
     * Convert Niftii file to Minc
     * */
    public String generateRawAndHeader(MultipartFile file) throws IOException{
        return generateRawAndHeaderProcess(file.getOriginalFilename());
    }

    /**
     * Convert Niftii file to Minc
     * */
    public String generateRawAndHeader(File file) throws IOException{
        return generateRawAndHeaderProcess(file.getName());
    }

    private String generateRawAndHeaderProcess(String fileOriginalName) throws IOException {
        System.out.println("SoftwareCommunicationController::generateRawAndHeaderProcess using UserEnvironmentService" + userEnvironmentService);
        String path = userEnvironmentService.getUSerEnvironment().getUserUploadPathWithEndSeparator();
        return runNConsecutiveCommands(CommandUtils.generateCommand_convert_nii_niigz_minc_to_header_raw(script_generating_rawHeader,path, fileOriginalName));
    }


    /**
     * This function runs N consecutives commands
     * */
     public String runNConsecutiveCommands(String... commands) throws IOException
     {
         String outputRuntime = null;

             if(commands.length > 0)
             {
                 for (String command : commands)
                 {
                     if (command != null)
                     {
                         Process pr = Runtime.getRuntime().exec(command);
                         String line = "";
                         BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                         while ((line = bfr.readLine()) != null)
                             outputRuntime = outputRuntime + line;
                         pr.destroy();
                     } else
                         outputRuntime = SpinalToolBoxWebConstants.COMMAND_NULL;
                 }
             }
         return outputRuntime;

     }

    public String runCommandInPath(String path, String command) throws IOException
    {
        String outputRuntime = null;

        File pathDir = new File(path);

        Process pr = Runtime.getRuntime().exec(command, null , pathDir);

        String line = "";
        BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));

        while ((line = bfr.readLine()) != null)
        {
            outputRuntime = outputRuntime + line;
        }

        return outputRuntime;

    }

    public File getLastModifiedFile(String path)
    {

        File directory = new File(path);
        File[] listFiles = directory.listFiles();
        File lastModifiedFile = listFiles[0];
        for(int i =1 ; i < listFiles.length ; i++)
        {
            if(lastModifiedFile.lastModified() < listFiles[i].lastModified())
            {
                lastModifiedFile = listFiles[i];
            }
        }

        return lastModifiedFile;

    }

}

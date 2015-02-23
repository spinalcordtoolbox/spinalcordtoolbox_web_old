package spinalToolBoxWeb.softwareCommunicator.internal;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import spinalToolBoxWeb.UserEnvironment.IUserEnvironmentService;
import spinalToolBoxWeb.command.model.CommandUtils;
import spinalToolBoxWeb.softwareCommunicator.ISoftwareCommunicationService;
import spinalToolBoxWeb.utils.SpinalToolBoxWebConstants;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by Laobien Jehiel KY - kjehiel@gmail.com on 2014-09-26.
 */

public class SoftwareCommunicationController implements ISoftwareCommunicationService
{

    @Autowired
    private IUserEnvironmentService userEnvironmentService;

    /**
     * Gets the script name reponsible for generating raw and header file from config.properties file.
     * The purpose of this is that if the script name changes,there is no need to change the java code.
     */
    @Value("${script_generating_raw_header}") private String script_generating_rawHeader;

    public SoftwareCommunicationController() {
        System.out.println("SoftwareCommunicationController:: SoftwareCommunicationController created " + this);
    }

    /**
     * This function takes a command and excute it in the terminal.
     *
     * @param command
     * @return terminal log
     * @throws IOException
     */
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
     * Generate raw and header file
     * @param  file - type Multipartfile
     * @return terminal log
     * @throws IOException
     */
    public String generateRawAndHeader(MultipartFile file) throws IOException{
        return generateRawAndHeaderProcess(file.getOriginalFilename());
    }

    /**
     * Generate raw and header file
     * @param  file - type file
     * @return terminal log
     * @throws IOException
     */
    public String generateRawAndHeader(File file) throws IOException{
        return generateRawAndHeaderProcess(file.getName());
    }

    /**
     * Takes the original file name and runs  the command to generate raw and header files
     * @param fileOriginalName
     * @return terminal log
     * @throws IOException
     */
    private String generateRawAndHeaderProcess(String fileOriginalName) throws IOException {
        System.out.println("SoftwareCommunicationController::generateRawAndHeaderProcess using UserEnvironmentService" + userEnvironmentService);
        String path = userEnvironmentService.getUserEnvironment().getUserUploadPathWithEndSeparator();
        return runNConsecutiveCommands(CommandUtils.generateCommand_convert_nii_niigz_minc_to_header_raw(script_generating_rawHeader,path, fileOriginalName));
    }


    /**
     * This function runs N consecutives commands
     *
     * @param commands takes has many commands you want. Note:: these commands has to be independants since
     *                 the process running the first command will be killed. So if command N process needs a state from
     *                 command N-1 process, this won't work
     * @return terminal log
     * @throws IOException
     */
     public String runNConsecutiveCommands(String... commands) throws IOException{
         String outputRuntime = null;
             if(commands.length > 0){
                 for (String command : commands){
                     if (command != null){
                         try{
                             Process pr = Runtime.getRuntime().exec(command);
                             String line = "";
                             BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                             while ((line = bfr.readLine()) != null)
                                 outputRuntime = outputRuntime + line;
                             pr.destroy();
                         }
                         catch(IOException e){
                             outputRuntime = SpinalToolBoxWebConstants.BACK_END_ERROR;
                             System.out.println(e.getStackTrace());
                         }
                     }
                     else{
                         outputRuntime = SpinalToolBoxWebConstants.COMMAND_NULL;
                     }
                 }
             }
         return outputRuntime;
     }

    /**
     * Runs a command in a specified folder
     * @param path
     * @param command
     * @return terminal log
     * @throws IOException
     */
    public String runCommandInPath(String path, String command) throws IOException{
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

    /**
     * Gets last modified file
     * @param path
     * @return last modified file
     */
    public File getLastModifiedFile(String path){
        File directory = new File(path);
        File[] listFiles = directory.listFiles();
        File lastModifiedFile = listFiles[0];
        for(int i =1 ; i < listFiles.length ; i++){
            if((lastModifiedFile.lastModified() < listFiles[i].lastModified()) && (!listFiles[i].isHidden()) && (!(((Character)(listFiles[i].getName()).charAt(0))).equals(new Character('.'))))
            {
                lastModifiedFile = listFiles[i];
            }
        }
        return lastModifiedFile;
    }

    public LinkedList<File> getLastModifiedFiles(String path){
        File directory = new File(path);
        File[] allFilesInDirectory = directory.listFiles();

        File lastModifiedFile = userEnvironmentService.getUserEnvironment().getLastModifiedFile();
        LinkedList<File> newlyGeneratedFiles = new LinkedList<>();

        for(int i = 0 ; i < allFilesInDirectory.length ; i++){
            if((lastModifiedFile.lastModified() < allFilesInDirectory[i].lastModified()) && (!allFilesInDirectory[i].isHidden())){
                String extension = FilenameUtils.getExtension(allFilesInDirectory[i].getName());
                if( !(extension == "header") && !(extension == "raw") )  newlyGeneratedFiles.add(allFilesInDirectory[i]);
            }
        }
        return newlyGeneratedFiles;
    }
}

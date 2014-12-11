package spinalToolBoxWeb; /**
 * Created by  Laobien Jehiel KY - - kjehiel@gmail.com on ----2014-09-20.
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import spinalToolBoxWeb.UserEnvironment.IUserEnvironmentService;
import spinalToolBoxWeb.fileOperations.IFileOperationsService;
import spinalToolBoxWeb.serverResponse.IServerResponse;
import spinalToolBoxWeb.serverResponse.ServerResponseModel;
import spinalToolBoxWeb.softwareCommunicator.ISoftwareCommunicationService;
import spinalToolBoxWeb.utils.SpinalToolBoxWebConstants;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

/**
 * This class is our app controller.
 * It defines the path (routage) for urls and actions do to
 *
 */
@Controller
public class SpinalToolboxWebController {

    @Autowired
    private IFileOperationsService fileOperationsService;

    @Autowired
    private IServerResponse serverResponse;


    @Autowired
    private ISoftwareCommunicationService softwareCommunicationService;

    @Autowired
    private IUserEnvironmentService userEnvironmentService;


    @RequestMapping(value = "/")
    public ModelAndView home(){
        System.out.println("Passing throught home controller");
        return new ModelAndView(SpinalToolBoxWebConstants.VIEW_HOME, "result", "command submitted : ");
    }



    /**
     * When we upload a file, the request is processed here
     * */
    @RequestMapping(value="/upload", method = RequestMethod.POST, produces="application/json")
    public  @ResponseBody
    ServerResponseModel handleUploadedFiles(@RequestParam(value = "file") MultipartFile file,
                                                      @RequestParam(value="token") String token)throws IOException {

        System.out.println("Passing throught upload controller");



        if(!fileOperationsService.isUploadedFileExtensionAllowed(file.getOriginalFilename())){
            serverResponse.setUndefinedResponse();
            return serverResponse.getServerResponse();
        }

        if(fileOperationsService.uploadFile(file, token)){
            serverResponse.setResponse(file, softwareCommunicationService.generateRawAndHeader(file));
            File lastModifiedFile = softwareCommunicationService.getLastModifiedFile(userEnvironmentService.getUserEnvironment().getUserUploadPathWithEndSeparator());
            userEnvironmentService.getUserEnvironment().setLastModifiedFile(lastModifiedFile);
        }
        else{
            serverResponse.setUndefinedResponse();
        }
        return serverResponse.getServerResponse();
    }


    @RequestMapping(value="/command", method = RequestMethod.POST, produces="application/json")
    public  @ResponseBody ServerResponseModel  handleProcessCommand(@RequestParam(value = "command") String command)throws IOException
    {
        System.out.println("Passing throught command controller");
        String path = userEnvironmentService.getUserEnvironment().getUserUploadPathWithEndSeparator();
        String outputRuntime = null;

        softwareCommunicationService.runCommandInPath(path, command);
        LinkedList<File> lastModifiedFiles = softwareCommunicationService.getLastModifiedFiles(path);

        for(int i = 0 ; i < lastModifiedFiles.size() ; i++)
        {
            outputRuntime+= softwareCommunicationService.generateRawAndHeader(lastModifiedFiles.get(i));
            serverResponse.setResponse(lastModifiedFiles.get(i).getName(), outputRuntime);
        }
        userEnvironmentService.getUserEnvironment().setLastModifiedFile(softwareCommunicationService.getLastModifiedFile(path));
        return serverResponse.getServerResponse();
    }


    @RequestMapping(value = "/deleteFiles", method = RequestMethod.POST)
    public  @ResponseBody String handleDeleteFile(@RequestParam(value = "fileName") String fileName) throws IOException
    {
        System.out.println("Passing throught delete files controller");
        fileOperationsService.cleanup(fileName);
        return "ServerDeleteResponse";
    }

    @RequestMapping(value = "/deleteRepoFiles", method = RequestMethod.POST)
    public  @ResponseBody String handleDeleteRepoFile() throws IOException
    {
        System.out.println("Passing throught delete repo files controller");
        fileOperationsService.cleanupRepository();
        return "ServerDeleteResponse";
    }

}

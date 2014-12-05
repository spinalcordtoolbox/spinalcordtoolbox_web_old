package spinalToolBoxWeb; /**
 * Created by  Laobien Jehiel KY on ----2014-09-20.
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.config.java.annotation.aop.ScopedProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import spinalToolBoxWeb.UserEnvironment.UserEnvironmentService;
import spinalToolBoxWeb.fileOperations.FileOperationsService;
import spinalToolBoxWeb.fileOperations.model.ServerResponse;
import spinalToolBoxWeb.softwareCommunicator.SoftwareCommunicationService;
import spinalToolBoxWeb.utils.SpinalToolBoxWebConstants;

import java.io.File;
import java.io.IOException;


@Controller
@Scope("request")
public class SpinalToolboxWebController {

    @Autowired
    private FileOperationsService fileOperationsService;


   /* @Autowired
    private Comparator<String> comparator;*/

    @Autowired
    private ServerResponse serverResponse;

    @Autowired
    private SoftwareCommunicationService softwareCommunicationService;

 	@Autowired
    private StringBuffer stringBuffer;

    @Autowired
    private UserEnvironmentService userEnvironmentService;

    @RequestMapping(value = "/")
    public ModelAndView home(){
        System.out.println("Passing throught home controller");
        return new ModelAndView(SpinalToolBoxWebConstants.VIEW_HOME, "result", "command submitted : ");
    }

 /*   @RequestMapping(value="/compare", method = RequestMethod.GET)
    public String compare(@RequestParam("price1") String price1, @RequestParam("price2") String price2, Model model){
        int result = comparator.compare(price1, price2);
        String resultMsg = (result < 0) ? " " + price1 + " less than" + price2+ " " : price2 + "less than" + price1;
        model.addAttribute("resultMsg" , resultMsg);
        return SpinalToolBoxWebConstants.VIEW_COMPARE_RESULT;
    }*/


    @RequestMapping(value="/upload", method = RequestMethod.POST, produces="application/json")
    public  @ResponseBody
    ServerResponse handleUploadedFiles(@RequestParam(value = "file") MultipartFile file,
                                                      @RequestParam(value="token") String token)throws IOException {

        System.out.println("Passing throught upload controller");

        if(!fileOperationsService.isUploadedFileExtensionAllowed(file.getOriginalFilename()))
        {
            serverResponse.setUndefinedResponse();
            return serverResponse;
        }

        if(fileOperationsService.uploadFile(file, token)){
            serverResponse.setResponse(file, softwareCommunicationService.generateRawAndHeader(file));
        }
        else{
            serverResponse.setUndefinedResponse();
        }
        return serverResponse;
    }




    /*
    * Here it's important to return a string. may be after we will specify JSON but there's not matter here.
    * If we put modelview, the whole view with the html code will be sent to the console and it will
    * display a big html code.
    * THATS NOT WHAT WE WANT HERE
    * */

/*     @RequestMapping(value="/command", method = RequestMethod.POST)
    public  @ResponseBody String  handleCommands(@RequestParam("command") String command)throws IOException {
        System.out.println("Passing throught command controller");
        String result = commandGeneratorService.handleCommand(command);
        //model.addAttribute("consoleOut", result);
        //return new ModelAndView(SpinalToolBoxWebConstants.VIEW_HOME, "consoleOut", result);
        return result;
    }
*/


    @RequestMapping(value="/command", method = RequestMethod.POST, produces="application/json")
    public  @ResponseBody ServerResponse  handleProcessCommand(@RequestParam(value = "command") String command)throws IOException
    {
        System.out.println("Passing throught command controller");
        String path = userEnvironmentService.getUSerEnvironment().getUserUploadPathWithEndSeparator();
        String outputRuntime = null;

        softwareCommunicationService.runCommandInPath(path, command);

        File lastModifiedFile = softwareCommunicationService.getLastModifiedFile(path);

        try{
            outputRuntime+= softwareCommunicationService.generateRawAndHeader(lastModifiedFile);
        }
        catch (Exception ex){
            outputRuntime = SpinalToolBoxWebConstants.BACK_END_ERROR;
            System.out.println(ex.getStackTrace());
        }
        //String fileNameWithOutExt = FilenameUtils.removeExtension(lastModifiedFile.getName());

        serverResponse.setResponse(lastModifiedFile.getName(), outputRuntime);
        return serverResponse;
    }



    @RequestMapping(value = "/about", method = {RequestMethod.GET, RequestMethod.POST}) String handleAboutUs(){
        return SpinalToolBoxWebConstants.VIEW_ABOUT;
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

        fileOperationsService.cleanupRepo();

        return "ServerDeleteResponse";

    }



}

package spinalToolBoxWeb; /**
 * Created by  Laobien Jehiel KY on ----2014-09-20.
 */


import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import spinalToolBoxWeb.TreeGenerator.TreeGeneratorService;
import spinalToolBoxWeb.UserEnvironment.UserEnvironmentService;
import spinalToolBoxWeb.fileUpload.EnvRuntime;
import spinalToolBoxWeb.fileUpload.FileUploadResponse;
import spinalToolBoxWeb.fileUpload.FileUploadService;
import spinalToolBoxWeb.softwareCommunicator.SoftwareCommunicationService;
import spinalToolBoxWeb.utils.SpinalToolBoxWebConstants;

import java.io.IOException;
import java.util.Comparator;



@Controller
public class SpinalToolboxWebController {

    @Autowired
    private FileUploadService fileUploadService;


    @Autowired
    private Comparator<String> comparator;

    @Autowired
    private TreeGeneratorService treeGeneratorService;

    @Autowired
    private FileUploadResponse fileUploadResponse;

    @Autowired
    private SoftwareCommunicationService softwareCommunicationService;

    @Autowired
    EnvRuntime envRuntime;

 	@Autowired
    private StringBuffer stringBuffer;

    @Autowired
    private UserEnvironmentService userEnvironmentService;

    @RequestMapping(value = "/")
    public ModelAndView home(){
        System.out.println("Passing throught home controller");

        treeGeneratorService.deleteGeneratedTreeElements();

        return new ModelAndView(SpinalToolBoxWebConstants.VIEW_HOME, "result", "command submitted : ");
    }

    @RequestMapping(value="/compare", method = RequestMethod.GET)
    public String compare(@RequestParam("price1") String price1, @RequestParam("price2") String price2, Model model){
        int result = comparator.compare(price1, price2);
        String resultMsg = (result < 0) ? " " + price1 + " less than" + price2+ " " : price2 + "less than" + price1;
        model.addAttribute("resultMsg" , resultMsg);
        return SpinalToolBoxWebConstants.VIEW_COMPARE_RESULT;
    }


    @RequestMapping(value="/upload", method = RequestMethod.POST, produces="application/json")
    public  @ResponseBody FileUploadResponse  handleUploadedFiles(@RequestParam(value = "file") MultipartFile file,
                                                      @RequestParam(value="token") String token)throws IOException {

        System.out.println("Passing throught upload controller");

        boolean uploadSuccess = fileUploadService.uploadFile(file, token);

        if(uploadSuccess)
        {


            String path = userEnvironmentService.getUSerEnvironment().getUserUploadPathWithEndSeparator();
            String fileNameWithOutExt = FilenameUtils.removeExtension(file.getOriginalFilename());

            String cd = "cd " + path;
            String commandConvNiiToMNC = "sct_convert -i " + file.getOriginalFilename() + " -o " + fileNameWithOutExt + ".mnc";
            String commandHeaderRaw = "isct_minc2volume-viewer " + fileNameWithOutExt + ".mnc";

            String commandTot = cd + " && " + commandConvNiiToMNC + " && " + commandHeaderRaw;

            String outputRuntime = null;

            try {
                outputRuntime = envRuntime.executeCommand(commandTot);

            }
            catch(Exception e)
            {
                System.out.println(e.getStackTrace());
                outputRuntime = SpinalToolBoxWebConstants.BACK_END_ERROR;
            }

            String pathHeader = stringBuffer.append(path).append(fileNameWithOutExt).append(".mnc.header").toString();
            stringBuffer.delete(0, stringBuffer.length()); //clear string buffer
            String pathRaw    = stringBuffer.append(path).append(fileNameWithOutExt).append(".mnc.raw").toString();
            stringBuffer.delete(0, stringBuffer.length());

            fileUploadResponse.setResponseFileName(fileNameWithOutExt);
            fileUploadResponse.setResponsePathHeader(pathHeader);
            fileUploadResponse.setResponsePathRaw(pathRaw);
            fileUploadResponse.setOutputConsole(outputRuntime);
        }
        else
        {
            //tree = treeGeneratorService.getActualTree();
            fileUploadResponse.setFileUploadResponse("undefined","undefined","undefined","undefined");
        }

        return fileUploadResponse;
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
    public  @ResponseBody String  handleProcessCommand(@RequestParam(value = "command") String command)throws IOException
    {

        System.out.println("Passing throught command controller");

        String outPutMessage = null;

        try
        {
            outPutMessage = softwareCommunicationService.executeProcess(command);
        }
        catch (Exception ex)
        {
            outPutMessage = SpinalToolBoxWebConstants.BACK_END_ERROR;
            System.out.println(ex.getStackTrace());
        }


        return outPutMessage;
    }



    @RequestMapping(value = "/about", method = {RequestMethod.GET, RequestMethod.POST}) String handleAboutUs(){
        return SpinalToolBoxWebConstants.VIEW_ABOUT;
    }


}

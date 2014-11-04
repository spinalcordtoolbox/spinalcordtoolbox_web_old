package spinalToolBoxWeb; /**
 * Created by  Laobien Jehiel KY on ----2014-09-20.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import spinalToolBoxWeb.commandGenerator.CommandGeneratorService;
import spinalToolBoxWeb.fileUpload.FileUploadService;
import spinalToolBoxWeb.requestMapping.model.Command;
import spinalToolBoxWeb.utils.SpinalToolBoxWebConstants;

import java.io.IOException;
import java.util.Comparator;

@Controller
public class SpinalToolboxWebController {

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private CommandGeneratorService commandGeneratorService;

    @Autowired
    private Comparator<String> comparator;



    @RequestMapping(value = "/")
    public ModelAndView home(){
        System.out.println("Passing throught home controller");
        return new ModelAndView(SpinalToolBoxWebConstants.VIEW_HOME, "result", "command submitted : ");
    }

    @RequestMapping(value="/compare", method = RequestMethod.GET)
    public String compare(@RequestParam("price1") String price1, @RequestParam("price2") String price2, Model model){
        int result = comparator.compare(price1, price2);
        String resultMsg = (result < 0) ? " " + price1 + " less than" + price2+ " " : price2 + "less than" + price1;
        model.addAttribute("resultMsg" , resultMsg);
        return SpinalToolBoxWebConstants.VIEW_COMPARE_RESULT;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody ModelAndView handleUploadedFiles(@RequestParam("file")MultipartFile file){
            System.out.println("Passing throught upload controller");
            fileUploadService.uploadFile(file);
            return  new ModelAndView(SpinalToolBoxWebConstants.VIEW_HOME);
    }


    @RequestMapping(value="/command", method = RequestMethod.POST)
    public @ResponseBody String handleCommands(@RequestParam("command") String command,Model model ) throws IOException {
        System.out.println("Passing throught command controller");
        String result = commandGeneratorService.handleCommand(command);

        model.addAttribute("consoleOut", result);
        return SpinalToolBoxWebConstants.VIEW_HOME;
    }

    @RequestMapping(value = "/about", method = {RequestMethod.GET, RequestMethod.POST})
    String handleAboutUs(){
        return SpinalToolBoxWebConstants.VIEW_ABOUT;
    }


}

package spinalToolBoxWeb.commandGenerator.internal;

import org.springframework.beans.factory.annotation.Autowired;
import spinalToolBoxWeb.commandGenerator.model.Command;
import spinalToolBoxWeb.utils.SpinalToolBoxWebConstants;
import java.io.*;


/**
 * Created by Laobien Jehiel KY - kjehiel@gmail.com on 2014-09-28.
 */
public class CommandGeneratorController {

    @Autowired
    public CommandGeneratorController(){};

    public String handleCommand(String command) throws IOException {

        String pythonScriptPath = "/home/adminer/test.py";
        String[] cmd            = new String[2];
        cmd[0]                  = "python";
        cmd[1]                  = pythonScriptPath;
        Runtime rt              = Runtime.getRuntime();
        Process pr              = rt.exec(cmd);
        BufferedReader bfr      = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line             = "";
        String result           = "";

        while((line = bfr.readLine()) != null)
        {
            result = result + line;
        }

        return result;
    }
}

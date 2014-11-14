package spinalToolBoxWeb.fileUpload;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Joe Kh on 08/11/2014.
 */
public class EnvRuntime
{

    private Runtime rt = null;
    private Process pr = null;


    public EnvRuntime()
    {
      rt = Runtime.getRuntime();
    }

    public String executeCommand(String command) throws IOException, InterruptedException
    {

        if(command != null)
        {
            pr = rt.exec(command);
            //pr. waitFor();
            //pr. waitFor();
        }

       return getRuntimeOutput();


    }

    private String getRuntimeOutput() throws IOException
    {

        String line = "";
        String consoleOutMessage = "";
        BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));

        while((line = bfr.readLine()) != null)
        {
            // afficher chaque ligne de l'output de la console
            consoleOutMessage = consoleOutMessage + line;
            System.out.println(line);
        }

        return consoleOutMessage;
    }


}

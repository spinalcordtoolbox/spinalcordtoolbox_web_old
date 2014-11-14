package spinalToolBoxWeb.softwareCommunicator.internal;

import spinalToolBoxWeb.utils.SpinalToolBoxWebConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Laobien Jehiel KY - kjehiel@gmail.com on 2014-09-26.
 */

public class SoftwareCommunicationController
{

    public SoftwareCommunicationController() {}


    public String executeProcess(String command) throws IOException {

        if(command != null) {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec(command);

            // prendre l'output du script
            BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));

            String line = "";

            String consoleOutMessage = "";

            while ((line = bfr.readLine()) != null) {
                // afficher chaque ligne de l'output de la console
                consoleOutMessage = consoleOutMessage + line;
                System.out.println(line);
            }

            return consoleOutMessage;
        }
        else
            return SpinalToolBoxWebConstants.COMMAND_VIOLATION;
    }
}

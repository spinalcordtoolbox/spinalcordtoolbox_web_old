package spinalToolBoxWeb.commandGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spinalToolBoxWeb.commandGenerator.internal.CommandGeneratorController;

import java.io.IOException;


/**
 *
 * Created by Laobien Jehiel KY - kjehiel@gmail.com on 2014-09-28.
 */


//http://www.oodesign.com/command-pattern.html
@Service
public class CommandGeneratorService {


    @Autowired
    private CommandGeneratorController commandGeneratorController;

    public CommandGeneratorService(){};


    public String handleCommand(String command) throws IOException {
        return commandGeneratorController.handleCommand(command);
    }


    /**
     *
     * Check in the sofware config file what are the loaded itens
     * */
    public void getListOfLoadedToolBoxItems(){

    }

}

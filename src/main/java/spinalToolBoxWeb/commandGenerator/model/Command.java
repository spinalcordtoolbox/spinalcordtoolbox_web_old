package spinalToolBoxWeb.commandGenerator.model;

/**
 * Created by Laobien Jehiel KY - kjehiel@gmail.com on 2014-09-28.
 */
public class Command {

    public Command() {
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public String getGeneratedCommand() {
        return generatedCommand;
    }

    public void setGeneratedCommand(String generatedCommand) {
        this.generatedCommand = generatedCommand;
    }

    private String commandName;
    private String scriptName;
    private String generatedCommand;


}

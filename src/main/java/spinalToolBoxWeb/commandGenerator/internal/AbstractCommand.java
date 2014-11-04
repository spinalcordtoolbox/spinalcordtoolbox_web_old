package spinalToolBoxWeb.commandGenerator.internal;

/**
 * Inspired from command design pattern
 * Created by Laobien Jehiel KY - kjehiel@gmail.com on 2014-09-28.
 */
public interface AbstractCommand {

    public abstract void generateCommand();
    public abstract void executeCommand();
}

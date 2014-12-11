package spinalToolBoxWeb.command.model;

/**
 * Created by Jehiel  on 16/11/14.
 */
public interface AbstractCommand {
    public abstract String generateCommand(String path , String fileOriginalName);
}

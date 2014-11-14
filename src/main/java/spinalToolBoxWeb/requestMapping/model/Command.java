package spinalToolBoxWeb.requestMapping.model;

import java.io.Serializable;

/**
 * Created by root on 18/10/14.
 */
public class Command implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String command;


    public String getName() {
        return command;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.command = name;
    }
}

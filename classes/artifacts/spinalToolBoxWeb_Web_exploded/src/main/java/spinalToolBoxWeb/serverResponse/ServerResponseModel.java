package spinalToolBoxWeb.serverResponse;

/**
 * Created by Jehiel Ky - - kjehiel@gmail.com  on 2014-12-06.
 */
public class ServerResponseModel {
    private String originalFileName;
    private String pathRawData;
    private String pathHeaderData;
    private String outputConsole;
    private String[][] serverInfoResponse;

    public String[][] getServerInfoResponse() {
        return serverInfoResponse;
    }

    public void setServerInfoResponse(String[][] serverInfoResponse) {
        this.serverInfoResponse = serverInfoResponse;
    }

    public String getOriginalFileName() {
        return originalFileName;

    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getPathRawData() {
        return pathRawData;
    }

    public void setPathRawData(String pathRawData) {
        this.pathRawData = pathRawData;
    }

    public String getPathHeaderData() {
        return pathHeaderData;
    }

    public void setPathHeaderData(String pathHeaderData) {
        this.pathHeaderData = pathHeaderData;
    }

    public String getOutputConsole() {
        return outputConsole;
    }

    public void setOutputConsole(String outputConsole) {
        this.outputConsole = outputConsole;
    }
}

package spinalToolBoxWeb.serverResponse;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by root on 05/12/14.
 */
public interface IServerResponse {

    public String[][] getServerInfoResponse();

    public void setServerInfoResponse(String[][] serverInfoResponse);

    public void setResponseFileName(String fn);

    public void setResponsePathRaw(String pathRaw);

    public void setOutputConsole(String out);

    public void setResponsePathHeader(String pathHeader);

    public void setResponse(String fileName, String pathRaw, String pathHeader, String out);

    public  void setResponse(MultipartFile file, String outputMincConversion);
    public void setUndefinedResponse();

    public void setResponse(String fileName, String out);

    public ServerResponseModel getServerResponse() ;
}

package spinalToolBoxWeb.fileUpload;

/**
 * Created by Joe Kh on 07/11/2014.
 */
public class FileUploadResponse {


    private String fileName;
    private String pathRawData;
    private String pathHeaderData;
    private String outputConsole;

    public FileUploadResponse(){};

    public void setResponseFileName(String fn)
    {
        fileName = fn;
    }

    public String getResponseFileName()
    {
        return fileName;
    }

    public void setResponsePathRaw(String pathRaw)
    {
        pathRawData = pathRaw;
    }

    public String getResponsePathRaw()
    {
        return pathRawData;
    }

    public void setOutputConsole(String out)
    {
        outputConsole = out;
    }

    public String getOutputConsole()
    {
        return outputConsole;
    }

    public void setResponsePathHeader(String pathHeader)
    {
        pathHeaderData = pathHeader;
    }

    public String getResponsePathHeader()
    {
        return pathHeaderData;
    }

    public void setFileUploadResponse(String fileName, String pathRaw, String pathHeader, String out)
    {
        setResponseFileName(fileName);
        setResponsePathRaw(pathRaw);
        setResponsePathHeader(pathHeader);
        setOutputConsole(out);
    }


}

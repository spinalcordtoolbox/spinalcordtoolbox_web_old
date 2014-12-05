package spinalToolBoxWeb.fileOperations.model;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import spinalToolBoxWeb.UserEnvironment.UserEnvironmentService;
import spinalToolBoxWeb.utils.SpinalToolBoxWebConstants;

/**
 * Created by Jehiel KY on 22/11/2014.
 */
public class ServerResponse {

    @Autowired
    private UserEnvironmentService userEnvironmentService;


    @Autowired
    private StringBuffer stringBuffer;

    private String originalFileName;
    private String pathRawData;
    private String pathHeaderData;
    private String outputConsole;

    public ServerResponse(){
        System.out.println("ServerResponse::ServerResponse  created " + this);


    };




    /**
     * Sets the filename
     * */
    public void setResponseFileName(String fn)
    {
        originalFileName = fn;
    }

    /**
     * Returns the filename
     * */
    public String getResponseFileName()
    {
        return originalFileName;
    }

    /**
     * Sets the pathraw
     * */
    public void setResponsePathRaw(String pathRaw)
    {
        pathRawData = pathRaw;
    }

    /**
     * Gets the pathraw
     * */
    public String getResponsePathRaw()
    {
        return pathRawData;
    }

    /**
     * Sets the consoleOutput
     * */
    public void setOutputConsole(String out)
    {
        outputConsole = out;
    }

    /**
     * Return the console output
     * */
    public String getOutputConsole()
    {
        return outputConsole;
    }

    /**
     * Sets path header
     * */
    public void setResponsePathHeader(String pathHeader)
    {
        pathHeaderData = pathHeader;
    }

    /**
     * Returns pathHeader.
     * */
    public String getResponsePathHeader()
    {
        return pathHeaderData;
    }

    /**
     * Sets the response variable settings.
     * */
    public void setResponse(String fileName, String pathRaw, String pathHeader, String out)
    {
        setResponseFileName(fileName);
        setResponsePathRaw(pathRaw);
        setResponsePathHeader(pathHeader);
        setOutputConsole(out);
    }

    /**
     * Sets the response variable settings.
     * */
    public void setUndefinedResponse()
    {
        setResponseFileName(SpinalToolBoxWebConstants.SERVER_RESPONSE_UNDEFINED);
        setResponsePathRaw(SpinalToolBoxWebConstants.SERVER_RESPONSE_UNDEFINED);
        setResponsePathHeader(SpinalToolBoxWebConstants.SERVER_RESPONSE_UNDEFINED);
        setOutputConsole(SpinalToolBoxWebConstants.SERVER_RESPONSE_UNDEFINED);
    }


    public void setResponse(String fileName, String out)
    {
        setResponseSetup(fileName, out);
    }

    public  void setResponse(MultipartFile file, String outputMincConversion) {
        setResponseSetup(file.getOriginalFilename(), outputMincConversion);
    }

    /**
     * Setup the response to send to the UI.
     * The object will be converted automatically to JSON by the Jackson tool
     * @Return: fileNameWithOutExt, pathHeader, pathRaw, outputMincConversion
     **/

    private void setResponseSetup(String filename, String outputMincConversion) {
        System.out.println("ServerResponse::setResponseSetup using  UserEnvironmentService " + userEnvironmentService);
        System.out.println("ServerResponse::setResponseSetup using  Stringbuffer " + stringBuffer);
        String systemUploadPath = userEnvironmentService.getUSerEnvironment().getUserUploadPathWithEndSeparator();
        String fileNameWithOutExt = FilenameUtils.removeExtension(FilenameUtils.removeExtension(filename));
        String pathWithoutSystemPart = systemUploadPath.substring(systemUploadPath.indexOf("external") - 1, systemUploadPath.length());

        String pathHeader = stringBuffer.append(pathWithoutSystemPart).append(fileNameWithOutExt).append(".header").toString();
        System.out.println(pathHeader);
        stringBuffer.delete(0, stringBuffer.length()); //clear string buffer
        String pathRaw = stringBuffer.append(pathWithoutSystemPart).append(fileNameWithOutExt).append(".raw").toString();
        stringBuffer.delete(0, stringBuffer.length());

        setResponseFileName(filename);
        setResponsePathHeader(pathHeader);
        setResponsePathRaw(pathRaw);
        setOutputConsole(outputMincConversion);
    }


}

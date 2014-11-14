package spinalToolBoxWeb.fileUpload.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import spinalToolBoxWeb.UserEnvironment.UserEnvironmentService;
import spinalToolBoxWeb.fileUpload.validator.FileValidator;
import spinalToolBoxWeb.utils.SpinalToolBoxWebConstants;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by unlocker on 2014-09-20.
 */


public class FileUploadController {

    //TODO: Create constants for string messages
    @Autowired
    private FileValidator fileValidator;

    @Autowired
    private UserEnvironmentService userEnvironmentService;

    public FileUploadController(){};

    public boolean uploadFile(MultipartFile file, String token){

      // fileValidator.validate(file, result);
       //if(result.hasErrors())
        try{
            System.out.println("uplOADdFileMethod");
            writeFileToUploadDirectory(file, token);
            return true;
        }catch (Exception e){
            System.out.println("Upload failure : " + e.getMessage());
        }

        return false;
    }

    private void writeFileToUploadDirectory(MultipartFile file, String token) throws IOException
    {
        String fileName = null;

        if(userEnvironmentService.getUSerEnvironment().isIsFirstUpload())
        {

            userEnvironmentService.getUSerEnvironment().setIsFirstUpload(false);
            userEnvironmentService.getUSerEnvironment().setToken(token);
            userEnvironmentService.getUSerEnvironment().setUserUploadPath(new StringBuffer().append(SpinalToolBoxWebConstants.UPLOAD_FILE_PATH)
                    .append("/")
                    .append(token)
                    .toString());
            userEnvironmentService.getUSerEnvironment().setUserUploadPathWithEndSeparator(new StringBuffer().append(SpinalToolBoxWebConstants.UPLOAD_FILE_PATH)
                    .append("/")
                    .append(token)
                    .append("/")
                    .toString());
        }

        File dir = new File(userEnvironmentService.getUSerEnvironment().getUserUploadPath());

        // normalement le repertoire ne devrait pas exister ...
        if (!dir.exists())
        {
            try{
                dir.mkdir();

            } catch(SecurityException se){

            }
        }

        fileName =  new StringBuffer().append(userEnvironmentService.getUSerEnvironment().getUserUploadPathWithEndSeparator())
                                      .append(file.getOriginalFilename())
                                      .toString();

        byte[] bytes = file.getBytes();
        //TODO:Use spring mvc bean declaration instead of new
        FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName));
        BufferedOutputStream bufferedFile = new BufferedOutputStream(fileOutputStream);
        bufferedFile.write(bytes);
        bufferedFile.close();
    }

}

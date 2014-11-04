package spinalToolBoxWeb.fileUpload.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
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

    public FileUploadController(){};

    public void uploadFile(MultipartFile file){

      // fileValidator.validate(file, result);
       //if(result.hasErrors())
        try{
            System.out.println("uplOADdFileMethod");
            writeFileToUploadDirectory(file);
        }catch (Exception e){
            System.out.println("Upload failure : " + e.getMessage());
        }
    }

    private void writeFileToUploadDirectory(MultipartFile file) throws IOException {
        String fileName =  SpinalToolBoxWebConstants.UPLOAD_FILE_PATH + file.getOriginalFilename();
        byte[] bytes = file.getBytes();
        //TODO:Use spring mvc bean declaration instead of new
        FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName));
        BufferedOutputStream bufferedFile = new BufferedOutputStream(fileOutputStream);
        bufferedFile.write(bytes);
        bufferedFile.close();
    }

}

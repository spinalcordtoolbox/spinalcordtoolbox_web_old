package spinalToolBoxWeb.fileOperations.internal;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import spinalToolBoxWeb.UserEnvironment.UserEnvironmentService;
import spinalToolBoxWeb.fileOperations.validator.FileValidator;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by unlocker on 2014-09-20.
 */


public class FileOperationsController {

    //TODO: Create constants for string messages
    /*@Autowired
    private FileValidator fileValidator;*/

   @Autowired
    private UserEnvironmentService userEnvironmentService;


    @Value("${permittedExtensions}")
    private String permittedExtensions;


    public FileOperationsController(){
        System.out.println("FileOperationsController:: FileOperationsController created " + this);

    };

    public boolean uploadFile(MultipartFile file, String token){
        System.out.println("FileOperationsController::uploadFile using UserEnvironmentService" + userEnvironmentService);
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

       /* if(userEnvironmentService.getUSerEnvironment().isIsFirstUpload())
        {*/

            userEnvironmentService.getUSerEnvironment().setIsFirstUpload(false);
            userEnvironmentService.getUSerEnvironment().setToken(token);
            userEnvironmentService.getUSerEnvironment().setUserUploadPath(new StringBuffer().append(userEnvironmentService.getUSerEnvironment().getUserUploadBasePath())
                    .append(token)
                    .toString());
            userEnvironmentService.getUSerEnvironment().setUserUploadPathWithEndSeparator(new StringBuffer().append(userEnvironmentService.getUSerEnvironment().getUserUploadBasePath())
                    .append(token)
                    .append("/")
                    .toString());
       // }

        File dir = new File(userEnvironmentService.getUSerEnvironment().getUserUploadPath());
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


    public boolean deteleFiles(File... files){
        boolean isEverythingGoneRight = true;
        if(files.length > 0){
            for(File file : files){
                isEverythingGoneRight &= file.delete();
            }
        }
        return isEverythingGoneRight;
    }

    /**
     * @Description: deletes all .nii, .mnc, .header, .raw files
     * in the user working directory
     * @param
     * @return boolean = true if everything is fine :)
     * */
    public boolean cleanup(String fileName){
        boolean isEverythingGoneRight = false;

        if(fileName!=null) {
            String path = userEnvironmentService.getUSerEnvironment().getUserUploadPathWithEndSeparator();
            String fileNameWithOutExt = FilenameUtils.removeExtension(FilenameUtils.removeExtension(fileName));

            String HeaderFile = path + fileNameWithOutExt + ".header";
            String RawFile = path + fileNameWithOutExt + ".raw";
            String fileNamePath = path  + fileName;


            try {
                File files[] = {new File(fileNamePath), new File(HeaderFile), new File(RawFile)};
                isEverythingGoneRight = deteleFiles(files);
                if (isEverythingGoneRight) {
                    System.out.println("files successfully deleted");
                }
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }else{
            System.out.println("Filename can't be null");
        }

        return isEverythingGoneRight;
    }

    public boolean cleanupRepo()
    {
        boolean isEverythingGoneRight = false;
        String path = userEnvironmentService.getUSerEnvironment().getUserUploadPathWithEndSeparator();

        try
        {
            File dir = new File(path);
            if(dir.exists())
            {
                FileUtils.deleteDirectory(dir);
            }
            isEverythingGoneRight = true;
        }
        catch(Exception e)
        {
            System.out.println(e.getStackTrace());
        }

        return isEverythingGoneRight;
    }


    public boolean isUploadedFileExtensionAllowed(String fileName)
    {
        List<String> permittedExtensionList = new LinkedList<>();
        String fileNameExtension = fileName.substring(fileName.indexOf("."), fileName.length());
        permittedExtensionList = Arrays.asList(permittedExtensions.split(","));
        return permittedExtensionList.contains(fileNameExtension);
    }

}

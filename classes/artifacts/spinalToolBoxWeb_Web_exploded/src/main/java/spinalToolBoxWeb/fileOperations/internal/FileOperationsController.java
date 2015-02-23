package spinalToolBoxWeb.fileOperations.internal;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import spinalToolBoxWeb.UserEnvironment.IUserEnvironmentService;
import spinalToolBoxWeb.fileOperations.IFileOperationsService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jehiel KY- kjehiel@gmail.com  on 2014-09-20.
 */

public class FileOperationsController implements IFileOperationsService {

    @Autowired
    private IUserEnvironmentService userEnvironmentService;


    // Retrieves allowed file extentions in property file
    @Value("${allowedFilesExtensions}")
    private String permittedExtensions;


    public FileOperationsController(){
        System.out.println("FileOperationsController:: FileOperationsController created " + this);

    };

    public boolean uploadFile(MultipartFile file, String token){
        System.out.println("FileOperationsController::uploadFile using UserEnvironmentService" + userEnvironmentService);

        try{
            System.out.println("uplOADdFileMethod");
            writeFileToUploadDirectory(file, token);
            return true;
        }catch (Exception e){
            System.out.println("Upload failure : " + e.getMessage());
        }

        return false;
    }

    private void writeFileToUploadDirectory(MultipartFile file, String token) throws IOException{

        userEnvironmentService.getUserEnvironment().setToken(token);
        userEnvironmentService.getUserEnvironment().setUserUploadPath(new StringBuffer().append(userEnvironmentService.getUserEnvironment().getUserUploadBasePath())
                .append(token)
                .toString());
        userEnvironmentService.getUserEnvironment().setUserUploadPathWithEndSeparator(new StringBuffer().append(userEnvironmentService.getUserEnvironment().getUserUploadBasePath())
                .append(token)
                .append("/")
                .toString());


        File dir = new File(userEnvironmentService.getUserEnvironment().getUserUploadPath());
        if (!dir.exists())
        {
            try{
                dir.mkdir();
            } catch(SecurityException se){

            }
        }

        String fileName =  new StringBuffer().append(userEnvironmentService.getUserEnvironment().getUserUploadPathWithEndSeparator())
                                      .append(file.getOriginalFilename())
                                      .toString();

        byte[] bytes = file.getBytes();

        FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName));
        BufferedOutputStream bufferedFile = new BufferedOutputStream(fileOutputStream);
        bufferedFile.write(bytes);
        bufferedFile.close();
    }



    /**
     * Delete specified files.
     * @param files - can take as many as files you provide
     * @return true if all input files had been deleted
     */
    public boolean deleteFiles(File... files){
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
            String path = userEnvironmentService.getUserEnvironment().getUserUploadPathWithEndSeparator();
            String fileNameWithOutExt = FilenameUtils.removeExtension(FilenameUtils.removeExtension(fileName));

            String HeaderFile = path + fileNameWithOutExt + ".header";
            String RawFile = path + fileNameWithOutExt + ".raw";
            String fileNamePath = path  + fileName;


            try {
                File files[] = {new File(fileNamePath), new File(HeaderFile), new File(RawFile)};
                isEverythingGoneRight = deleteFiles(files);
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

    /**
     * Deletes all files in user upload repository
     * @return true if no errors
     */
    public boolean cleanupRepository(){
        boolean isEverythingGoneRight = false;
        String path = userEnvironmentService.getUserEnvironment().getUserUploadPathWithEndSeparator();

        try{
            File dir = new File(path);
            if(dir.exists()){
                FileUtils.deleteDirectory(dir);
            }
            isEverythingGoneRight = true;
        }
        catch(Exception e){
            System.out.println(e.getStackTrace());
        }
        return isEverythingGoneRight;
    }


    /**
     * Checks if uploaded file exention is allowed.
     * Allowed file extentions are specified in config.properties file
     * @param fileName
     * @return true if uploaded file is allowed.
     */
    public boolean isUploadedFileExtensionAllowed(String fileName){
        List<String> permittedExtensionList = new LinkedList<>();
        String fileNameExtension = fileName.substring(fileName.indexOf("."), fileName.length());
        permittedExtensionList = Arrays.asList(permittedExtensions.split(","));
        return permittedExtensionList.contains(fileNameExtension);
    }

}

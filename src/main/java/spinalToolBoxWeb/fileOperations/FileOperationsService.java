package spinalToolBoxWeb.fileOperations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.config.java.annotation.aop.ScopedProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spinalToolBoxWeb.fileOperations.internal.FileOperationsController;
import spinalToolBoxWeb.fileOperations.validator.FileValidator;

import java.io.File;

/**
 * Created by Laobien Jehiel KY on 2014-09-20.
 */

@Service
@Scope("request")
public class FileOperationsService {

    @Autowired
    private FileValidator fileValidator;

    @Autowired
    private FileOperationsController fileOperationsController;

    public FileOperationsService(){
        System.out.println("FileOperationsService:: FileOperationsService created " + this);
    };

    public boolean uploadFile(MultipartFile file, String token)
    {
        System.out.println("FileOperationsService::uploadFile using FileOperationsController " + fileOperationsController);
        return fileOperationsController.uploadFile(file, token);
    }
    public boolean deleteFiles(File... files){return fileOperationsController.deteleFiles(files);}

    public boolean cleanup(String fileName){return fileOperationsController.cleanup(fileName);}

    public boolean cleanupRepo(){return fileOperationsController.cleanupRepo();}

    public boolean isUploadedFileExtensionAllowed(String fileName){return fileOperationsController.isUploadedFileExtensionAllowed(fileName);}


}

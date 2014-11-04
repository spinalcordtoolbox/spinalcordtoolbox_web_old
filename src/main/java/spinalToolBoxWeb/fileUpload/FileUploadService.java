package spinalToolBoxWeb.fileUpload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spinalToolBoxWeb.fileUpload.internal.FileUploadController;
import spinalToolBoxWeb.fileUpload.validator.FileValidator;

/**
 * Created by Laobien Jehiel KY on 2014-09-20.
 */

@Service
public class FileUploadService {

    @Autowired
    private FileValidator fileValidator;

    @Autowired
    private FileUploadController fileuploadController;

    public FileUploadService(){};

    public void uploadFile(MultipartFile file){
        fileuploadController.uploadFile(file);
    }
}

package spinalToolBoxWeb.fileOperations;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created by Laobien Jehiel KY on 2014-09-20.
 */


public interface IFileOperationsService {

    public boolean uploadFile(MultipartFile file, String token);

    public boolean deleteFiles(File... files);

    public boolean cleanup(String fileName);

    public boolean cleanupRepository();

    public boolean isUploadedFileExtensionAllowed(String fileName);


}

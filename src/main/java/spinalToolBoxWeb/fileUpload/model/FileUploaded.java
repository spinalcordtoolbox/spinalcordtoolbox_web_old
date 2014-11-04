package spinalToolBoxWeb.fileUpload.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Laobien Jehiel KY - kjehiel@gmail.com on 2014-09-22.
 */
public class FileUploaded {
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}

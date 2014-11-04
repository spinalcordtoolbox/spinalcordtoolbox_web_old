package spinalToolBoxWeb.fileUpload.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import spinalToolBoxWeb.fileUpload.model.FileUploaded;

/**
 * Created by Laobien Jehiel KY - kjehiel@gmail.com on 2014-09-22.
 */
public class FileValidator implements Validator{

    private FileUploaded file;

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object file, Errors errors) {
        this.file = (FileUploaded) file;
       validateConsistency(this.file, errors);
    }

    private void validateConsistency(FileUploaded file,  Errors errors) {
        if(file.getFile().isEmpty()){
            errors.rejectValue("File","uploaded", "Hey! You've to select a file");
        }
    }
}

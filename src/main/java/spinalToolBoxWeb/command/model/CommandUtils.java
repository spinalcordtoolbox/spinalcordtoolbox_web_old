package spinalToolBoxWeb.command.model;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laobien Jehiel KY - kjehiel@gmail.com  on 01/12/14.
 */
public class CommandUtils {




        /**
         * Generates sct_convert Command for .nii, .nii.gz, .mnc file to .raw and .header
         * The hint here is that if the file is .mnc, we have to convert it to .nii.gz before converting to .header and .raw
         *
         * */


         public static String[] generateCommand_convert_nii_niigz_minc_to_header_raw(String script_generating_rawHeader, String path , String fileOriginalName) {

            List<String> results = new ArrayList<>();
            String fileNameExtension = fileOriginalName.substring(fileOriginalName.indexOf("."), fileOriginalName.length());
            Boolean isMinc = (fileNameExtension == ".mnc" || fileNameExtension == ".mnc.gz");


            StringBuffer command =   new StringBuffer().append(script_generating_rawHeader)
                                    .append(" -i ")
                                    .append(path)
                                    .append(fileOriginalName)
                                    .append(" -o ")
                                    .append(path)
                    .append(FilenameUtils.removeExtension(FilenameUtils.removeExtension(fileOriginalName)));

            if(isMinc){
                results.add(command.append(".nii.gz").toString());
                results.add(command.delete(command.length(), command.length()).append(".header").toString());
            }else{
                results.add(command.append(".header").toString());
            }


           return results.toArray(new String[results.size()]);
        }
}

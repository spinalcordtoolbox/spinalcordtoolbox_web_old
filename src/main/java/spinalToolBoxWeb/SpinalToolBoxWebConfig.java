package spinalToolBoxWeb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import spinalToolBoxWeb.UserEnvironment.internal.UserEnvironmentController;
import spinalToolBoxWeb.UserEnvironment.model.UserEnvironment;
import spinalToolBoxWeb.fileUpload.EnvRuntime;
import spinalToolBoxWeb.fileUpload.FileUploadResponse;
import spinalToolBoxWeb.fileUpload.internal.FileUploadController;
import spinalToolBoxWeb.TreeGenerator.internal.TreeGeneratorController;
import spinalToolBoxWeb.fileUpload.validator.FileValidator;
import spinalToolBoxWeb.fileUpload.FileUploadResponse;
import spinalToolBoxWeb.log.internal.LogController;
import spinalToolBoxWeb.softwareCommunicator.SoftwareCommunicationService;
import spinalToolBoxWeb.softwareCommunicator.internal.SoftwareCommunicationController;
import spinalToolBoxWeb.utils.SpinalToolBoxWebConstants;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laobien Jehiel KY - kjehiel@gmail.com on 2014-09-21.
 */
@Configuration
public class SpinalToolBoxWebConfig {

    //Resolve view name to jsp
    @Bean
    ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("WEB-INF/view/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public FileValidator fileValidator(){
        return new FileValidator();
    }

    @Bean
    public FileUploadController fileUploadController(){
        return new FileUploadController();
    }

    @Bean
    public LogController logController() {return new LogController();}



    @Bean
    public TreeGeneratorController treeGeneratorController() {return new TreeGeneratorController(); }

    @Bean
    public FileUploadResponse fileUploadResponse() {return new FileUploadResponse();}

    @Bean
    public SoftwareCommunicationController softwareCommunicationController() {return new SoftwareCommunicationController();}

    @Bean
    public EnvRuntime envRuntime() {return new EnvRuntime();}

    @Bean
    public CommonsMultipartResolver multipartResolver() throws java.io.IOException{
        Resource fileSystemResource = new FileSystemResource(SpinalToolBoxWebConstants.UPLOAD_FILE_PATH);
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setUploadTempDir(fileSystemResource);
        commonsMultipartResolver.setMaxUploadSize(-1); //no limit to file upload size
        return commonsMultipartResolver;
    }

    @Bean
    public StringBuffer stringBuffer(){ return new StringBuffer();}

    //@Scope("session")
    @Bean
    public UserEnvironment userEnvironment(){return new UserEnvironment(); }

    @Bean
    public UserEnvironmentController userEnvironmentController(){return new UserEnvironmentController(); }

/*    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){
        MappingJackson2HttpMessageConverter mapping = new MappingJackson2HttpMessageConverter();
        mapping.setPrefixJson(false);
        List<MediaType> mediaSupported = new ArrayList<MediaType>();
        MediaType mediaJson = new MediaType("application/json");
        mediaSupported.add(mediaJson);
        mapping.setSupportedMediaTypes(mediaSupported);
        return mapping;
    }*/

}

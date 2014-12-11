package spinalToolBoxWeb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import spinalToolBoxWeb.UserEnvironment.internal.UserEnvironmentController;
import spinalToolBoxWeb.UserEnvironment.model.UserEnvironment;
import spinalToolBoxWeb.fileOperations.internal.FileOperationsController;
import spinalToolBoxWeb.log.internal.LogController;
import spinalToolBoxWeb.serverResponse.ServerResponse;
import spinalToolBoxWeb.softwareCommunicator.internal.SoftwareCommunicationController;
import spinalToolBoxWeb.utils.SpinalToolBoxWebConstants;

/**
 * Created by Laobien Jehiel KY - kjehiel@gmail.com on 2014-09-21.
 *
 * This class is an alternative to xml beans definitions.
 * Its more easier and readable.
 *
 */
@Configuration
public class SpinalToolBoxWebConfig {

    //Gets the value of uploadPath in the config.propertie file
    @Value("${uploadPath}") private String uploadPathFromPropertyFile;

    /** Resolve view name to jsp - spring configuration
       When the controller returns the view name for example index
       the view resolver will prefix it so it will be: WEB-INF/view/index
       and will suffix it: WEB-INF/view/index.jsp
        this will be returned to fetch the desired view in the specified folder
     */
    @Bean
    ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("WEB-INF/view/");
        resolver.setSuffix(".jsp");
        return resolver;
    }


    /**
     *  Configuration for the file upload.
     */
    @Bean
    public CommonsMultipartResolver multipartResolver() throws java.io.IOException{
        Resource fileSystemResource = new FileSystemResource(uploadPathFromPropertyFile);
        System.out.println(uploadPathFromPropertyFile);
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setUploadTempDir(fileSystemResource);
        commonsMultipartResolver.setMaxUploadSize(-1); //no limit to file upload size
        return commonsMultipartResolver;
    }

    /**
        Bean definition for fileuploadController.
        This bean is in request scope with a proxy mode since we will be injecting this
        in a singleton beam ( the controller)
     */
    @Bean
    @Scope(value = SpinalToolBoxWebConstants.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public FileOperationsController fileOperationsController(){
        return new FileOperationsController();
    }


    /**
     Bean definition for UserEnvironmentController.
     This bean is in request scope with a proxy mode since we will be injecting this
     in a singleton beam ( the controller)
     */
    @Bean
    @Scope(value = SpinalToolBoxWebConstants.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public UserEnvironmentController userEnvironmentController(){return new UserEnvironmentController(); }


    @Bean
    @Scope(value = SpinalToolBoxWebConstants.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public SoftwareCommunicationController softwareCommunicationController() {return new SoftwareCommunicationController();}

    @Bean
    @Scope(value = SpinalToolBoxWebConstants.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public LogController logController() {return new LogController();}



    @Bean
    @Scope(value = SpinalToolBoxWebConstants.SCOPE_REQUEST, proxyMode = ScopedProxyMode.INTERFACES)
    public ServerResponse serverResponse(){return new ServerResponse();}


    @Bean
    @Scope(value = SpinalToolBoxWebConstants.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public UserEnvironment userEnvironment(){
        return new UserEnvironment();
    }


}

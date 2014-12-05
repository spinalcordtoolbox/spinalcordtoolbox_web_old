package spinalToolBoxWeb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.config.java.annotation.aop.ScopedProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import spinalToolBoxWeb.UserEnvironment.internal.UserEnvironmentController;
import spinalToolBoxWeb.UserEnvironment.model.UserEnvironment;
import spinalToolBoxWeb.fileOperations.internal.FileOperationsController;
import spinalToolBoxWeb.fileOperations.model.ServerResponse;
import spinalToolBoxWeb.fileOperations.validator.FileValidator;
import spinalToolBoxWeb.log.internal.LogController;
import spinalToolBoxWeb.softwareCommunicator.internal.SoftwareCommunicationController;

/**
 * Created by Laobien Jehiel KY - kjehiel@gmail.com on 2014-09-21.
 */
@Configuration
public class SpinalToolBoxWebConfig {

    @Value("${uploadPath}") private String uploadPathFromPropertyFile;

    //Resolve view name to jsp
    @Bean
    @Scope("prototype")
    ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("WEB-INF/view/");
        resolver.setSuffix(".jsp");
        /*resolver.setExposeContextBeansAsAttributes(true);
        resolver.setExposedContextBeanNames("configProperties");*/
        return resolver;
    }

    @Bean
    public FileValidator fileValidator(){
        return new FileValidator();
    }

    @Bean
    @Scope("request")
    @ScopedProxy
    public FileOperationsController fileOperationsController(){
        return new FileOperationsController();
    }

    @Bean
    @Scope("request")
    @ScopedProxy
    public LogController logController() {return new LogController();}

    @Bean
    @Scope("request")
    @ScopedProxy
    public ServerResponse serverResponse(){return new ServerResponse();}

    @Bean
    @Scope("request")
    @ScopedProxy
    public SoftwareCommunicationController softwareCommunicationController() {return new SoftwareCommunicationController();}


    @Bean
    @Scope("prototype")
    public CommonsMultipartResolver multipartResolver() throws java.io.IOException{
        Resource fileSystemResource = new FileSystemResource(uploadPathFromPropertyFile);
        System.out.println(uploadPathFromPropertyFile);
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setUploadTempDir(fileSystemResource);
        commonsMultipartResolver.setMaxUploadSize(-1); //no limit to file upload size
        return commonsMultipartResolver;
    }

    @Bean
    @Scope("request")
    @ScopedProxy
    public StringBuffer stringBuffer(){ return new StringBuffer();}


    @Bean
    @Scope("request")
    @ScopedProxy
    public UserEnvironment userEnvironment(){
        return new UserEnvironment();
    }

    @Bean
    @Scope("request")
    @ScopedProxy
    public UserEnvironmentController userEnvironmentController(){return new UserEnvironmentController(); }


}

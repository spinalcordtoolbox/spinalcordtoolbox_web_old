package spinalToolBoxWebTest.SoftwareCommunicationTest;

import org.junit.Before;
import org.junit.Test;
import org.kubek2k.springockito.annotations.SpringockitoContextLoader;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.ContextConfiguration;
import spinalToolBoxWeb.softwareCommunicator.ISoftwareCommunicationService;

import java.io.IOException;

/**
 * Created by root on 10/11/14.
 */
@ContextConfiguration(loader = SpringockitoContextLoader.class, locations = "/WEB-INF/spring/spinalToolBoxServlet/servlet-context.xml")
public class SoftwareCommunicationServiceTest {


    //private SoftwareCommunicationController softwareCommunicationController;

    @Spy
    ISoftwareCommunicationService spy_softwareCommunicationService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    //TODO: to be continued. for now it fails
    @Test
    public void executeProcessNullCommand() throws IOException {
       // Assert.assertEquals(softwareCommunicationService.executeCommandLine(""), SpinalToolBoxWebConstants.COMMAND_VIOLATION);

        //Mockito.verify(softwareCommunicationService).executeCommandLine("");

        //spy_softwareCommunicationService.runNConsecutiveCommands("cd /home/adminer", "mkdir jehiel");
    }
}

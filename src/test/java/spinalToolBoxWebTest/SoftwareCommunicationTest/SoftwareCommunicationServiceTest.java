package spinalToolBoxWebTest.SoftwareCommunicationTest;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import spinalToolBoxWeb.softwareCommunicator.SoftwareCommunicationService;
import spinalToolBoxWeb.utils.SpinalToolBoxWebConstants;

import java.io.IOException;

/**
 * Created by root on 10/11/14.
 */
public class SoftwareCommunicationServiceTest {

    @Spy
    SoftwareCommunicationService softwareCommunicationService;

    //TODO: to be continued. for now it fails
    @Test
    public void executeProcessNullCommand() throws IOException {
        Assert.assertEquals(softwareCommunicationService.executeProcess(""), SpinalToolBoxWebConstants.COMMAND_VIOLATION);

        Mockito.verify(softwareCommunicationService).executeProcess("");
    }
}

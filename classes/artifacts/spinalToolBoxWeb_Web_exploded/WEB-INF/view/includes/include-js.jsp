<%--
  Created by IntelliJ IDEA.
  User: Jehiel KY
  Date: 18/10/14
  Time: 9:53 PM
--%>


<%--
 THIS FILE CONTAINS THE JAVASCRIPT DEPENDENCIES OF THE APPLICATION
--%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%--NE PAS CHNAGER LORDRE. TRES TRES IMPORTANT--%>
    <script src="<spring:url value="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"/>" type="text/javascript"></script>

    <script src="<c:url value="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.15/angular.min.js"/>" type="text/javascript"></script>

    <%--<script src="<spring:url value="/resources/viewRessouces/js/command/command.js"/>" type="text/javascript"></script>--%>
<%--fin NE PAS CHNAGER LORDRE. TRES TRES IMPORTANT--%>

<%--Dependances du fileinput plugin --%>

<script src="<spring:url value="/resources/viewRessouces/lib/fileinput-plugin/js/fileinput.js"/>" type="text/javascript"></script>

<%--Dependances du terminal--%>

<script src="<spring:url value="/resources/viewRessouces/lib/terminal/js/jquery.mousewheel-min.js"/>" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/lib/terminal/js/jquery.terminal-0.8.8.min.js"/>" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/lib/terminal/js/console.js"/>" type="text/javascript"></script>


<%--Dependances du spinalCordToolbox Web--%>
<script src="<spring:url value="/resources/viewRessouces/js/modules/SpinalCordToolboxApp.js"/>" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/js/services/sharedPropertiesService.js"/>" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/js/commandsInputGenerator/commandGeneratorController.js"/>" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/js/commandsInputGenerator/model/commandOperations.js"/>" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/js/commandsInputGenerator/model/HTMLOperations.js"/>" type="text/javascript"></script>



<script src="<spring:url value="/resources/viewRessouces/js/directives/FileDirective.js"/>" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/js/upload/uploadController.js"/>" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/js/commandsInputGenerator/specialScripts/labelsGenerator.js"/>" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/js/sessionClose.js"/>" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/js/upload/model/uploadFile.js"/>" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/js/filesManager/filesManagerController.js"/>" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/js/filesManager/model/deleteFileOperations.js"/>" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/js/filesManager/model/volumesOperations.js"/>" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/js/utils/appConfig.js"/>" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/js/terminal/terminalController.js"/>" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/js/log/logController.js"/>" type="text/javascript"></script>

<script src="<spring:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"/>" type="text/javascript"></script>

<%--Dependances du BrainBrowser--%>


<script src="<spring:url value="/resources/viewRessouces/js/lib/examples/js/ui.js"/>" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/js/lib/brainbrowser/brainbrowser.js" />" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/js/lib/brainbrowser/core/tree-store.js"  />" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/js/lib/brainbrowser/lib/config.js"  />" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/js/lib/brainbrowser/lib/utils.js"  />" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/js/lib/brainbrowser/lib/events.js" />" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/js/lib/brainbrowser/lib/loader.js" />" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/js/lib/brainbrowser/lib/color-map.js"  />" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/js/lib/brainbrowser/volume-viewer.js" />" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/js/lib/brainbrowser/volume-viewer/lib/display.js" />" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/js/lib/brainbrowser/volume-viewer/lib/panel.js"/>" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/js/lib/brainbrowser/volume-viewer/lib/utils.js"/>" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/js/lib/brainbrowser/volume-viewer/modules/loading.js" />" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/js/lib/brainbrowser/volume-viewer/modules/rendering.js"  />" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/js/lib/brainbrowser/volume-viewer/volume-loaders/overlay.js" />" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/js/lib/brainbrowser/volume-viewer/volume-loaders/minc.js" />" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/js/lib/examples/volume-viewer-demo.config.js" />" type="text/javascript"></script>

<script src="<spring:url value="/resources/viewRessouces/js/lib/examples/volume-viewer-demo.js" />" type="text/javascript"></script>





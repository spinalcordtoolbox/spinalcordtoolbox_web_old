<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 18/10/14
  Time: 9:53 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script src="<c:url value="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.15/angular.min.js"/>"></script>

<spring:url value="/resources/viewRessouces/js/command/command.js" var="commandUrl" />
<script src="${commandUrl}"></script>



<spring:url value="/resources/viewRessouces/js/lib/examples/js/jquery-1.6.4.min.js" var="jq" />
<script src ="${jq}"></script>

<spring:url value="/resources/viewRessouces/js/lib/examples/js/jquery-ui-1.8.10.custom.min.js" var="jqui" />
<script src="${jqui}"></script>

<spring:url value="/resources/viewRessouces/js/lib/examples/js/ui.js" var="uilol" />
<script src="${uilol}"></script>

<spring:url value="/resources/viewRessouces/js/lib/brainbrowser/brainbrowser.js" var="brainbrowserjs" />
<script src="${brainbrowserjs}"></script>

<spring:url value="/resources/viewRessouces/js/lib/brainbrowser/core/tree-store.js" var="treestore" />
<script src="${treestore}"></script>

<spring:url value="/resources/viewRessouces/js/lib/brainbrowser/lib/config.js" var="config" />
<script src="${config}"></script>

<spring:url value="/resources/viewRessouces/js/lib/brainbrowser/lib/utils.js" var="utils" />
<script src="${utils}"></script>

<spring:url value="/resources/viewRessouces/js/lib/brainbrowser/lib/events.js" var="events" />
<script src="${events}"></script>

<spring:url value="/resources/viewRessouces/js/lib/brainbrowser/lib/loader.js" var="loader" />
<script src="${loader}"></script>

<spring:url value="/resources/viewRessouces/js/lib/brainbrowser/lib/color-map.js" var="colormap" />
<script src="${colormap}"></script>

<spring:url value="/resources/viewRessouces/js/lib/brainbrowser/volume-viewer.js" var="volumeviewer" />
<script src="${volumeviewer}"></script>

<spring:url value="/resources/viewRessouces/js/lib/brainbrowser/volume-viewer/lib/display.js" var="display" />
<script src="${display}"></script>

<spring:url value="/resources/viewRessouces/js/lib/brainbrowser/volume-viewer/lib/panel.js" var="panel" />
<script src="${panel}"></script>

<spring:url value="/resources/viewRessouces/js/lib/brainbrowser/volume-viewer/lib/utils.js" var="utilss" />
<script src="${utilss}"></script>

<spring:url value="/resources/viewRessouces/js/lib/brainbrowser/volume-viewer/modules/loading.js" var="loading" />
<script src="${loading}"></script>

<spring:url value="/resources/viewRessouces/js/lib/brainbrowser/volume-viewer/modules/rendering.js" var="rendering" />
<script src="${rendering}"></script>

<spring:url value="/resources/viewRessouces/js/lib/brainbrowser/volume-viewer/volume-loaders/overlay.js" var="overlay" />
<script src="${overlay}"></script>

<spring:url value="/resources/viewRessouces/js/lib/brainbrowser/volume-viewer/volume-loaders/minc.js" var="minc" />
<script src="${minc}"></script>


<spring:url value="/resources/viewRessouces/js/lib/examples/volume-viewer-demo.config.js" var="volumeviewerdemoconfig" />
<script src="${volumeviewerdemoconfig}"></script>


<spring:url value="/resources/viewRessouces/js/lib/examples/volume-viewer-demo.js" var="volumeviewerdemo" />
<script src="${volumeviewerdemo}"></script>


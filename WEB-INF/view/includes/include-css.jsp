<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 18/10/14
  Time: 9:51 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url value="/resources/viewRessouces/lib/bootstrap/css/bootstrap.css" var="bootstrap_css" />
<link href="${bootstrap_css}" rel="stylesheet">

<spring:url value="/resources/viewRessouces/lib/bootstrap/css/custom.css" var="custom_css" />
<link href="${custom_css}" rel="stylesheet">

<spring:url value="/resources/viewRessouces/lib/bootstrap/css/bootstrap-theme.min.css" var="min_theme_css" />
<link href="${min_theme_css}" rel="stylesheet">

<link href="http://getbootstrap.com/examples/offcanvas/offcanvas.css" rel="stylesheet">


<spring:url value="/resources/viewRessouces//js/lib/examples/css/ui-darkness/jquery-ui-1.8.2.custom.css" var="jq_ui_css" />
<link type="text/css" href="${jq_ui_css}" rel="Stylesheet" />

<spring:url value="/resources/viewRessouces//js/lib/examples/css/common.css" var="common_css" />
<link type="text/css" href="${common_css}" rel="Stylesheet" />

<spring:url value="/resources/viewRessouces//js/lib/examples/css/volume-viewer-demo.css" var="vol_view_css" />
<link type="text/css" href="${vol_view_css}" rel="Stylesheet" />

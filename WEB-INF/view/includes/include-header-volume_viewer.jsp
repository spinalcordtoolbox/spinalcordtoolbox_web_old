<%--
  Created by IntelliJ IDEA.
  User: unlocker
  Date: 2014-09-28
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html>
<head>
    <meta content="text/html;charset=utf-8" http-equiv="Content-Type">
    <meta content="utf-8" http-equiv="encoding">

    <title>Volume viewer</title>



    <spring:url value="/resources/viewRessouces/lib/bootstrap/css/bootstrap.css" var="bootstrap_css" />
    <link href="${bootstrap_css}" rel="stylesheet">


    <spring:url value="/resources/viewRessouces/lib/bootstrap/css/custom.css" var="custom_css" />
    <link href="${custom_css}" rel="stylesheet">

    <spring:url value="/resources/viewRessouces//js/lib/examples/css/common.css" var="common_css" />
    <link type="text/css" href="${common_css}" rel="Stylesheet" />

    <spring:url value="/resources/viewRessouces//js/lib/examples/css/volume-viewer-demo.css" var="vol_view_css" />
    <link type="text/css" href="${vol_view_css}" rel="Stylesheet" />


</head>
<body>


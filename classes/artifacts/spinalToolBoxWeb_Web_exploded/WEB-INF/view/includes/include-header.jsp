<%--
  Created by IntelliJ IDEA.
  User: unlocker
  Date: 2014-09-28
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>

<%--
 THIS FILE CONTAINS THE HEADER OF INDEX.JSP PAGE
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html>
<head>
    <meta content="text/html;charset=utf-8" http-equiv="Content-Type">
    <meta content="utf-8" http-equiv="encoding">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Spinal Cord Toolbox web</title>

    <%@ include file="include-css.jsp" %>

</head>
<body ng-app = "SpinalCordToolboxApp" onbeforeunload="return ClosingConfimation()" onunload ="deleteFilesSession()" >


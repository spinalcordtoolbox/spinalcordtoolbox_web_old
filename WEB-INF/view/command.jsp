<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 07/10/14
  Time: 9:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="includes/include-header.jsp" %>

<div ng-app="myNoteApp" >
    <form ng-controller="formCtrl" >
        <input type="text" name = "command" id="command" ng-model="myForm.command" ng-minlength="1" ng-maxlength="30"/>
        <button ng-click="myForm.submitTheForm()">Submit</button>
    </form>

    <div>
        ${consoleOut}
    </div>
</div>
<%@ include file="includes/include-footer.jsp" %>


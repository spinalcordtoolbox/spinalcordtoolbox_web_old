<%--
  Created by IntelliJ IDEA.
  User: unlocker
  Date: 2014-09-28
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<hr/>
<script>
    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
        (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
            m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

    ga('create', 'UA-57080437-1', 'auto');
    ga('send', 'pageview');

</script>
<footer>
    <hr/>
    <div id="footer" class="footer" style="background-color:#333333;height:50px;">
        <span class="pull-left" style="color:#FC5242;">&copy; NeuroPoly-SpinalCord Toolbox 2014</span>
        <a target="_blank" href="http://www.polymtl.ca" class="pull-right" style="color:#FC5242;">POLYTECHNIQUE MONTREAL</a>
    </div>

</footer>


<%@ include file="include-js.jsp" %>

<script type="text/javascript">
    $("#uploadForm").submit(function(event) {
        $("#tabNavigation").scope().myForm.uploadFile();
    });
</script>

</body>

</html>
<%--
  Created by IntelliJ IDEA.
  User: Laobien Jehiel KY - kjehiel@gmail.com
  Date: 2014-09-20
  Time: 15:28
--%>
<%@ include file="includes/include-header.jsp" %>
<div id="main">

    <header>

        <div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="<spring:eval expression="@configProperties.getProperty('home_url')"/>">Spinal Cord Toolbox Web</a>
                </div>
                <div class="navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li><a target="_blank" href="<spring:eval expression="@configProperties.getProperty('sourceforge_url')"/>">Documentation</a></li>
                        <li class="pull-right" data-toggle="modal" data-target="#myModal"><a href="#">About</a></li>
                    </ul>
                </div><!-- /.nav-collapse -->
            </div><!-- /.container -->
        </div><!-- /.navbar -->

        <!-- Modal -->
        <div class="modal" id="myModal" >
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            <span aria-hidden="true">&times;</span>
                            <span class="sr-only">Close</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel">Contact Us</h4>
                    </div>
                    <div class="modal-body">
                        <p>
                            ~~~
                            (c) NeuroPoly lab, Polytechnique Montreal 2014<br/>
                            www.neuro.polymtl.ca<br/>

                            Link to the Spinal Cord Toolbox:<br/>
                            https://sourceforge.net/projects/spinalcordtoolbox<br/>

                            Powered by BrainBrowser:<br/>
                            https://brainbrowser.cbrain.mcgill.ca/<br/>

                            ~~~
                        </p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <div class="alert alert-warning" >This Website is still under development!</div>

    <div id='container-spinal' class="row">
        <div class="col-md-offset-2 col-md-8" >
            <div class="panel panel-danger" >
                <div class="panel-heading">
                    <h3 class="panel-title">404 Not Found</h3>
                </div>
                <div class="panel-body">Hey, unfortunatly, i can't give you what i don't have. You're looking for an inexisting resource</div>
            </div>
        </div>
    </div> <!-- end of 'container-spinal'-->
</div>

<%@ include file="includes/include-footer.jsp" %>
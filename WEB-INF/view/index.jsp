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
                <a class="navbar-brand" href="<spring:eval expression="@configProperties.getProperty('home_url')"/>">WEBSITE IN DEVELOPMENT - Spinal Cord Toolbox web</a>
            </div>
            <div class="navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a target="_blank" href="<spring:eval expression="@configProperties.getProperty('sourceforge_url')"/>">Documentation</a></li>
                    <li class="pull-right"><a href="#">Contact & About</a></li>
                </ul>
            </div><!-- /.nav-collapse -->
        </div><!-- /.container -->
    </div><!-- /.navbar -->
</header>

<div id='container-spinal' >
    <!--Brain browser Module section-->
    <div id="brainbrowser-field" >
        <script id="volume-ui-template" type="x-volume-ui-template">
            <div class="volume-viewer-display col-md-9">
            </div>


            <div class="volume-viewer-controls volume-controls col-md-3">

                <div class="coords">
                    <div class="control-heading" id="voxel-coordinates-heading-{{VOLID}}">
                        Voxel Coordinates:
                    </div>
                   
                    <div class="voxel-coords" data-volume-id="{{VOLID}}">
                        X:<input id="voxel-i-{{VOLID}}" class="control-inputs">
                        Y:<input id="voxel-j-{{VOLID}}" class="control-inputs">
                        Z:<input id="voxel-k-{{VOLID}}" class="control-inputs">
                    </div>
                    <div class="control-heading" id="world-coordinates-heading-{{VOLID}}">
                        World Coordinates:
                    </div>
                    <div class="world-coords" data-volume-id="{{VOLID}}">
                        X:<input id="world-x-{{VOLID}}" class="control-inputs">
                        Y:<input id="world-y-{{VOLID}}" class="control-inputs">
                        Z:<input id="world-z-{{VOLID}}" class="control-inputs">
                    </div>
                </div>

                <div id="intensity-value-div-{{VOLID}}">
        <span class="control-heading" data-volume-id="{{VOLID}}">
          Value:
        </span>
                    <span id="intensity-value-{{VOLID}}" class="intensity-value"></span>
                </div>

                <div id="color-map-{{VOLID}}">
        <span class="control-heading" id="color-map-heading-{{VOLID}}">
          Color Map:
        </span>
                </div>

                <div id="color-map-file-{{VOLID}}" class="color-map-file-div" data-volume-id="{{VOLID}}" >
                    <span class="control-heading">Color map file: </span><input type="file" name="color-map-file-{{VOLID}}" class="color-map-file">
                </div>

                <div class="threshold-div" data-volume-id="{{VOLID}}">
                    <div class="control-heading">
                        Threshold:
                    </div>
                    <div class="thresh-inputs">
                        <input id="min-threshold-{{VOLID}}" class="control-inputs thresh-input-left" value="0"/>
                        <input id="max-threshold-{{VOLID}}" class="control-inputs thresh-input-right" value="255"/>
                    </div>
                    <div class="slider volume-viewer-threshold" id="threshold-slider-{{VOLID}}"></div>
                </div>

                <div id="time-{{VOLID}}" class="time-div" data-volume-id="{{VOLID}}" style="display:none">
                    <span class="control-heading">Time:</span>
                    <input class="control-inputs" value="0" id="time-val-{{VOLID}}"/>
                    <div class="slider volume-viewer-threshold" id="time-slider-{{VOLID}}"></div>
                    <input type="checkbox" class="button" id="play-{{VOLID}}"><label for="play-{{VOLID}}">Play</label>
                </div>

                <div class="contrast-div" data-volume-id="{{VOLID}}">
                    <span class="control-heading" id="contrast-heading{{VOLID}}">Contrast (0.0 to 2.0):</span>
                    <input class="control-inputs" value="1.0" id="contrast-val"/>
                    <div id="contrast-slider" class="slider volume-viewer-contrast"></div>
                </div>

                <div class="brightness-div" data-volume-id="{{VOLID}}">
                    <span class="control-heading" id="brightness-heading{{VOLID}}">Brightness (-1 to 1):</span>
                    <input class="control-inputs" value="0" id="brightness-val"/>
                    <div id="brightness-slider" class="slider volume-viewer-brightness"></div>
                </div>

                <fieldset id="slice-series-{{VOLID}}" class="slice-series-div" data-volume-id="{{VOLID}}">
                    <legend class="control-heading" id="slice-series-heading-{{VOLID}}">All slices: </legend>
                    <button type="button" class="slice-series-button button btn btn-primary btn-sm" data-axis="xspace">Sagittal</button>
                    <button type="button" class="slice-series-button button btn btn-primary btn-sm" data-axis="yspace">Coronal</button>
                    <button type="button" class="slice-series-button button btn btn-primary btn-sm" data-axis="zspace">Transverse</button>
                </fieldset>
                <br/>
                <fieldset>
                    <legend>Zoom:</legend>
                    <button type="button" id="zoom-in-btn" class="button btn btn-primary btn-xs pull-left">zoom In</button>

                    <select id="zoom-type">
                        <option value="coronal">Coronal</option>
                        <option value="sagittal">Sagittal</option>
                        <option value="transverse">Transverse</option>
                    </select>

                    <button type="button" id="clear-zoom-btn" class="button btn btn-primary btn-xs pull-right">Clear Zoom</button>
                </fieldset>
                <br/>
                <fieldset id="label-series" class="label-series-div">
                    <legend>All labels:</legend>
                    <button type="button" id="label-series-add-button"    class="button btn btn-success btn-sm" >Add</button>
                    <button type="button" id="label-series-remove-button" class="button btn btn-danger btn-sm">Remove</button>
                    <button type="button" id="label-series-tag-button"    class="button btn btn-primary btn-sm pull-right">Tag</button>
                    <br/>
                    <div id="label-series-div"></div>
                </fieldset>
            </div>

        </script>
        <script id="overlay-ui-template" type="x-volume-ui-template">
            <div class="overlay-viewer-display">
            </div>

            <div class="volume-viewer-controls volume-controls">

                <div id="intensity-value-div-{{VOLID}}">
        <span class="control-heading" data-volume-id="{{VOLID}}">
          Value:
        </span>
                    <span id="intensity-value-{{VOLID}}" class="intensity-value"></span>
                </div>

                <div class="blend-div" data-volume-id="{{VOLID}}">
                    <span class="control-heading" id="blend-heading{{VOLID}}">Blend (0.0 to 1.0):</span>
                    <input class="control-inputs" value="0.5" id="blend-val"/>
                    <div id="blend-slider" class="slider volume-viewer-blend"></div>
                </div>

                <div class="contrast-div" data-volume-id="{{VOLID}}">
                    <span class="control-heading" id="contrast-heading{{VOLID}}">Contrast (0.0 to 2.0):</span>
                    <input class="control-inputs" value="1.0" id="contrast-val"/>
                    <div id="contrast-slider" class="slider volume-viewer-contrast"></div>
                </div>

                <div class="brightness-div" data-volume-id="{{VOLID}}">
                    <span class="control-heading" id="brightness-heading{{VOLID}}">Brightness (-1 to 1):</span>
                    <input class="control-inputs" value="0" id="brightness-val"/>
                    <div id="brightness-slider" class="slider volume-viewer-brightness"></div>
                </div>
            </div>
        </script>
    </div>

    <div id="brainbrowser-wrapper" style="display:none" class="row" >

        <div id="tabs_spinal" class="col-md-3 " style="margin-top: 25px;">
            <ul id="tabs" class="nav nav-tabs nav-justified" data-tabs="tabs">
                <li class="active"><a href="#tabNavigation" data-toggle="tab">Navigation</a></li>
                <li><a href="#tabProcess" data-toggle="tab">Process</a></li>
                <li><a href="#tabOutput" data-toggle="tab">Output</a></li>
            </ul>
            <div id="tab-spinal-field" class="tab-content" style="overflow-y:auto; overflow-x:hidden;   max-height:900px; " >
                <div class="tab-pane active" id="tabNavigation" ng-controller="uploadCtrl" >
                    <!--buttons and Navigation file DIV-->
                    <div class="row" id='nav-buttons-field'>
                        <div class="col-md-offset-0 col-md-12">
                            <form id = "uploadForm" >
                                <br/>
                                <legend>File to upload</legend>
                               <%-- <input  type="file" file-M="myFile "/>
                                <button id = "volume-file-submit" type="button" ng-click="myForm.submitTheForm()" class="btn btn-primary">Submit</button>--%>

                                <input id="input-1a" type="file" class="file" data-show-preview="false" file-M="myFile ">
                            </form>
                        </div>
                    </div>

                    <!--Navigation files DIV-->
                    <div ng-controller = "fileManagerCtrl">
                      <table  id='navigation-field' class="table table-striped table-bordered table-hover">
                            <tr ng-repeat="file in listOfFiles">
                                <td >
                                    <input type="checkbox" ng-model="file.checked" ng-show="file.display" ng-click="updateSelection($event, file)" />
                                    <label> {{file.fileName}} </label>
                                    <button type="button" class="btn btn-primary btn-sm btn-danger pull-right" ng-show="file.display" ng-click ="deleteFile(file)">
                                        <span class="glyphicon glyphicon-trash"> </span>
                                    </button>
                                </td>

                            </tr>
                            <!--tree-->


                      </table>
                    </div>

                </div> <!-ab_Navigation-->

                <!--Tab process DIV-->
                <div class="tab-pane" id="tabProcess">
                    <div class="row" id="process-field" >
                        <div ng-controller="PostsCtrl" id="commandInputGenerator" class="col-md-offset-0 col-md-12 ">
                            <br/>
                            <h4>Select process</h4>
                            <select class = "selectpicker" ng-change = "getJsonConfig()" ng-model="currentScript" ng-options="script.title for script in scripts | filter:{activateScript:'true'} | orderBy : 'title' "></select>
                            <form class="form-horizontal">
                                <fieldset id = "htmlInject">
                                </fieldset>
                                <button ng-disabled = "false" ng-show = "getProcessShow()"  ng-click = "runScript()" class="btn btn-primary" >Run Process</button>
                            </form>
                        </div>
                    </div>
                </div><!--tab_Process-->

                <!--Tab Output DIV-->
                <div class="tab-pane" id="tabOutput">
                    <!-- here output tab code -->
                </div> <!-- end of Output tab DIV-->
            </div><!-- tab content -->
        </div><!--tabs_spinal -->

        <div id="volume-viewer" class="col-md-9">
            <div id="loading" style="display: none">
                <spring:url value="/resources/viewRessouces/js/lib/examples/img/ajax-loader.gif" var="image_ajax_loader" />
                <img src="${image_ajax_loader}" />
            </div><br>
            <div id="brainbrowser" ></div>
        </div>

    </div>

    <div class="row" id = "tabConsole">
        <div class="panel-group" id="accordion">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a id="console" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                            <span  id="consoleExpand"   class="glyphicon glyphicon-expand"></span>
                        Mode expert - Click to show/hide the terminal
                        </a>
                    </h4>
                </div>
                <div id="collapseOne" class="panel-collapse collapse out">
                    <div class="panel-body">
                        <div class="col-lg-6 col-lg-offset-3">
                            <div ng-app="myNoteApp" id='commandline-field'></div>
                        </div><!-- end of col-lg-6 col-lg-offset-3-->
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- row Console -->

</div> <!-- end of 'container-spinal'-->

<%@ include file="includes/include-footer.jsp" %>

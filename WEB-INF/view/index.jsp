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
                    <button type="button" class="navbar-toggle
            " data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="">SpinalCordToolbox Web</a>
                </div>
                <div class="navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="4">Home</a></li>
                        <li><a href="index.html">Documentation</a></li>
                        <li><a href="index.html">About</a></li>
                        <li><a href="index.html">Contact</a></li>
                    </ul>
                </div><!-- /.nav-collapse -->
            </div><!-- /.container -->
        </div><!-- /.navbar -->

    </header>


    <div id='container-spinal' >
        <!--Brain browser Module section-->
        <div id="brainbrowser-field" >
            <script id="volume-ui-template" type="x-volume-ui-template" class="row">
                <div class="volume-viewer-display col-md-9">
                </div>
                <div class="volume-viewer-controls volume-controls col-md-3">

                    <div class="coords">
                        <div class="control-heading" id="voxel-coordinates-heading-{{VOLID}}">
                            Voxel Coordinates:
                        </div>
                        <div class="voxel-coords" data-volume-id="{{VOLID}}">
                            I:<input id="voxel-i-{{VOLID}}" class="control-inputs">
                            J:<input id="voxel-j-{{VOLID}}" class="control-inputs">
                            K:<input id="voxel-k-{{VOLID}}" class="control-inputs">
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

                    <div id="slice-series-{{VOLID}}" class="slice-series-div" data-volume-id="{{VOLID}}">
                        <div class="control-heading" id="slice-series-heading-{{VOLID}}">All slices: </div>
                        <span class="slice-series-button button" data-axis="xspace">Sagittal</span>
                        <span class="slice-series-button button" data-axis="yspace">Coronal</span>
                        <span class="slice-series-button button" data-axis="zspace">Transverse</span>
                    </div>

                    <fieldset id="label-series" class="label-series-div">
                        <legend style="font-weight:bold;">All labels:</legend>
                        <span id="label-series-add-button" class="button " >add</span>
                        <span id="label-series-remove-button" style="margin-bottom:10px;"class="button">remove</span>
                        <span id="label-series-tag-button" style="float:right;"class="button">tag</span>
                        <div id="label-series-div"></div>
                    </fieldset>
                    <span id="zoom-in-btn" class="button" style="float:left;">zoom In</span>
                    <select id="zoom-type">
                        <option value="coronal">Coronal</option>
                        <option value="sagittal">Sagittal</option>
                        <option value="transverse">Transverse</option>
                    </select>
                    <span id="clear-zoom-btn" class="button" style="float:left;">Clear Zoom</span>
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
            <div id="tabs_spinal" class="col-md-3" >
                <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
                    <li class="active"><a href="#tabNavigation" data-toggle="tab">Navigation</a></li>
                    <li><a href="#tabProcess" data-toggle="tab">Process</a></li>
                    <li><a href="#tabOutput" data-toggle="tab">Output</a></li>
                </ul>
                <div id="tab_spinal" class="tab-content">
                    <div class="tab-pane active" id="tabNavigation" ng-controller="uploadCtrl">
                        <!--buttons and Navigation file DIV-->
                        <div class="row" id='nav-buttons-field'>
                            <div class="btn-toolbar">
                                <div class="btn-group">
                                    <form>
                                        File to upload: <input type="file" file-M="myFile" />
                                        <button id = "volume-file-submit" type="button" ng-click="myForm.submitTheForm()">Submit</button>
                                    </form>
                                </div>
                            </div>
                        </div>

                        <!--Navigation files DIV-->
                        <div  id='navigation-field'>
                            <!-- Put Navigation files code here -->
                            <h4> Uploaded NIfTI Images </h4>

                            <!--tree-->
                            <label ng-repeat="file in listOfFiles">
                                <input type="checkbox" ng-click="updateSelection($event, file.id)"> {{file.fileName}}  <button>delete</button>
                            </label>
                            <!--<p>{{result}}</p>-->
                        </div>

                    </div> <!--tab_Navigation-->

                    <!--Tab process DIV-->
                    <div class="tab-pane" id="tabProcess">
                        <div class="row" id="process-field" >
                            <div ng-controller="PostsCtrl" id="commandInputGenerator">
                                <center>
                                    <h4>select process</h4>
                                    <select class = "selectpicker" ng-change = "getJsonConfig()" ng-model="currentScript" ng-options="script.title for script in scripts | filter:{activateScript:'true'}"></select>
                                </center>
                                <br><hr><br>
                                <form>
                                    <div id = "htmlInject">
                                    </div>
                                    <button ng-disabled = "false" ng-show = "getProcessShow()"  ng-click = "runScript()" >Run Process</button>
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
                <div id="brainbrowser"></div>
            </div>

        </div>


    </div> <!-- end of 'container-spinal'-->

<%@ include file="includes/include-footer.jsp" %>

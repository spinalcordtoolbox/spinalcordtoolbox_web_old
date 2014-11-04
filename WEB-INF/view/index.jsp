<%--
  Created by IntelliJ IDEA.
  User: Laobien Jehiel KY - kjehiel@gmail.com
  Date: 2014-09-20
  Time: 15:28
--%>
<%@ include file="includes/include-header.jsp" %>
<script id="volume-ui-template" type="x-volume-ui-template">
    <div class="volume-viewer-display">
    </div>
    <div class="volume-viewer-controls volume-controls">

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

        <div id="label-series-{{VOLID}}" class="label-series-div" data-volume-id="{{VOLID}}">
            <div class="control-heading" id="label-series-heading-{{VOLID}}">All labels: </div>
            <span class="label-series-button button " >tag</span>
            <div class="voxel-coords" data-volume-id="{{VOLID}}">
                I:<input id="voxel-i-1" class="control-inputs">
                J:<input id="voxel-j-1" class="control-inputs">
                K:<input id="voxel-k-1" class="control-inputs">
            </div>
        </div>

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
<div id="brainbrowser-wrapper" style="display:none">
    <div id="volume-viewer">
        <div id="brainbrowser"></div>
    </div>
</div>
<%@ include file="includes/include-footer.jsp" %>


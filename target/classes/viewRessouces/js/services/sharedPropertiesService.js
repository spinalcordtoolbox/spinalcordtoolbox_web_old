/*
 THIS FILE CONTAINS SERVICE TO SHARE AND COMMUNICATE SOME DATA BETWEEN DIFFERENT APPLICATION CONTROLLERS
 */

app.service('sharedProperties', function () {

    var sharedSelectedFiles = [];
    var sharedListFiles = [];
    var sharedIndex = 0;
    var sharedSelected = [];
    var sharedLoadVolumesParameter = {};
    var sharedVolumes = [];
    var consoleLog = null;
    var terminalData = null;

    return {
        getSharedSelectedFiles: function () {
            return sharedSelectedFiles;
        },
        setSharedSelectedFiles: function(val) {
            sharedSelectedFiles = val;
        },
        getSharedListFiles: function () {
            return sharedListFiles;
        },
        setSharedListFiles: function(val) {
            sharedListFiles = val;
        },
        getSharedIndex: function () {
            return sharedIndex;
        },
        setSharedIndex: function(val) {
            sharedIndex = val;
        },
        getSharedSelected: function () {
            return sharedSelected;
        },
        setSharedSelected: function(val) {
            sharedSelected = val;
        },
        getSharedLoadVolumesParameter: function () {
            return sharedLoadVolumesParameter;
        },
        setSharedLoadVolumesParameter: function (val) {
            sharedLoadVolumesParameter= val;
        },
        getSharedVolumes: function() {
            return sharedVolumes;
        },
        setSharedVolumes: function(val) {
            sharedVolumes= val;
        },
        setConsoleLog: function(log){
            consoleLog = log;
        },
        getConsoleLog: function(){
            return consoleLog;
        },
        setTerminalData: function(terminalData){
            terminalData = terminalData;
        },
        getTerminal: function(){
            return terminalData;
        }

    };
});

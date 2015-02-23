
/*
    THIS FILE CONTAINS COMMAND GENERATION CONTROLLER.
    IT CALLS FUNCTIONS FROM CommandOperations and HTMLOperation Models.
    by Youssef - youssef.khairallah@gmail.com
 */

app.controller("PostsCtrl", ["$scope","$http","sharedProperties", function($scope, $http, sharedProperties)
{

    this.currentScript = $scope.currentScript;
    $scope.processButtonShow = false;
    $scope.processButtonDisabled = true;
    $scope.mapStructure = {};
    $scope.pythonCommand = "";
    $scope.inputImageRequired = false;

    this.scriptConfigJson = $scope.scriptConfigJson;

    $scope.HtmlToInject = "";

    this.len = 0;


    /*
        GET JSON INFORMATION OF SCRIPTS FROM DISTANT SERVE"
     */
    $http.get(PROCESS_JSON_LIST_PATH_CST).
        success(function(data, status, headers, config)
        {
            $scope.scripts = data;
        }).
        error(function(data, status, headers, config)
        {
            alert("Error server communication !");
        });


    $scope.generateDynamicHTML = function(data, sharedProperties)
    {
        generateDynamicHTML(data, sharedProperties);
    }


    /*
        GET JSON CONFIG INFORMATION OF SELECTED PROCESS BY USER FROM DISTANT SERVER
     */
    $scope.getJsonConfig = function()
    {


        var path = this.currentScript.pathConfig;

        $http.get(path).
            success(function(data, status, headers, config)
            {

                $scope.generateDynamicHTML(data, sharedProperties);

            }).
            error(function(data, status, headers, config)
            {

                alert("Error server communication !");

            });

    }


    $scope.resetHtml = function()
    {
        resetHTML();
    }


    /*
        CALLED WHEN USER CLICK ON RUN PROCESS BUTTON
     */
    $scope.runScript = function()
    {

        if($scope.verifEmptyMandatoryField(sharedProperties))
        {
            //alert("Please fill the empty mandatory field(s) !");
            return;
        }

        generateCommand();

        $scope.sendCommandToServer($scope.pythonCommand);

        $scope.init();

        $scope.currentScript = "";

    }

    $scope.sendCommandToServer = function(command)
    {
        sendCommandToServer($http, sharedProperties, command);
    }


    $scope.init = function()
    {
        $scope.processButtonShow = false;
        $scope.processButtonDisabled = true;
        $scope.resetHtml();
        $scope.pythonCommand = "";
        $scope.mapStructure = {};
        $scope.inputImageRequired = false;

    }

    $scope.getProcessShow = function()
    {
        return $scope.processButtonShow;
    }


    $scope.HTMLSpace = function(nbrEspace)
    {
        return HTMLSpace(nbrEspace);
    }

    $scope.getProcessDisabled = function()
    {
        return  $scope.processButtonDisabled;
    }

    $scope.verifEmptyMandatoryField = function(sharedProperties)
    {
        return verifEmptyMandatoryField(sharedProperties);
    }

    $scope.getSharedPropertiesService = function()
    {
        return sharedProperties;
    }

    $scope.commandReceptionTreatments = function(data)
    {
        commandReceptionTreatments(data, sharedProperties);
    }


}]);
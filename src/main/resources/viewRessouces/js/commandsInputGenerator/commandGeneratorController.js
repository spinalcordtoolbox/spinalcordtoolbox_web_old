
app.controller("PostsCtrl", ["$scope","$http","sharedProperties", function($scope, $http, sharedProperties)
{

    this.currentScript = $scope.currentScript;
    $scope.processButtonShow = false;
    $scope.processButtonDisabled = true;
    $scope.mapStructure = {};
    $scope.pythonCommand = "";

    this.scriptConfigJson = $scope.scriptConfigJson;

    $scope.HtmlToInject = "";

    this.len = 0;


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


    $scope.getJsonConfig = function()
    {

        //var selectedFiles = sharedProperties.getSharedSelectedFiles();

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


    $scope.runScript = function()
    {

        if($scope.verifEmptyMandatoryField())
        {
            alert("Please fill the empty mandatory field(s) !");
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

    $scope.verifEmptyMandatoryField = function()
    {
        return verifEmptyMandatoryField();
    }


}]);
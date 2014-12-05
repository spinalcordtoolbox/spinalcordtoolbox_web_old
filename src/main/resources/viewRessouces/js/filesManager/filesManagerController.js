app.controller("fileManagerCtrl", ["$scope", "$http" ,"sharedProperties", function($scope, $http, sharedProperties)
{


    $scope.deleteFromServer = function(nomImage)
    {
        deleteFromServer($scope, $http, nomImage);
    }

    $scope.deleteFile = function(file)
    {
        deleteFile($scope, $http, sharedProperties, file);
    }


    $scope.displayVolume = function()
    {
        displayVolume(sharedProperties);
    }

    $scope.updateSelection = function($event, file)
    {
        updateSelection($event, file, sharedProperties);
    };

}]);
/**
 * Created by inf4990 on 2014-12-08.
 */
app.controller("terminalController", ["$scope" , "sharedProperties", function($scope, sharedProperties)
{

    $scope.setTerminalData = function(data){

        var commandControllerScope = angular.element('[ng-controller="PostsCtrl"]').scope();
        console.log(commandControllerScope);
        commandControllerScope.commandReceptionTreatments(data);
    };




}]);

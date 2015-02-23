/**
 * Created by root on 06/12/14.
 */


app.controller("logController", ["$scope" , "sharedProperties", function($scope, sharedProperties)
{
    $scope.consoleLog = "First log";
    var log = function(){
        $scope.consoleLog = sharedProperties.getConsoleLog();
    };
    setInterval(function(){
            $scope.$apply(log);
        }, 1000
    );
}]);

/**
 * Created by root on 17/10/14.
 */

var app = angular.module("myNoteApp", []);
app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
}]);

app.controller("formCtrl", function($scope, $http) {
    $scope.myForm = {};
    $scope.myForm.command = "";

    $scope.myForm.submitTheForm = function() {

        console.log("--> Submitting form");
        $scope.dataObject = {
            command : $scope.myForm.command
        };
        var data = 'command='+    $scope.myForm.command;
        var responsePromise = $http.post("/command",data, {});

        responsePromise.success(function(dataFromServer, status, headers, config) {
            console.log("server ok");
        });
        responsePromise.error(function(data, status, headers, config) {
            console.log("Submitting form failed!");
        });
    }

});


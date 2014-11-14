/**
 * Created by root on 17/10/14.
 * source : http://jsfiddle.net/JeJenny/ZG9re/
 */

/*
var app = angular.module("uploadApp", []);

app.directive('fileM', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileM);
            var ms = model.assign;

            element.bind('change', function(){
                scope.$apply(function(){
                    ms(scope, element[0].files[0]);
                });
            });
        }
    };
}]);



app.internal("uploadCtrl", function($scope, $http) {

    $scope.myForm = {};
    $scope.listOfFiles= [];
    $scope.selection={};




    $scope.myForm.submitTheForm = function() {

        console.log("--> Submitting form");

        var fd = new FormData();

        var file = $scope.myFile;
        // console.log('file is ' + JSON.stringify(file));
        fd.append('file', file);



        var token = {
            val: Number(new Date())
        };




        var token = (Number(new Date())).toString();

        //console.log('token is ' + JSON.stringify(token));

        fd.append('token', angular.toJson(token));

        fd.append('token', token);

        var responsePromise =  $http.post("/upload", fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        });

        responsePromise.success(function(dataFromServer, status, headers, config) {
            console.log("server ok");
            $scope.listOfFiles.push(dataFromServer );

        });

        responsePromise.error(function(data, status, headers, config) {
            console.log("Submitting form failed!");
        });


    }



});
*/

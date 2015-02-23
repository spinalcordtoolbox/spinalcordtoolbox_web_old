
/*
THIS FILE CONTAINS THE APPLICATION UPLOAD CONTROLLER
 */

app.controller("uploadCtrl", ["$scope", "$http" ,"sharedProperties", function($scope, $http, sharedProperties) {

 var loading_div = $("#loading");

 $scope.myForm = {};
 $scope.listOfFiles= [];
 $scope.selected = [];
 $scope.selectedFiles = [];
 $scope.index=0;
 $scope.checked=false;
 $scope.isFirstUpload = true;
 $scope.userToken = null;


 $scope.myForm.uploadFile = function() {

     uploadFile($scope, $http, sharedProperties);
 }
 }]);

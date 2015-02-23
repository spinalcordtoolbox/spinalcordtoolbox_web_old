/*
    THIS FILE CONTAINS FILE MANAGER MODELS
 */


/*
    THIS FUNCTION DELETE SELECTED IMAGE BY USER IN THE UI FROM THE DISTANT SERVER
    IT IS CALLED BY deleteFile(...) FUNCTION
 */
function deleteFromServer(serviceScope, serviceHttp, nomImage)
{

    var loading_div = $("#loading");
    loading_div.show();

    var fd = new FormData();

    fd.append('fileName', nomImage);

    var res = serviceHttp.post('/deleteFiles', fd, {
        transformRequest: angular.identity,
        headers: {'Content-Type': undefined}
    });

    res.success(function(data, status, headers, config)
    {
        serviceScope.message = data;
        loading_div.hide();
    });
    res.error(function(data, status, headers, config)
    {
        alert( "failure message: " + JSON.stringify({data: data}));
        loading_div.hide();
    });
}

/*
    THIS FUNCTION DELETE SELECTED IMAGE BY USER IN THE UI.
    IT DELETES ALSO THE IMAGE LOCATED IN DISTANT SERVER AFTER CALLING deleteFromServer(...) FUNCTION
 */
function deleteFile(serviceScope, serviceHttp,serviceSharedProperties, file)
{
    var sharedListOfFile = serviceSharedProperties.getSharedListFiles();
    var sharedSelected   = serviceSharedProperties.getSharedSelected();


    var index = sharedSelected.indexOf(file.id);
    sharedSelected.splice(index,1);
    serviceSharedProperties.setSharedSelected(sharedSelected);

    //$scope.listOfFiles = sharedProperties.getSharedListFiles();
    var index = sharedListOfFile.indexOf(file);
    sharedListOfFile[index].checked=false;
    serviceSharedProperties.setSharedListFiles(sharedListOfFile);


    /////////////////////////////////////

    var index = sharedListOfFile.indexOf(file);
    var fileName = sharedListOfFile[index].fileName;
    sharedListOfFile[index].display = false;
    sharedListOfFile.splice(index,1);
    serviceSharedProperties.setSharedListFiles(sharedListOfFile);

    var indexOfListOfFiles = serviceSharedProperties.getSharedIndex();
    indexOfListOfFiles = indexOfListOfFiles - 1;

    serviceSharedProperties.setSharedIndex(indexOfListOfFiles);


    if(sharedListOfFile.length == 0 || sharedSelected.length == 0)
    {
        viewer.clearVolumes();
        var loadVolumesParameter = {};
        serviceSharedProperties.setSharedLoadVolumesParameter(loadVolumesParameter);
        //$scope.loadVolumesParameter = {};
        //$scope.volumes = [];
        var volumes = [];
        serviceSharedProperties.setSharedVolumes(volumes);
        generateStartupImageVolume(serviceSharedProperties);
        loadVolumesParameter = serviceSharedProperties.getSharedLoadVolumesParameter();
        viewer.loadVolumes(loadVolumesParameter);
    }
    else
    {
        processing(serviceSharedProperties);
    }
    console.log(fileName);
    deleteFromServer(serviceScope, serviceHttp, fileName);

}

/*
    THIS FUNCTION DELETE THE UPLOAD REPOSITORY OF THE USER AFTER SESSION LOGOUT
 */
deleteUserRepositoryFromServer = function()
{
    var uploadControllerScope = angular.element('[ng-controller="uploadCtrl"]').scope();

    var userToken = uploadControllerScope.userToken;

    if(userToken != null)
    {
        var loading_div = $("#loading");
        loading_div.show();

        $.ajax({

                type: 'POST',
                async : false,
                url : '/deleteRepoFiles'
            }
        )

    }


}

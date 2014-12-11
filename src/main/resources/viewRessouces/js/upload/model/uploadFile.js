

/*
THIS FILE CONTAINS UPLOAD MODELS
 */




/*
    THIS FUNCTION UPLOAD AN IMAGE TO THE DISTANT SERVER AND ADD IT TO THE UI
 */
function uploadFile(scopeService, httpService, sharedPropertiesService)
{

    var loading_div = $("#loading");
    loading_div.show();
    console.log("--> Submitting form");

    var fd = new FormData();
    var file = scopeService.myFile;

    if(typeof file == "undefined")
    {
        loading_div.hide();
        alert("Select an Image to upload first !");
        return;

    }


    // verif is file already uploaded. Assume that user doesn't have same images name for different content.
    var tempSharedFiles = sharedPropertiesService.getSharedListFiles();
    var cpt = 0;
    for(cpt ; cpt < tempSharedFiles.length ; cpt++)
    {
        if(tempSharedFiles[cpt].fileName == file.name)
        {
            alert("File already uploaded !");
            loading_div.hide();
            return;
        }
    }

    fd.append('file', file);

    if(scopeService.isFirstUpload)
    {
        scopeService.isFirstUpload = false;
        scopeService.userToken = (Number(new Date())).toString();
        console.log("Original token" + scopeService.userToken);
    }
    fd.append('token', scopeService.userToken);

    var responsePromise = httpService.post("/upload", fd, {
        transformRequest: angular.identity,
        headers: {'Content-Type': undefined}
    });

    responsePromise.success(function (dataFromServer, status, headers, config)
    {

        console.log("server ok");

        console.log(dataFromServer);


        // information received from the server about the uploaded file
        var fileName = dataFromServer.serverInfoResponse[0][0]; // fileName
        var headerPathName = dataFromServer.serverInfoResponse[0][1]; // headerPath
        var rawPathName = dataFromServer.serverInfoResponse[0][2]; // rawPath
        var consoleOut = dataFromServer.serverInfoResponse[0][3]; // consoleOut


        var id = scopeService.index;
        scopeService.listOfFiles = sharedPropertiesService.getSharedListFiles();
        scopeService.listOfFiles.push({"id": id, "fileName": fileName, "headerPathName": headerPathName, "rawPathName": rawPathName, "display": true, "checked": true});
        sharedPropertiesService.setSharedListFiles(scopeService.listOfFiles);
        scopeService.index = sharedPropertiesService.getSharedIndex();
        scopeService.index = scopeService.index + 1;
        sharedPropertiesService.setSharedIndex(scopeService.index);
        loading_div.hide();

        var fileManagerScope = angular.element('[ng-controller="fileManagerCtrl"]').scope();
        fileManagerScope.displayVolume();


        sharedPropertiesService.setConsoleLog(consoleOut);


    });
    responsePromise.error(function (data, status, headers, config) {
        alert("Submitting file failed ! Error with server communication");
        var consoleOut = data.serverInfoResponse[0][INDEX_OF_SERVER_RESPONSE_CONSOLE_OUTPUT];
        sharedPropertiesService.setConsoleLog(consoleOut);

        loading_div.hide();
    });
}





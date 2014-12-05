

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

        if(dataFromServer.outputConsole != "BackEnd execution ERROR !")
        {
            var fileName = dataFromServer.responseFileName;
            var headerPathName = dataFromServer.responsePathHeader;
            var rawPathName = dataFromServer.responsePathRaw;
            var consoleOut = dataFromServer.outputConsole;


            if(fileName == "undefined")
            {
                loading_div.hide();
                alert("Uploading file Error ! File Extension not permitted or server execution error !");
                return
            }

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
        }
        else
        {
            alert("Uploading file Error !");
        }
    });
    responsePromise.error(function (data, status, headers, config) {
        alert("Submitting file failed ! Error with server communication");
        loading_div.hide();
    });
}





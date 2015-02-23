/*
    THIS FILE CONTAINS COMMAND OPERATIONS MODELS
 */


/*
    THIS FUNCTION SENDS THE GENERATED COMMAND TO DISTANT SERVER TO BE EXECUTED
    IT RECEIVES RESPONSE FROM THE SERVER AND TREAT IT. (ADD GENERATED FILES TO THE UI FOR MANIPULATIONS)
 */
function sendCommandToServer(serviceHttp, serviceSharedProperties, command)
{
    var loading_div = $("#loading");

    loading_div.show();

    console.log("--> sending command to server ...");

    var fd = new FormData();

    var commandS = command.toString();

    fd.append('command', commandS);

    var responsePromise = serviceHttp.post("/command", fd, {
        transformRequest: angular.identity,
        headers: {'Content-Type': undefined}
    });

    responsePromise.success(function (dataFromServer, status, headers, config)
    {

        console.log(dataFromServer);

        loading_div.hide();

        commandReceptionTreatments(dataFromServer, serviceSharedProperties);

    });

    responsePromise.error(function (data, status, headers, config) {
        console.log("Submitting form failed!");
        var consoleOutput = null;
        var nbrFileReceived = data.serverInfoResponse.length;
        var i = 0;
        for(i = 0; i < nbrFileReceived ; i++) {
            consoleOutput += data.serverInfoResponse[i][INDEX_OF_SERVER_RESPONSE_CONSOLE_OUTPUT];
        }
        serviceSharedProperties.setConsoleLog(consoleOutput);

        loading_div.hide();
    });


}


/*
    THIS FUNCTION RECEIVES SERVER RESPONSE AFTER COMMAND EXECUTION AND TREATS.
    THE SERVER RESPONSE IS FILES GENERATED IN THE BACKEND.
    THESE FILES WILL BE ADDED AFTERWARD IN THE UI LIST.
 */
commandReceptionTreatments = function(dataFromServer, serviceSharedProperties)
{
    var loading_div = $("#loading");

    loading_div.show();

    var nbrFileReceived = dataFromServer.serverInfoResponse.length;
    var consoleOutput = "";
    if(nbrFileReceived > 0)
    {
        var i = 0;

        for(i = 0; i < nbrFileReceived ; i++)
        {

            /*
             INFORMATION OF GENERATED FILES RECEIVED FROM SERVER
             */
            var fileName = dataFromServer.serverInfoResponse[i][0];
            var headerPathName = dataFromServer.serverInfoResponse[i][1];
            var rawPathName = dataFromServer.serverInfoResponse[i][2];


            if(fileName == null)
            {
                serviceSharedProperties.setConsoleLog(consoleOutput);
                loading_div.hide();
                return;
            }

            var fileNameExist = false;

            /*
             If the name of resulting file already exists in UI, it will be ignored
             */
            var tempSharedFiles = serviceSharedProperties.getSharedListFiles();
            var cpt = 0;
            for(cpt ; cpt < tempSharedFiles.length ; cpt++)
            {
                if(tempSharedFiles[cpt].fileName == fileName)
                {
                    fileNameExist = true;
                    break;
                }
            }

            if(fileNameExist)
            {
                continue;
            }

            consoleOutput += dataFromServer.serverInfoResponse[i][3];
            consoleOutput += "\r\n" + "=======================================" + "\r\n";

            var id = serviceSharedProperties.getSharedIndex();

            var listOfFilesTemp = serviceSharedProperties.getSharedListFiles();

            listOfFilesTemp.push({"id": id, "fileName": fileName, "headerPathName": headerPathName, "rawPathName": rawPathName, "display": true, "checked": true});

            serviceSharedProperties.setSharedListFiles(listOfFilesTemp);

            serviceSharedProperties.setSharedIndex(id + 1);
        }
    }

    loading_div.hide();
}



/*
    THIS FUNCTION GENERATES THE COMMAND TO BE SEND TO DISTANT SERVER FOR EXECUTION
 */
generateCommand = function()
{


    var commandGeneratorControllerScope = angular.element('[ng-controller="PostsCtrl"]').scope();


    len = Object.keys(commandGeneratorControllerScope.mapStructure).length;

    keyObj = Object.keys(commandGeneratorControllerScope.mapStructure);

    var cpt = 0;

    for(cpt ; cpt < len ; cpt++)
    {

        IdElement = keyObj[cpt];
        element = document.getElementById(IdElement);


        // verifier si l'usager a entre une valeur
        if(element != null)
        {
            valInput = element.value;
            typeInput = element.type;

            if(typeInput != "checkbox")
            {
                if(typeInput != "text")
                {
                    if(document.getElementById(IdElement).value != "" && document.getElementById(IdElement).selected)
                    {
                        commandGeneratorControllerScope.pythonCommand += commandGeneratorControllerScope.mapStructure[IdElement] + " " + document.getElementById(IdElement).value + " ";
                    }
                }
                else
                {
                    if(document.getElementById(IdElement).value != "")
                    {
                        commandGeneratorControllerScope.pythonCommand += commandGeneratorControllerScope.mapStructure[IdElement] + " " + document.getElementById(IdElement).value + " ";
                    }
                }
            }
            else
            {
                if(element.checked)
                {
                    commandGeneratorControllerScope.pythonCommand += commandGeneratorControllerScope.mapStructure[IdElement] + " ";
                }
            }
        }

    }


    // if there are additional processing scripts
    var additionalScripts = commandGeneratorControllerScope.scriptConfigJson[0].additionalScriptGenerator;

    if(additionalScripts.length > 0)
    {
        var i = 0;
        for(i = 0 ; i < additionalScripts.length ; i++)
        {
            var command = additionalScripts[i].command;

            var scriptName = additionalScripts[i].specialScript;
            var result = eval(scriptName).call();

            if(result != "")
            {
                commandGeneratorControllerScope.pythonCommand += command + " ";
                commandGeneratorControllerScope.pythonCommand += result + " ";
            }



        }
    }

    console.log(commandGeneratorControllerScope.pythonCommand);

}
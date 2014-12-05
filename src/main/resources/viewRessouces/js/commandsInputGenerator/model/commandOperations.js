

function sendCommandToServer(serviceHttp, serviceSharedProperties, command)
{
    var loading_div = $("#loading");

    loading_div.show();

    console.log("--> sending command to server");

    var fd = new FormData();

    var commandS = command.toString();

    fd.append('command', commandS);

    var responsePromise = serviceHttp.post("/command", fd, {
        transformRequest: angular.identity,
        headers: {'Content-Type': undefined}
    });

    responsePromise.success(function (dataFromServer, status, headers, config)
    {
        if(dataFromServer.outputConsole != "BackEnd execution ERROR !")
        {

            var fileName = dataFromServer.responseFileName;
            var headerPathName = dataFromServer.responsePathHeader;
            var rawPathName = dataFromServer.responsePathRaw;
            var consoleOut = dataFromServer.outputConsole;

            loading_div.hide();

            var id = serviceSharedProperties.getSharedIndex();
            var listOfFilesTemp = serviceSharedProperties.getSharedListFiles();

            listOfFilesTemp.push({"id": id, "fileName": fileName, "headerPathName": headerPathName, "rawPathName": rawPathName, "display": true, "checked": true});

            serviceSharedProperties.setSharedListFiles(listOfFilesTemp);

            serviceSharedProperties.setSharedIndex(id + 1);

            loading_div.hide();

        }
    });

    responsePromise.error(function (data, status, headers, config) {
        console.log("Submitting form failed!");
        loading_div.hide();
    });


}

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
                if(document.getElementById(IdElement).value != "")
                {
                    commandGeneratorControllerScope.pythonCommand += commandGeneratorControllerScope.mapStructure[IdElement] + " " + document.getElementById(IdElement).value + " ";
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
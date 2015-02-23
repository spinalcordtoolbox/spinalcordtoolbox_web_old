/*
    THIS FILE CONTAINS HTML OPERATIONS MODELS -
    by Youssef - youssef.khairallah@gmail.com
 */



/*
    THIS FUNCTION GENERATES DYNAMICALLY HTML FORM OF SELECTED PROCESS BY USER.
    GENERATION OF DYNAMIC HTML IS BASED ON JSON CONFIG INFORMATION RECEIVED FROM THE SERVER.
 */
generateDynamicHTML = function(data, sharedProperties)
{
    var commandGeneratorControllerScope = angular.element('[ng-controller="PostsCtrl"]').scope();

    commandGeneratorControllerScope.init();

    commandGeneratorControllerScope.scriptConfigJson = data;

    nbrSection = commandGeneratorControllerScope.scriptConfigJson[0].arguments.length;

    commandGeneratorControllerScope.pythonCommand += commandGeneratorControllerScope.scriptConfigJson[0].commandName + " ";

    var i = 0;

    if(nbrSection > 0)
    {
        for (i; i < nbrSection; i++) {
            sectionCourante = commandGeneratorControllerScope.scriptConfigJson[0].arguments[i];

            // Afficher le tite de la section des parametres dans le document HTML
            commandGeneratorControllerScope.HtmlToInject += "<fieldset>"
            commandGeneratorControllerScope.HtmlToInject += "<legend>" + sectionCourante.argumentsSection + "</legend>";


            var k = 0;
            nbrParamParSection = sectionCourante.parameters.length;

            for (k; k < nbrParamParSection; k++) {

                parametreCourant = sectionCourante.parameters[k];

                switch (parametreCourant.HTMLRendering.HTMLType) {

                    case "file":

                        //$scope.mapStructure[parametreCourant.id] = parametreCourant.command;

                        break;


                    case "fileList":

                        var listOfFiles = sharedProperties.getSharedListFiles();
                        var lenFiles = listOfFiles.length;

                        commandGeneratorControllerScope.inputImageRequired = true;

                        commandGeneratorControllerScope.HtmlToInject += "<label class='control-label' title=" + parametreCourant.description + ">" + parametreCourant.HTMLRendering.Title + "</label>";

                        commandGeneratorControllerScope.HtmlToInject += "<select id = 'listFilesID' class='pull-right' required>";

                        var j = 0;

                        for (j; j < lenFiles; j++)
                        {
                            commandGeneratorControllerScope.HtmlToInject += "<option " +
                            "id ='" + parametreCourant.id + "_" + j + "' value ='" + listOfFiles[j].fileName + "'>" +
                            listOfFiles[j].fileName + "</option>";
                            commandGeneratorControllerScope.mapStructure[parametreCourant.id + "_" + j] = parametreCourant.command;
                        }

                        commandGeneratorControllerScope.HtmlToInject += '</select class="form-control"><br><br>';


                        break;

                    case "select":

                        lenSelect = parametreCourant.HTMLRendering.selectList.length;

                        commandGeneratorControllerScope.HtmlToInject += "<label title=" + parametreCourant.description + ">" +parametreCourant.HTMLRendering.Title + "</label>";

                        if(parametreCourant.mandatory == "true")
                        {
                            commandGeneratorControllerScope.HtmlToInject += "<select class='pull-right' required = 'required'>";
                        }
                        else
                        {
                            commandGeneratorControllerScope.HtmlToInject += "<select class='pull-right'>";
                        }


                        var j = 0;


                        //$scope.HtmlToInject += "<option value='' selected disabled>Select an option...</option>";
                        for (j; j < lenSelect; j++)
                        {
                            commandGeneratorControllerScope.HtmlToInject += "<option " +
                            "id ='" + parametreCourant.id + "_" + j + "' value ='" + parametreCourant.HTMLRendering.selectList[j].value + "'>" +
                            parametreCourant.HTMLRendering.selectList[j].value + "</option>";
                            commandGeneratorControllerScope.mapStructure[parametreCourant.id + "_" + j] = parametreCourant.command;
                        }

                        commandGeneratorControllerScope.HtmlToInject += "</select><br><br>";

                        break;

                    case "checkbox":

                        commandGeneratorControllerScope.HtmlToInject += "<div><input " +
                        "type = 'checkbox' id ='" + parametreCourant.id + "'";

                        if (parametreCourant.mandatory == "true") {
                            commandGeneratorControllerScope.HtmlToInject += "required";
                        }
                        commandGeneratorControllerScope.HtmlToInject += "<label title=" + parametreCourant.description + ">" + parametreCourant.HTMLRendering.Title + "</label>" + "</div>";
                        commandGeneratorControllerScope.mapStructure[parametreCourant.id] = parametreCourant.command;

                        break;

                    case "text":

                        commandGeneratorControllerScope.HtmlToInject += parametreCourant.HTMLRendering.Title + "<input type = 'text' id =' " + parametreCourant.id + "' ";

                        if (parametreCourant.mandatory == "true") {
                            commandGeneratorControllerScope.HtmlToInject += "required = 'required'";
                        }

                        commandGeneratorControllerScope.HtmlToInject += "><br><br>";

                        commandGeneratorControllerScope.mapStructure[parametreCourant.id] = parametreCourant.command;

                        break;

                    case "number":


                        commandGeneratorControllerScope.HtmlToInject += "<label title=" + parametreCourant.description + ">" + parametreCourant.HTMLRendering.Title + "</label>";

                        // this.HtmlToInject += $scope.HTMLSpace(lenDescripMax - parametreCourant.HTMLRendering.Title.length);
                        commandGeneratorControllerScope.HtmlToInject += "<div class = 'numberCSS'>";
                        commandGeneratorControllerScope.HtmlToInject += "<input type = 'number ' id ='" + parametreCourant.id + "' " +
                        "min =' " + parametreCourant.HTMLRendering.min + "' " +
                        "max = '" + parametreCourant.HTMLRendering.max + "' " +
                        "step = ' " + parametreCourant.HTMLRendering.step + " '";

                        if (parametreCourant.mandatory == "true") {
                            commandGeneratorControllerScope.HtmlToInject += "required = 'required'";
                        }

                        commandGeneratorControllerScope.HtmlToInject += "></div><br><br>";

                        commandGeneratorControllerScope.mapStructure[parametreCourant.id] = parametreCourant.command;

                        break;

                    default:

                }

            }

            // next section of parametres
            commandGeneratorControllerScope.HtmlToInject += "</fieldset><br>";

        }
    }

    document.getElementById("htmlInject").innerHTML = commandGeneratorControllerScope.HtmlToInject;

    commandGeneratorControllerScope.processButtonShow = true;

}

/*
    THIS FUNCTION CLEAR THE HTML OF GENERATED FORM
 */
resetHTML = function()
{
    var commandGeneratorControllerScope = angular.element('[ng-controller="PostsCtrl"]').scope();
    commandGeneratorControllerScope.HtmlToInject = "";
    document.getElementById("htmlInject").innerHTML = commandGeneratorControllerScope.HtmlToInject;
}


/*
    THIS FUNCTION VERIFY IF MANDATORY FIELDS ARE NOT FILLED BY USER
 */
verifEmptyMandatoryField = function(sharedProperties)
{

    var commandGeneratorControllerScope = angular.element('[ng-controller="PostsCtrl"]').scope();


    // verif mandatory input image first if exists
    var listOfFiles = sharedProperties.getSharedListFiles();
    var lenFiles = listOfFiles.length;

    if(commandGeneratorControllerScope.inputImageRequired && lenFiles <= 0)
    {
        alert("To run the current process, you should upload and select at least one image !");
        commandGeneratorControllerScope.init();
        commandGeneratorControllerScope.currentScript = "";
        return true;
    }




    len = Object.keys(commandGeneratorControllerScope.mapStructure).length;
    keyObj = Object.keys(commandGeneratorControllerScope.mapStructure);

    var cpt = 0;

    for(cpt ; cpt < len ; cpt++)
    {

        IdElement = keyObj[cpt];

        element = document.getElementById(IdElement);

        typeInput = element.type;

        if(typeInput != "checkbox")
        {
            if(element.required && element.value == "")
            {
                return true;
            }
        }
        else
        {
            if(!element.checked && element.required)
            {
                return true;
            }
        }

    }

    return false;
}

/*
    THIS FUNCTION ADD HTML SPACES IN WHICH NUMBER IS SPECIFIED WITH nbrEspace ARGUMENT
 */
HTMLSpace = function(nbrEspace)
{
    var i=0;
    var chaineEspace = "";
    for(i ; i< nbrEspace ; i++)
    {
        chaineEspace+= " ";
    }

    return chaineEspace;
}

/*
    THIS FUNCTION GET LIST OF UPLOADED FILE
 */
getListOfFile = function()
{

    var commandGeneratorControllerScope = angular.element('[ng-controller="PostsCtrl"]').scope();
    var sharedProperties = commandGeneratorControllerScope.getSharedPropertiesService();
    var listOfFiles = sharedProperties.getSharedListFiles();

    var $inputFileSelectList = $("#listFilesID");
    $inputFileSelectList.empty();

    var i = 0;
    for(i ; i < listOfFiles.length ; i++)
    {
        $('#listFilesID').append($('<option>', {
            value: listOfFiles[i].fileName,
            text:  listOfFiles[i].fileName
        }))
    }

}
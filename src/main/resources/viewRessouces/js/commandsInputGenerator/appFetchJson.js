/**
 *
 */

var app = angular.module("appFetchJson", []);

app.controller("PostsCtrl", function($scope, $http)
{

    this.currentScript = $scope.currentScript;
    $scope.processButtonShow = false;
    $scope.processButtonDisabled = true;
    $scope.mapStructure = {};
    $scope.pythonCommand = "";

    this.scriptConfigJson = $scope.scriptConfigJson;

    $scope.HtmlToInject = "";

    this.len = 0;


    $http.get('/resources/viewRessouces/js/commandsInputGenerator/data/liste_scripts.json').
        success(function(data, status, headers, config) {
            //console.log(data);
            $scope.scripts = data;
        }).
        error(function(data, status, headers, config) {
            // log error
        });


    $scope.getJsonConfig = function()
    {

        var path = this.currentScript.pathConfig;

        $http.get(path).
            success(function(data, status, headers, config)
            {

                $scope.scriptConfigJson = data;

                nbrSection = $scope.scriptConfigJson[0].arguments.length;

                $scope.pythonCommand += $scope.scriptConfigJson[0].commandName + " ";

                var i = 0;

                if(nbrSection > 0)
                {
                    for (i; i < nbrSection; i++) {
                        sectionCourante = $scope.scriptConfigJson[0].arguments[i];

                        // Afficher le tite de la section des parametres dans le document HTML
                        $scope.HtmlToInject += "<fieldset>"
                        $scope.HtmlToInject += "<legend>" + sectionCourante.argumentsSection + "</legend>";


                        var k = 0;
                        nbrParamParSection = sectionCourante.parameters.length;

                        for (k; k < nbrParamParSection; k++) {

                            parametreCourant = sectionCourante.parameters[k];

                            switch (parametreCourant.HTMLRendering.HTMLType) {

                                case "file":

                                    break;

                                case "select":

                                    lenSelect = parametreCourant.HTMLRendering.selectList.length;


                                    $scope.HtmlToInject += "<span><span>" + parametreCourant.description + "</span>" +
                                        parametreCourant.HTMLRendering.Title + "</span>" + "  ";

                                    $scope.HtmlToInject += "<select required = 'required'>";

                                    var j = 0;


                                    for (j; j < lenSelect; j++)
                                    {
                                        $scope.HtmlToInject += "<option " +
                                            "id ='" + parametreCourant.id + "'" + ">" +
                                            parametreCourant.HTMLRendering.selectList[j].value + "</option>";
                                        $scope.mapStructure[parametreCourant.id] = parametreCourant.command;
                                    }


                                    // $scope.HtmlToInject += "<option value='' selected hidden></option>";

                                    $scope.HtmlToInject += "</select><br><br>";

                                    break;

                                case "checkbox":

                                    $scope.HtmlToInject += "<input " +
                                        "type = 'checkbox' id ='" + parametreCourant.id + "'";

                                    if (parametreCourant.mandatory == "true") {
                                        $scope.HtmlToInject += "required"
                                    }
                                    $scope.HtmlToInject += ">" + " " + "<span><span>" + parametreCourant.description + "</span>" +
                                        parametreCourant.HTMLRendering.Title + "</span>" + "<br><br>";

                                    $scope.mapStructure[parametreCourant.id] = parametreCourant.command;

                                    break;

                                case "text":

                                    $scope.HtmlToInject += "<span><span>" + parametreCourant.description + "</span>" +
                                        parametreCourant.HTMLRendering.Title + "</span>" +
                                        "  <input type = 'text' id =' " + parametreCourant.id + "' ";

                                    if (parametreCourant.mandatory == "true") {
                                        $scope.HtmlToInject += "required = 'required'";
                                    }

                                    $scope.HtmlToInject += "><br><br>";

                                    $scope.mapStructure[parametreCourant.id] = parametreCourant.command;

                                    break;

                                case "number":


                                    $scope.HtmlToInject += "<span><span>" + parametreCourant.description + "</span>" +
                                        parametreCourant.HTMLRendering.Title + "</span>" + " ";

                                    // this.HtmlToInject += $scope.HTMLSpace(lenDescripMax - parametreCourant.HTMLRendering.Title.length);
                                    $scope.HtmlToInject += "<div class = 'numberCSS'>";
                                    $scope.HtmlToInject += "<input type = 'number ' id ='" + parametreCourant.id + "' " +
                                        "min =' " + parametreCourant.HTMLRendering.min + "' " +
                                        "max = '" + parametreCourant.HTMLRendering.max + "' " +
                                        "step = ' " + parametreCourant.HTMLRendering.step + " '" +
                                        "value = '" + parametreCourant.defaultValue + "' ";

                                    if (parametreCourant.mandatory == "true") {
                                        $scope.HtmlToInject += "required = 'required'";
                                    }

                                    $scope.HtmlToInject += "></div><br><br>";

                                    $scope.mapStructure[parametreCourant.id] = parametreCourant.command;

                                    break;

                                default:

                            }

                        }

                        // next section of parametres
                        $scope.HtmlToInject += "</fieldset><br>";

                    }
                }

                document.getElementById("htmlInject").innerHTML = $scope.HtmlToInject;

                $scope.processButtonShow = true;


            }).
            error(function(data, status, headers, config) {
                // log error
            });

    }


    $scope.resetHtml = function()
    {
        $scope.HtmlToInject = "";
        document.getElementById("htmlInject").innerHTML = $scope.HtmlToInject;
    }


    $scope.runScript = function()
    {

        if($scope.verifEmptyMandatoryField())
        {
            return;
        }

        len = Object.keys($scope.mapStructure).length;

        keyObj = Object.keys($scope.mapStructure);

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
                        $scope.pythonCommand += $scope.mapStructure[IdElement] + " " + document.getElementById(IdElement).value + " ";
                    }
                }
                else
                {
                    if(element.checked)
                    {
                        $scope.pythonCommand += $scope.mapStructure[IdElement] + " ";
                    }
                }
            }
        }

        console.log($scope.pythonCommand);


        $scope.sendCommandToServer($scope.pythonCommand);

        $scope.init();


    }

    $scope.sendCommandToServer = function(command)
    {
        var fd = new FormData();

        var commandS = command.toString();

        fd.append('command', commandS);

        var responsePromise = $http.post("/command", fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        });

        responsePromise.success(function (dataFromServer, status, headers, config)
        {
            console.log("server ok");
            console.log(dataFromServer)
        });

        responsePromise.error(function (data, status, headers, config) {
            console.log("Submitting form failed!");
        });
    }


    $scope.init = function()
    {
        $scope.processButtonShow = false;
        $scope.processButtonDisabled = true;
        $scope.resetHtml();
        $scope.pythonCommand = "";
        $scope.mapStructure = {};

    }

    $scope.getProcessShow = function()
    {
        return $scope.processButtonShow;
    }


    $scope.HTMLSpace = function(nbrEspace)
    {
        var i=0;
        var chaineEspace = "";
        for(i ; i< nbrEspace ; i++)
        {
            chaineEspace+= " ";
        }

        return chaineEspace;
    }

    $scope.getProcessDisabled = function()
    {
        return  $scope.processButtonDisabled;
    }

    $scope.verifEmptyMandatoryField = function()
    {
        len = Object.keys($scope.mapStructure).length;
        keyObj = Object.keys($scope.mapStructure);

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



});


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




app.controller("uploadCtrl", function($scope, $http) {


    var loading_div = $("#loading");

    $scope.myForm = {};
    $scope.listOfFiles= [];
    $scope.selected = [];
    $scope.selectedFiles = [];
    $scope.index=0;



    $scope.updateSelection = function($event, id) {
        var checkbox = $event.target;
        if(checkbox.checked ) {

           $scope.selected.push(id);

        }

        else {
            var index = $scope.selected.indexOf(id);

           $scope.selected.splice(index,1);

        }

        $scope.processing();
    };

    $scope.processing = function()
    {
        var i =0;
        var j =0;
        $scope.selectedFiles = [];

        if($scope.selected.length == 0) {
            console.log("no file selected");
        }

        for(i;i<$scope.selected.length;i++) {
            var index = $scope.selected[i];
            $scope.selectedFiles[i] = $scope.listOfFiles[index];
        }
        for(j;j<$scope.selectedFiles.length;j++ )
            console.log($scope.selectedFiles[j]);
        console.log("*********");

        $scope.generateVolumes();

        console.log($scope.loadVolumesParameter);
        console.log("*********");

    }


    $scope.generateVolumes = function()
    {
        $scope.loadVolumesParameter = {};
        $scope.volumes = [];
        var i = 0;

        for(i ; i < $scope.selectedFiles.length ; i++)
        {
            $scope.volumes.push(
                {
                    type         : "minc",
                    header_url   : $scope.selectedFiles[i].headerPathName,
                    raw_data_url : $scope.selectedFiles[i].rawPathName,
                    template     :
                    {
                        element_id           : "volume-ui-template",
                        viewer_insert_class  : "volume-viewer-display"
                    }
                });
        }

        // parametre pour la fonction (LoadVolumes()) de Brainbrowser pour faire ses traitements d'affichage des volumes
        $scope.loadVolumesParameter =
        {
            volumes : $scope.volumes,
            overlay:
            {
                template:
                {
                    element_id           : "overlay-ui-template",
                    viewer_insert_class  : "overlay-viewer-display"
                }
            }
        };
    }


    /*
     $scope.deleteFile = function(id){

     var index = $scope.listOfFiles.indexOf(id);
     console.log("file deleted" );
     console.log("*********");
     $scope.listOfFiles[index+1].display = false;
     $scope.listOfFiles.splice(index,1);

     }
     */

    /*
    $scope.deleteFile = function(id){

        var index = $scope.listOfFiles.indexOf(id);
        console.log("file deleted" );
        console.log("*********");
        $scope.listOfFiles[index+1].display = false;
        $scope.listOfFiles.splice(index,1);

    }
    */




    $scope.myForm.submitTheForm = function()
    {


            loading_div.show();


        console.log("--> Submitting form");

        var fd = new FormData();

        var file = $scope.myFile;
        // console.log('file is ' + JSON.stringify(file));
        fd.append('file', file);

        /*
         var token = {
         val: Number(new Date())
         };
         */

        var token = (Number(new Date())).toString();

        //console.log('token is ' + JSON.stringify(token));

        //fd.append('token', angular.toJson(token));

        fd.append('token', token);

        var responsePromise = $http.post("/upload", fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        });

        responsePromise.success(function (dataFromServer, status, headers, config)
        {

             console.log("server ok");
/*
             viewer.clearVolumes();
             viewer.loadVolumes({
             volumes: [
             {
             type: "minc",
             header_url: dataFromServer.responsePathHeader,
             raw_data_url: dataFromServer.responsePathRaw,
             template: {
             element_id: "volume-ui-template",
             viewer_insert_class: "volume-viewer-display"
             }
             }
             ],
             overlay: {
             template: {
             element_id: "overlay-ui-template",
             viewer_insert_class: "overlay-viewer-display"
             }
             }
             });
*/

            var fileName = dataFromServer.responseFileName;
            var headerPathName = dataFromServer.responsePathHeader;
            var rawPathName = dataFromServer.responsePathRaw;
            var consoleOut = dataFromServer.outputConsole;
            var id = $scope.index;

            $scope.listOfFiles.push({"id":id,"fileName": fileName, "headerPathName" : headerPathName, "rawPathName" : rawPathName,"display": true});
            $scope.index = $scope.index + 1;
            loading_div.hide();
        });

        responsePromise.error(function (data, status, headers, config) {
            console.log("Submitting form failed!");
            loading_div.hide();
        });


           //



    }

});






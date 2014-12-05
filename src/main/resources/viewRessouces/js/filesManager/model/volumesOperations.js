generateStartupImageVolume = function(serviceSharedProperties)
{


    var volumes = serviceSharedProperties.getSharedVolumes();
    var loadVolumesParameter = serviceSharedProperties.getSharedLoadVolumesParameter();

        volumes.push(
            {
                type         : "minc",
                header_url   : STARTUP_APP_IMAGE_HEADER_PATH,
                raw_data_url : STARTUP_APP_IMAGE_RAW_PATH,
                template     :
                {
                    element_id           : "volume-ui-template",
                    viewer_insert_class  : "volume-viewer-display"
                }
            });

    loadVolumesParameter =
    {
        volumes : volumes,
        overlay: false
    };

    serviceSharedProperties.setSharedVolumes(volumes);
    serviceSharedProperties.setSharedLoadVolumesParameter(loadVolumesParameter);

}


generateVolumes = function(serviceSharedProperties)
{
    var loadVolumesParameter = {};
    var volumes = [];

    serviceSharedProperties.setSharedLoadVolumesParameter(loadVolumesParameter);
    serviceSharedProperties.setSharedVolumes(volumes);

    var i = 0;
    var overlay = false;

    var selectedFiles = serviceSharedProperties.getSharedSelectedFiles();

    if(selectedFiles.length == 0)
    {
        generateStartupImageVolume(serviceSharedProperties);
    }
    else if(selectedFiles.length > 0)
    {
        for(i ; i < selectedFiles.length ; i++)
        {
                volumes.push(
                {
                    type         : "minc",
                    header_url   : selectedFiles[i].headerPathName,
                    raw_data_url : selectedFiles[i].rawPathName,
                    template     :
                    {
                        element_id           : "volume-ui-template",
                        viewer_insert_class  : "volume-viewer-display"
                    }
                });
        }

        serviceSharedProperties.setSharedVolumes(volumes);

        if(selectedFiles.length > 1)
        {
            overlay = true;
        }

        // parametre pour la fonction (LoadVolumes()) de Brainbrowser pour faire ses traitements d'affichage des volumes
        loadVolumesParameter =
        {
            volumes : volumes,
            overlay: overlay
        };

        serviceSharedProperties.setSharedLoadVolumesParameter(loadVolumesParameter);
    }

}

processing = function(serviceSharedProperties)
{


    console.log("processing");

    var i =0;
    var j =0;
    var k=0;
    var selectedFiles = [];
    var index=0;
    serviceSharedProperties.setSharedSelectedFiles(selectedFiles);

    var selected = serviceSharedProperties.getSharedSelected();
    var listOfFile = serviceSharedProperties.getSharedListFiles();

    if(selected.length == 0)
    {
        console.log("no file selected");
    }

    for(i ; i < selected.length ; i++)
    {
        var id = selected[i];



        for(k ; k < listOfFile.length;k++)
        {
            if(listOfFile[k].id == id)
            {
                index=k;
            }
        }

        serviceSharedProperties.setSharedListFiles(listOfFile);

        selectedFiles[i] = listOfFile[index];
        k=0;
    }


    for( j ; j < selectedFiles.length ; j++ )
    {
        console.log(selectedFiles[j]);
        console.log("*********");
    }

    serviceSharedProperties.setSharedSelectedFiles(selectedFiles);

    viewer.clearVolumes();
    generateVolumes(serviceSharedProperties);

    var loadVolumesParameter = serviceSharedProperties.getSharedLoadVolumesParameter();

    viewer.loadVolumes(loadVolumesParameter);

}

displayVolume = function(serviceSharedProperties)
{
    var indexOfFiles = serviceSharedProperties.getSharedIndex();
    var selected = serviceSharedProperties.getSharedSelected();
    selected.push(indexOfFiles-1);
    serviceSharedProperties.setSharedSelected(selected);
    processing(serviceSharedProperties);
}

updateSelection = function(event, file, serviceSharedProperties)
{
    var checkbox = event.target;

    var selected = serviceSharedProperties.getSharedSelected();
    var listOfFiles = serviceSharedProperties.getSharedListFiles();

    if(checkbox.checked )
    {

        selected.push(file.id);
        serviceSharedProperties.setSharedSelected(selected);


        var index = listOfFiles.indexOf(file);
        listOfFiles[index].checked=true;
        serviceSharedProperties.setSharedListFiles(listOfFiles);
    }
    else
    {
        var index = selected.indexOf(file.id);

        selected.splice(index,1);
        serviceSharedProperties.setSharedSelected(selected);

        listOfFiles = serviceSharedProperties.getSharedListFiles();
        var index = listOfFiles.indexOf(file);
        listOfFiles[index].checked=false;
        serviceSharedProperties.setSharedListFiles(listOfFiles);

    }

    processing(serviceSharedProperties);

};
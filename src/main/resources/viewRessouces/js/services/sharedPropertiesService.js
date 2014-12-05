app.service('sharedProperties', function () {

    var sharedSelectedFiles = [];
    var sharedListFiles = [];
    var sharedIndex = 0;
    var sharedSelected = [];
    var sharedLoadVolumesParameter = {};
    var sharedVolumes = [];

    return {
        getSharedSelectedFiles: function () {
            return sharedSelectedFiles;
        },
        setSharedSelectedFiles: function(val) {
            sharedSelectedFiles = val;
        },
        getSharedListFiles: function () {
            return sharedListFiles;
        },
        setSharedListFiles: function(val) {
            sharedListFiles = val;
        },
        getSharedIndex: function () {
            return sharedIndex;
        },
        setSharedIndex: function(val) {
            sharedIndex = val;
        },
        getSharedSelected: function () {
            return sharedSelected;
        },
        setSharedSelected: function(val) {
            sharedSelected = val;
        },
        getSharedLoadVolumesParameter: function () {
            return sharedLoadVolumesParameter;
        },
        setSharedLoadVolumesParameter: function (val) {
            sharedLoadVolumesParameter= val;
        },
        getSharedVolumes: function() {
            return sharedVolumes;
        },
        setSharedVolumes: function(val) {
            sharedVolumes= val;
        }

    };
});

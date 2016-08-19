define(["app"], function (app) {
    var injectParams = ['$scope', '$routeParams', '$location', 'repoService', 'utilService'];
    var regiontelesegImportCtrl = function ($scope, $routeParams, $location, repo, util) {
        var regiontelesegConf = {
            url: 'basicdata/regionteleseg', //基本URL，必填
            showName: '区域号段' //提示的名称，一般为模块名，必填
        };

        $scope.fileType = "excel";
        $scope.delimiter=",";
        $scope.ajaxFileUpload = function () {
            console.log($scope.fileUpload);
        }
        $scope.onFileSelect = function ($files) {
            console.log($files);
        }
    };

    regiontelesegImportCtrl.$inject = injectParams;
    app.register.controller('regiontelesegImportCtrl', regiontelesegImportCtrl);
});

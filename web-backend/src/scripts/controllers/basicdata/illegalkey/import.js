define(["app"], function (app) {
    var injectParams = ['$scope', '$routeParams', '$location', 'repoService', 'utilService'];
    var illegalkeyImportCtrl = function ($scope, $routeParams, $location, repo, util) {
        var illegalkeyConf = {
            url: 'basicdata/illegalkey', //基本URL，必填
            showName: '非法关键字' //提示的名称，一般为模块名，必填
        };
        
        $scope.ajaxFileUpload = function () {
            alert("上传文件");
        }
        
    };

    illegalkeyImportCtrl.$inject = injectParams;
    app.register.controller('illegalkeyImportCtrl', illegalkeyImportCtrl);
});

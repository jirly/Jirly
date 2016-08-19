define(["app"], function (app) {
    var injectParams = ['$scope', '$routeParams', '$location', 'repoService', 'utilService'];
    var blacklistImportCtrl = function ($scope, $routeParams, $location, repo, util) {
        var blacklistConf = {
            url: 'basicdata/blacklist', //基本URL，必填
            showName: '区域号段' //提示的名称，一般为模块名，必填
        };
        
        $scope.ajaxFileUpload = function () {
            alert("上传文件");
        }
    };

    blacklistImportCtrl.$inject = injectParams;
    app.register.controller('blacklistImportCtrl', blacklistImportCtrl);
});

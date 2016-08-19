define(["app"], function(app) {
    var injectParams = ['$scope', '$location', 'repoService', 'utilService'];
    var illegalkeyAddCtrl = function($scope, $location, repo, util) {
        var illegalkeyConf = {
            url: 'basicdata/illegalkey', //基本URL，必填
            showName: '运营商号段' //提示的名称，一般为模块名，必填
        };
        $scope.illegalkey = {};

        $scope.submitForm = function() {
            repo.add(illegalkeyConf, $scope.illegalkey).then(function(data) {
                $location.path(illegalkeyConf.url);
            });
        };
    };

    illegalkeyAddCtrl.$inject = injectParams;
    app.register.controller('illegalkeyAddCtrl', illegalkeyAddCtrl);
});

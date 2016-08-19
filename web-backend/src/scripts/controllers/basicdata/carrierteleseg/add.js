define(["app"], function(app) {
    var injectParams = ['$scope', '$location', 'repoService', 'utilService'];
    var carriertelesegAddCtrl = function($scope, $location, repo, util) {
        var carriertelesegConf = {
            url: 'basicdata/carrierteleseg', //基本URL，必填
            showName: '运营商号段' //提示的名称，一般为模块名，必填
        };
        repo.getByUrl("common/carriers").then(function(data) {
            $scope.carriers = data.data;
        });

        $scope.submitForm = function() {
            repo.add(carriertelesegConf, $scope.carrierteleseg).then(function(data) {
                $location.path(carriertelesegConf.url);
            });
        };
    };

    carriertelesegAddCtrl.$inject = injectParams;
    app.register.controller('carriertelesegAddCtrl', carriertelesegAddCtrl);
});

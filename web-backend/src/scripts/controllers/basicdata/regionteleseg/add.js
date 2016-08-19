define(["app"], function(app) {
    var injectParams = ['$scope', '$location', 'repoService', 'utilService'];
    var regiontelesegAddCtrl = function($scope, $location, repo, util) {
        var regiontelesegConf = {
            url: 'basicdata/regionteleseg', //基本URL，必填
            showName: '运营商号段' //提示的名称，一般为模块名，必填
        };
        $scope.regionteleseg = {};
        repo.getByUrl("common/carriers").then(function(data) {
            $scope.carriers = data.data;
        });
        repo.getByUrl("common/provinces").then(function(data) {
            $scope.provinces = data.data;
        });
        $scope.submitForm = function() {
            $scope.regionteleseg.carrierId = $scope.regionteleseg.carrier.id;
            $scope.regionteleseg.regionId = $scope.regionteleseg.province.id;
            delete $scope.regionteleseg.carrier;
            delete $scope.regionteleseg.province;
            console.log($scope.regionteleseg);
            repo.add(regiontelesegConf, $scope.regionteleseg).then(function(data) {
                $location.path(regiontelesegConf.url);
            });
        };
    };

    regiontelesegAddCtrl.$inject = injectParams;
    app.register.controller('regiontelesegAddCtrl', regiontelesegAddCtrl);
});

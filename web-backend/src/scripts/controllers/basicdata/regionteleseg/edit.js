define(["app"], function (app) {
    var injectParams = ['$scope', '$routeParams', '$location', 'repoService', 'utilService'];
    var regiontelesegEditCtrl = function ($scope, $routeParams, $location, repo, util) {
        var regiontelesegConf = {
            url: 'basicdata/regionteleseg', //基本URL，必填
            showName: '区域号段' //提示的名称，一般为模块名，必填
        };

        var id = $routeParams.id;
        $scope.regionteleseg = {};
        repo.get(regiontelesegConf, id).then(function (data) {
            $scope.regionteleseg = data.data;
            angular.forEach($scope.provinces, function (o) {
                if (o.id == $scope.regionteleseg.regionId) {
                    $scope.regionteleseg.province = o;
                }
            });
        });

        $scope.submitForm = function () {
            $scope.regionteleseg.regionId = $scope.regionteleseg.province.id;
            delete $scope.regionteleseg.province;
            console.log($scope.regionteleseg);
            repo.update(regiontelesegConf, $scope.regionteleseg).then(function (data) {
                $location.path(regiontelesegConf.url);
            });
        };
    };

    regiontelesegEditCtrl.$inject = injectParams;
    app.register.controller('regiontelesegEditCtrl', regiontelesegEditCtrl);
});

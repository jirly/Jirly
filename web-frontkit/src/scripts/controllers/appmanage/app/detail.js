define(["app"], function(app) {
    var injectParams = ['$scope', '$routeParams', '$location', 'repoService', 'utilService'];
    var appDetailCtrl = function($scope, $routeParams, $location, repo, util) {
        var appConf = {
            url: 'appmanage/app', //基本URL，必填
            showName: '应用' //提示的名称，一般为模块名，必填
        };

        $scope.appId = $routeParams.appId;
        $scope.app = { enablers: [] };
        repo.get(appConf, $scope.appId).then(function(data) {
            $scope.app = data.data;
            $scope.app.trustIpsStr = $scope.app.trustIps.join(",");
            if ($scope.app.enablers == null) {
                $scope.app.enablers = [];
            }
        });

        repo.getByUrl("common/enablers").then(function(data) {
            $scope.enablers = data.data;
        });

        $scope.isSelected = function(typeIdx) {
            return $scope.app.enablers.indexOf(typeIdx) >= 0;
        };

        $scope.editApp = function(appId) { //编辑应用
            $location.path('/appmanage/app/edit/' + appId);
            //$scope.$apply();
        };
    };

    appDetailCtrl.$inject = injectParams;
    app.register.controller('appDetailCtrl', appDetailCtrl);
});

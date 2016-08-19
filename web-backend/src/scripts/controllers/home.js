define(["app"], function (app) {
    app.controller("homeCtrl", function ($scope, breadcrumbs,repoService) { // 主模块
        $scope.breadcrumbs = breadcrumbs;
        $scope.testText = "~Hey, Home!!!";

        repoService.getByUrl("common/provinces").then(function (data) {
            $scope.provinces = data.data;
        });
    });
});

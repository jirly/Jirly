define(["app"], function(app) {
    app.controller("indexCtrl", function($scope, breadcrumbs) { // 主模块
        $scope.breadcrumbs = breadcrumbs;
    });
});

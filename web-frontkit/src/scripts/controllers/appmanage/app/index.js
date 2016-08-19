define(["app"], function(app) {
    var injectParams = ['$scope', '$location', 'NgTableParams', 'repoService', 'utilService'];
    var appIndexCtrl = function($scope, $location, NgTableParams, repo, util) {
        var appConf = {
            url: 'appmanage/app', //基本URL，必填
            showName: '应用' //提示的名称，一般为模块名，必填
        };

        $scope.appParams = {
            name: ""
        };

        $scope.appsTable = new NgTableParams({
            page: 1,
            count: 10
        }, {
            total: 0,
            getData: function(params) {
                return repo.query(appConf, util.buildQueryParam(params, $scope.appParams)).then(function(data) {
                    params.total(data.total);
                    return data.data;
                });
            }
        });

        $scope.selectAll = { checked: false };
        $scope.$watch('selectAll.checked', function(value) {
            util.selectAll($scope.appsTable, value);
        });
        $scope.checkOne = function(app) {
            util.checkOne($scope.selectAll, $scope.appsTable, app);
        };

        $scope.searchApps = function() {
            $scope.appsTable.reload();
        };

        $scope.addApp = function() { //新建应用
            $location.path('/appmanage/app/add');
            //$scope.$apply();
        };

        $scope.editApp = function(appId) { //编辑应用
            $location.path('/appmanage/app/edit/' + appId);
            //$scope.$apply();
        };

        $scope.deleteApp = function(app) { //删除应用
            repo.removeOne(appConf, app.id, app.name).then(function(data) {
                $scope.appsTable.reload();
            });
        };

        $scope.updateAppState = function(app, state) { //更改状态
            var tips = (state == 1 ? "上线" : (state == 2 ? "暂停" : "恢复"))
            var content = "确定要" + tips + "应用[" + app.name + "]？";
            util.confirm(content, function() {
                repo.updateByPath(appConf, "/updateState", { "id": app.id, "stateIdx": state == 3 ? 1 : state }, tips).then(function(data) {
                    $scope.appsTable.reload();
                });
            });
        };
    };

    appIndexCtrl.$inject = injectParams;
    app.register.controller('appIndexCtrl', appIndexCtrl);
});

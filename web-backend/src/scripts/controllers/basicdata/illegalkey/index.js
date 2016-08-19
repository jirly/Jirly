define(["app"], function(app) {
    var injectParams = ['$scope', '$location', 'NgTableParams', 'repoService', 'utilService'];
    var illegalkeyIndexCtrl = function($scope, $location, NgTableParams, repo, util) {
        var illegalkeyConf = {
            url: 'basicdata/illegalkey', //基本URL，必填
            showName: '非法关键字' //提示的名称，一般为模块名，必填
        };

        $scope.illegalkeyParams = {
            name: ""
        };
        

        $scope.illegalkeysTable = new NgTableParams({
            page: 1,
            count: 10
        }, {
            total: 0,
            getData: function(params) {
                console.log("load");
                return repo.query(illegalkeyConf, util.buildQueryParam(params, $scope.illegalkeyParams)).then(function(data) {
                    params.total(data.total);
                    return data.data;
                });
            }
        });
        $scope.selectAll = { checked: false };
        $scope.$watch('selectAll.checked', function(value) {
            util.selectAll($scope.illegalkeysTable, value);
        });
        $scope.checkOne = function(illegalkey) {
            util.checkOne($scope.selectAll, $scope.illegalkeysTable, illegalkeys);
        };
        $scope.searchIllegalkeys= function() {
            $scope.illegalkeysTable.reload();
        };

        $scope.deleteIllegalkey = function(illegalkey) { //删除应用
            repo.removeOne(illegalkeyConf, illegalkey.id, illegalkey.name).then(function(data) {
                $scope.illegalkeysTable.reload();
            });
        };

        $scope.addIllegalkey = function() { //新建运营商号段
            $location.path('/basicdata/illegalkey/add');
        };


        $scope.importIllegalkeys = function(){
            $location.path('/basicdata/illegalkey/import');
        }
    };

    illegalkeyIndexCtrl.$inject = injectParams;
    app.register.controller('illegalkeyIndexCtrl', illegalkeyIndexCtrl);
});

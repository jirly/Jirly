define(["app"], function(app) {
    var injectParams = ['$scope', '$location', 'NgTableParams', 'repoService', 'utilService'];
    var blacklistIndexCtrl = function($scope, $location, NgTableParams, repo, util) {
        var blacklistConf = {
            url: 'basicdata/blacklist', //基本URL，必填
            showName: '运营商号段' //提示的名称，一般为模块名，必填
        };

        $scope.blacklistParams = {

        };

        $scope.blacklistsTable = new NgTableParams({
            page: 1,
            count: 10
        }, {
            total: 0,
            getData: function(params) {
                return repo.query(blacklistConf, util.buildQueryParam(params, $scope.blacklistParams)).then(function(data) {
                    params.total(data.total);
                    return data.data;
                });
            }
        });
        $scope.selectAll = { checked: false };
        $scope.$watch('selectAll.checked', function(value) {
            util.selectAll($scope.blacklistsTable, value);
        });
        $scope.checkOne = function(blacklists) {
            util.checkOne($scope.selectAll, $scope.blacklistsTable, blacklists);
        };
        $scope.searchBlacklistss = function() {
            $scope.blacklistsTable.reload();
        };

        $scope.deleteBlacklist = function(blacklist) { //删除应用
            repo.removeOne(blacklistConf, blacklist.id, blacklist.name).then(function(data) {
                $scope.blacklistsTable.reload();
            });
        };

        $scope.addBlacklist = function() { //新建运营商号段
            $location.path('/basicdata/blacklist/add');
        };

        $scope.importBlacklists = function(){
            $location.path('/basicdata/blacklist/import');
        }
    };

    blacklistIndexCtrl.$inject = injectParams;
    app.register.controller('blacklistIndexCtrl', blacklistIndexCtrl);
});

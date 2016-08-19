define(["app"], function(app) {
    var injectParams = ['$scope', '$location', 'NgTableParams', 'repoService', 'utilService'];
    var carriertelesegIndexCtrl = function($scope, $location, NgTableParams, repo, util) {
        var carriertelesegConf = {
            url: 'basicdata/carrierteleseg', //基本URL，必填
            showName: '运营商号段' //提示的名称，一般为模块名，必填
        };

        $scope.carriertelesegParams = {
            name: ""
        };

        $scope.carriertelesegsTable = new NgTableParams({
            page: 1,
            count: 10,
            sorting: { name: "asc" }
        }, {
            total: 0,
            getData: function(params) {
                return repo.query(carriertelesegConf, util.buildQueryParam(params, $scope.carriertelesegParams)).then(function(data) {
                    params.total(data.total);
                    return data.data;
                });
            }
        });
        $scope.selectAll = { checked: false };
        $scope.$watch('selectAll.checked', function(value) {
            util.selectAll($scope.carriertelesegsTable, value);
        });
        $scope.checkOne = function(carriertelesegs) {
            util.checkOne($scope.selectAll, $scope.carriertelesegsTable, carriertelesegs);
        };
        $scope.searchCarriertelesegs = function() {
            $scope.carriertelesegsTable.reload();
        };

        $scope.deleteCarriertelesegs = function() {
            repo.remove(carriertelesegConf, $scope.carriertelesegsTable.data, "id").then(function(data) {
                $scope.carriertelesegsTable.reload();
            });
        };

        repo.getByUrl("common/carriers").then(function(data) {
            $scope.carriers = data.data;
        });

        $scope.addCarrierteleseg = function() { //新建运营商号段
            $location.path('/basicdata/carrierteleseg/add');
        };
    };

    carriertelesegIndexCtrl.$inject = injectParams;
    app.register.controller('carriertelesegIndexCtrl', carriertelesegIndexCtrl);
});

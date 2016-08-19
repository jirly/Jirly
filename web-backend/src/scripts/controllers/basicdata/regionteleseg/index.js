define(["app"], function(app) {
    var injectParams = ['$scope', '$location', 'NgTableParams', 'repoService', 'utilService'];
    var regiontelesegIndexCtrl = function($scope, $location, NgTableParams, repo, util) {
        var regiontelesegConf = {
            url: 'basicdata/regionteleseg', //基本URL，必填
            showName: '区域号段' //提示的名称，一般为模块名，必填
        };

        $scope.regiontelesegParams = {
            name: ""
        };

        repo.getByUrl("common/carriers").then(function(data) {
            $scope.carriers = data.data;
        });

        repo.getByUrl("common/provinces").then(function(data) {
            $scope.provinces = data.data;
        });

        $scope.regiontelesegsTable = new NgTableParams({
            page: 1,
            count: 10
        }, {
            total: 0,
            getData: function(params) {
                return repo.query(regiontelesegConf, util.buildQueryParam(params, $scope.regiontelesegParams)).then(function(data) {
                    params.total(data.total);
                    return data.data;
                });
            }
        });
        $scope.selectAll = { checked: false };
        $scope.$watch('selectAll.checked', function(value) {
            util.selectAll($scope.regiontelesegsTable, value);
        });
        $scope.checkOne = function(regiontelesegs) {
            util.checkOne($scope.selectAll, $scope.regiontelesegsTable, regiontelesegs);
        };
        $scope.searchRegiontelesegs = function() {
            $scope.regiontelesegsTable.reload();
        };

        $scope.deleteRegiontelesegs = function() { //删除多个区域号段
            repo.remove(regiontelesegConf, $scope.regiontelesegsTable.data, "id").then(function(data) {
                $scope.regiontelesegsTable.reload();
            });
        };
          $scope.deleteRegionteleseg = function(regionteleseg) { //删除单个区域号段
            repo.removeOne(regiontelesegConf, regionteleseg.id, regionteleseg.name).then(function(data) {
                $scope.regiontelesegsTable.reload();
            });
        };

        $scope.addRegionteleseg = function() { //新建运营商号段
            $location.path('/basicdata/regionteleseg/add');
        };

        $scope.editRegionteleseg = function (id) {
            $location.path('/basicdata/regionteleseg/edit/' + id);
        }

        $scope.importRegiontelesegs = function(){
            console.log("import");
            $location.path('/basicdata/regionteleseg/import');
        }
    };

    regiontelesegIndexCtrl.$inject = injectParams;
    app.register.controller('regiontelesegIndexCtrl', regiontelesegIndexCtrl);
});

define(["app"], function(app) {
    var injectParams = ['$scope','$location', 'NgTableParams', 'repoService', 'utilService'];
    var infoIndexCtrl = function($scope, $location, NgTableParams, repo, util) {
       $scope.infoIndex = "通道基本信息——/channelmgmt/info/index";
        var infoConf = {
            url: 'channelmgmt/info', //基本URL，必填
            showName: '通道基本信息' //提示的名称，一般为模块名，必填
        };

        /** 检索栏参数*/
        $scope.channelInfoParams = {
            name: "",
            channelNum:"",
            carrer:""
        };

        /**
         * 表格字段
         * 通道名、通道号、所属运营商、所属地区、可发送信息类型、是否支持状态报告、通道类型、状态、操作（编辑、删除）
         * */
        $scope.infoCols = [
            {title: "通道名", show: true},
            {title: "通道号", show: true},
            {title: "所属运营商", show: true},
            {title: "所属地区", show: true},
            {title: "可发送信息类型", show: true},
            {title: "是否支持状态报告", show: true},
            {title: "通道类型", show: true},
            {title: "状态", show: true},
            {title: "操作", show: true}
        ];

        $scope.channelInfoTable = new NgTableParams({
            page: 1,
            count: 10
        }, {
            total: 0,
            getData: function(params) {
                return repo.query(infoConf, util.buildQueryParam(params, $scope.channelInfoParams)).then(function(data) {
                    params.total(data.total);
                    return data.data;
                });
            }
        });

        //新建
        $scope.addChannelInfo = function() { //新建通道基本信息
            $location.path('/channelmgmt/info/add');
            //$scope.$apply();
        };

        //查询
        $scope.searchChannelInfo = function() {
            $scope.channelInfoTable.reload();
        };

        //重置查询
        $scope.resetSearch = function() {
            $scope.channelInfoParams = {};
            $scope.searchChannelInfo();
        };

        //删除
        $scope.deleteChannelInfo = function(app) { //删除通道基本信息
            repo.removeOne(infoConf, app.id, app.name).then(function(data) {
                $scope.channelInfoTable.reload();
            });
        };

        //编辑
        $scope.editChannelInfo = function(info) {
            $location.path("/channelmgmt/info/edit/"+info.id).replace();
        };
        
    };

    infoIndexCtrl.$inject = injectParams;
    app.register.controller('infoIndexCtrl', infoIndexCtrl);
});

define(["app"], function (app) {
    var injectParams = ['$scope', 'NgTableParams', 'repoService', 'utilService', '$location'];
    var smsPhraseIndexCtrl = function ($scope, NgTableParams, repo, util, $location) {
        var phraseConf = {
            url: 'phrase/sms', //基本URL，必填
            showName: '模板' //提示的名称，一般为模块名，必填
        };

        $scope.params = {};
        $scope.identifyCompleteUrl = xpath.service('phrase/sms/identifycomplete/');
        /**
         * 应用名称、模板id、模板类型、模板名称（提供链接查看）、申请时间、审核状态（已通过、待审核、不通过）、操作（编辑、删除）
         * */
        $scope.cols = [
            {title: "应用名称", show: true},
            {title: "UUID", show: true},
            {title: "模板类型", show: true},
            {title: "模板名称", show: true},
            {title: "申请时间", show: true},
            {title: "审核状态", show: true},
            {title: "操作", show: true}
        ];


        $scope.phraseTable = new NgTableParams({page: 1, count: 10}, {
            total: 0,
            getData: function (params) {
                return repo.query(phraseConf, util.buildQueryParam(params, $scope.params)).then(function (data) {
                    params.total(data.total);
                    return data.data;
                });
            }
        });

        //新增
        $scope.add = function() {
            $location.path("/appmanage/smsphrase/edit/add").replace();
        }

        //查询
        $scope.search = function() {
            $scope.params.identify = $("#identify_value").val();
            $scope.phraseTable.reload();
        };

        //重置查询
        $scope.resetSearch = function() {
            $("#identify_value").val("")
            $scope.params = {};
            $scope.search();
        };

        //删除模块
        $scope.delete = function(phrase) {
            repo.removeOne(phraseConf, phrase.id, phrase.title).then(function () {
                $scope.phraseTable.reload();
            });
        };

        //编辑模块
        $scope.edit = function(phrase) {
            $location.path("/appmanage/smsphrase/edit/"+phrase.id).replace();
        };

    };

    smsPhraseIndexCtrl.$inject = injectParams;
    app.register.controller('smsPhraseIndexCtrl', smsPhraseIndexCtrl);
});

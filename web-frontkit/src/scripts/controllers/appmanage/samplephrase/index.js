define(["app"], function (app) {
    var injectParams = ['$scope', 'repoService', 'utilService'];
    var phraseSampleIndexCtrl = function ($scope, repo, util) {
        var phraseConf = {
            url: 'phrase/sample', //基本URL，必填
            showName: '参考模板' //提示的名称，一般为模块名，必填
        };

        $scope.cols = [
            {title: "模板类型", show: true},
            {title: "模板内容", show: true},
            {title: "操作", show: true}
        ];

        $scope.table = {data: [],
                        isLoading: true
                       };

        var init= function() {
            repo.getByUrl(phraseConf.url +"/list").then(function(data) {
                $scope.table.data = data.data;
                $scope.table.isLoading = false;
            });
        }
        init();

    };

    phraseSampleIndexCtrl.$inject = injectParams;
    app.register.controller('phraseSampleIndexCtrl', phraseSampleIndexCtrl);
});

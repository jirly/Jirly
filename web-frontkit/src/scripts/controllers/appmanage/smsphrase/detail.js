define(["app"], function(app) {
    var injectParams = ['$scope', '$routeParams', 'repoService', 'utilService'];
    var smsPhraseDetailCtrl = function($scope, $routeParams, repo, util) {
        var phraseConf = {
            url: 'phrase/sms/detail', //基本URL，必填
            showName: '短信模板详情', //提示的名称，一般为模块名，必填
            downloadUrl: 'common/download?path='
        };

        $scope.phraseId = $routeParams.phraseId;
        repo.getByPath(phraseConf, "/"+$scope.phraseId).then(function(data) {
            $scope.phrase = data.data;
        });

        $scope.downloadFile = function (url) {
            repo.downloadFile(phraseConf.downloadUrl + url);
        }
        
    };

    smsPhraseDetailCtrl.$inject = injectParams;
    app.register.controller('smsPhraseDetailCtrl', smsPhraseDetailCtrl);
});

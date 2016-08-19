define(["app"], function(app) {
    var injectParams = ['$scope', '$routeParams', 'repoService', 'utilService'];
    var voicePhraseDetailCtrl = function($scope, $routeParams, repo, util) {
        var phraseConf = {
            url: 'phrase/voice/detail', //基本URL，必填
            showName: '语音模板详情', //提示的名称，一般为模块名，必填
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

    voicePhraseDetailCtrl.$inject = injectParams;
    app.register.controller('voicePhraseDetailCtrl', voicePhraseDetailCtrl);
});

define(["app"], function(app) {
    var injectParams = ['$scope', '$routeParams', 'repoService', 'utilService'];
    var voicePhraseAddCtrl = function($scope, $routeParams, repo, util) {
        var phraseConf = {
            url: 'phrase/voice', //基本URL，必填
            showName: '语音模板详情', //提示的名称，一般为模块名，必填
            uploadUrl: 'common/upload',
            downloadUrl: 'common/download?path='
        };

        $scope.apps = [];

        $scope.progress = {
            appLogo : 0,
            appVerifyPage : 0
        };

        $scope.phrase = {
            type : 'VOICE_NOTIFICATION',
            appType : 'ONLINE',
        };

        $scope.uploadData = {
            appLogo : null,
            appVerifyPage : null
        };

        $scope.appCallbackUrl = null;

        $scope.phraseId = $routeParams.phraseId;

        //检验上传文件
        var checkUploadFile = function(fileObj) {
            var reg1 = ['application/pdf','application/msword','image/png','image/jpeg'];
            var reg2 = ['application/zip'];
            if((reg1.indexOf(fileObj.type) != -1 && fileObj.size < 2 * 1024 * 1024) ||
                (reg2.indexOf(fileObj.type) != -1 && fileObj.size < 10 * 1024 * 1024)) {
                return true;
            }else{
                toastr.success('文件大小超过限定!');
                return false;
            }
        };


        $scope.uploadAppLogo = function () {
            if (!$scope.uploadData.appLogo) {
                return;
            }else if(!checkUploadFile($scope.uploadData.appLogo)){
                return;
            }

            repo.uploadFile (
                phraseConf.uploadUrl,
                $scope.uploadData.appLogo,
                {},
                function(evt){
                    $scope.progress.appLogo = parseInt(100.0 * evt.loaded / evt.total);
                },
                function(data){
                    if(data.result == 'SUCCESS') {
                        $scope.phrase.appLogo = data.files[0].path;
                        toastr.success('文件上传成功!');
                    }
                }
            );

        };


        $scope.uploadAppVerifyPage = function () {
            if (!$scope.uploadData.appVerifyPage) {
                return;
            }else if(!checkUploadFile($scope.uploadData.appVerifyPage)){
                return;
            }

            repo.uploadFile (
                phraseConf.uploadUrl,
                $scope.uploadData.appVerifyPage,
                {},
                function(evt){
                    $scope.progress.appVerifyPage = parseInt(100.0 * evt.loaded / evt.total);
                },
                function(data){
                    if(data.result == 'SUCCESS') {
                        $scope.phrase.appVerifyPage = data.files[0].path;
                        toastr.success('文件上传成功!');
                    }
                }
            );
        };

        //表单校验
        var validateForm = function() {
            if($scope.phrase.type == 'SMS_VERIFACATION_CODE') {
                if($scope.phrase.appType == 'ONLINE' && !$scope.phrase.appUrl){
                    toastr.warning("请填写APP/网页地址！");
                    return false;
                }else if($scope.phrase.appType == 'OFFLINE' && !$scope.phrase.appLogo) {
                    toastr.warning("请上传产品名截图！");
                    return false;
                }else if($scope.phrase.appType == 'OFFLINE' && !$scope.phrase.appVerifyPage) {
                    toastr.warning("请上传验证页截图！");
                    return false;
                }
            }
            return true;
        }

        //保存模板
        $scope.save = function() {
            if(validateForm()) {
                repo.add(phraseConf,$scope.phrase).then(function() {
                });
            }
        }

        //显隐app链接
        $scope.toggleAppUrl = function() {
            angular.forEach($scope.apps, function(app){
                if(app.id == $scope.phrase.appId){
                    $scope.appCallbackUrl = app.callbackUrl ? app.callbackUrl : null;
                }
            });
        }

        $scope.downloadFile = function (url) {
            repo.downloadFile(phraseConf.downloadUrl + url);
        }

        //初始化信息
        var init = function() {
            if($scope.phraseId != 'add'){
                repo.getByUrl("phrase/voice/detail/"+$scope.phraseId).then(function(data) {
                    $scope.phrase = data.data;
                    repo.getByUrl("common/apps").then(function(data) {
                        $scope.apps = data.data;
                        $scope.toggleAppUrl();
                    });
                });
            }else {
                repo.getByUrl("common/apps").then(function(data) {
                    $scope.apps = data.data;
                });
            }
        };

        init();

    };

    voicePhraseAddCtrl.$inject = injectParams;
    app.register.controller('voicePhraseAddCtrl', voicePhraseAddCtrl);
});

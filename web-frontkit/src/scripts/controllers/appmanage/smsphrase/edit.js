define(["app"], function(app) {
    var injectParams = ['$scope', '$routeParams', 'repoService', 'utilService','$route'];
    var smsPhraseAddCtrl = function($scope, $routeParams, repo, util, $route) {
        var phraseConf = {
            url: 'phrase/sms', //基本URL，必填
            showName: '短信模板详情', //提示的名称，一般为模块名，必填
            uploadUrl: 'common/upload',
            downloadUrl: 'common/download?path='
        };

        //签名类型:商标证、网站、app、其他
        $scope.signTypes = [{value:'BRAND', label:"商标证"},
                            {value:'SITES', label:"网站"},
                            {value:'APP', label:"app"},
                            {value:'OTHER', label:"其他"}];

        $scope.apps = [];

        $scope.signs = [];

        $scope.progress = {
            certifyFile : 0,
            appLogo : 0,
            appVerifyPage : 0
        };

        $scope.phrase = {
            type : 'SMS_NOTIFICATION',
            appType : 'ONLINE',
        };

        $scope.uploadData = {
            certifyFile : null,
            appLogo : null,
            appVerifyPage : null
        };

        $scope.addSgin = false;

        $scope.appCallbackUrl = null;

        $scope.phraseId = $routeParams.phraseId;

        //添加签名按钮
        $scope.toggleAddSign = function(addSign) {
            if(addSign) {
                $scope.addSign = false;
            }else {
                $scope.phrase.signId = null;
                $scope.addSign = true;
            }
        };

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

        $scope.uploadCertifyFile = function () {
            if (!$scope.uploadData.certifyFile) {
                return;
            }else if(!checkUploadFile($scope.uploadData.certifyFile)){
                return;
            }

            repo.uploadFile (
                phraseConf.uploadUrl,
                $scope.uploadData.certifyFile,
                {},
                function(evt){
                    $scope.progress.certifyFile = parseInt(100.0 * evt.loaded / evt.total);
                },
                function(data){
                    if(data.result == 'SUCCESS') {
                        $scope.phrase.certifyFile = data.files[0].path;
                        toastr.success('证明函上传成功!');
                    }
                }
            );
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
            if($scope.addSign) {
                if(!$scope.phrase.signContent) {
                    toastr.warning("请填写新增签名内容！");
                    return false;
                }else if(!$scope.phrase.signType){
                    toastr.warning("请选择新增签名类型！");
                    return false;
                }else if(!$scope.phrase.certifyFile){
                    toastr.warning("请上传新增签名验证函！");
                    return false;
                }
            }else if(!$scope.addSign) {
                if(!$scope.phrase.signId) {
                    toastr.warning("选择企业签名！");
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
            if($scope.phraseId == "add"){
                repo.getByUrl("common/apps").then(function(data) {
                    $scope.apps = data.data;
                });
                if($routeParams.content){
                    $scope.phrase.content = $routeParams.content;
                }
                if($routeParams.type){
                    $scope.phrase.type = $routeParams.type;
                }
            }else {
                repo.getByUrl("phrase/sms/detail/"+$scope.phraseId).then(function(data) {
                    $scope.phrase = data.data;
                    repo.getByUrl("common/apps").then(function(data) {
                        $scope.apps = data.data;
                        $scope.toggleAppUrl();
                    });
                });
            }
            repo.getByUrl("common/signs").then(function(data) {
                $scope.signs = data.data;
            });
        };

        init();

    };

    smsPhraseAddCtrl.$inject = injectParams;
    app.register.controller('smsPhraseAddCtrl', smsPhraseAddCtrl);
});

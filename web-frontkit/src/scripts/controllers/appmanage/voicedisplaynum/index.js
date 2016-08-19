define(["app"],
function(app) {
    var injectParams = ['$scope', '$q', '$modal', 'NgTableParams', 'repoService', 'utilService', 'Upload'];
    var voiceDisplayNumCtrl = function($scope, $q, $modal, NgTableParams, repo, util, Upload) {

        var voiceDisplayNumConf = {
            url: 'appmanage/voice-display-num',
            //基本URL，必填
            showName: '显号管理' //提示的名称，一般为模块名，必填
        };
        $scope.voiceDisplayNumParams = {
            count: 100,
            page: 1,
            sorts: {},
            params: {}
        };
        repo.queryByPath(voiceDisplayNumConf, "/list", $scope.voiceDisplayNumParams).then(function(data) {
            $scope.voiceDisplayNums = data.data;
        });
        $scope.deleteVoiceDisplayNum = function(data) { //删除
            repo.removeOne(voiceDisplayNumConf, data.id, data.displayNum).then(function(data) {
                repo.queryByPath(voiceDisplayNumConf, "/list", $scope.voiceDisplayNumParams).then(function(data) {
                    $scope.voiceDisplayNums = data.data;
                });
            });
        };
        $scope.addVoiceDisplayNum = function() {
            util.htmlModal($scope, "号码绑定", "modal/addVoiceDisplayNumForm.tpl.html",
            function(modal) {
                var mScope = modal.$scope;

                mScope.voiceDisplayNum = {
                    id: 0,
                    displayNum: "",
                    certifyFile: ""
                };
                mScope.downloadUrl=xpath.service("common/download?path="+mScope.voiceDisplayNum.certifyFile);
                mScope.file = {
                    file: ""
                };
                mScope.diagCls = "modal-md";
                mScope.okBtn = {
                    text: "新增",
                    click: function() {
                        var file = mScope.file.file;
                        if (!file) {
                            toastr.error('请选择文件！');
                            return;
                        } else if (!checkUploadFile(file)) {
                            return;
                        }
                        repo.uploadFile("common/upload", file, {},
                        function() {},
                        function(data) {
                            if (data.result == 'SUCCESS') {
                                if (data.result) {
                                    toastr.success('文件上传成功!');
                                    mScope.voiceDisplayNum.certifyFile = data.files[0].path;
                                    repo.add(voiceDisplayNumConf, mScope.voiceDisplayNum).then(function(json) {
                                        if (json.status == 0) {
                                            repo.queryByPath(voiceDisplayNumConf, "/list", $scope.voiceDisplayNumParams).then(function(data) {
                                                $scope.voiceDisplayNums = data.data;
                                            });
                                            util.hideModal(modal);
                                        }
                                    });
                                } else {
                                    toastr.error('文件上传失败!');
                                    return;
                                }
                            }
                        });
                    }
                };
            });
        };
        $scope.editVoiceDisplayNum = function(number) {
            util.htmlModal($scope, "编辑", "modal/addVoiceDisplayNumForm.tpl.html",
            function(modal) {
                var mScope = modal.$scope;
                mScope.voiceDisplayNum = angular.copy(number);
                mScope.file = {
                    file: ""
                };
                mScope.downloadUrl=xpath.service("common/download?path="+mScope.voiceDisplayNum.certifyFile);
                mScope.diagCls = "modal-md";
                mScope.okBtn = {
                    text: "确定",
                    click: function() {
                        var file = mScope.file.file;
                        if (file) {
                            if (!checkUploadFile(file)) {
                                return;
                            }
                            repo.uploadFile("common/upload", file, {},
                            function() {},
                            function(data) {
                                if (data.result == 'SUCCESS') {
                                    if (data.result) {
                                        toastr.success('文件上传成功!');
                                        mScope.voiceDisplayNum.certifyFile = data.files[0].path;
                                        repo.updateByPath(voiceDisplayNumConf, "/update", mScope.voiceDisplayNum, "更新").then(function(json) {
                                            if (json.status == 0) {
                                                repo.queryByPath(voiceDisplayNumConf, "/list", $scope.voiceDisplayNumParams).then(function(data) {
                                                    $scope.voiceDisplayNums = data.data;
                                                });
                                                util.hideModal(modal);
                                            }
                                        });
                                    } else {
                                        toastr.error('文件上传失败!');
                                        return;
                                    }
                                }
                            });
                        } else {
                            //只更新号码
                            repo.updateByPath(voiceDisplayNumConf, "/update", mScope.voiceDisplayNum, "更新").then(function(json) {
                                if (json.status == 0) {
                                    repo.queryByPath(voiceDisplayNumConf, "/list", $scope.voiceDisplayNumParams).then(function(data) {
                                        $scope.voiceDisplayNums = data.data;
                                    });
                                    util.hideModal(modal);
                                }
                            });
                        }
                    }
                };
            });
        };
        $scope.showVoiceDisplayNum = function(number) {
            htmlModal($scope, "查看", "modal/showVoiceDisplayNum.tpl.html",
            function(modal) {
                var mScope = modal.$scope;
                mScope.voiceDisplayNum = angular.copy(number);
                mScope.downloadUrl=xpath.service("common/download?path="+mScope.voiceDisplayNum.certifyFile);
            });
        }
         //检验上传文件
        var checkUploadFile = function(fileObj) {
            var reg1 = ['application/pdf', 'application/msword', 'image/png', 'image/jpeg'];
            var reg2 = ['application/zip'];
            if ((reg1.indexOf(fileObj.type) != -1 && fileObj.size < 2 * 1024 * 1024) || (reg2.indexOf(fileObj.type) != -1 && fileObj.size < 10 * 1024 * 1024)) {
                return true;
            } else {
                toastr.error('文件大小超过限定!');
                return false;
            }
        };
        //弹窗底部按钮
        var htmlModal = function(parentScope, title, tempId, init) {
            var modal = $modal({
                scope: parentScope,
                title: title,
                templateUrl: "modal/voiceDisplayNumCommon.tpl.html",
                contentTemplate: tempId,
                html: true,
                show: false,
                backdrop: "static"
            });
            modal.$promise.then(function() {
                init(modal);
                modal.show();
            });
            var scope = modal.$scope;
            scope.$on("modal.hide",
            function() {
                scope.$destroy();
            });
        };
    };
    voiceDisplayNumCtrl.$inject = injectParams;
    app.register.controller('voiceDisplayNumCtrl', voiceDisplayNumCtrl);
});
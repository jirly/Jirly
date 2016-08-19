define(["app"], function(app) {

    var injectParams = ['$scope', '$q', 'NgTableParams', 'utilService', 'repoService'];
    var testAppCtrl = function($scope, $q, NgTableParams, util, repo) {
        var testAppConf = {
            url: 'appmanage/testapp',
            //基本URL，必填
            showName: '白名单' //提示的名称，一般为模块名，必填
        };

        $scope.refreshData = function() {
            repo.getByPath(testAppConf, "/getTestApp").then(function(data) {
                $scope.app = data.data;

            });

            repo.getByPath(testAppConf, "/getTestPhrase").then(function(data) {
                $scope.phrases = data.data;
            });

            $scope.whiteListParams = { count: 100, page: 1, sorts: {}, params: {} };
            repo.queryByPath(testAppConf, "/getUsrtTestNumList", $scope.whiteListParams).then(function(data) {
                $scope.numbers = data.data;
            });
        };
        $scope.refreshData();

        $scope.deleteWhiteList = function(user) { //删除
            repo.removeOne(testAppConf, user.id, user.phone).then(function(data) {
                $scope.refreshData();
            });
        };

        $scope.bindValidate = function() {
            util.htmlModal($scope, "号码绑定", "modal/bindValidateForm.tpl.html",
                function(modal) {
                			
                    var mScope = modal.$scope;
                    mScope.whiteList = {
                        id: 0,
                        number: ""
                    };

                    mScope.diagCls = "modal-sm";
                    mScope.okBtn = {
                        text: "绑定",
                        click: function() {
                            repo.add(testAppConf, mScope.whiteList).then(function(json) {
                                if (json.status == 0) {
                                    $scope.refreshData();
                                    util.hideModal(modal);
                                }
                            });

                        }
                    };
                    var msgWait = 60;
                    var voiceWait = 60;
                    function time(button, text, wait) {
                        if (wait == 0) {
                            button.attr("disabled", false);
                            button.text(text);
                            $('#code-text').attr("disabled", true);		
                            $('#number-text').attr("disabled", false);				
                            wait = 60;
                        } else {
                            button.attr("disabled", true);
                            button.text("请等待" + wait + "s");
                            wait--;
                            setTimeout(function() {
                                time(button, text, wait);
                            }, 1000);
                        }
                    }

                    mScope.getCode = function(action) { //action 1短信验证码，2语音验证码
                        				
                        var number = mScope.whiteList.number;
                        var url = "/getCode?number=" + number + "&action=" + action;
                        repo.getByPath(testAppConf, url).then(function(data) {
                            if (data.status == 0) {
                                time($('#msgCode'), "获取短信验证码", msgWait);
                                time($('#voiceCode'), "获取语音验证码", voiceWait);
                                $('#code-text').attr("disabled", false);		
                                $('#number-text').attr("disabled", true);	
                            }else{
                            	toastr.error(data.errorMsg);
                            }
                        });
                    };
                });
        };

    };

    testAppCtrl.$inject = injectParams;
    app.register.controller('testAppCtrl', testAppCtrl);
});

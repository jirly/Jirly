'use strict';
define([], function() {
    /**
     * 路由设置函数，所以这里定义setRoute
     *
     * @param {[type]}　url [模块相对路径]
     * @param {[type]}　ctrl [Controller名称]
     * @param {[type]}　reqJs [模块对应的控制器JS文件]
     * @param {[type]}　label [标签，对应于导航条]
     */
    function setRoute(url, ctrl, reqJs, label) {
        var routeDef = {};
        routeDef.templateUrl = xpath.res("views" + url + ".html?ran=" + Math.random());
        routeDef.controller = ctrl;
        routeDef.resolve = {
            load: ['$q', '$rootScope',
                function($q, $rootScope) {
                    var defer = $q.defer();
                    require([xpath.res("scripts/controllers") + reqJs],
                        function() {
                            defer.resolve();
                            $rootScope.$apply();
                        });
                    return defer.promise;
                }
            ]
        };
        routeDef.label = label;
        return routeDef;
    }

    var app = angular.module('cmpApp', ['ngRoute', 'ui.router', 'mgcrea.ngStrap', 'ng-breadcrumbs', 'ngTable', "w5c.validator", "ngFileUpload", "angucomplete"]);

    app.config(["w5cValidatorProvider", function(w5cValidatorProvider) {
        // 全局配置
        w5cValidatorProvider.config({
            blurTrig: true,
            showError: true,
            removeError: true
        });

        w5cValidatorProvider.setRules({
            email: {
                required: "输入的邮箱地址不能为空",
                email: "输入邮箱地址格式不正确"
            },
            username: {
                required: "输入的用户名不能为空",
                pattern: "用户名必须输入字母、数字、下划线,以字母开头",
                w5cuniquecheck: "输入用户名已经存在，请重新输入"
            },
            password: {
                required: "密码不能为空",
                minlength: "密码长度不能小于{minlength}",
                maxlength: "密码长度不能大于{maxlength}"
            },
            repeatPassword: {
                required: "重复密码不能为空",
                repeat: "两次密码输入不一致"
            },
            number: {
                required: "数字不能为空"
            }
        });
    }]);

    app.config(function($routeProvider, $controllerProvider, $provide, $httpProvider) {
        app.register = {
            controller: $controllerProvider.register,
            factory: $provide.factory,
            service: $provide.service
        };

        var checkAccessState = function(resp) {
            var headers = resp.headers();
            var accessState = headers["access-state"];
            if (angular.isDefined(accessState) && accessState == "login") {
                location.href = addContext("login");
            } else if (angular.isDefined(accessState) && accessState == "unauthorized") {
                toastr.error("对不起，你没有权限进行此项操作。请联系系统管理员！");
                location.href = "#/";
            } else {
                return resp;
            }
        }

        $httpProvider.interceptors.push(function($q) {
            return {
                "response": function(resp) {
                    return checkAccessState(resp);
                },
                "responseError": function(rejection) {
                    var ret = checkAccessState(rejection);
                    if (angular.isDefined(ret)) {
                        toastr.error("请求处理失败！");
                    }
                    return $q.reject(rejection);
                }
            };
        });

        /** 设置路由 */
        $routeProvider
            .when('/', setRoute('/console/index', 'consoleIndexCtrl', '/console/index.js', '控制台'))


        //应用管理
        .when('/appmanage/app', setRoute('/appmanage/app/index', 'appIndexCtrl', '/appmanage/app/index.js', '应用管理'))
            .when('/appmanage/app/add', setRoute('/appmanage/app/edit', 'appAddCtrl', '/appmanage/app/add.js', '新增应用'))
            .when('/appmanage/app/detail/:appId', setRoute('/appmanage/app/detail', 'appDetailCtrl', '/appmanage/app/detail.js', '查看应用'))
            .when('/appmanage/app/edit/:appId', setRoute('/appmanage/app/edit', 'appEditCtrl', '/appmanage/app/edit.js', '修改应用'))

            // 短信模板管理
        .when('/appmanage/smsphrase', setRoute('/appmanage/smsphrase/index', 'smsPhraseIndexCtrl', '/appmanage/smsphrase/index.js', '短信模板'))
            .when('/appmanage/smsphrase/detail/:phraseId', setRoute('/appmanage/smsphrase/detail', 'smsPhraseDetailCtrl', '/appmanage/smsphrase/detail.js', '短信模板详情'))
            .when('/appmanage/smsphrase/edit/:phraseId', setRoute('/appmanage/smsphrase/edit', 'smsPhraseAddCtrl', '/appmanage/smsphrase/edit.js', '编辑短信模板'))

            // 语音模板管理
        .when('/appmanage/voicephrase', setRoute('/appmanage/voicephrase/index', 'voicePhraseIndexCtrl', '/appmanage/voicephrase/index.js', '语音模板'))
            .when('/appmanage/voicephrase/detail/:phraseId', setRoute('/appmanage/voicephrase/detail', 'voicePhraseDetailCtrl', '/appmanage/voicephrase/detail.js', '语音模板详情'))
            .when('/appmanage/voicephrase/edit/:phraseId', setRoute('/appmanage/voicephrase/edit', 'voicePhraseAddCtrl', '/appmanage/voicephrase/edit.js', '编辑语音模板'))

            //参考模板
        .when('/appmanage/samplephrase', setRoute('/appmanage/samplephrase/index', 'phraseSampleIndexCtrl', '/appmanage/samplephrase/index.js', '参考模板'))




        .when('/appmanage/testapp', setRoute('/appmanage/testapp/index', 'testAppCtrl', '/appmanage/testapp/index.js', '测试Demo'))
            .when('/appmanage/voice-display-num', setRoute('/appmanage/voicedisplaynum/index', 'voiceDisplayNumCtrl', '/appmanage/voicedisplaynum/index.js', '显号管理'));
    });

    return app;
});

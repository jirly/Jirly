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

    var app = angular.module('cmpApp', ['ngRoute', 'ui.router', 'mgcrea.ngStrap', 'ng-breadcrumbs', 'ngTable', "w5c.validator","angularjs-dropdown-multiselect","ngFileUpload"]);

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
            .when('/', {
                templateUrl: xpath.res('views/home.html?ran=' + Math.random()),
                label: '主页'
            })
            .when('/console', setRoute('/console/index', 'consoleIndexCtrl/index', '/console/index.js', '控制台'))

            /** 通道管理路由*/
            .when('/channelmgmt/info',setRoute('/channelmgmt/info/index','infoIndexCtrl','/channelmgmt/info/index.js','通道基本信息'))
            .when('/channelmgmt/info/add', setRoute('/channelmgmt/info/add', 'infoAddCtrl', '/channelmgmt/info/add.js', '新增通道基本信息'))
            .when('/channelmgmt/info/edit/:infoId', setRoute('/channelmgmt/info/edit', 'infoEditCtrl', '/channelmgmt/info/edit.js', '修改通道基本信息'))

            .when('/channelmgmt/whitelist/index',setRoute('/channelmgmt/whitelist/index','whitelistIndexCtrl','/channelmgmt/whitelist/index.js','通道白名单'))
            .when('/channelmgmt/nonwhitelist/index',setRoute('/channelmgmt/nonwhitelist/index','nonwhitelistIndexCtrl','/channelmgmt/nonwhitelist/index.js','非白名单管理'))
            .when('/channelmgmt/portspare/index',setRoute('/channelmgmt/portspare/index','portspareIndexCtrl','/channelmgmt/portspare/index.js','白名单通道备用'))
            .when('/channelmgmt/channelpriority/index',setRoute('/channelmgmt/channelpriority/index','channelpriorityIndexCtrl','/channelmgmt/channelpriority/index.js','区域优先'))
            .when('/channelmgmt/channelchange/index',setRoute('/channelmgmt/channelchange/index','channelchangeIndexCtrl','/channelmgmt/channelchange/index.js','通道切换'))

            /** 基础数据管理路由 */
            .when('/basicdata/regionteleseg', setRoute('/basicdata/regionteleseg/index', 'regiontelesegIndexCtrl', '/basicdata/regionteleseg/index.js', '区域号段'))
            .when('/basicdata/regionteleseg/add', setRoute('/basicdata/regionteleseg/add', 'regiontelesegAddCtrl', '/basicdata/regionteleseg/add.js', '新增区域号段'))
            .when('/basicdata/regionteleseg/edit/:id', setRoute('/basicdata/regionteleseg/edit', 'regiontelesegEditCtrl', '/basicdata/regionteleseg/edit.js', '修改区域号段'))
            .when('/basicdata/regionteleseg/import', setRoute('/basicdata/regionteleseg/import', 'regiontelesegImportCtrl', '/basicdata/regionteleseg/import.js', '导入区域号段'))
            .when('/basicdata/blacklist', setRoute('/basicdata/blacklist/index', 'blacklistIndexCtrl', '/basicdata/blacklist/index.js', '黑名单'))
            .when('/basicdata/blacklist/import', setRoute('/basicdata/blacklist/import', 'blacklistImportCtrl', '/basicdata/blacklist/import.js', '导入黑名单'))
            .when('/basicdata/carrierteleseg', setRoute('/basicdata/carrierteleseg/index', 'carriertelesegIndexCtrl', '/basicdata/carrierteleseg/index.js', '运营商号段'))
            .when('/basicdata/carrierteleseg/add', setRoute('/basicdata/carrierteleseg/add', 'carriertelesegAddCtrl', '/basicdata/carrierteleseg/add.js', '新增运营商号段'))
            .when('/basicdata/illegalkey', setRoute('/basicdata/illegalkey/index', 'illegalkeyIndexCtrl', '/basicdata/illegalkey/index.js', '非法关键字'))
            .when('/basicdata/illegalkey/add', setRoute('/basicdata/illegalkey/add', 'illegalkeyAddCtrl', '/basicdata/illegalkey/add.js', '新增非法关键字'))
            .when('/basicdata/illegalkey/import', setRoute('/basicdata/illegalkey/import', 'illegalkeyImportCtrl', '/basicdata/illegalkey/import.js', '导入非法关键字'))
        ;

    });

    return app;
});

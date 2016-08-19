define(["app"], function(app) {
    var injectParams = ['$scope', '$routeParams', '$location', 'repoService', 'utilService'];
    var appEditCtrl = function($scope, $routeParams, $location, repo, util) {
        var appConf = {
            url: 'appmanage/app', //基本URL，必填
            showName: '应用' //提示的名称，一般为模块名，必填
        };

        var appId = $routeParams.appId;
        $scope.app = { enablers: [] };
        $scope.appTrustIpsStr = "";
        repo.get(appConf, appId).then(function(data) {
            $scope.app = data.data;
            $scope.appTrustIpsStr = $scope.app.trustIps.join(",");
            if ($scope.app.enablers == null) {
                $scope.app.enablers = [];
            }
        });

        repo.getByUrl("common/enablers").then(function(data) {
            $scope.enablers = data.data;
        });

        //选择能力
        $scope.selectEnablers = function($event, typeIdx) {
            var checkbox = $event.target;
            var checked = checkbox.checked;

            var idx = $scope.app.enablers.indexOf(typeIdx);
            if (checked && idx == -1) { //选中
                $scope.app.enablers.push(typeIdx);
            }
            if (!checked && idx != -1) { //取消选中
                $scope.app.enablers.splice(idx, 1);
            }
            $scope.app.enablers.sort(function(a, b) {
                return a - b;
            });

            $scope.appForm.$pristine = false;
        };

        $scope.isSelected = function(typeIdx) {
            return $scope.app.enablers.indexOf(typeIdx) >= 0;
        };

        $scope.submitForm = function() {
            $scope.app.trustIps = new Array();
            var trustIps = $.trim($scope.appTrustIpsStr);
            if (trustIps != "") {
                var ips = trustIps.split(",");
                for (var i = 0; i < ips.length; i++) {
                    var ip = $.trim(ips[i]);

                    if (!util.isIpAddress(ip)) {
                        toastr.warning("第 " + (i + 1) + " 个IP地址(" + ip + ")格式错误！");
                        return;
                    }
                    $scope.app.trustIps.push(ip);
                }
            }


            if ($scope.app.enablers.length == 0) {
                toastr.warning("请至少选择一个支持能力！");
                return;
            }

            repo.update(appConf, $scope.app).then(function(data) {
                $location.path(appConf.url);
            });
        };
    };

    appEditCtrl.$inject = injectParams;
    app.register.controller('appEditCtrl', appEditCtrl);
});

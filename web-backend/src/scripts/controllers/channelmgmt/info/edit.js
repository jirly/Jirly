define(["app"], function(app) {
    var injectParams = ['$scope', '$routeParams', 'repoService', 'utilService', 'Upload'];
    var infoEditCtrl = function($scope, $routeParams,repo, util) {
        var channelEditConf = {
            url: 'channelmgmt/info/edit', //基本URL，必填
            showName: '新增' //提示的名称，一般为模块名，必填
        };

        $scope.app = { enablers: [] };
        repo.getByUrl("common/enablers").then(function(data) {
            $scope.enablers = data.data;
        });

        $scope.infoId = $routeParams.infoId;//注意
        $scope.updateSelection = function($event, typeIdx) {
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
        }

        $scope.isSelected = function(typeIdx) {
            return $scope.app.enablers.indexOf(typeIdx) >= 0;
        }

        var init = function() {
            if($scope.infoId != 'add'){
                repo.getByUrl("phrase/sms/detail/"+$scope.phraseId).then(function(data) {
                    $scope.phrase = data.data;
                });
            }
            repo.getByUrl("phrase/sms/signs").then(function(data) {
                $scope.signs = data.data;
                console.info(data.data);
            });
            repo.getByUrl("phrase/sms/apps").then(function(data) {
                $scope.apps = data.data;
            });
        };

        init();
    };

    infoEditCtrl.$inject = injectParams;
    app.register.controller('infoEditCtrl', infoEditCtrl);
});

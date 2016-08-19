define(["app"], function(app) {
    var injectParams = ['$scope', 'NgTableParams', 'repoService', 'utilService'];
    var whitelistIndexCtrl = function($scope, NgTableParams, repo, util) {
       $scope.whitelistIndex = "通道白名单——/channelmgmt/whitelist/index";

        
    };

    whitelistIndexCtrl.$inject = injectParams;
    app.register.controller('whitelistIndexCtrl', whitelistIndexCtrl);
});

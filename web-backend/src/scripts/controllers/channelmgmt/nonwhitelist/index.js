define(["app"], function(app) {
    var injectParams = ['$scope', 'NgTableParams', 'repoService', 'utilService'];
    var nonwhitelistIndexCtrl = function($scope, NgTableParams, repo, util) {
       $scope.nonwhitelistIndex = "非白名单管理——/channelmgmt/nonwhitelist/index";

        
    };

    nonwhitelistIndexCtrl.$inject = injectParams;
    app.register.controller('nonwhitelistIndexCtrl', nonwhitelistIndexCtrl);
});

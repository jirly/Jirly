define(["app"], function(app) {
    var injectParams = ['$scope', 'NgTableParams', 'repoService', 'utilService'];
    var channelpriorityIndexCtrl = function($scope, NgTableParams, repo, util) {
       $scope.channelpriorityIndex = "区域优先——/channelmgmt/channelpriority/index";

        
    };

    channelpriorityIndexCtrl.$inject = injectParams;
    app.register.controller('channelpriorityIndexCtrl', channelpriorityIndexCtrl);
});

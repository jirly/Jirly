define(["app"], function(app) {
    var injectParams = ['$scope', 'NgTableParams', 'repoService', 'utilService'];
    var channelchangeIndexCtrl = function($scope, NgTableParams, repo, util) {
       $scope.channelchangeIndex = "通道切换——/channelmgmt/channelchange/index";

        
    };

    channelchangeIndexCtrl.$inject = injectParams;
    app.register.controller('channelchangeIndexCtrl', channelchangeIndexCtrl);
});

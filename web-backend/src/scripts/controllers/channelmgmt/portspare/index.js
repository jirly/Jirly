define(["app"], function(app) {
    var injectParams = ['$scope', 'NgTableParams', 'repoService', 'utilService'];
    var portspareIndexCtrl = function($scope, NgTableParams, repo, util) {
       $scope.portspareIndex = "白名单通道备用——/channelmgmt/portspare/index";

        
    };

    portspareIndexCtrl.$inject = injectParams;
    app.register.controller('portspareIndexCtrl', portspareIndexCtrl);
});

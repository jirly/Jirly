define(["app"], function(app) {
    var injectParams = ['$scope', 'NgTableParams', 'repoService', 'utilService'];
    var consoleIndexCtrl = function($scope, NgTableParams, repo, util) {
       $scope.hello = "xxxxxx";

        
    };

    consoleIndexCtrl.$inject = injectParams;
    app.register.controller('consoleIndexCtrl', consoleIndexCtrl);
});

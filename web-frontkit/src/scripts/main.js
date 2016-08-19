var serviceRoot = "service/"; //API根路径
var resRoot = ""; //静态资源根路径

var xpath = {
    service: function(path) {
        return serviceRoot + path;
    },
    res: function(path) {
        return resRoot + path;
    }
}


require.config({
    baseUrl: "scripts"
});
require([
        "app",
        "controllers/index",
        "services/util-service",
        "services/repo-service",
        "directives/util-directive"
    ],
    function() {
        angular.bootstrap(document, ["cmpApp"]);
    });

$(function($) {
    console.log(" ~~ Document is ready!!!");
});

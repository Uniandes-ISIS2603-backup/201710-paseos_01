(function(ng){
var app = angular.module('mainApp',[
    'ui.router',
    'appPaseos',
    'ofertasModule'
]);
// Resuelve problemas de las promesas
   app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
})(window.angular);
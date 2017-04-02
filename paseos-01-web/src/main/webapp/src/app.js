(function(ng){
var app = angular.module('mainApp',[
    'ui.router',
    'paseosModule',
    'ofertasModule',
    'usuarioModule'  
]);
// Resuelve problemas de las promesas
   app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
})(window.angular);
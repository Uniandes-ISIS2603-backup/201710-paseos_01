(function(ng){
var mainApp=ng.module('mainApp',['ui.router','appPaseos']);
// Resuelve problemas de las promesas
    mainApp.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
})(window.angular);
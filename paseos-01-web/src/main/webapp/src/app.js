(function(ng){
var mainApp=ng.module('mainApp',['ui.router','paseosModule','usuariosModule']);
// Resuelve problemas de las promesas
   // mainApp.config(['$qProvider', function ($qProvider) {
     //      $qProvider.errorOnUnhandledRejections(false);
       // }]);

})(window.angular);
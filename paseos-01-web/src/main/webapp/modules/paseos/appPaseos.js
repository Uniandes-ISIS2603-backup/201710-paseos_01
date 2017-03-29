var appPaseos=angular.module('appPaseos',[]);
var mainPaseosController=appPaseos.controller("mainPaseosController",['$scope','getPaseos', function($scope,getPaseos){
        getPaseos.success(function(data){
             $scope.catalogo=data;
        });
       
        
}]);

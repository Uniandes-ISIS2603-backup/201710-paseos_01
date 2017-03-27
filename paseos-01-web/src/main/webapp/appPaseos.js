var mainApp=angular.module('mainApp',[]);
var mainController=mainApp.controller("mainController",['$scope','getPaseos', function($scope,getPaseos){
        getPaseos.success(function(data){
             $scope.catalogo=data;
        });
       
        
}]);

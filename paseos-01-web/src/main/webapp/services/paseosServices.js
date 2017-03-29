mainApp.factory("getPaseos",["$http",function($http ){
     return $http.get("/paseos-01-web/api/paseos").success(function(data){
         return data;
     }).error(function(err){
         return err;
     });
}]);
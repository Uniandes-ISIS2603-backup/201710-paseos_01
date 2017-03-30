(function(ng){
    //defincicón del modulo
var appPaseos=ng.module('appPaseos',['ui.router']);
 // Configuración de los estados del módulo
    appPaseos.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/paseos/';
            // Mostrar la lista de libros será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/paseos");
            // Definición del estado 'booksList' donde se listan los libros
            $stateProvider.state('paseos', {
                // Url que aparecerá en el browser
                url: '/paseos',
                // Se define una variable books (del estado) que toma por valor 
                // la colección de libros que obtiene utilizando $http.get 
                 resolve: {
                    getPaseos: ["$http",function($http ){
                    return $http.get("/paseos-01-web/api/paseos").success(function(data){
                        return data;
                    }).error(function(err){
                        return err;
                    });
               }]
                },
                // Template que se utilizara para ejecutar el estado
                templateUrl: basePath + 'paseosCatalogo.html',
                // El controlador guarda en el scope en la variable booksRecords los datos que trajo el resolve
                // booksRecords será visible en el template
                controller: ['$scope','getPaseos', function($scope,getPaseos){
          
                    $scope.catalogo=getPaseos.data;
       
        
                }]            
            });
            
            $stateProvider.state('paseoDetail',{
                url: '/{idPaseo}',
                parent:"paseos",
                param : {
                    idPaseo: null
                },
                resolve: {
                    getPaseo: ["$http",function($http ){
                    return $http.get("/paseos-01-web/api/paseos/"+idPaseo).success(function(data){
                        return data;
                    }).error(function(err){
                        return err;
                    });
               }]
                },
                views:{
                     templateUrl: basePath + 'paseo.detail.html',
                        controller: ['$scope','getPaseo', function ($scope, getPaseo) {
                                $scope.paseoActual = getPaseo.data;
                            }]
                
                }
            
            });
        }
    ]);

})(window.angular);

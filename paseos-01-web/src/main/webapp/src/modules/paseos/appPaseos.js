(function(ng){
    //defincicón del modulo
var appPaseos=ng.module('appPaseos',['ui.router']);
 // Configuración de los estados del módulo
    appPaseos.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/paseos/';
            // Mostrar la lista de libros será el estado por defecto del módulo
            $urlRouterProvider.otherwise("paseos/catalogo");
           //Definición de un estado abstracto
           $stateProvider.state('paseos',{
               
                abstract:true,
                url: '/paseos',
                 resolve: {
                    getPaseos: ["$http",function($http ){
                    return $http.get("/paseos-01-web/api/paseos").success(function(data){
                        return data;
                    }).error(function(err){
                        return err;
                    });
               }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'paseos.html',
                         controller: ['$scope','getPaseos', function($scope,getPaseos){
          
                    $scope.catalogo=getPaseos.data;
       
        
                }]  
                    }
                    
                }
               
           })
            
            
            // Definición del estado 
           
            .state('paseosCatalogo', {
                // Url que aparecerá en el browser
                url: '/catalogo',
                parent:"paseos",
                
                
                views: {
                    'catalogoView': {
                // Template que se utilizara para ejecutar el estado
                templateUrl: basePath + 'paseosCatalogo.html',
                // El controlador guarda en el scope en la variable booksRecords los datos que trajo el resolve
                // booksRecords será visible en el template
               }
            }}).state('paseoDetail',{
                url: '/{idPaseo: int}/detail',
                parent:"paseos",
                param : {
                    idPaseo: null
                },
                resolve: {
                    getPaseo: ["$http",'$stateParams', function($http, $params ){
                    return $http.get("/paseos-01-web/api/paseos/"+ $params.idPaseo.toString()).success(function(data){
                        return data;
                    }).error(function(err){
                        return err;
                    });
               }]
                },
                views:{
                'catalogoView': {
                // Template que se utilizara para ejecutar el estado
                templateUrl: basePath + 'paseosCatalogo.html',
             
                 },
                'detailView':{
                templateUrl: basePath + 'paseo.detail.html',
                controller: ['$scope','getPaseo', function ($scope, getPaseo) {
                             //   $window.document.getElementById("view").ui-view="detailedView";
                                $scope.paseoActual = getPaseo.data;
                            }]
                
                
                    }}
            }).state('addPaseo', {
                // Url que aparecerá en el browser
                url: '/addPaseo',
                parent:"paseos",
                resolve: {
                    setPaseo: ["$http",'$scope', function($http, $scope ){
                    return $http.post("/paseos-01-web/api/paseos/",$scope.addPaseo).success(function(data){
                        return data;
                    }).error(function(err){
                        return err;
                    });
               }]
                },
                
                views: {
                'addView': {
                // Template que se utilizara para ejecutar el estado
                templateUrl: basePath + 'addPaseo.html',
                controller: ['$scope', 'postPaseo', function ($scope, setPaseo){
                        $scope.savePaseo= function(){
                            $scope.addPaseo={
                                "destino":window.document.getElementById("destino").getValue(),
                                "tematica":window.document.getElementById("tematica").getValue(),
                                "condicionFisica":window.document.getElementById("condicionFisica").getValue(),
                                "almuerzo":window.document.getElementById("almuerzo").getValue(),
                                "transporte":window.document.getElementById("transporte").getValue(),
                                "numeroMinimo":window.document.getElementById("numeroMinimo").getValue(),
                                "numeroMaximo":window.document.getElementById("numeroMaximo").getValue(),
                                "costo":window.document.getElementById("costo").getValue()
                            }
                        }
                        
                }]
                // El controlador guarda en el scope en la variable booksRecords los datos que trajo el resolve
                // booksRecords será visible en el template
               }
            }});
    }]);

})(window.angular);

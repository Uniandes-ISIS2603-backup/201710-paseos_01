(function(ng){
    //defincicón del modulo
var appPaseos=ng.module('paseosModule',['ui.router']);
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
                    return $http.get("/paseos-01-web/api/paseos"
                            ).success(function(data){
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
                                if($scope.paseoActual.almuerzo){
                                    $scope.paseoActual.almuerzo="Sí";
                                }else{
                                    $scope.paseoActual.almuerzo="No";
                                }
                                
                                if($scope.paseoActual.transporte){
                                    $scope.paseoActual.transporte="Sí";
                                }else{
                                    $scope.paseoActual.transporte="No";
                                }
                            }]
                
                
                    }}
            }).state('addPaseo', {
                // Url que aparecerá en el browser
                url: '/addPaseo',
                parent:"paseos",
                
                resolve: {
                    setPaseo: ["$http",function($http){
                          var adicionPaseo =  
                     function (addPaseo){
                    $http.post("/paseos-01-web/api/paseos/",addPaseo).success(function(data){
                        return data;
                    }).error(function(err){
                        return err;
                    });
                }
                    return adicionPaseo;
                    
                    
               }]
                },
                
                views: {
                'addView': {
                // Template que se utilizara para ejecutar el estado
                templateUrl: basePath + 'addPaseo.html',
                controller: ['$scope', 'setPaseo', function ($scope, setPaseo){
                        $scope.destino="";
                        $scope.tematica="";
                        $scope.condicionFisica=0;
                        $scope.almuerzo="";
                        $scope.transporte="";
                        $scope.numeroMinimo=0;
                        $scope.numeroMaximo=0;
                        $scope.costo=0.0;
                        $scope.addPaseo={};
                        $scope.savePaseo= function(){
                            $scope.addPaseo={
                                "destino":$scope.destino,
                                "tematica":$scope.tematica,
                                "condicionFisica":$scope.condicionFisica,
                                "almuerzo":$scope.almuerzo,
                                "transporte":$scope.transporte,
                                "numeroMinimo":$scope.numeroMinimo,
                                "numeroMaximo":$scope.numeroMaximo,
                                "costo":$scope.costo
                            }
                        setPaseo($scope.addPaseo);

                        };
                        
                }]
                // El controlador guarda en el scope en la variable booksRecords los datos que trajo el resolve
                // booksRecords será visible en el template
               }
            }}).state('modificarPaseo', {
                // Url que aparecerá en el browser
                url: '/updatePaseo',
                parent:"paseos",
                
             
                
                views: {
                'addView': {
                // Template que se utilizara para ejecutar el estado
                templateUrl: basePath + 'paseo.update.html'
            }
            }}
        ).state('eliminarPaseo',{
                // Url que aparecerá en el browser
                url: '/deletePaseo',
                parent:"paseos",
                resolve: {
                    deletePaseo: ["$http",function($http){
                     var eliminarPaseo =  
                     function (id){
                    $http.delete("/paseos-01-web/api/paseos/"+id.toString()).success(function(data){
                        return data;
                    }).error(function(err){
                        return err;
                    });
                }
                    return eliminarPaseo;
                    
                    
               }]
                },
             
                
                views: {
                'addView': {
                // Template que se utilizara para ejecutar el estado
                templateUrl: basePath + 'paseo.delete.html',
                controller: ['$scope', 'deletePaseo', function ($scope, deletePaseo){
                        
                        $scope.deletePaseo= function(id){
                            deletePaseo(id);

                        };
                        
                }]
            }
            }});
    }]);

})(window.angular);

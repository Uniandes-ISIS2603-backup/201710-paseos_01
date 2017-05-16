(function(ng){
    //defincicón del modulo
const appPaseos=ng.module('paseosModule',['ui.router']);
 // Configuración de los estados del módulo
    appPaseos.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            const basePath = 'src/modules/paseos/';
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
                          const adicionPaseo =  
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
                controller: ['$scope', 'setPaseo','$state', function ($scope, setPaseo,$state){
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
                        $state.reload();
                        };
                        
                }]
                // El controlador guarda en el scope en la variable booksRecords los datos que trajo el resolve
                // booksRecords será visible en el template
               }
            }}).state('modificarPaseo', {
                // Url que aparecerá en el browser
                url: '/{idPaseo: int}/updatePaseo',
                parent:"paseos",
                param : {
                    idPaseo: null
                },
               resolve: {
                    putPaseo: ["$http",'$stateParams',function($http,$params){
                          const modificarPaseo =  
                     function (putPaseo){
                    $http.put("/paseos-01-web/api/paseos/"+$params.idPaseo.toString(),putPaseo).success(function(data){
                        return data;
                    }).error(function(err){
                        return err;
                    });
                }
                    return modificarPaseo;
                    
                    
               }]
               /*,
                    getPaseo: ["$http",'$stateParams', function($http, $params ){
                    return $http.get("/paseos-01-web/api/paseos/"+ $params.idPaseo.toString()).success(function(data){
                        return data;
                    }).error(function(err){
                        return err;
                    });
               }]*/
                },
                
                views: {
                'addView': {
                // Template que se utilizara para ejecutar el estado
                templateUrl: basePath + 'paseo.update.html',
                controller: ['$scope', 'putPaseo','$state', "$http",'$stateParams', 
                    function ($scope, putPaseo,$state, $http, $params){
                        
                        $http
                            .get("/paseos-01-web/api/paseos/"+ $params.idPaseo.toString())
                            .success(function(data){
                             
                                $scope.addPaseo = data;
                        $scope.destino=$scope.addPaseo.destino;
                        $scope.tematica=$scope.addPaseo.tematica;
                        $scope.condicionFisica=$scope.addPaseo.condicionFisica;
                        $scope.almuerzo=$scope.addPaseo.almuerzo;
                        $scope.transporte=$scope.addPaseo.transporte;
                        $scope.numeroMinimo=$scope.addPaseo.numeroMinimo;
                        $scope.numeroMaximo=$scope.addPaseo.numeroMaximo;
                        $scope.costo=$scope.addPaseo.costo;
                        });
                    
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
                            };
                            putPaseo($scope.addPaseo);
                            $state.reload();
                        };
            }]
            }}
    }).state('eliminarPaseo',{
                // Url que aparecerá en el browser
                url: '/deletePaseo',
                parent:"paseos",
                resolve: {
                    deletePaseo: ["$http",function($http){
                     const eliminarPaseo =  
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
                controller: ['$scope', 'deletePaseo','$state', function ($scope, deletePaseo, $state){
                        
                        $scope.deletePaseo= function(id){
                            deletePaseo(id);
                             $state.reload();

                        };
                        
                }]
            }
            }});
    }]);

})(window.angular);

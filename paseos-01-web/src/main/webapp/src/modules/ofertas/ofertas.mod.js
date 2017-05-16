(function (ng) {
    var mod = ng.module("ofertasModule", ['ui.router']);
    mod.constant("ofertasContext", "api/ofertas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/ofertas/';
            $urlRouterProvider.otherwise("/ofertasList");

            $stateProvider.state('ofertas', {
                url: '/ofertas',
                abstract: true,
                resolve: {
                    ofertas: ['$http','ofertasContext', function ($http,ofertasContext) {
                                return $http.get(ofertasContext);
                           }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'ofertas.html',
                        controller: ['$scope', 'ofertas', function ($scope, ofertas) {
                                $scope.ofertasRecords = ofertas.data;
                            }]
                    }
                }
            }).state('ofertasList', {
                url: '/list',
                parent: 'ofertas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'ofertas.list.html'
                    }
                }
            }).state('agregarOferta', {
                url: '/agregarOferta',
                parent: 'ofertas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'addOferta.html',
                        resolve: {
                                catalogo: ['$http', function ($http) {
                                return $http.get('api/paseos?catalogo=0');
                        }],
                                guias: ['$http', function ($http) {
                                return $http.get('api/usuarios?guia=1');
                        }],
                                setOferta: ["$http",function($http){
                                const adicionOferta =  
                                function (addOferta){
                                $http.post("api/ofertas/",addOferta).success(function(data){
                                return data;
                                }).error(function(err){
                                return err;
                                });
                            }
                    return adicionOferta;                  
               }]
                },
                controller: ['$scope','catalogo','guias', 'setOferta', '$state', function ($scope,catalogo,guias,setOferta,$state) {
                                $scope.catalogo = catalogo.data;
                                $scope.guias = guias.data;
                                $scope.paseoElegido = 0;
                                $scope.guiaElegido = 0;
                                $scope.fechaElegida = 0;
                                $scope.oferta = {};
                                $scope.ok = function(){                                    
                                    $scope.oferta = {
                                            "fecha": $scope.fechaElegida,
                                            "inscritos": 0,
                                            "guia":{
                                                    "id": $scope.guiaElegido
                                                    },
                                            "paseo":{
                                                    "id": $scope.paseoElegido
                                                    },

                                            "visitas": []
                                        };
                                    setOferta($scope.oferta);
                                    $state.go($state.current, {}, {reload: true});
                                    //$state.reload();
                                };
                            }]
                    }
                }
            }).state('editarOferta', {
                url: '/{ofertaId:int}/updateOferta',
                parent: 'ofertas',
                param: {
                    ofertaId: null
                },
                resolve: {
                                putOferta:["$http",'$stateParams',function($http,$params){
                                const modificarOferta = function (putOferta){
                                $http.put("api/ofertas/"+$params.ofertaId.toString(),putOferta).success(function(data){
                                return data;
                    }).error(function(err){
                        return err;
                    });
                };
                    return modificarOferta;
               }] 
                        },
                views: {
                    'listView': {                        
                        templateUrl: basePath + 'editOferta.html',   
                        resolve: {
                                guias: ['$http', function ($http) {
                                return $http.get('api/usuarios?guia=1');
                                }]
                                },
                                controller: ['$scope', 'putOferta', '$state','$http','guias', '$stateParams',
                                    function ($scope, putOferta, $state, $http, guias, $params) {
                                        $scope.guias = guias.data;
                                        $http.get("/paseos-01-web/api/ofertas/"+ $params.ofertaId.toString())
                                        .success(function(data){                             
                                                    $scope.addOferta = data;
                                                $scope.paseoElegido=$scope.addOferta.paseo.id;                                             
                                                });
                                                
                                        $scope.ok = function (){
                                            $scope.addOferta={
                                            "fecha": $scope.fechaElegida,
                                            "inscritos": 0,
                                            "guia":{
                                                    "id": $scope.guiaElegido
                                                    },
                                            "paseo":{
                                                    "id": $scope.paseoElegido
                                                    },

                                            "visitas": []
                                            };
                                            putOferta($scope.addOferta);
                                            $state.go('ofertasList');
                                        };                                                                               
                                 }]
                                },
                    'detailView': {                       
                                templateUrl: basePath + 'ofertas.detail.html',        
                                resolve: {
                                        currentOferta: ['$http','ofertasContext','$stateParams', function ($http,ofertasContext,$params) {
                                        return $http.get(ofertasContext+'/'+$params.ofertaId);
                                        }]
                                     },
                                        controller: ['$scope','currentOferta', function ($scope,currentOferta) {
                                        $scope.currentOferta = currentOferta.data;
                                        }]
                    }
                }
            }).state('administrarOfertas', {
                url: '/adminOfertas',
                parent:"ofertas",
                resolve: {
                        deleteOferta: ["$http","ofertasContext",function($http, ofertasContext){
                        const eliminarOferta = function (id){
                        $http.delete(ofertasContext+"/"+id.toString()).success(function(data){
                        return data;
                    }).error(function(err){
                        return err;
                    });
                }
                    return eliminarOferta;                    
               }]},               
                views: {
                'listView': {
                templateUrl: basePath + 'adminOfertas.html',
                controller: ['$scope', 'deleteOferta','$state', function ($scope, deleteOferta, $state, $window){                        
                            $scope.deleteOferta = function(id){
                            deleteOferta(id);
                            $state.reload();
                        };
                        
                }]
            }
            }}).state('ofertaDetail', {
                url: '/{ofertaId:int}/detail',
                parent: 'ofertas',
                param: {
                    ofertaId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'ofertas.list.html'                       
                    },
                    'detailView': {
                                resolve: {
                                        currentOferta: ['$http','ofertasContext','$stateParams', function ($http,ofertasContext,$params) {
                                        return $http.get(ofertasContext+'/'+$params.ofertaId);
                                        }]
                                     },
                                        templateUrl: basePath + 'ofertas.detail.html',
                                        controller: ['$scope','currentOferta', function ($scope,currentOferta) {
                                        $scope.currentOferta = currentOferta.data;
                                        }]
                    }
                }
            });
        }]);
})(window.angular);
(function (ng) {
    const mod = ng.module("usuarioModule", ['ui.router']);
    mod.constant("usuariosContext", "api/usuarios");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            const basePath = 'src/modules/usuarios/';
           
            $urlRouterProvider.otherwise("/usuariosList");

            $stateProvider.state('usuarios', {
                url: '/usuarios',
                abstract: true,
                resolve: {
                    usuarios: ['$http','usuariosContext', function ($http, usuariosContext) {
                            return $http.get(usuariosContext);
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'usuarios.html',
                        controller: ['$scope', 'usuarios', function ($scope, usuarios) {
                                $scope.usuariosRecords = usuarios.data;
                            }]
                    }
                }
            }).state('usuariosList', {
                url: '/list',
                parent: 'usuarios',
                views: {
                    'listView': {
                        templateUrl: basePath + 'usuarios.list.html'
                    }
                }
            }).state('editarUsuario', {
                url: '/{usuarioId:int}/editarUsuario',
                parent: 'usuarios',
                param: {
                    usuarioId: null
                },
                resolve: {
                                putUsuario:["$http",'$stateParams',function($http,$params){
                                const modificarUsuario = function (putUsuario){
                                $http.put("api/usuarios/"+$params.usuarioId.toString(),putUsuario).success(function(data){
                                return data;
                    }).error(function(err){
                        return err;
                    });
                };
                    return modificarUsuario;
               }] 
                        },
                views: {
                    'listView': {                        
                        templateUrl: basePath + 'editarUsuario.html',   
                        resolve: {
                               
                                },
                                controller: ['$scope', 'putUsuario', '$state','$http', '$stateParams',
                                    function ($scope, putUsuario, $state, $http, $params) {
                                        
                                        $http.get("/paseos-01-web/api/usuarios/"+ $params.usuarioId.toString())
                                        .success(function(data){                             
                                                    $scope.agregarUsuario = data;
                                                                                            
                                                });
                                                 $scope.nombresElegido = "";
                                $scope.apellidosElegido = "";
                                $scope.fechaNacimientoElegido =  0;
                                $scope.formacionElegido = 0;
                                $scope.loginElegido = 0;
                                $scope.condicionFisicaElegido = 0;
                                $scope.guiaElegido = false;
                                $scope.experienciaElegido = "";
                                
                                                
                                        $scope.saveUsuario = function (){
                                            $scope.agregarUsuario={
                                            "nombres": $scope.nombresElegido,
                                            "apellidos": $scope.apellidosElegido,
                                            "fechaNacimiento":$scope.fechaNacimientoElegido, 
                                            "formacion":$scope.formacionElegido,
                                            "login": $scope.loginElegido,
                                            "condicionFisica": $scope.condicionFisicaElegido,
                                            "guia": $scope.guiaElegido,
                                            "experiencia": $scope.experienciaElegido
                                            };
                                            putUsuario($scope.agregarUsuario);
                                            $state.go('usuariosList');
                                        };                                                                               
                                 }]
                                },
                    'detailView': {                       
                                templateUrl: basePath + 'usuarios.detail.html',        
                                resolve: {
                                        currentUsuario: ['$http','usuariosContext','$stateParams', function ($http,usuariosContext,$params) {
                                        return $http.get(usuariosContext+'/'+$params.usuarioId);
                                        }]
                                     },
                                        controller: ['$scope','currentUsuario', function ($scope,currentUsuario) {
                                        $scope.currentUsuario = currentUsuario.data;
                                        }]
                    }
                }
                
            }).state('agregarUsuario', {
                url: '/agregarUsuario',
                parent: 'usuarios',
                views: {
                    'listView': {
                        templateUrl: basePath + 'agregarUsuario.html',
                        resolve: {
                               
                                setUsuario: ["$http",function($http){
                                const adicionUsuario =  
                                function (agregarUsuario){
                                $http.post("api/usuarios/",agregarUsuario).success(function(data){
                                return data;
                                }).error(function(err){
                                return err;
                                });
                            }
                    return adicionUsuario;                  
               }]
                },
                controller: ['$scope', 'setUsuario', '$state', function ($scope,setUsuario,$state) {
                             
                                $scope.nombresElegido = "";
                                $scope.apellidosElegido = "";
                                $scope.fechaNacimientoElegido =  0;
                                
                                $scope.loginElegido = 0;
                                $scope.guiaElegido = false;
                                $scope.usuario = {}
                                $scope.saveUsuario = function(){                                    
                                    $scope.usuario = {
                                        
                                            "nombres": $scope.nombresElegido,
                                            "apellidos": $scope.apellidosElegido,
                                            "fechaNacimiento":$scope.fechaNacimientoElegido, 
                                            
                                            "login": $scope.loginElegido,
                                            "guia": $scope.guiaElegido
                      
                                        };
                                    setUsuario($scope.usuario);
                                    $state.go($state.current, {}, {reload: true});
                                    //$state.reload();
                                };
                            }]
                    }
                }
            }).state('administrarGuias', {
                url: '/administrarGuias',
                parent:"usuarios",
                resolve: {
                        deleteUsuario: ["$http","usuariosContext",function($http, usuariosContext){
                        const eliminarUsuario = function (id){
                        $http.delete(usuariosContext+"/"+id.toString()).success(function(data){
                        return data;
                    }).error(function(err){
                        return err;
                    });
                }
                    return eliminarUsuario;                    
               }]},               
                views: {
                'listView': {
                templateUrl: basePath + 'administrarGuias.html',
                controller: ['$scope', 'deleteUsuario','$state', function ($scope, deleteUsuario, $state, $window){                        
                            $scope.deleteUsuario = function(id){
                            deleteUsuario(id);
                            $state.reload();
                        };
                        
                }]
            }
            }
                
            }).state('eliminarUsuario', {
                url: '/eliminarUsuario',
                parent: 'usuarios',
                param: {
                    usuarioId: null
                },
                resolve: {
                    'listView': {
                        deleteUsuario: [ '$http','usuariosContext','$stateParams', function ($http,$params){
                                
                                  $http.delete(basePath + $params.usuarioId);  
                                 
                                
                        }
                            
                            
                            
                        ]
                    }
                }
                
            }).state('usuarioDetail', {
                url: '/{usuarioId:int}/detail',
                parent: 'usuarios',
                param: {
                    usuarioId: null
                },
                views: {
                     'listView': {
                        templateUrl: basePath + 'usuarios.list.html'
                        
                    },
                    
                    
                    'detailView': {
                        
                         resolve: {
                    currentUsuario: ['$http','usuariosContext','$stateParams', function ($http,usuariosContext,$params) {
                            const i =(usuariosContext+'/'+$params.usuarioId);
                            return $http.get(usuariosContext+'/'+$params.usuarioId)
                            
                            //return $http.get('data/usuarios.json');
                        }]
                },
                        templateUrl: basePath + 'usuarios.detail.html',
                        controller: ['$scope', 'currentUsuario', function ($scope, currentUsuario) {
                                $scope.currentUsuario = currentUsuario.data;
                            }]
                    }
                }
            });
        }]);
})(window.angular);
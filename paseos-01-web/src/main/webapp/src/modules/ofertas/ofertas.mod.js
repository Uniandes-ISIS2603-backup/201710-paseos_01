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
                            //return $http.get('data/ofertas.json');
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
                                return $http.get('api/usuarios');
                        }]
                },
                controller: ['$scope','catalogo','guias', function ($scope,catalogo,guias) {
                                $scope.catalogo = catalogo.data;
                                $scope.guias = guias.data;
                            }]
                    }
                }
            }).state('editarOferta', {
                url: '/editarOferta',
                parent: 'ofertas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'editOferta.html',
                        resolve: {
                                catalogo: ['$http', function ($http) {
                                return $http.get('api/paseos?catalogo=0');
                        }],
                                guias: ['$http', function ($http) {
                                return $http.get('api/usuarios');
                        }]
                },
                controller: ['$scope','catalogo','guias', function ($scope,catalogo,guias) {
                                $scope.catalogo = catalogo.data;
                                $scope.guias = guias.data;
                                $scope.paseoElegido = 0;
                                $scope.guiaElegido = 0;
                                //$scope.ok = function()
                            }]
                    }
                }
            }).state('administrarOfertas', {
                url: '/administrarOfertas',
                parent: 'ofertas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'adminOfertas.html'
                    }
                }
            }).state('ofertaDetail', {
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


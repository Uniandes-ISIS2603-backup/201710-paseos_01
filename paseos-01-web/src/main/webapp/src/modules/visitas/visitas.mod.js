(function (ng) {
    var mod = ng.module("visitasModule", ['ui.router']);
    mod.constant("visitasContext", "api/visitas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/visitas/';
            $urlRouterProvider.otherwise("/visitasList");

            $stateProvider.state('visitas', {
                url: '/visitas',
                abstract: true,
                resolve: {
                    visitas: ['$http','visitasContext', function ($http, visitasContext) {
                            return $http.get(visitasContext);
                            //return $http.get('data/visitas.json');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'visitas.html',
                        controller: ['$scope', 'visitas', function ($scope, visitas) {
                                $scope.visitasRecords = visitas.data;
                            }]
                    }
                }
            }).state('visitasList', {
                url: '/list',
                parent: 'visitas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'visitas.list.html'
                    }
                }
            }).state('addVisita', {
                url: '/addVisita',
                parent: 'visitas',
                 views: {
                    'listView': {
                        templateUrl: basePath + 'addOferta.html',
                        resolve: {
                                catalogo: ['$http', function ($http) {
                                return $http.get('api/ofertas');
                        }]}                
                    }
                }
            }).state('deleteVisita', {
                url: '/deleteVisita',
                parent: 'visitas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'deleteVisita.html',
                        resolve: {
                                catalogo: ['$http', function ($http) {
                                return $http.get('api/visitas');
                        }]}  
                    }
                }
            }).state('updateVisita', {
                url: '/updateVisita',
                parent: 'visitas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'updateVisita.html',
                        resolve: {
                                catalogo: ['$http', function ($http) {
                                return $http.get('api/visitas');
                        }]}  
                    }
                }
            }).state('visitasDetail', {
                url: '/{visitaId:int}/detail',
                parent: 'visitas',
                param: {
                    visitaId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'visitas.list.html'
                        
                    },
                    'detailView': {
                                      resolve: {
                    currentVisita: ['$http','visitasContext','$stateParams', function ($http,visitasContext,$params) {
                            return $http.get(visitasContext+'/'+$params.visitaId)
                            //return $http.get('data/ofertas.json');
                        }]
                },
                        templateUrl: basePath + 'visitas.detail.html',
                        controller: ['$scope', 'currentVisita', function ($scope, currentVisita) {
                                $scope.currentVisita = currentVisita.data;
                            }]
                    }

                }

            });
        }]);
})(window.angular);
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
                    ofertas: ['$http', function ($http) {
                            return $http.get('data/ofertas.json');
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
                        templateUrl: basePath + 'addOferta.html'
                    }
                }
            }).state('editarOferta', {
                url: '/editarOferta',
                parent: 'ofertas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'editOferta.html'
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
                        templateUrl: basePath + 'ofertas.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentOferta = $scope.ofertasRecords[$params.ofertaId-1];
                            }]
                    }

                }

            });
        }]);
})(window.angular);


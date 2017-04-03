(function (ng) {
    var mod = ng.module("visitasModule", ['ui.router']);
    mod.constant("visitasContext", "api/visitas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/visitas/';
            var basePathVisitas = 'src/modules/visitas/';
            $urlRouterProvider.otherwise("/visitasList");

            $stateProvider.state('visitas', {
                url: '/visitas',
                abstract: true,
                resolve: {
                    usuarios: ['$http', function ($http) {
                            return $http.get('data/visitas.json');
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
            }).state('visitasDetail', {
                url: '/{usuarioId:int}',
                parent: 'visitas',
                param: {
                    usuarioId: null
                },
                views: {
                     'listView': {
                        templateUrl: basePath + 'visitas.list.html'
                        
                    },
                    
                    'detailView': {
                        templateUrl: basePath + 'visitas.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentVisita = $scope.visitasRecords[$params.usuarioId - 1];
                            }]
                    }
                }
            });
        }]);
})(window.angular);
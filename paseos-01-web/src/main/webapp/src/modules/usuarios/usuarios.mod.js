(function (ng) {
    var mod = ng.module("usuarioModule", ['ui.router']);
    mod.constant("usuariosContext", "api/usuarios");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/usuarios/';
            var basePathUsuarios = 'src/modules/usuarios/';
            $urlRouterProvider.otherwise("/usuariosList");

            $stateProvider.state('usuarios', {
                url: '/usuarios',
                abstract: true,
                resolve: {
                    usuarios: ['$http', function ($http) {
                            return $http.get('data/usuarios.json');
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
                url: '/editarUsuario',
                parent: 'usuarios',
                views: {
                    'listView': {
                        templateUrl: basePath + 'editarUsuario.html'
                    }
                }
                
            }).state('agregarUsuario', {
                url: '/agregarUsuario',
                parent: 'usuarios',
                views: {
                    'listView': {
                        templateUrl: basePath + 'agregarUsuario.html'
                    }
                }
            }).state('administrarGuias', {
                url: '/administrarGuias',
                parent: 'usuarios',
                views: {
                    'listView': {
                        templateUrl: basePath + 'administrarGuias.html'
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
                        templateUrl: basePath + 'usuarios.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentUsuario = $scope.usuariosRecords[$params.usuarioId - 1];
                            }]
                    }
                }
            });
        }]);
})(window.angular);
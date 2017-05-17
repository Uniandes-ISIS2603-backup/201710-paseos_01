(function (ng) {
    const mod = ng.module('fotosModule', ['ui.router']);
    mod.constant('fotosContext', 'api/fotos');
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider',
        function ($stateProvider, $urlRouterProvider) {
            const basePath = 'src/modules/fotos/';
            $urlRouterProvider.otherwise('src/index.html');
            $stateProvider.state('fotos', {
                // Url que aparecerá en el browser
                url: '/fotos',
                abstract: true,
                resolve: {
                    fotos: ['$http', function ($http) {
                            return $http.get('/paseos-01-web/api/fotos');
                            // $http retorna una promesa 
                        }],
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'fotos.html',
                        controller: ['$scope', 'fotos', function ($scope, fotos) {
                                $scope.fotosRecords = fotos.data;
                            }],
                    },
                },
            }).state('fotosList', {
                url: '/list',
                parent: 'fotos',
                views: {
                    'listView': {
                        templateUrl: basePath + 'fotos.list.html',
                    },
                },
            }).state('fotosListUsuario', {
                url: '/listUsuario',
                parent: 'fotos',
                views: {
                    'listViewUsuario': {
                        templateUrl: basePath + 'fotos.list.vistausuario.html',
                    },
                    'addView': {
                        templateUrl: basePath + 'fotos.add.html',
                    },
                },
            }).state('fotoDetail', {
                url: '/{fotoId:int}/detail',
                parent: 'fotos',
                param: {
                    fotoId: null,
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'fotos.detail.html',
                        controller: ['$scope', '$stateParams',
                            function ($scope, $params) {
                                for (let i = 0; i < $scope.fotosRecords.length; i++) {
                                    if ($scope.fotosRecords[i].id == $params.fotoId) {
                                        $scope.currentFoto = $scope.fotosRecords[i];
                                    }
                                }
                            }],
                    },
                },
            }).state('fotoDetailUsuario', {
                url: '/{fotoId:int}/detailUsuario',
                parent: 'fotos',
                param: {
                    fotoId: null,
                },
                views: {
                    'detailViewUsuario': {
                        templateUrl: basePath
                                + 'fotos.detail.vistausuario.html',
                        controller: ['$scope', '$stateParams',
                            function ($scope, $params) {
                                for (let i = 0; i < $scope.fotosRecords.length; i++) {
                                    if ($scope.fotosRecords[i].id == $params.fotoId) {
                                        $scope.currentFoto = $scope.fotosRecords[i];
                                    }
                                }
                            }],
                    },
                    'addView': {
                        templateUrl: basePath + 'fotos.add.html',
                    },
                },
            }).state('eliminarFoto', {
                // Url que aparecerá en el browser
                url: '/deleteFoto',
                parent: 'fotos',
                resolve: {
                    deleteFoto: ['$http', '$stateParams', function ($http) {
                            return function (id) {
                                $http.delete('/paseos-01-web/api/fotos/'
                                        + id.toString()).success(function (data) {
                                    return data;
                                }).error(function (err) {
                                    return err;
                                });
                            }
                        }],
                },
                views: {
                    'listViewUsuario': {
                        templateUrl: basePath + 'fotos.list.vistausuario.html',
                        controller: ['$scope', 'deleteFoto', '$state',
                            function ($scope, deleteFoto, $state) {
                                $scope.deleteFoto = function (id) {
                                    deleteFoto(id);
                                    $state.reload();
                                };
                            }],
                    },
                    'addView': {
                        templateUrl: basePath + 'fotos.add.html',
                    },
                },
            }).state('addFoto', {
                // Url que aparecerá en el browser
                url: '/addFoto',
                parent: "fotos",

                resolve: {
                    setFoto: ['$http', function ($http) {
                            const adicionFoto =
                                    function (addFoto) {
                                        $http.post("/paseos-01-web/api/visitas/1/fotos", addFoto).success(function (data) {
                                            return data;
                                        }).error(function (err) {
                                            return err;
                                        });
                                    }
                            return adicionFoto;
                        }]
                },

                views: {
                    'addSuccessView': {
                        // Template que se utilizara para ejecutar el estado
                        templateUrl: basePath + 'fotos.add.success.html',
                        controller: ['$scope', 'setFoto', '$state', function ($scope, setFoto, $state) {
                                $scope.id = 0;
                                $scope.valor = "";
                                $scope.addFoto = {};
                                $scope.saveFoto = function () {
                                    $scope.addFoto = {
                                        "id": 1,
                                        "valor": $scope.valor
                                    }
                                    setFoto($scope.addFoto);
                                    $state.reload();
                                };

                            }]
                                // El controlador guarda en el scope en la variable booksRecords los datos que trajo el resolve
                                // booksRecords será visible en el template
                    }
                }})
        }]);
})(window.angular);

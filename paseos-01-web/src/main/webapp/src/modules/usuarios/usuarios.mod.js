(function (ng) {
    var mod = ng.module("usuariosModule", ['ui.router']);
    mod.constant("usuariosContext", "api/usuarios");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/usuarios/';
            var basePathBooks = 'src/modules/usuarios/';
            $urlRouterProvider.otherwise("/usuariosList");

            $stateProvider.state('usuarios', {
                url: '/usuarios',
                abstract: true,
                resolve: {
                    authors: ['$http', function ($http) {
                            return $http.get('data/usuarios.json');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'usuarios.html',
                        controller: ['$scope', 'usuarios', function ($scope, authors) {
                                $scope.authorsRecords = authors.data;
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
            }).state('usuarioDetail', {
                url: '/{usuarioId:int}/detail',
                parent: 'usuarios',
                param: {
                    authorId: null
                },
                views: {
                    'listView': {
                        resolve: {
                            books: ['$http', function ($http) {
                                    return $http.get('data/books.json');
                                }]
                        },
                        templateUrl: basePathBooks + 'books.list.html',
                        controller: ['$scope', 'books', '$stateParams', function ($scope, books, $params) {
                                $scope.booksRecords = books.data;
                                $scope.currentAuthor = $scope.authorsRecords[$params.authorId - 1];
                            }]
                    },
                    'detailView': {
                        templateUrl: basePath + 'authors.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentAuthor = $scope.authorsRecords[$params.authorId - 1];
                            }]
                    }
                }
            });
        }]);
})(window.angular);
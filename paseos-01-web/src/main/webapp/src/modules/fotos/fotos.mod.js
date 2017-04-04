(function(ng){
    var mod = ng.module("fotosModule", ['ui.router']);
    mod.constant("fotosContext", "api/fotos");
   // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
       var basePath = 'src/modules/fotos/';
       $urlRouterProvider.otherwise("src/index.html");
       $stateProvider.state('fotos', {
                // Url que aparecerá en el browser
                url: '/fotos',
                abstract: true,
                 resolve: {
                    fotos: ['$http', function ($http) {
                            return $http.get('/paseos-01-web/api/fotos'); // $http retorna una promesa que aquí no se está manejando si viene con error.
                        }]
                },
                views: {
                    'mainView':{
                        templateUrl: basePath + 'fotos.html',
                        controller: ['$scope','fotos',function($scope,fotos){
                                $scope.fotosRecords = fotos.data;
                        }]
                    }
                }             
            }).state('fotosList',{
                url: '/list',
                parent: 'fotos',
                views:{
                    'listView':{
                        templateUrl: basePath + 'fotos.list.html'
                    }
                }
            }).state('fotoDetail',{
                url: '/{fotoId:int}/detail',
                parent: 'fotos',
                param:{
                    fotoId: null
                },
                views: {
                    'detailView':{
                        templateUrl: basePath + 'fotos.detail.html',
                        controller: ['$scope','$stateParams',function($scope,$params){
                                for(var i=0; i<$scope.fotosRecords.length;i++){
                                    if($scope.fotosRecords[i].id == $params.fotoId){
                                        $scope.currentFoto = $scope.fotosRecords[i];
                                    }
                                }
                        }]
                    }
                }
            });
            
    }]);
})(window.angular);
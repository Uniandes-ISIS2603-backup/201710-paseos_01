(function(ng){
    //defincicón del modulo
    var mod = ng.module("fotoModule",['ui.router']);
     // Configuración de los estados del módulo
    mod.config(['$stateProvider','$urlRouterProvider',function($stateProvider, $urlRouterProvider){
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/fotos/';
            // Mostrar la lista de libros será el estado por defecto del módulo
            $urlRouterProvider.otherwhise("/fotos");
            // Definición del estado 'booksList' donde se listan los libros
            $stateProvider.state('fotos',{
                // Url que aparecerá en el browser
                url: '/fotos',
                 // Se define una variable books (del estado) que toma por valor 
                // la colección de libros que obtiene utilizando $http.get
                resolve: {
                    getFotos: ["&http",function($http) {
                            return $http.get("/paseos-01-web/api/fotos").success(function(data){
                                return data;
                            }).error(function(err){
                                return err;
                            });
                    }]
                },
                // Template que se utilizara para ejecutar el estado
                templateUrl: basePath + 'paseosCatalogo.html',
                // El controlador guarda en el scope en la variable fotos los datos que trajo el resolve
                // fotos será visible en el template
                controller: ['$scope','getFotos',function($scope,getFotos){
                        $scope.fotos = getFotos.data;
                }]
            });
    }]);
})(window.angular);
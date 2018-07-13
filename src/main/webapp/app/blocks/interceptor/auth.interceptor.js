(function() {
    'use strict';

    angular
        .module('testbedRegistryApp')
        .factory('authInterceptor', authInterceptor);

    authInterceptor.$inject = ['$rootScope', '$q', '$location', '$localStorage', '$sessionStorage','$cookies'];

    function authInterceptor ($rootScope, $q, $location, $localStorage, $sessionStorage, $cookies) {
        var service = {
            request: request
        };

        return service;

        function request (config) {
            /*jshint camelcase: false */
            
            config.headers = config.headers || {};
           
           console.log($cookies.get('JSESSIONID'));
            if($cookies.get('JSESSIONID') !== null) {
                console.log('remove....');
                $cookies.remove('JSESSIONID');
            }
     
            var token = $localStorage.authenticationToken || $sessionStorage.authenticationToken;
            if (token) {
                config.headers.Authorization = 'Bearer ' + token;
            }
            return config;
        }
    }
})();

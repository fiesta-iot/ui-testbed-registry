(function () {
    'use strict';

    angular
        .module('testbedRegistryApp')
        .factory('Register', Register);

    Register.$inject = ['$resource'];

    function Register ($resource) {
        return $resource('api/register', {}, {});
    }
})();

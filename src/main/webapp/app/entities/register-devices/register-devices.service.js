(function() {
    'use strict';
    angular
        .module('testbedRegistryApp')
        .factory('RegisterDevices', RegisterDevices);

    RegisterDevices.$inject = ['$resource'];

    function RegisterDevices ($resource) {
        var resourceUrl =  'api/register-devices/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();

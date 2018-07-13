(function() {
    'use strict';
    angular
        .module('testbedRegistryApp')
        .factory('RegisterDevicesManual', RegisterDevicesManual);

    RegisterDevicesManual.$inject = ['$resource'];

    function RegisterDevicesManual ($resource) {
        var resourceUrl =  'api/register-devices/manual';

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

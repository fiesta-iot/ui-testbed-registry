(function() {
    'use strict';
    angular
        .module('testbedRegistryApp')
        .factory('RegisterDevicesText', RegisterDevicesText);

    RegisterDevicesText.$inject = ['$resource'];

    function RegisterDevicesText ($resource) {
        var resourceUrl =  'api/register-devices/text';

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

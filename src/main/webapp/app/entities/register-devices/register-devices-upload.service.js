(function() {
    'use strict';
    angular
        .module('testbedRegistryApp')
        .factory('RegisterDevicesUpload', RegisterDevicesUpload);

    RegisterDevicesUpload.$inject = ['$resource'];

    function RegisterDevicesUpload ($resource) {
        var resourceUrl =  'api/register-devices/upload';

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

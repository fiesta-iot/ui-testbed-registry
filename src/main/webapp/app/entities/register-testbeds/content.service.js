(function() {
    'use strict';
    angular
        .module('testbedRegistryApp')
        .factory('ContentTypeService', ContentTypeService);

    ContentTypeService.$inject = ['$resource'];

    function ContentTypeService ($resource) {
        var resourceUrl =  'api/contentTypes/:id';

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

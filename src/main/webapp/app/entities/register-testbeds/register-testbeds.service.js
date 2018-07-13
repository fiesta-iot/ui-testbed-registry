(function () {
    'use strict';
    angular
            .module('testbedRegistryApp')
            .factory('RegisterTestbeds', RegisterTestbeds);

    RegisterTestbeds.$inject = ['$resource'];

    function RegisterTestbeds($resource) {
        var resourceUrl = 'api/register-testbeds/:id';

        return $resource(resourceUrl, {}, {
            'query': {method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'getAllTestbedsByCurrentUser': {
                url: 'api/getAllTestbedsByCurrentUser',
                method: 'GET',
                isArray: true
            },
            'validateAnnotatedResource': {
                url: 'api/register-testbeds/validateAnnotatedResource',
                method: 'POST',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }

            },
            'validateAnnotatedObseration': {
                url: 'api/register-testbeds/validateAnnotatedObseration',
                method: 'POST',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': {method: 'PUT'}
        },
                {headers: {'Cookie.JSESSIONID': null}}
        );
    }
})();

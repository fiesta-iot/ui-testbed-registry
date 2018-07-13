(function() {
    'use strict';

    angular
        .module('testbedRegistryApp')
        .controller('RegisterTestbedsDetailController', RegisterTestbedsDetailController);

    RegisterTestbedsDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'RegisterTestbeds'];

    function RegisterTestbedsDetailController($scope, $rootScope, $stateParams, previousState, entity, RegisterTestbeds) {
        var vm = this;

        vm.registerTestbeds = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('testbedRegistryApp:registerTestbedsUpdate', function(event, result) {
            vm.registerTestbeds = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

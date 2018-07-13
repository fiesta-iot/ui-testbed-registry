(function() {
    'use strict';

    angular
        .module('testbedRegistryApp')
        .controller('RegisterDevicesDetailController', RegisterDevicesDetailController);

    RegisterDevicesDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'RegisterDevices', 'RegisterTestbeds'];

    function RegisterDevicesDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, RegisterDevices, RegisterTestbeds) {
        var vm = this;

        vm.registerDevices = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('testbedRegistryApp:registerDevicesUpdate', function(event, result) {
            vm.registerDevices = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

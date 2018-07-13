(function() {
    'use strict';

    angular
        .module('testbedRegistryApp')
        .controller('RegisterDevicesDeleteController',RegisterDevicesDeleteController);

    RegisterDevicesDeleteController.$inject = ['$uibModalInstance', 'entity', 'RegisterDevices'];

    function RegisterDevicesDeleteController($uibModalInstance, entity, RegisterDevices) {
        var vm = this;

        vm.registerDevices = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            RegisterDevices.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

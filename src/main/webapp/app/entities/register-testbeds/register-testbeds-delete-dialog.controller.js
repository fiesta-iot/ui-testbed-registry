(function() {
    'use strict';

    angular
        .module('testbedRegistryApp')
        .controller('RegisterTestbedsDeleteController',RegisterTestbedsDeleteController);

    RegisterTestbedsDeleteController.$inject = ['$uibModalInstance', 'entity', 'RegisterTestbeds'];

    function RegisterTestbedsDeleteController($uibModalInstance, entity, RegisterTestbeds) {
        var vm = this;

        vm.registerTestbeds = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            RegisterTestbeds.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

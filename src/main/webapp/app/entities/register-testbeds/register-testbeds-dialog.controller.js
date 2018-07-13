(function () {
    'use strict';

    angular
            .module('testbedRegistryApp')
            .controller('RegisterTestbedsDialogController', RegisterTestbedsDialogController);

    RegisterTestbedsDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'RegisterTestbeds', 'ContentTypeService'];

    function RegisterTestbedsDialogController($timeout, $scope, $stateParams, $uibModalInstance, entity, RegisterTestbeds, ContentTypeService) {
        var vm = this;

        vm.registerTestbeds = entity;
        vm.clear = clear;
        vm.save = save;
        vm.contentTypes = ContentTypeService.query();

        $timeout(function () {
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear() {
            $uibModalInstance.dismiss('cancel');
        }

        function save() {
            vm.isSaving = true;
            if (vm.registerTestbeds.id !== null) {
                RegisterTestbeds.update(vm.registerTestbeds, onSaveSuccess, onSaveError);
            } else {
                RegisterTestbeds.save(vm.registerTestbeds, onSaveSuccess, onSaveError);
            }
        }

        $scope.validateAnnotatedResource = function () {
            var body = {
                annotatedObservation: vm.registerTestbeds.annotatedObservation,
                annotatedResourceDescription: vm.registerTestbeds.annotatedResourceDescription,
                contentType: vm.registerTestbeds.resourceDescriptionContentType

            };
            if (vm.registerTestbeds.annotatedResourceDescription != null && vm.registerTestbeds.resourceDescriptionContentType != null) {
                RegisterTestbeds.validateAnnotatedResource(body, function (data) {
                    alert(data.message);
                });

            } else {
                alert("Please select annotated resource content type and annotated resource description!");
            }

        }

        $scope.validateAnnotatedObseration = function () {
            console.log('vm.registerTestbeds:' + vm.registerTestbeds.annotatedResourceDescription);
            //alert("Annotated Observation is valid");
            var body = {
                annotatedObservation: vm.registerTestbeds.annotatedObservation,
                annotatedResourceDescription: vm.registerTestbeds.annotatedResourceDescription,
                contentType: vm.registerTestbeds.resourceObservationContentType

            };
            if (vm.registerTestbeds.annotatedObservation != null && vm.registerTestbeds.resourceObservationContentType != null) {
                RegisterTestbeds.validateAnnotatedObseration(body, function (data) {

                    alert(data.message);

                });
            } else {
                alert("Please select annotated observation and annotated observation content type!")
            }

        }

        function onSaveSuccess(result) {
            $scope.$emit('testbedRegistryApp:registerTestbedsUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError(result) {
            vm.isSaving = false;
            if (result !== null && !angular.isUndefined(result) && !angular.isUndefined(result.data) &&
                    !angular.isUndefined(result.data.errorMessage)) {
                alert(result.data.errorMessage);
            }
        }


    }
})();




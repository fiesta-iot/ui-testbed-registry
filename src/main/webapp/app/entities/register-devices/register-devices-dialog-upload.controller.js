(function () {


    angular
            .module('testbedRegistryApp')
            .controller('RegisterDevicesDialogUploadController', RegisterDevicesDialogUploadController);

    RegisterDevicesDialogUploadController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'RegisterDevices', 'RegisterTestbeds', 'UnitOfMesurements', 'QuantityKinds', 'RegisterDevicesManual','RegisterDevicesText','RegisterDevicesUpload','ContentTypeService'];

    function RegisterDevicesDialogUploadController($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, RegisterDevices, RegisterTestbeds, UnitOfMesurements, QuantityKinds, RegisterDevicesManual, RegisterDevicesText, RegisterDevicesUpload, ContentTypeService) {
        var vm = this;

        vm.registerDevices = entity;
        vm.registerDevicesManual = {};
        vm.clear = clear;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        vm.save = save;
        vm.registertestbeds = RegisterTestbeds.query();
        vm.quantityKinds = QuantityKinds.query();
        vm.uoms = UnitOfMesurements.query();
        vm.contentTypes = ContentTypeService.query();

        vm.datas = {
            quantityKind: null,
            unitofMesuarement: null,
            multipleSelect: [],
            option1: 'option-1'
        };

        var counter = 0;
        $scope.data = {
            fields: []
        }

        $timeout(function () {
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear() {
            $uibModalInstance.dismiss('cancel');
        }

        $scope.addmore = function () {

            $scope.data.fields.push({
                index: counter++
            });
            console.log($scope.data.fields.length);
        }


        function save() {
            vm.isSaving = true;
            if (vm.registerDevices.id !== null) {
                RegisterDevicesUpload.update(vm.registerDevices, onSaveSuccess, onSaveError);
            } else {
                RegisterDevicesUpload.save(vm.registerDevices, onSaveSuccess, onSaveError);
            }
        }

        $scope.validateAnnotatedResource = function () {
            var body = {
                annotatedObservation: "",
                annotatedResourceDescription: vm.registerDevices.annotatedResourceDescription,

                
            };
            
            RegisterTestbeds.validateAnnotatedResource(body, function (data) {
                alert(data.message);
            });

        }

        function onSaveSuccess(result) {
            $scope.$emit('testbedRegistryApp:registerDevicesUpdate', result);
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


        vm.setUploadContent = function ($file, registerDevices) {
            if ($file) {
                DataUtils.toBase64($file, function (base64Data) {
                    $scope.$apply(function () {
                        registerDevices.uploadContent = base64Data;
                        
                        registerDevices.uploadContentContentType = $file.type;
                    });
                });
            }
        };

    }
})();

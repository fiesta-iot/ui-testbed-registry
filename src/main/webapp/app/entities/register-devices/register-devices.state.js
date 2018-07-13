(function() {
    'use strict';

    angular
        .module('testbedRegistryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('register-devices', {
            parent: 'entity',
            url: '/register-devices?page&sort&search',
            data: {
               // authorities: ['ROLE_USER'],
                pageTitle: 'RegisterDevices'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/register-devices/register-devices.html',
                    controller: 'RegisterDevicesController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }]
            }
        })
        .state('register-devices-detail', {
            parent: 'entity',
            url: '/register-devices/{id}',
            data: {
               // authorities: ['ROLE_USER'],
                pageTitle: 'RegisterDevices'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/register-devices/register-devices-detail.html',
                    controller: 'RegisterDevicesDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'RegisterDevices', function($stateParams, RegisterDevices) {
                    return RegisterDevices.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'register-devices',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('register-devices-detail.edit', {
            parent: 'register-devices-detail',
            url: '/detail/edit',
            data: {
                //authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/register-devices/register-devices-dialog.html',
                    controller: 'RegisterDevicesDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RegisterDevices', function(RegisterDevices) {
                            return RegisterDevices.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('register-devices.new', {
            parent: 'register-devices',
            url: '/new',
            data: {
                //authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/register-devices/register-devices-dialog.html',
                    controller: 'RegisterDevicesDialogManualController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                serviceResponse: null,
                                uploadContent: null,
                                uploadContentContentType: null,
                                userID: null,
                                registerID: null,
                                annotatedResourceDescription: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('register-devices', null, { reload: 'register-devices' });
                }, function() {
                    $state.go('register-devices');
                });
            }]
        })
        .state('register-devices.text', {
            parent: 'register-devices',
            url: '/text',
            data: {
                //authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/register-devices/register-devices-dialog-text.html',
                    controller: 'RegisterDevicesDialogTextController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                serviceResponse: null,
                                uploadContent: null,
                                uploadContentContentType: null,
                                userID: null,
                                registerID: null,
                                annotatedResourceDescription: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('register-devices', null, { reload: 'register-devices' });
                }, function() {
                    $state.go('register-devices');
                });
            }]
        })
        .state('register-devices.upload', {
            parent: 'register-devices',
            url: '/upload',
            data: {
               // authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/register-devices/register-devices-dialog-upload.html',
                    controller: 'RegisterDevicesDialogUploadController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                serviceResponse: null,
                                uploadContent: null,
                                uploadContentContentType: null,
                                userID: null,
                                registerID: null,
                                annotatedResourceDescription: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('register-devices', null, { reload: 'register-devices' });
                }, function() {
                    $state.go('register-devices');
                });
            }]
        })
        .state('register-devices.edit', {
            parent: 'register-devices',
            url: '/{id}/edit',
            data: {
                //authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/register-devices/register-devices-dialog.html',
                    controller: 'RegisterDevicesDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RegisterDevices', function(RegisterDevices) {
                            return RegisterDevices.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('register-devices', null, { reload: 'register-devices' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('register-devices.delete', {
            parent: 'register-devices',
            url: '/{id}/delete',
            data: {
                //authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/register-devices/register-devices-delete-dialog.html',
                    controller: 'RegisterDevicesDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['RegisterDevices', function(RegisterDevices) {
                            return RegisterDevices.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('register-devices', null, { reload: 'register-devices' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

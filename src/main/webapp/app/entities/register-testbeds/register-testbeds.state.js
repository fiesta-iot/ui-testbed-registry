(function() {
    'use strict';

    angular
        .module('testbedRegistryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('register-testbeds', {
            parent: 'entity',
            url: '/register-testbeds?page&sort&search',
            data: {
                //authorities: ['ROLE_USER'],
                pageTitle: 'RegisterTestbeds'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/register-testbeds/register-testbeds.html',
                    controller: 'RegisterTestbedsController',
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
        .state('register-testbeds-detail', {
            parent: 'entity',
            url: '/register-testbeds/{id}',
            data: {
                //authorities: ['ROLE_USER'],
                pageTitle: 'RegisterTestbeds'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/register-testbeds/register-testbeds-detail.html',
                    controller: 'RegisterTestbedsDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'RegisterTestbeds', function($stateParams, RegisterTestbeds) {
                    return RegisterTestbeds.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'register-testbeds',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('register-testbeds-detail.edit', {
            parent: 'register-testbeds-detail',
            url: '/detail/edit',
            data: {
                //authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/register-testbeds/register-testbeds-dialog.html',
                    controller: 'RegisterTestbedsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RegisterTestbeds', function(RegisterTestbeds) {
                            return RegisterTestbeds.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('register-testbeds.new', {
            parent: 'register-testbeds',
            url: '/new',
            data: {
                //authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/register-testbeds/register-testbeds-dialog.html',
                    controller: 'RegisterTestbedsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                getApiKey: null,
                                getLastObservationsURL: null,
                                getObservationsURL: null,
                                iri: null,
                                latitude: null,
                                longitude: null,
                                name: null,
                                pushApiKey: null,
                                pushLastObservationsURL: null,
                                pushObservationsURL: null,
                                annotatedObservation: null,
                                annotatedResourceDescription: null,
                                resourceID: null,
                                resourceType: null,
                                registerID: null,
                                userID: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('register-testbeds', null, { reload: 'register-testbeds' });
                }, function() {
                    $state.go('register-testbeds');
                });
            }]
        })
        .state('register-testbeds.edit', {
            parent: 'register-testbeds',
            url: '/{id}/edit',
            data: {
               // authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/register-testbeds/register-testbeds-dialog.html',
                    controller: 'RegisterTestbedsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RegisterTestbeds', function(RegisterTestbeds) {
                            return RegisterTestbeds.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('register-testbeds', null, { reload: 'register-testbeds' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('register-testbeds.delete', {
            parent: 'register-testbeds',
            url: '/{id}/delete',
            data: {
                //authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/register-testbeds/register-testbeds-delete-dialog.html',
                    controller: 'RegisterTestbedsDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['RegisterTestbeds', function(RegisterTestbeds) {
                            return RegisterTestbeds.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('register-testbeds', null, { reload: 'register-testbeds' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

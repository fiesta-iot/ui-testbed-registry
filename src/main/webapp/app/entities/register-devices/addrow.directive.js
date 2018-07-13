

(function() {
    'use strict';

    angular
        .module('testbedRegistryApp')
        .directive('demoDisplay', demoDisplay);

    demoDisplay.$inject = ['$compile'];

    function demoDisplay($compile) {
        var directive = {
        		scope: {
      	          demoDisplay: "=", //import referenced model to our directives scope
      	          quantityKinds: "=",
      	          uoms:"="
      	        },
      	        
          template : '<br><div style="border:2px solid; border-color:#337ab7;padding:6px;"><div class="form-group">' +
          				   '<label style="color:#337ab7">Sensor  {{demoDisplay.index}} information</label>' +
          				   '</br>' + 
				          '<label for="field_quantitykinds">Sensor ID</label>' +
				      
				          '<input type="text" class="form-control" name="SensorID" id="field_SensorID"'+
	                        'ng-model="demoDisplay.id"'+
	                            'required />' +
	                    '<div ng-show="editFormManual.sensorID.$invalid">' +
	                        '<p class="help-block"'+
	                            'ng-show="editFormManual.sensorID.$error.required">'+
	                            'This field is required.'+
	                        '</p>' +
	                    '</div>' +
			         
			          '</div>' +
			          '<div class="form-group">' +
				          '<label for="field_latitude">Latitude</label>' +
				      
				          '<input type="text" class="form-control" name="Latitude" id="field_latitude"'+
	                        'ng-model="demoDisplay.lat"'+
	                            'required />' +
	                    '<div ng-show="editFormManual.latitude.$invalid">' +
	                        '<p class="help-block"'+
	                            'ng-show="editFormManual.latitude.$error.required">'+
	                            'This field is required.'+
	                        '</p>' +
	                    '</div>' +
			         
				       '</div>' +
				       '<div class="form-group">' +
				          '<label for="field_longitude">Longitude</label>' +
				      
				          '<input type="text" class="form-control" name="Longitude" id="field_field_longitude"'+
	                        'ng-model="demoDisplay.lon"'+
	                            'required />' +
	                    '<div ng-show="editFormManual.longitude.$invalid">' +
	                        '<p class="help-block"'+
	                            'ng-show="editFormManual.longitude.$error.required">'+
	                            'This field is required.'+
	                        '</p>' +
	                    '</div>' +
			         
				       '</div>' +
			          '<div class="form-group">' +
				          '<label for="field_quantitykinds">Quantity Kind</label>' +
				      
				          '<select class="form-control" id="field_quantitykinds" name="quantitykinds" ng-model="demoDisplay.qk" ng-options="quantitykind for quantitykind in quantityKinds" required> ' +
				              '<option value="">-- Select quantity kind --</option>' +
				          '</select>' +
				          '<div ng-show="editFormManual.quantitykinds.$invalid">' +
	                        '<p class="help-block"'+
	                            'ng-show="editFormManual.quantitykinds.$error.required">'+
	                            'This field is required.'+
	                        '</p>' +
	                    '</div>' +
		         
			          '</div>' +
				      '<div class="form-group">' +
				          '<label for="field_quantitykinds">Unit of Measurement</label>' +
				          '<select class="form-control" id="field_uoms" name="uoms" ng-model="demoDisplay.uom" ng-options="uom for uom in uoms" required>' +
				              '<option value="">-- Select Unit of Measurement --</option>' +
				          '</select>' +
				          '<div ng-show="editFormManual.uoms.$invalid">' +
	                        '<p class="help-block"'+
	                            'ng-show="editFormManual.uoms.$error.required">'+
	                            'This field is required.'+
	                        '</p>' +
	                    '</div>' +
				      '</div></div>',
            link : linkFunc
        };

        return directive;

        function linkFunc(scope, elem, attr, ctrl) {
            
        }
    }
})(); 

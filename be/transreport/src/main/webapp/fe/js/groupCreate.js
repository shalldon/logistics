"use strict";

angular.module('groupCreate',[])
.controller('groupCreateController',function($scope, $http, $injector){
	var $uibModal = $injector.get("$uibModal");

	$scope.openPop = function(){

		$uibModal.open({
	      animation: true,
	      ariaLabelledBy: 'modal-title',
	      ariaDescribedBy: 'modal-body',
	      template: 'testModal',
	      // controller: 'ModalInstanceCtrl',
	      // controllerAs: '$ctrl',
	      size: 'sm',
	      appendTo: $('#main-content')
   		})
	}

})
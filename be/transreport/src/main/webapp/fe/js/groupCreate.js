"use strict";

angular.module('groupCreate',[])
.controller('groupCreateController',function($scope, $http, $injector){

	var $uibModal = $injector.get("$uibModal");

	$scope.openPop = function(){

		$http({
			method: 'GET',
			url: '/createGroup',
			params:{groupName: $scope.groupName}
		}).then(function(res){
			$scope.groupID = res.data.groupID;
			$uibModal.open({
		      animation: true,
		      ariaLabelledBy: 'modal-title',
		      ariaDescribedBy: 'modal-body',
		      templateUrl: '/fe/views/groupCreateModal.html',
		      size: 'sm',
		      scope: $scope,
		      controllerAs: '$ctrl',
		      controller:function($scope,$uibModalInstance){
		      	this.ok = function(){
		      		$uibModalInstance.close();
		      	}
		      }
	   		})
		},function(){

		});
	};

	$scope.closeModal = function(){
		$uibModalInstance.close();
	}

})
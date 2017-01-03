"use strict";

angular.module('groupCreate',[])
.controller('groupCreateController',function($scope, $http, $injector){

	var $uibModal = $injector.get("$uibModal");
	var $location = $injector.get("$location");

	$scope.openPop = function(){				
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
	      		$http({
	    			method: 'POST',
	    			url: apiRoot + '/createGroup',
	    			data:{groupName: $scope.groupName}
	    		}).then(function(res){
	    			$location.path(apiRoot + "/group");
	    		},function(){

	    		});
	      		$uibModalInstance.close();
	      	}
	      }
   		})

		
	};

	$scope.closeModal = function(){
		$uibModalInstance.close();
	}
	
	$scope.backToGroup = function(){
		$location.path(apiRoot + "/group");
	}

})
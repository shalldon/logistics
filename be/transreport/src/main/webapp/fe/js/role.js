"use strict";

angular.module('role',[])
.controller('roleController',function($scope, $http, $injector){

	var $location = $injector.get("$location");

	$scope.selectRole = function(role){
		
		$http({
			method: 'POST',
			url: 'updateUser',
			data: {
				userName : $scope.userName,
				userRole : role
			}
		}).then(function(data){
			console.log(data)
			$location.path("/group");
		})		
	}

})
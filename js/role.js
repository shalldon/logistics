"use strict";

angular.module('role',[])
.controller('roleController',function($scope, $http, $injector){

	var $location = $injector.get("$location");

	$scope.selectRole = function(){
		$location.path("/group");
	}

})
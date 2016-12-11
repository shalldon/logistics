"use strict";

angular.module('login',[])
.controller('loginController',function($scope, $http, $injector){

	var $location = $injector.get("$location");

	$scope.login = function(){
		$location.path("/role")
	};

})
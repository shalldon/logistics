"use strict";

angular.module('group',[])
.controller('groupController',function($scope, $http, $injector){

	var $location = $injector.get("$location");

	$scope.groupList = [{
		label   : "林芝物流协同组1",
		desc	: "协同号001",
		id		: 1
	},
	{
		label   : "林芝物流协同组2",
		desc	: "协同号001",
		id		: 2
	},
	{
		label   : "林芝物流协同组3",
		desc	: "协同号001",
		id		: 3
	}];

	$scope.joinGroup = function(){
		$location.path("/groupJoin");
	}

	$scope.createGroup = function(){
		$location.path("/groupCreate");
	}

})
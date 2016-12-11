"use strict";

angular.module('group',[])
.controller('groupController',function($scope, $http, $injector){

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

})
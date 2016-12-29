"use strict";

angular.module('groupJoin',[])
.controller('groupJoinController',function($scope, $http, $injector){
	
	var $location = $injector.get("$location");

	var getGroupList = function(){
		$http({
			method: 'GET',
			url: '/getGroups'
		}).then(function(res){
			$scope.groupList = res.data.responseBody;			
		})
	}
	
	getGroupList();
	
	$scope.joinGroup = function(id){
		$http({
			method: 'GET',
			url: 'joinGroup',
			params: {
				groupId : id
			}
		}).then(function(data){
			console.log(data)
		})
	}
	
	$scope.backToGroup = function(){
		$location.path("/group");
	}
})
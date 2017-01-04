"use strict";

angular.module('group',[])
.controller('groupController',function($scope, $http, $injector){

	var $location = $injector.get("$location");
	var $rootScope = $injector.get("$rootScope");

	
	var getGroupList = function(){
		$http({
			method: 'GET',
			url: '/getGroups'
		}).then(function(res){
			$scope.groupList = res.data.responseBody;			
		})
	}
	
	getGroupList();

	$scope.joinGroup = function(){
		$location.path("/groupJoin");
	}

	$scope.createGroup = function(){
		$location.path("/groupCreate");
	}

	$scope.gotoGroup = function(group) {
		console.log(group)
		var id=group.id;
		var userLength = group.users.length;
		var groupName = group.groupName;
		$rootScope.group = group;
		
		var url = ["/groupHome/",id,"?users=",userLength,"&groupName=",groupName].join("")
		$location.path(url);
  }
	
	$scope.back = function(){
		$location.path("/join");
	}

})
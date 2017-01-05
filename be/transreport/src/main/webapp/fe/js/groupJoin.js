"use strict";

angular.module('groupJoin',[])
.controller('groupJoinController',function($scope, $http, $injector){
	
	var $location = $injector.get("$location");
	var $ionicPopup = $injector.get("$ionicPopup");

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
      $ionicPopup.alert({
        title: 'Success',
        template: '成功加入协同组，丫',
        okText: 'OK'
      }).then(function() {
        $scope.backToGroup();
      })
		})
	}
	
	$scope.backToGroup = function(){
		$location.path("/group");
	}
})
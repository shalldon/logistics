"use strict";

angular.module('userInvite',[])
.controller('userInviteController',function($scope, $http, $injector, $routeParams){
	
	var $ionicModal = $injector.get("$ionicModal");
	var $location = $injector.get("$location");
	var urlParams = $routeParams.id;
	var groupId = $routeParams.id.split('?')[0];
	var $ionicPopup = $injector.get("$ionicPopup");
	
	$scope.users = [];
	$scope.user = {};
	$scope.isError = false;
	
	$ionicModal.fromTemplateUrl('/fe/templates/invite-user-modal.html', {
	    scope: $scope,
	    animation: 'slide-in-up'
	  }).then(function(modal){
	    $scope.inviteUserModal = modal;
	  });

	
	$scope.addUser = function($ctrl){
		console.log($scope.user.phone)
		if(!$scope.user.phone){
			$scope.isError = true;
			return;
		}
		$scope.users.push({
			userName: $scope.user.userName,
			phone :$scope.user.phone
		})
		$scope.inviteUserModal.hide();
		$scope.user = {};	
	}
	
	$scope.invite = function(){
		$scope.isError = false;
		$scope.inviteUserModal.show();
	}
	
	$scope.removeUser = function(user){
		for(var i=0,len=$scope.users;i<len;i++){
			var userInList = $scope.users[i];
			if(userInList.name == user.name){
				$scope.users.splice(i,1);
			}
		}
	}
	
	$scope.inviteAll = function(){
		
		var phoneList = [];
		console.log($scope.users)
		for(var i=0,len=$scope.users.length;i<len;i++){
			console.log($scope.users[i])
			phoneList.push($scope.users[i].phone);
		}
		$http({
			method:"POST",
			url:"/inviteUser",
			data:{
				groupId : groupId,
				phoneList: phoneList
			}
		}).then(function(){

		 $ionicPopup.alert({
       title: 'Success',
       template: '邀请成功，丫',
       okText: 'OK'
     }).then(function(){
     	$scope.back();
     })
			
		})
	}
	
	$scope.back = function(){
		$location.path(["/groupHome/",urlParams].join(""));
	}

})
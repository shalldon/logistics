"use strict";

angular.module('login',[])
.controller('loginController',function($scope, $http, $injector){
	
	var $http = $injector.get("$http");
	var $location = $injector.get("$location");
	var $rootScope = $injector.get("$rootScope");

	$scope.error = '';

	$scope.getValidateCode = function(){
		$http({method:'GET',
			   url: '/requestValidateCode',
			   params:{
				   		phoneNumber : $scope.phoneNumber
			   		}
			  }).then(function(res){
				  if(res.data.error){
				  	$scope.error = res.data.error;
				  }
			  })
	}
	
	$scope.login = function(){
		$http({method: 'POST',
			   url: 'login',
			   data:{
				   phoneNumber : $scope.phoneNumber,
				   validateCode: $scope.validateCode
			   }
			 }).then(function(res){
				 var status = res.data.status;
				 var body = res.data.responseBody;
				 $rootScope.user = body;
				 if(status == "SUCCESS"){
					 if(!body.userRole){
						 $location.path("/role");
					 }else{
						 $location.path("/group");
					 }					 
				 }else{
				 	$scope.error = res.data.error;
				 }	
			 },function(res){
			 		$scope.error = res.data.error;
			 })
	}

})
"use strict";

angular.module('login',[])
.controller('loginController',function($scope, $http, $injector){
	
	var $http = $injector.get("$http");
	var $location = $injector.get("$location");

	$scope.getValidateCode = function(){
		$http({method:'GET',
			   url: apiRoot + '/requestValidateCode',
			   params:{
				   		phoneNumber : $scope.phoneNumber
			   		}
			  }).then(function(data){
				  console.log(data)
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
				 if(status == "SUCCESS"){
					 $location.path("/role");
				 }			
			 },function(){
			 })
	}

})
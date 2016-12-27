"use strict";

angular.module('login',[])
.controller('loginController',function($scope, $http, $injector){
	
	var $http = $injector.get("$http");
	var $location = $injector.get("$location");

	// $scope.login = function(){
	// 	$location.path("/role");
	// };
	
	$scope.getValidateCode = function(){
		console.log(1111)
		$http({method:'GET',
			   url: ['/requestValidateCode',['phoneNumber',$scope.phoneNumber].join("=")].join("?")
//			   data:{
//				   		phoneNumber : $scope.phoneNumber
//			   		}
			  }).then(function(data){
				  console.log(data)
			  })

//		$http.get("/requestValidateCode",{data:{phoneNumber : $scope.phoneNumber}})
//		.then(function(data){
//			console.log(data)
//		})
	}
	
	$scope.login = function(){
		$http({method: 'POST',
			   url: 'login',
			   data:{
				   phoneNumber : $scope.phoneNumber,
				   validateCode: $scope.validateCode
			   }
			 }).then(function(data){
				  console.log(data)
			 },function(){
			 	$location.path("/role");
			 })
	}

})
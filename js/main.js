"use strict";

angular.module('main', ['ngRoute','login','role'])
.config(function($routeProvider){
	less.watch();
	$routeProvider
	.when('/login', {
		templateUrl: 'login.html',
		controller: 'loginController'
	})
	.when('/role',{
		templateUrl: 'roleSelect.html',
		controller: 'roleController'
	})
	.otherwise({
		redirectTo: '/login'
	});
})
.controller('mainController',function($scope, $http){


})
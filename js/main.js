"use strict";

angular.module('main', ['ngRoute','login','role','group'])
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
	.when('/group',{
		templateUrl: 'group.html',
		controller: 'groupController'
	})
	.otherwise({
		redirectTo: '/login'
	});
})
.controller('mainController',function($scope, $http){


})
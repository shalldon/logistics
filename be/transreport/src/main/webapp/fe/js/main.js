"use strict";

angular.module('main', ['ngRoute','ui.bootstrap','login','role','group','groupCreate',"groupJoin"])
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
		templateUrl: 'groupMain.html',
		controller: 'groupController'
	})
	.when('/groupCreate',{
		templateUrl: 'groupCreate.html',
		controller: 'groupCreateController'
	})
	.when('/groupJoin',{
		templateUrl: 'groupJoin.html',
		controller: 'groupJoinController'
	})
	.otherwise({
		redirectTo: '/login'
	});
})
.controller('mainController',function($scope, $http){


})
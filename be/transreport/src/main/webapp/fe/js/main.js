"use strict";

angular.module('main', ['ngRoute','ui.bootstrap','login','role','group','groupCreate',"groupJoin"])
.config(function($routeProvider){
	less.watch();
	$routeProvider
	.when('/login', {
		templateUrl: '/fe/views/login.html',
		controller: 'loginController'
	})
	.when('/role',{
		templateUrl: '/fe/views/roleSelect.html',
		controller: 'roleController'
	})
	.when('/group',{
		templateUrl: '/fe/views/groupMain.html',
		controller: 'groupController'
	})
	.when('/groupCreate',{
		templateUrl: '/fe/views/groupCreate.html',
		controller: 'groupCreateController'
	})
	.when('/groupJoin',{
		templateUrl: '/fe/views/groupJoin.html',
		controller: 'groupJoinController'
	})
	.otherwise({
		redirectTo: '/login'
	});
})
.controller('mainController',function($scope, $http){


})
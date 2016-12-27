"use strict";

angular.module('main', ['ngRoute','ui.bootstrap','group','login','role','group','groupCreate',"groupJoin",'groupHome'])
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
	.when('/group/:id/home',{
		templateUrl: '/fe/views/groupHome.html',
		controller: 'groupHomeController'
	})
	.otherwise({
		redirectTo: '/login'
	});
})
.controller('mainController',function($scope, $http){


})
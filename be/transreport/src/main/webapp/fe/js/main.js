"use strict";

angular.module('main', ['ngRoute','ui.bootstrap','ionic','group','login','role','group','groupCreate',"groupJoin",'groupHome','userInvite'])
.config(function($routeProvider){
	less.watch();
	$routeProvider
	.when(apiRoot + '/login', {
		templateUrl: '/fe/views/login.html',
		controller: 'loginController'
	})
	.when(apiRoot + '/role',{
		templateUrl: '/fe/views/roleSelect.html',
		controller: 'roleController'
	})
	.when(apiRoot + '/group',{
		templateUrl: '/fe/views/groupMain.html',
		controller: 'groupController'
	})
	.when(apiRoot + '/groupCreate',{
		templateUrl: '/fe/views/groupCreate.html',
		controller: 'groupCreateController'
	})
	.when(apiRoot + '/groupJoin',{
		templateUrl: '/fe/views/groupJoin.html',
		controller: 'groupJoinController'
	})
	.when(apiRoot + '/groupHome/:id',{
		templateUrl: '/fe/views/groupHome.html',
		controller: 'groupHomeController'
	})
	.when(apiRoot + '/userInvite/:id',{
		templateUrl: '/fe/views/userInvite.html',
		controller: 'userInviteController'
	})
	.otherwise({
		redirectTo: apiRoot + '/login'
	});
})
.controller('mainController',function($scope, $http){


})
"use strict";

angular.module('groupJoin',[])
.controller('groupJoinController',function($scope, $http, $injector){
//	$scope.groupList = [{label: '物流协同组1',desc: '协同号001'},
//						{label: '物流协同组2',desc: '协同号002'},
//						{label: '物流协同组3',desc: '协同号003'}
//					   ]
	var getGroupList = function(){
		$http({
			method: 'GET',
			url: '/getGroups'
		}).then(function(res){
			$scope.groupList = res.data.responseBody;			
		})
	}
	
	getGroupList();
	
	$scope.joinGroup = function(id){
		$http({
			method: 'GET',
			url: 'joinGroup',
			params: {
				groupId : id
			}
		}).then(function(data){
			console.log(data)
		})
	}
})
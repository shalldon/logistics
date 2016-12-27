"use strict";

angular.module('groupJoin',[])
.controller('groupJoinController',function($scope, $http, $injector){
	$scope.groupList = [{label: '物流协同组1', desc: '协同号001'},
						{label: '物流协同组2', desc: '协同号002'},
						{label: '物流协同组3', desc: '协同号003'}
					   ]

})
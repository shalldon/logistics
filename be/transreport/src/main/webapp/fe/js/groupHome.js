"use strict";

angular.module('group',[])
.controller('groupHomeController',function($scope, $routeParams, $http, $injector){
  console.log("access group/" + $routeParams.id);
});

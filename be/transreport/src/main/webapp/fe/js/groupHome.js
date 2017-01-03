"use strict";

angular.module('groupHome',[])
.controller('groupHomeController',function($scope, $routeParams, $http, $injector, $ionicActionSheet, $ionicModal){
  console.log("access group/" + $routeParams.id);
  var $location = $injector.get("$location");
  
  $scope.message = {};
  $scope.messages = [];

  $ionicModal.fromTemplateUrl('/fe/templates/say-something-modal.html', {
    scope: $scope,
    animation: 'slide-in-up'
  }).then(function(modal){
    $scope.saySomethingModal = modal;
  });
  
  var details = $routeParams.id.split("?");
  $scope.id = details[0];
  var params = details[1].split("&");
  $scope.users = params[0].split("=")[1];
  $scope.groupName = params[1].split("=")[1];
  
  $http({
	  method:"GET",
	  url:"/listAllEvents",
	  params:{
		  groupId : $scope.id
	  }
  }).then(function(res){
	  var list = res.data.responseBody;
	  angular.forEach(list,function(item){
		  $scope.messages.push({
			  date : item.createdAt,
			  text : item.content
		  })
	  })
	  if(res.data.error){
		  $scope.error = res.data.error;
	  }
  })
  
  $scope.sendSaySomthing = function(text) {
    var date = new Date();
    $scope.message.date = date.toDateString();	
	 
	$http({
		method: "POST",
		url: "/createTextEvent",
		data: {
			content : text,
			groupId : $routeParams.id
		}
	}).then(function(){
		$scope.messages.push({
			text :text,
			date : date.toDateString()
		});
	})

    $scope.saySomethingModal.hide();
  };
  
  $scope.backToGroup = function(){
	$location.path("/group");
  }

  const GROUP_ACTIONS = {
    SEND_LOCATION: 0,
    SAY_SOMETHING: 1,
    SEND_RED_PACKET: 2
  };

  $scope.showAction = function() {
    var hideSheet = $ionicActionSheet.show({
      buttons: [
        {text: '<i class="icon ion-arrow-shrink"></i> 位置'},
        {text: '<i class="icon ion-navicon"></i> 说两句'}
      ],
      cancelText: '取消',
      cancel: function() {
        hideSheet();
      },
      buttonClicked: function(index) {
        console.log("action index:", index);

        switch (index) {
          case GROUP_ACTIONS.SEND_LOCATION:
            console.log("Action: SEND LOCATION");
            break;
          case GROUP_ACTIONS.SAY_SOMETHING:
            console.log("Action: SAY SOMETHING");
            $scope.saySomethingModal.show();
            break;
          case GROUP_ACTIONS.SEND_RED_PACKET:
            console.log("Action: SEND RED PACKET");
            break;
        }

        return true;
      }
    });
  };
  
  $scope.gotoInvite = function(){
	  $location.path(["/userInvite/",$routeParams.id].join(""));
  }
});

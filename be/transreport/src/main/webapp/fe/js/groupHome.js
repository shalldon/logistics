"use strict";

angular.module('groupHome',[])
.controller('groupHomeController',function($scope, $routeParams, $http, $injector, $ionicActionSheet, $ionicModal){
  console.log("access group/" + $routeParams.id);
  var $location = $injector.get("$location");
  var $rootScope = $injector.get("$rootScope");
  
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
  $scope.user = $rootScope.user;


  function loadMessages() {
      $http({
          method: "GET",
          url: "/listAllEvents",
          params: {
              groupId: $scope.id
          }
      }).then(function(res) {
          var list = res.data.responseBody;
          angular.forEach(list, function(item) {
              $scope.messages.push({
                  type: item.eventType == "REPORT_POSITION" ? "location" : "text",
                  date: item.createdAt,
                  text: item.content,
                  createBy: item.createdBy.id,
                  id: item.id,
                  user: item.createdBy.userName,
                  requestId: item.reportPositionRequest.id
              })

          })
          if (res.data.error) {
              $scope.error = res.data.error;
          }
      })
  }
  
  loadMessages();
  
  
  $scope.requireLocation = function(){
	  console.log($rootScope.user)
	  console.log($rootScope.group)
	  var date = new Date();
	  var group = $rootScope.group;
	  var users = [];
	  
	  angular.forEach(group.users,function(userObj,i){
		  users.push(userObj.user.id);
	  });

	  $http({
		  method: 'POST',
		  url: '/createPositionEvent',
		  data:{
			  content:'',
			  groupId:group.id,
			  userIds :users,
			  hasRedEnvelop: false,
			  totalValue:0,
			  totalSize:0
		  }
	  }).then(function(){
		  loadMessages();
	  })
  }
  
  $scope.sendSaySomthing = function(text) {
    var date = new Date();
	 
	$http({
		method: "POST",
		url: "/createTextEvent",
		data: {
			content : text,
			groupId : $routeParams.id
		}
	}).then(function(){
		loadMessages();
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
            $scope.requireLocation();
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

  $scope.refreshLocation = function(id){
    $http({
      method : "GET",
      url: "/getEvent",
      params:{
        eventId : id
      }
    }).then(function(res){
    	var requests = res.data.responseBody.reportPositionRequest;
    	$scope.locations[id]=requests.actions;
    	$scope.answeredRequest = requests.answeredRequest;
    })
  }

  $scope.reportPosition = function(id){
    MapUtil.getCurrentLocation().then(function(data) {
      var location = data;
      MapUtil.getAddressListByLocations([
          data
      ]).then(function(data) {
          var address = data[0];
          $http({
            method : 'POST',
            url: '/reportPosition',
            data: {
              requestId : id,
              positionX: location[0],
              positionY: location[1],
              address: address
            }
          }).then(function(){

          })
      })
    })
  }
  

  
  $scope.gotoInvite = function(){
	  $location.path(["/userInvite/",$routeParams.id].join(""));
  }
});

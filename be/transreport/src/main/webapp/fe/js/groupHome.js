"use strict";

angular.module('groupHome',['ionic'])
.controller('groupHomeController',function($scope, $routeParams, $http, $injector, $ionicActionSheet, $ionicModal){
  console.log("access group/" + $routeParams.id);

  $ionicModal.fromTemplateUrl('say-something-modal-orig.html', {
    scope: $scope,
    animation: 'slide-in-up'
  }).then(function(modal){
    $scope.saySomethingModal = modal;
  });

  const GROUP_ACTIONS = {
    SEND_LOCATION: 0,
    SAY_SOMETHING: 1,
    SEND_RED_PACKET: 2
  };

  $scope.showAction = function() {
    var hideSheet = $ionicActionSheet.show({
      buttons: [
        {text: '<i class="icon ion-arrow-shrink"></i> 位置'},
        {text: '<i class="icon ion-navicon"></i> 说两句'},
        {text: '<i class="icon ion-heart"></i> 发红包'}
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
});

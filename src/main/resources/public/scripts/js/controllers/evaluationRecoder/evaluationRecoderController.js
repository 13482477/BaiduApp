/**
 * Created by jhonelee on 2017/9/27.
 */
define(
    [
        '../module'
    ],
    function (ctrlApp) {
        'use strict';

        ctrlApp.controller('evaluationRecoderController', function ($scope, $http) {
            $scope.evaluations = [];
            $scope.loadData = function () {
                $http({
                    method : 'GET',
                    url : '/api/v1/evaluations',
                    params : {
                        openid : 'helloworld',
                        lastId : $scope.evaluations.length == 0 ? 0 : evaluations[evaluations.length - 1].id,
                        showCount : 10
                    }
                }).then(function successCallback(response) {
                    if (response.data.returnCode == '200') {
                        $scope.evaluations = response.data.data;
                    }
                    else {
                        console.log(response.data.returnMessage);
                    }
                }, function errorCallback(response) {

                });
            }

            $scope.loadData();
        });
    }
);

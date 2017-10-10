/**
 * Created by jhonelee on 2017/9/18.
 */
define(
    ['../module'],
    function (ctrlApp) {
        'use strict';

        ctrlApp.controller('evaluationController', function ($scope, $http, evaluationService) {
            $scope.message = '评估结果为0-100分!';

            $scope.submitForm = function () {

                $scope.mask = true;

                $http({
                    method : 'POST',
                    url : '/api/v1/evaluation/evaluate',
                    data : {
                        username : $scope.username,
                        mobile : $scope.mobile
                    }
                }).then(function successCallback(response) {
                    $scope.mask = false;
                    if (response.data.returnCode == "200") {
                        $scope.messageObject.title = '您是第10000次使用次功能的用户!';
                        $scope.messageObject.message = '您本次为手机号' + response.data.data.mobile +
                            '客户的投保综合评估的分值结果为' +
                            response.data.data.score +
                            '分，在所有的评分记录中，处在了前10%的位置，属于高意图用户！';
                        $scope.messageObject.messageClass = 'highlight';
                    }
                }, function errorCallback(response) {
                    $scope.mask = false;
                    $scope.messageObject.title = '网络传输错误!';
                    $scope.messageObject.messageClass = 'highlight';
                });
            }
        });
    }
)

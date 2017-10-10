/**
 * Created by jhonelee on 2017/9/13.
 */
define(['app'],
    function(app) {
        'use strict';
        return app.config(
            function config($urlRouterProvider, $stateProvider, $locationProvider) {
                $urlRouterProvider
                    .when("/", "/insuranceEvaluation/index").otherwise('/evaluationRecoder/index')
                    ;
                $stateProvider
                    .state('evaluationIndex', {
                        title : '投保综合评估首页',
                        url : '/insuranceEvaluation/index',
                        templateUrl : 'views/features/insuranceEvaluation/index.html'
                    })
                    .state('evaluationProcess', {
                        title : '投保综合评估',
                        url : '/insuranceEvaluation/evaluation',
                        templateUrl : 'views/features/insuranceEvaluation/evaluation.html'
                    })
                    .state('evaluationRecoder', {
                        title : '投保评估记录',
                        url : '/evaluationRecoder/index',
                        templateUrl : 'views/features/evaluationRecoder/index.html'
                    });

                $locationProvider.html5Mode(true);
            }
        );
    });
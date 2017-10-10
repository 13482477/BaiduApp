/**
 * Created by jhonelee on 2017/7/28.
 */
define([
        'require',
        'jquery',
        'angular',
        'uiRouter',
        'animate',
        'touch',
        'uiBootstrap',
        'domReady',
        'router',
        'app'
    ],
    function (require, jquery, angular) {
        require(['domReady!'], function(document) {
            angular.bootstrap(document, ['app']);
        });
    }
);
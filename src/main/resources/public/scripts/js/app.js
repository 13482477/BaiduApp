/**
 * Created by jhonelee on 2017/7/31.
 */
define(
    [
        'angular',
        'directives/imports',
        'controllers/imports',
        'services/imports'
    ],
    function(angular) {
        'use strict';
        return angular.module('app', ['ui.router', 'ui.bootstrap', 'app.directives', 'app.controllers', 'app.services']);
    }
);

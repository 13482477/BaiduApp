require.config({
    baseUrl : 'scripts/js',
    paths : {
        'angular'       :   '/bower_components/angular/angular.min',
        'uiRouter'      :   '/bower_components/angular-ui-router/release/angular-ui-router.min',
        'animate'       :   '/bower_components/angular-animate/angular-animate',
        'touch'         :   '/bower_components/angular-touch/angular-touch',
        'uiBootstrap'   :   '/bower_components/angular-bootstrap/ui-bootstrap',
        'jquery'        :   '/bower_components/jquery/dist/jquery',
        'domReady'      :   '/bower_components/domReady/domReady',
        'router'        :   'router',
        'app'           :   'app',
        'log'           :   'log',
        'start'         :   'start'
    },
    shim : {
        'angular'       : {
            exports         :   'angular'
        },
        'uiRouter'      : {
            deps            :   ['angular'],
            exports         :   'uiRouter'
        },
        'animate'       : {
            deps            :   ['angular'],
            exports         :   'animate'
        },
        'touch'         : {
            deps            :   ['angular'],
            exports         :   'touch'
        },
        'uiBootstrap'   : {
            deps            :   ['angular', 'animate', 'touch'],
            exports         :   'uiBootstrap'
        },
        'jquery'    : {
            exports :   'jquery'
        }
    },
    deps : [
        'start'
    ]
});
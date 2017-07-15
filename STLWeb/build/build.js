/*
 * Copyright (c) 2015 by Marfeel Solutions (http://www.marfeel.com)
 * All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Marfeel Solutions S.L and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Marfeel Solutions S.L and its
 * suppliers and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Marfeel Solutions SL.
 */

({
    paths: {
        angular: './../src/main/external/js/angular.min',
        'angular-bootstrap': './../src/main/external/js/ui-bootstrap.min',
        'angular-bootstrap-tpls': './../src/main/external/js/ui-bootstrap-tpls.min'
    },
    shim: {
        angular: {
            exports: 'angular'
        },
        'angular-bootstrap': {
            deps: ['angular']
        },
        'angular-bootstrap-tpls': {
            deps: ['angular-bootstrap']
        }
    },

    exclude: ["angular", "angular-bootstrap", "angular-bootstrap-tpls"],
    name: "app",
    baseUrl: './../src/main/js',
    out: "./../src/main/resources/META-INF/resources/js/app.js"
})
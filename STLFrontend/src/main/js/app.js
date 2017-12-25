import angular from 'angular';


export default {
    init () {
        console.log("--------------------");
        console.log("App Running");
        console.log("--------------------");

        angular.module('app', []);
        angular.bootstrap(document, ['app']);
    }
};

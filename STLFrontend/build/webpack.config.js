
var path = require('path');

var projectRoot = __dirname + "/../src/main/js/";

module.exports = {
    resolve: {
        alias: {},
        "root": projectRoot
    },
    resolveLoader: {
        root: __dirname + "/node_modules"
    },
    module: {
        loaders: [
            { test: /\.js$/, exclude: /node_modules|webapp\/resources/, loader: "babel-loader"}
        ]
    },
    output: {
        filename: "[name].js"
    }
};
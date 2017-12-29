
var path = require('path');

var projectRoot = __dirname + "/../src/main/js/";

module.exports = {
    resolve: {
        "root": projectRoot,
        extensions: ['', '.webpack.js', '.web.js', '.ts', '.tsx', '.js']
    },
    resolveLoader: {
        root: __dirname + "/node_modules"
    },
    module: {
        loaders: [
            { test: /\.js$/, exclude: /node_modules|webapp\/resources/, loader: "babel-loader"},
            { test: /[\/]angular\.min\.js$/, loader: "exports?window.angular" }
        ]
    },
    output: {
        filename: "[name].js"
    }
};
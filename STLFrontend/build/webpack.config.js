var path = require('path'),
    projectRoot = __dirname + "/../src/main/js/";

module.exports = {
    entry: {
        polyfills: projectRoot + "polyfills.ts",
        main: projectRoot + "main.ts"
    },
    resolve: {
        root: [
            path.resolve(projectRoot),
            path.resolve(__dirname + "/node_modules")
        ],
        modules: [
            path.resolve(__dirname + "/node_modules")
        ],
        extensions: ['', '.webpack.js', '.web.js', '.ts', '.tsx', '.js', '.json']
    },
    resolveLoader: {
        root: [path.resolve(__dirname + "/node_modules")]
    },
    module: {
        loaders: [
            { test: /\.ts$/, loader: "babel-loader", exclude: /node_modules|webapp\/resources/ },
            { test: /\.ts$/, loader: "ts-loader", exclude: /node_modules|webapp\/resources/ }
        ],
    },
    output: {
        filename: "[name].js"
    }
};
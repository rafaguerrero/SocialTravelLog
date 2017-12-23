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

var path = require('path');
var projectRoot = __dirname + "/../src/main/js/";

module.exports = {
    resolve: {
        alias: {},
        "root": projectRoot,
        modules: [
            path.resolve(__dirname, 'node_modules')
        ]
    },
    resolveLoader: {
        root: __dirname + "/node_modules"
    },
    module: {
        loaders: []
    },
    output: {
        filename: "[name].js"
    }
}
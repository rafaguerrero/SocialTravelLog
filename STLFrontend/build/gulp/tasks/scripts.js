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

var gulp = require('gulp'),
	webpackStream = require('webpack-stream'),
	webpack = require('webpack'),
	plumber = require('gulp-plumber'),
	errorHandler = require('../utils/handle-error'),
	config = require('../config').js,
	gutil = require('gulp-util'),
	argv = require('yargs').argv;
var path = require('path');

gulp.task('scripts', [], function() {
	var webpackConfig = require(config.config);

	return gulp.src(config.optimize)
		.pipe(plumber(errorHandler))
		.pipe(webpackStream(webpackConfig))
		.pipe(gulp.dest(config.dest));
});

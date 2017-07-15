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
	minifycss = require('gulp-minify-css'),
	sass = require('gulp-sass'),
	autoprefixer = require('gulp-autoprefixer'),
	rename = require('gulp-rename'),
	plumber = require('gulp-plumber'),
	errorHandler = require('../utils/handle-error'),
	config = require('../config').sass,
	sourcemaps = require('gulp-sourcemaps');

// Compile, add vendor prefixes and minify sass.
gulp.task('web-styles', function() {
	return gulp.src(config.source)
		.pipe(plumber(errorHandler))
		.pipe(sourcemaps.init())
		.pipe(sass())
		.pipe(autoprefixer({
			browsers: 'last 2 versions',
			cascade: true
		}))
		.pipe(gulp.dest(config.dest))
		.pipe(rename({suffix: '.min'}))
		.pipe(minifycss())
		.pipe(sourcemaps.write())
		.pipe(gulp.dest(config.dest));
});
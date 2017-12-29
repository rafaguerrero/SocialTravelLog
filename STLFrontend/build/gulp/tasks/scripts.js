/*var gulp = require('gulp'),
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
*/


var gulp = require("gulp"),
    ts = require("gulp-typescript"),
    config = require('../config').js,
    tsProject = ts.createProject("tsconfig.json");

gulp.task("default", function () {
    return tsProject.src()
                    .pipe(tsProject()).js
                    .pipe(gulp.dest(config.dest));
});
var gulp = require('gulp'),
    config = require('../config').js,
    webpackConfig = require(config.config);

var plumber = require('gulp-plumber'),
    errorHandler = require('../utils/handle-error');

var webpack = require('webpack'),
    webpackStream = require('webpack-stream');

gulp.task('scripts', [], function() {
	var webpackConfig = require(config.config);

	return gulp.src(config.source)
                .pipe(plumber(errorHandler))
                .pipe(webpackStream(webpackConfig))
                .pipe(gulp.dest(config.dest));
});
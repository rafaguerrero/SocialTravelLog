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
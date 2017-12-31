var gulp = require('gulp'),
    plumber = require('gulp-plumber'),
    errorHandler = require('../utils/handle-error'),
    concat = require('gulp-concat'),
    config = require('../config').libsCss;

gulp.task('lib-styles', function() {
    return gulp.src(config.source)
        .pipe(plumber(errorHandler))
        .pipe(concat("libs.css"))
        .pipe(gulp.dest(config.dest));
});
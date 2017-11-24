
/*
 * Copyright (c) 2016 by Marfeel Solutions (http://www.marfeel.com)
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
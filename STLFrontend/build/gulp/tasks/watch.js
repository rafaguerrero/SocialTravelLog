var gulp = require('gulp'),
	config = require('../config');

// Watch for changes.
gulp.task('watch', function() {
	gulp.watch(config.sass.source, ['styles']);
	gulp.watch(config.js.source, ['scripts']);
});

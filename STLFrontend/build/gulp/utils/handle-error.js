var notify = require("gulp-notify");

module.exports =  {
	errorHandler: function (err) {
		var args = Array.prototype.slice.call(arguments);

		notify.onError({
			title: "Compile Error",
			message: "<%= error.plugin %>: <%= error.message %>"
		}).apply(this, args);

		this.emit('end');
	}
};
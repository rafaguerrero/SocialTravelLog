var dest = '../src/main/resources/',
	src = '../src/main/';

module.exports = {
	js: {
		source: src + 'js/**/*.js',
		optimize: src + 'js/main.js',
		config: '../../webpack.config.js',
		dest: dest + 'js'
	},
	sass: {
		source: src + 'scss/*.scss',
		dest: dest + 'css'
	},
	libsCss: {
		source: [],
		dest: dest + 'css'
	}
};

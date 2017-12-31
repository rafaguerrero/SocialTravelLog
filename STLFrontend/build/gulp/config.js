var dest = '../src/main/resources/',
	src = '../src/main/';

module.exports = {
	js: {
		source: src + 'js/**/*.ts',
		optimize: src + 'js/main.ts',
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

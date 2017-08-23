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

var dest = '../src/main/resources/META-INF/resources/',
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
		source: [
			src + 'external/css/bootstrap.min.css'
		],
		dest: dest + 'css'
	}
};

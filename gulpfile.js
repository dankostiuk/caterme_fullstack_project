var request = require('superagent');
var gulp = require('gulp');
var gutil = require('gulp-util');
var uglify = require('gulp-uglify');
var concat = require('gulp-concat');
var connect = require('gulp-connect');

gulp.task('uglify', function () {
    gulp.src('./WebContent/js/*.js')
        .pipe(uglify())
        .pipe(concat('all-ugly.js'))
        .pipe(gulp.dest('./WebContent/js'));
});

gulp.task('webserver', function() {
	connect.server({
		root: 'WebContent',
		port: 9000,
		livereload: true,
		middleware: function(connect, o) {	
			return [ (function() {
				var url = require('url');
		        var proxy = require('proxy-middleware');
		        var options = url.parse('http://localhost:8080/CaterMe/rest');
		        options.route = '/rest';
		        return proxy(options);
			})() ];
	    }
	});
});

gulp.task('default', ['webserver']);


var request = require('superagent');
var gulp = require('gulp');
var gutil = require('gulp-util');
var uglify = require('gulp-uglify');
var concat = require('gulp-concat');
var connect = require('gulp-connect');
var ts = require('gulp-typescript');

// uglifies js
gulp.task('uglify', function () {
    gulp.src('app/*.js')
        .pipe(uglify())
        .pipe(concat('all-ugly.js'))
        .pipe(gulp.dest('app'));
});

// compiles ts to js
gulp.task('ts-compile', function () {
	return gulp.src('app/*.ts')
	    .pipe(ts({
	        noImplicitAny: true
	    }))
	    .pipe(gulp.dest('app'));
});

// runs server with proxy to allow rest calls to 8080
gulp.task('webserver', function() {
	connect.server({
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

gulp.task('default', ['ts-compile', 'webserver']);


/**
 * Main javascript class that gets browserified into main.js
 */

window.getUser = function(id) {
	var Q = require('q');
	var request = require('superagent');
	
	var deferred = Q.deferred();
	
	request
	 	.get('/rest/user')
	    .query({ 'id': id })
	    .end(function(err, res){
	    	
	    	if (err != null) {
	    		console.log('error: ' + err);
	    	}
	    	
	    	if (res != null) {
	    		var user = res.body;
	    		
	    		deferred.resolve(user);
	    	}
	    });
	
	return deferred.promise;
};
import { Injectable } from '@angular/core';
import { Http, URLSearchParams } from '@angular/http';

import 'rxjs/add/operator/toPromise';

@Injectable()
export class LoginService {

    private loginUrl = '/rest/login';

    constructor(public http : Http) {
    }

    isValidLogin(email, password) {

        // Parameters obj-
        let params: URLSearchParams = new URLSearchParams();
        params.set('email', email);
        params.set('password', password);

        return this.http.get(this.loginUrl, { search: params})
            .toPromise()
            .then(response => response.json() as boolean)
            .catch(this.handleError);
    }

    private handleError(error: any) {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}
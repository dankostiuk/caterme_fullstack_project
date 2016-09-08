import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { RegistrationInfo } from './registration-info';

@Injectable()
export class RegistrationService {

    private registerUrl = '/rest/register';
    private response;

    constructor(public http : Http) {
    }

    register(regInfo: RegistrationInfo) {

        return this.http.post(this.registerUrl, regInfo)
            .toPromise()
            .then(response => this.response = response)
            .catch(this.handleError);
    }

    private handleError(error: any) {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}
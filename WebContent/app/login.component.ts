import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { LoginService } from './login.service';

@Component({
    selector: 'my-login',
    templateUrl: 'app/login.component.html',
    styleUrls: ['app/login.component.css', '../css/bootstrap.min.css']
})

export class LoginComponent {

    email: String;
    password: String;
    error: any;

    constructor(private router: Router,
                private loginService: LoginService) {
    }

    isValidLogin(email, password) {
        this.loginService.isValidLogin(email, password)
            .then(result => this.gotoDashboard(result))
            .catch(error => this.error = error);
    }

    gotoDashboard(result) {
        if (result) {
            this.router.navigateByUrl('/dashboard');
        } else {
            this.error = 'Incorrect password.';
        }
    }
}
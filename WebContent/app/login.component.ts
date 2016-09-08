import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { LoginService } from './login.service';
import { CookieService } from 'angular2-cookie/core';

@Component({
    selector: 'my-login',
    templateUrl: 'app/login.component.html',
    styleUrls: ['app/login.component.css', '../css/bootstrap.min.css'],
})

export class LoginComponent implements OnInit {

    email: String;
    password: String;
    error: any;

    constructor(private router: Router,
                private loginService: LoginService,
                private cookieService: CookieService) {
    }

    isValidLogin(email, password) {

        if (!email || email.length == 0) {
            this.error = 'Email must not be blank.';
            return
        }

        if (!password || password.length == 0) {
            this.error = 'Password must not be blank.';
            return
        }

        this.loginService.isValidLogin(email, password)
            .then(result => this.gotoDashboard(result))
            .catch(error => this.error = error);
    }

    ngOnInit() {
        if(this.cookieService.get('loginCookie') &&
                this.cookieService.get('loginCookie').localeCompare('true') != -1) {
            this.router.navigateByUrl('/dashboard');
        }
    }

    gotoRegister() {
        this.router.navigate(['/register']);
    }

    gotoDashboard(result) {
        if (result) {
            this.cookieService.put('loginCookie', 'true');

            this.router.navigateByUrl('/dashboard');
        } else {
            this.error = 'Incorrect email and/or password.';
        }
    }
}
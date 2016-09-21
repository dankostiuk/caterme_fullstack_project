import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { RegistrationService } from './registration.service';
import { RegistrationInfo } from './registration-info'

import { ToastsManager } from 'ng2-toastr/ng2-toastr';

@Component({
    selector: 'my-register',
    templateUrl: 'app/register.component.html',
    styleUrls: ['app/register.component.css', '../css/bootstrap.min.css']
})

export class RegisterComponent {
    regInfo: RegistrationInfo;
    error: any;
    confPassword: string;

    constructor(private router: Router,
                private regService: RegistrationService,
                private toastr: ToastsManager) {

        var regInfo: RegistrationInfo = {
            username: "",
            password: "",
            firstName: "",
            lastName: "",
            phone: "",
            address: ""
        };

        this.regInfo = regInfo;
    }

    gotoLogin() {
        this.router.navigate(['/login']);
    }

    register(regInfo: RegistrationInfo) {

        if (!regInfo.username || regInfo.username.length == 0) {
            this.error = "Email must not be blank."
            return;
        }

        if (!regInfo.password || regInfo.password.length == 0) {
            this.error = "Password must not be blank."
            return;
        }

        if (regInfo.password != this.confPassword) {
            this.error = "Passwords must match."
            return;
        }

        if (!regInfo.firstName || regInfo.firstName.length == 0) {
            this.error = "First name must not be blank."
            return;
        }

        if (!regInfo.lastName || regInfo.lastName.length == 0) {
            this.error = "Last name must not be blank."
            return;
        }

        if (!regInfo.phone || regInfo.phone.length == 0) {
            this.error = "Phone number must not be blank."
            return;
        } else {
            if (isNaN(regInfo.phone) || regInfo.phone.length != 10) {
                this.error = "Phone number must be a 10-digit number."
                return;
            }
        }

        if (!regInfo.address || regInfo.address.length == 0) {
            this.error = "Company address must not be blank."
            return;
        }

        this.regService.register(regInfo);
        this.showSuccess(regInfo.username);
    }

    showSuccess(username) {
        this.toastr.success('Registered user ' + username + ' successfully')
        this.gotoLogin();
    }

}
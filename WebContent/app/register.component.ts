import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { RegistrationService } from './registration.service';
import { RegistrationInfo } from './registration-info'

@Component({
    selector: 'my-register',
    templateUrl: 'app/register.component.html',
    styleUrls: ['app/register.component.css', '../css/bootstrap.min.css']
})

export class RegisterComponent {
    regInfo: RegistrationInfo;

    constructor(private router: Router,
                private regService: RegistrationService) {

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
        this.regService.register(regInfo);
    }

}
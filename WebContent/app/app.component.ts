import { Component } from '@angular/core';
import { ROUTER_DIRECTIVES } from '@angular/router';

import { LoginService } from './login.service';
import { OrderService } from './order.service';

@Component({
  selector: 'my-app',
  template: `

<router-outlet></router-outlet>
    `,
    styleUrls: ['app/app.component.css'],
    directives: [ROUTER_DIRECTIVES],
    providers: [LoginService, OrderService]
})

export class AppComponent {

    title = "CaterMe";
}
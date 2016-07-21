import { Component } from '@angular/core';
import { ROUTER_DIRECTIVES } from '@angular/router';

import { OrderService } from './order.service';

@Component({
  selector: 'my-app',
  template: `

        <h1>{{title}}</h1>

        <nav>
            <a [routerLink]="['/dashboard']" routerLinkActive="active">Dashboard</a>
            <a [routerLink]="['/orders']" routerLinkActive="active">Orders</a>
        </nav>

        <router-outlet></router-outlet>
    `,
    styleUrls: ['app/app.component.css'],
    directives: [ROUTER_DIRECTIVES],
    providers: [OrderService]
})

export class AppComponent {

    title = "CaterMe";
}
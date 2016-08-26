import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'angular2-cookie/core'

import { Order } from './order';
import { OrderService } from './order.service';

@Component({
    selector: 'my-dashboard',
    templateUrl: 'app/dashboard.component.html',
    styleUrls: ['app/dashboard.component.css', '../css/bootstrap.min.css']
})

export class DashboardComponent implements OnInit {
    orders: Order[] = [];

    constructor(
        private router: Router,
        private orderService: OrderService,
        private cookieService: CookieService) {

    }

    ngOnInit() {
        this.orderService.getOrders()
            .then(orders => this.orders = orders);
    }
    gotoDetail(order: Order) {
        let link = ['/detail', order.id]
        this.router.navigate(link);
    }

    logout() {
        this.cookieService.put('loginCookie', 'false');
        this.router.navigateByUrl('/login');
    }
}
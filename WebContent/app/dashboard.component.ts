import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Order } from './order';
import { OrderService } from './order.service';

@Component({
    selector: 'my-dashboard',
    templateUrl: 'app/dashboard.component.html',
    styleUrls: ['app/dashboard.component.css']
})

export class DashboardComponent implements OnInit {
    orders: Order[] = [];

    constructor(
        private router: Router,
        private orderService: OrderService) {

    }

    ngOnInit() {
        this.orderService.getOrders()
            .then(orders => this.orders = orders);
    }
    gotoDetail(order: Order) {
        let link = ['/detail', order.id]
        this.router.navigate(link);
    }
}
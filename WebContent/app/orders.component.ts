import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Order } from './order';
import { OrderService } from './order.service';

@Component({
    selector: 'my-orders',
    templateUrl: 'app/orders.component.html',
    styleUrls: ['app/orders.component.css']
})

export class OrdersComponent implements OnInit {
    orders: Order[];
    selectedOrder: Order;
    error: any;


    constructor(private router: Router,
                private orderService: OrderService) {
    }

    getOrders() {
        this.orderService.getOrders()
            .then(orders => this.orders = orders)
            .catch(error => this.error = error);
    }

    ngOnInit() {
        this.getOrders();
    }

    onSelect(order: Order) {
        this.selectedOrder = order;
    }

    gotoDetail() {
        this.router.navigate(['/detail', this.selectedOrder.id]);
    }
}
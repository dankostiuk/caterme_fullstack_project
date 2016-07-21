import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Order } from './order'

@Injectable()
export class OrderService {

    private allOrdersUrl = '/rest/order/all';

    constructor(public http : Http) {
    }

    getOrders() {

        let heroes = [
            {id: 11, summary: 'Mr. Nice', description: 'asdf'}
        ];

        return this.http.get(this.allOrdersUrl)
            .toPromise()
            .then(response => response.json() as Order[])
            .catch(this.handleError);
    }


    getOrder(id: number) {
        return this.getOrders()
            .then(orders => orders.find(order => order.id === id));
    }

    private handleError(error: any) {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}
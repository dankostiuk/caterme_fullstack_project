import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Order } from './order';
import { OrderService } from './order.service';

@Component({
  selector: 'my-order-detail',
  templateUrl: 'app/order-detail.component.html',
  styleUrls: ['app/order-detail.component.css', '../css/bootstrap.min.css']
})

export class OrderDetailComponent implements OnInit, OnDestroy {
  order: Order;
  error: any;
  sub: any;

  constructor(
      private orderService: OrderService,
      private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      let id = +params['id'];
      this.orderService.getOrder(id)
          .then(order => this.order = order);
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  goBack() {
    window.history.back();
  }
}
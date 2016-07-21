import { provideRouter, RouterConfig } from '@angular/router';

import { OrdersComponent } from './orders.component';
import { OrderDetailComponent } from './order-detail.component'
import { DashboardComponent } from './dashboard.component';

const routes: RouterConfig = [
    {
        path: 'orders',
        component: OrdersComponent
    },
    {
        path: 'detail/:id',
        component: OrderDetailComponent
    },
    {
        path: 'dashboard',
        component: DashboardComponent
    },
    {
        path: '',
        redirectTo: '/dashboard',
        pathMatch: 'full'
    }
];

export const appRouterProviders = [
    provideRouter(routes)
]

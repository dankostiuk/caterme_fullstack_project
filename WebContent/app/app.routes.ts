import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { OrdersComponent } from './orders.component';
import { OrderDetailComponent } from './order-detail.component'
import { DashboardComponent } from './dashboard.component';
import { LoginComponent } from './login.component';

const appRoutes: Routes = [
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
        path: 'login',
        component: LoginComponent
    },
    {
        path: '',
        redirectTo: '/login',
        pathMatch: 'full'
    }
];

export const routing = RouterModule.forRoot(appRoutes);

export const routedComponents = [OrdersComponent, OrderDetailComponent, DashboardComponent, LoginComponent];

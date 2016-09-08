import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { OrdersComponent } from './orders.component';
import { OrderDetailComponent } from './order-detail.component'
import { DashboardComponent } from './dashboard.component';
import { LoginComponent } from './login.component';
import { RegisterComponent } from './register.component';

const appRoutes: Routes = [
    {
        path: '',
        redirectTo: '/login',
        pathMatch: 'full'
    },
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'register',
        component: RegisterComponent
    },
    {
        path: 'dashboard',
        component: DashboardComponent
    },
    {
        path: 'orders',
        component: OrdersComponent
    },
    {
        path: 'detail/:id',
        component: OrderDetailComponent
    }
];

export const routing = RouterModule.forRoot(appRoutes);

export const routedComponents = [OrdersComponent, OrderDetailComponent, DashboardComponent, LoginComponent, RegisterComponent];

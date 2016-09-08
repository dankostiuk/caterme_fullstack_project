import { NgModule }       from '@angular/core';
import { BrowserModule  } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent }   from './app.component';
import { routing, routedComponents } from './app.routes';

import { CookieService } from 'angular2-cookie/core';
import { LoginService } from './login.service';
import { RegistrationService } from "./registration.service";
import { OrderService } from './order.service';


@NgModule({
  imports:      [
                BrowserModule,
                // Forms
                FormsModule,
                routing,
                HttpModule
  ],
  declarations: [
                AppComponent,
                routedComponents
                ],
  providers:    [
      CookieService,
      LoginService,
      RegistrationService,
      OrderService,
  ],
  bootstrap:    [AppComponent],
})
export class AppModule {}

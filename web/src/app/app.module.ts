import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { OrderModule } from 'ngx-order-pipe';
import { NgxMaskModule, IConfig } from 'ngx-mask'
import { pipeCpfCnpj } from './_helpers/pipeCpfCnpj.pipe';


import { AppRoutingModule } from './app-routing.module';
import { ErrorInterceptor } from './_helpers';
import { AppComponent } from './app.component';
import { AlertComponent } from './_components';
import { HomeComponent } from './home';
import { AuthInterceptor } from './_helpers/auth.interceptor';;
import { CadastrarComponent } from './clientes/cadastrar/cadastrar.component';
import { EditarComponent } from './clientes/editar/editar.component';
import { DetalhesComponent } from './clientes/detalhes/detalhes.component';

export const options: Partial<IConfig> | (() => Partial<IConfig>) = null;


@NgModule({
    imports: [
        BrowserModule,
        ReactiveFormsModule,
        HttpClientModule,
        AppRoutingModule,
        FormsModule,
        OrderModule,
        NgxMaskModule.forRoot(),
    ],
    declarations: [
        AppComponent,
        AlertComponent,
        HomeComponent,
        CadastrarComponent ,
        EditarComponent ,
        DetalhesComponent,pipeCpfCnpj],
    providers: [
        { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
        { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
    ],
    bootstrap: [AppComponent]
})
export class AppModule { };
import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AccountService } from '@app/_services';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor(private accountService: AccountService) {}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        if (request.url.endsWith("/api/usuario/login") && request.method == 'POST') {

            request = request.clone({
                setHeaders: { 
                    Authorization: `Bearer ${this.accountService.userValue?.access_token}`
                }
            });
        }

        if (request.url.includes("/api/cliente")) {

            request = request.clone({
                setHeaders: { 
                    Authorization: `Bearer ${this.accountService.userValue?.access_token}`
                }
            });
        }

        return next.handle(request);
    }
}
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { environment } from '@environments/environment';
import { User, Oauth } from '@app/_models';

const httpOptions = {
    headers: new HttpHeaders({
      'Accept':	'application/json',
      'Content-Type':  'application/json',
      'Authorization': 'Basic ' + btoa('client-id:secret-id')
    })
  };

@Injectable({ providedIn: 'root' })
export class AccountService {
    
    private userSubject: BehaviorSubject<Oauth>;
    public user: Observable<Oauth>;

    constructor(
        private router: Router,
        private http: HttpClient
    ) {
        this.userSubject = new BehaviorSubject<Oauth>(JSON.parse(localStorage.getItem('user')));
        this.user = this.userSubject.asObservable();
    }

    public get userValue(): Oauth {
        return this.userSubject.value;
    }
       
    login(login, senha) {

        return this.http.post<Oauth>(`${environment.apiUrl}/oauth/token?grant_type=password&username=${login}&password=${senha}`, null, httpOptions)
            .pipe(map(oauth => {
                localStorage.setItem('user', JSON.stringify(oauth));
                this.userSubject.next(oauth);

                return oauth;
        }));
    }

    logout() {
        localStorage.removeItem('user');
        this.userSubject.next(null);
        this.router.navigate(['/account/login']);
    }

    register(user: User) {
        return this.http.post(`${environment.apiUrl}/api/usuario/registrar`, user);
    }

    getById(id: string) {
        return this.http.get<User>(`${environment.apiUrl}/api/usuario/${id}`);
    }

}
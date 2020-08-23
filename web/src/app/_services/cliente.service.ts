import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '@environments/environment';
import { Cliente } from '@app/_models/cliente';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<Cliente>(`${environment.apiUrl}/api/cliente`);
  }

  getById(id: string) {
    return this.http.get<Cliente>(`${environment.apiUrl}/api/cliente/${id}`);
  }

  update(id, params) {
    return this.http.put(`${environment.apiUrl}/api/cliente/${id}`, params)
        .pipe(map(cliente => {
            return cliente;
        }));
  }

  add(cliente: Cliente) {
    return this.http.post(`${environment.apiUrl}/api/cliente`, cliente)
        .pipe(map(cliente => {
            return cliente;
        }));
  }

  delete(id: string) {
      return this.http.delete(`${environment.apiUrl}/api/cliente/${id}`)
          .pipe(map(cliente => {
              return cliente;
          }));
  }
}

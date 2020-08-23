import { Component, OnInit } from '@angular/core';

import { Oauth, Cliente } from '@app/_models';
import { AccountService, AlertService } from '@app/_services';
import { ClienteService } from '@app/_services/cliente.service';
import { first } from 'rxjs/operators';

@Component({ templateUrl: 'home.component.html' })
export class HomeComponent implements OnInit {
    user: Oauth;
    clientes: Cliente;

    constructor(
        private accountService: AccountService,
        private clienteService: ClienteService, 
        private alertService: AlertService
    ) {this.user = this.accountService.userValue;
}

    logout(){
        this.accountService.logout();
    }

    ngOnInit() {
        this.clienteService.getAll()
            .pipe(first())
            .subscribe(cliente => this.clientes = cliente);
    }

    deleteCliente(id: string) {
        this.clienteService.delete(id)
            .pipe(first())
            .subscribe(
                () => {
                    this.alertService.success('Cliente deletado com sucesso!', {autoClose:true});
                    this.ngOnInit();
                },
                error => {
                    this.alertService.error(error);
                });
    }
}
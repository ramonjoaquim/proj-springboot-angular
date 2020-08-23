import { Component } from '@angular/core';

import { AccountService } from './_services';
import { Oauth } from './_models';

@Component({ selector: 'app', templateUrl: 'app.component.html' })
export class AppComponent {
    user: Oauth;

    constructor(private accountService: AccountService) {
        this.user = this.accountService.userValue;
    }

    logout() {
        this.accountService.logout();
    }
}
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home';
import { AuthGuard } from './_helpers';
import { CadastrarComponent } from './clientes/cadastrar/cadastrar.component';
import { EditarComponent } from './clientes/editar/editar.component';
import { DetalhesComponent } from './clientes/detalhes/detalhes.component';

const accountModule = () => import('./account/account.module').then(x => x.AccountModule);

const routes: Routes = [
    { path: '', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'account', loadChildren: accountModule },
    { path: 'add', component: CadastrarComponent },
    { path: 'details/:id', component: DetalhesComponent },
    { path: 'edit/:id', component: EditarComponent },
    { path: '**', redirectTo: '' }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
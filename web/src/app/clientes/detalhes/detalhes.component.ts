import { Component, OnInit } from '@angular/core';
import { ClienteService } from '@app/_services';
import { Cliente } from '@app/_models';
import { ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-detalhes',
  templateUrl: './detalhes.component.html'
})
export class DetalhesComponent implements OnInit {
  id: string;
  cliente: Cliente;

  constructor(
    private clienteService: ClienteService,
    private route: ActivatedRoute 
  ) { }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.clienteService.getById(this.id).pipe(first()).subscribe((cliente) => this.cliente = cliente['object']);
  } 

}

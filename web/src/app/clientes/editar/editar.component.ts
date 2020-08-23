import { Component, OnInit } from '@angular/core';
import { ClienteService, AlertService } from '@app/_services';
import { ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';
import { FormGroup, Validators, FormBuilder, FormArray } from '@angular/forms';

@Component({
  selector: 'app-editar',
  templateUrl: './editar.component.html'
})
export class EditarComponent implements OnInit {
  form: FormGroup;
  id: string;
  loading = false;
  submitted = false;
   
  constructor(
    private formBuilder: FormBuilder,
    private clienteService: ClienteService,
    private route: ActivatedRoute,
    private alertService: AlertService
  ) {}
    ngOnInit(){
      this.id = this.route.snapshot.params['id'];
      this.form = this.formBuilder.group({
        id: '',
        nome: ['', Validators.required],
        cpfCnpj: ['', Validators.required],
        enderecos: this.formBuilder.array([]), 
        contatos: this.formBuilder.array([])
      });

      this.f.cpfCnpj.disable();

      this.clienteService.getById(this.id)
                .pipe(first())
                .subscribe(x => {
                    this.f.id.setValue(x['object'].id);
                    this.f.nome.setValue(x['object'].nome);
                    this.f.cpfCnpj.setValue(this.formatCpfCnpj(x['object'].cpfCnpj));

                    for(let e of x['object'].enderecos){
                      this.enderecos().push(this.formBuilder.group({
                        id: e.id,
                        rua: e.rua,
                        bairro: e.bairro,
                        numero: e.numero,
                        principal: e.principal,
                      }))
                    };

                    for(let c of x['object'].contatos){
                      this.contatos().push(this.formBuilder.group({
                        id: c.id,
                        telefone: c.telefone
                      }))
                    };
                });      
    }

  get f() { return this.form.controls; }
  
  enderecos() : FormArray {
    return this.form.get("enderecos") as FormArray
  }

  contatos() : FormArray {
    return this.form.get("contatos") as FormArray
  }
   
  novoEnderecoLinha(): FormGroup {
    return this.formBuilder.group({
      rua: '',
      bairro: '',
      numero: '',
      principal: false,
    })
  }
   
  novoContatoLinha(): FormGroup {
    return this.formBuilder.group({
      telefone: ''
    })
  }

  addEnderecoNovaLinha() {
    this.enderecos().push(this.novoEnderecoLinha());
  }

  addContatoNovaLinha() {
    this.contatos().push(this.novoContatoLinha());
  }
   
  removerEnderecoLinha(i:number) {
    this.enderecos().removeAt(i);
  }

  removerContatoLinha(i:number) {
    this.contatos().removeAt(i);
  }
  
  onSubmit() {
      this.submitted = true;

      this.alertService.clear();

      if (this.form.invalid) {
          return;
      }

      this.loading = true;
      this.updateCliente();
  }

  private updateCliente() {
    this.clienteService.update(this.id, this.form.getRawValue())
        .pipe(first())
        .subscribe(
            () => {
                this.alertService.success('Cliente atualizado com sucesso!', {autoClose:true, keepAfterRouteChange: true});
                this.loading = false;
            },
            error => {
                this.alertService.error(error);
                this.loading = false;
            });
  }

  formatCpfCnpj(value: string){
        if (value.length === 11) 
          return value.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/g, '\$1.\$2.\$3\-\$4');
        else
            return value.replace(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/g, '\$1.\$2.\$3\/\$4-\$5');              
  }

}

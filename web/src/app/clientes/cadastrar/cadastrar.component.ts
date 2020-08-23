﻿import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray, NgForm } from '@angular/forms';
import { first } from 'rxjs/operators';

import { ClienteService, AlertService } from '@app/_services';

@Component({
  selector: 'app-cadastrar',
  templateUrl: './cadastrar.component.html'
})
export class CadastrarComponent implements OnInit {
  form: FormGroup;
  id: string;
  loading = false;
  submitted = false;
   
  constructor(
    private formBuilder: FormBuilder,
    private clienteService: ClienteService,
    private alertService: AlertService
  ) {
   
  }
    ngOnInit(){
      this.form = this.formBuilder.group({
        nome: ['', Validators.required],
        cpfCnpj: ['', Validators.required],
        enderecos: this.formBuilder.array([]), 
        contatos: this.formBuilder.array([])
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

  clear(form: FormGroup): void {
    form.reset();
    Object.keys(form.controls).forEach(key =>{
       form.controls[key].setErrors(null)
    });
  }

  clearInputs(){
    this.clear(this.form);
    this.contatos().clear();
    this.enderecos().clear();
  }
  
  onSubmit() {
      this.submitted = true;

      this.alertService.clear();

      if (this.form.invalid) {
          return;
      }

      this.loading = true;
      this.add();
  }

  private add() {
      this.clienteService.add(this.form.value)
          .pipe(first())
          .subscribe(
              () => {
                  this.alertService.success('Cliente cadastrado com sucesso!', {autoClose:true, keepAfterRouteChange: true });
                  this.loading = false;
                  this.clearInputs();
              },
              error => {
                  this.alertService.error(error);
                  this.loading = false;
              });
  }
}

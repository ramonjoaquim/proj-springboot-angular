import { Endereco } from './endereco';
import { Contato } from './contato';

export class Cliente {
    id: string;
    nome: string;
    cpfCnpj: string;
    enderecos: Endereco[];
    contatos: Contato[];
}
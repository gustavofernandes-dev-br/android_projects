abstract class Cliente {
  String _nome;
  String _email;

  //abstrato
  void calcularDesconto();

  Cliente(this._nome, this._email);

  //CONTRUTOR VAZIO
  Cliente.vazio();

  String get nome => this._nome;
  String get email => this._email;

  set email(String email) => this._email = email;
  set nome(String nome) => this._nome = nome;

  @override
  String toString() {
    // TODO: implement toString
    return "$nome, $email";
  }
}

enum EnumSexo { masculino, feminino }

extension on EnumSexo {
  String toShortString() {
    return this.toString().split('.').last;
  }

  String nome() {
    switch (this) {
      case EnumSexo.masculino:
        return "Masc";
      case EnumSexo.feminino:
        return "Fem";
      default:
        return null;
    }
  }
}

class ClienteFisico extends Cliente {
  EnumSexo _sexo;
  String _cpf;

  calcularDesconto() {
    print("Desconto 10,00");
  }

  ClienteFisico.vazio() : super.vazio();

  ClienteFisico(nome, email, this._sexo, this._cpf) : super(nome, email);

  EnumSexo get sexo => this._sexo;
  set sexo(EnumSexo sexo) => this._sexo = sexo;

  get cpf => this._cpf;
  set cpf(cpf) => this._cpf = cpf;

  @override
  String toString() {
    return "${super.toString()}, ${sexo.nome()}, $cpf";
  }
}

class ClienteJuridico extends Cliente {
  String _cnpj;
  String _ramo;

  calcularDesconto() {
    print("Desconto 30,00");
  }

  ClienteJuridico.vazio() : super.vazio();

  ClienteJuridico(nome, email, this._cnpj, this._ramo) : super(nome, email);

  get cnpj => this._cnpj;

  set cnpj(value) => this._cnpj = value;

  get getRamo => this._ramo;

  set setRamo(ramo) => this._ramo = ramo;

  @override
  String toString() {
    // TODO: implement toString
    return super.toString() + " $_cnpj  $_ramo";
  }
}

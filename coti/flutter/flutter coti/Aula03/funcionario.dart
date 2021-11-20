class Funcionario {
  String _nome;
  String _cargo;
  double _salario;
  Funcionario.vazio();

  Funcionario(this._nome, this._cargo, this._salario);

  get nome => this._nome;
  set nome(value) => this._nome = value;

  get cargo => this._cargo;
  set cargo(value) => this._cargo = value;

  get salario => this._salario;
  set salario(value) => this._salario = value;

  @override
  String toString() {
    // TODO: implement toString
    return "$nome $cargo $salario";
  }
}

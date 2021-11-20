class Produto {
  //Atributos publicos
  //privados -> acesso restrito a propria classe
  String _nome;
  double _preco;
  int _quantidade;

  //Named Contructor
  Produto.vazio();

  //Contrutor -> Cheio
  Produto(String nome, double preco, int quantidade) {
    this._nome = nome;
    this._preco = preco;
    this._quantidade = quantidade;
  }

  //Encapsulamento setters e getters
  String get nome {
    return this._nome;
  }

  double get preco {
    return this._preco;
  }

  int get quantidade {
    return this._quantidade;
  }

  set nome(String nome) {
    this._nome = nome;
  }

  set preco(double preco) {
    this._preco = preco;
  }

  set quantidade(int quantidade) {
    this._quantidade = quantidade;
  }

  @override
  String toString() {
    // TODO: implement toString
    return "Nome: $_nome , $_preco , $_quantidade";
  }
}

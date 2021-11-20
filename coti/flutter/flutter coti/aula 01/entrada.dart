import 'dart:io';

class EntradaProduto {
  String inputNome() {
    print("Digite o nome do produto.");
    var input = stdin.readLineSync();
    return input;
  }

  int inputQuantidade() {
    try {
      print("Digite a quantidade do produto.");
      var input = stdin.readLineSync();
      int quantidade = int.parse(input);
      return quantidade;
    } catch (ex) {
      print(ex);
      return this.inputQuantidade();
    }
  }

  double inputPreco() {
    try {
      print("Digite o preco do produto.");
      var input = stdin.readLineSync();
      double preco = double.parse(input);
      if (preco < 0) {
        throw Exception("O preço não pode ser negativo.");
      }

      return preco;
    } on FormatException {
      print("Formato incorreto");
      return this.inputPreco();
    } catch (ex) {
      print(ex);
      return this.inputPreco();
    }
  }
}

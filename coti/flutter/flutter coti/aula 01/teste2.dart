import 'produto.dart';

void main() {
  //instancia em p
  Produto p = new Produto.vazio();

  //entrada de dados
  p.nome = "Controle";
  p.preco = 20.0;
  p.quantidade = 10;

  //saida de dados
  print(p.nome);
  print(p.preco);
  print(p.quantidade);

  //

  Produto p1 = Produto("Copo", 100.0, 10);
  print(p1);
}

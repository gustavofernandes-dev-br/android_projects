import 'produto.dart';
import 'entrada.dart';

void main() {
  try {
    Produto p = Produto.vazio();
    EntradaProduto entrada = EntradaProduto();

    p.nome = entrada.inputNome();
    p.quantidade = entrada.inputQuantidade();
    p.preco = entrada.inputPreco();

    print("Produto: $p");
  } catch (e) {
    print(e);
  } finally {
    print("finalizando");
  }
}

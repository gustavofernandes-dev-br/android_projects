import 'funcionario.dart';

void main() {
  Map<int, String> mapPessoas = Map();

  mapPessoas[10] = "Ana";
  mapPessoas[20] = "bbb";
  mapPessoas[30] = "ccc";
  mapPessoas[40] = "ddd";
  mapPessoas[50] = "fff";
  mapPessoas[60] = "ttt";

  print(mapPessoas.keys);
  print(mapPessoas.values);

  for (var nome in mapPessoas.values) {
    print("nome:$nome ");
  }
  for (var key in mapPessoas.keys) {
    print("nome:${mapPessoas[key]} ");
  }

  Map<String, Funcionario> mapFunc = new Map<String, Funcionario>();

  mapFunc["0000"] = new Funcionario("teste", "teste", 10.0);
  mapFunc["0001"] = new Funcionario("teste1", "teste1", 10.0);
  mapFunc["0002"] = new Funcionario("test2", "teste2", 10.0);
  mapFunc["0003"] = new Funcionario("teste3", "teste3", 10.0);

  for (String chave in mapFunc.keys) {
    print("chave: $chave , Funcionário:" + mapFunc[chave].toString());
  }
}

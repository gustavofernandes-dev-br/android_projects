import 'funcionario.dart';

void main() {
  List<String> nomes = ["Rui", "Ana", "Bia", "Nat"];

  print(nomes);
  print(nomes[0]);
  nomes.add("Maria");
  nomes.removeAt(0);
  nomes.remove("Nat");
  print(nomes.length);
  nomes.insert(2, "Marcos");
  print(nomes.contains("bob"));
  print(nomes);

  List<Funcionario> func = List<Funcionario>();
  func.add(new Funcionario("Eu", "analista", 12600.0));
  func.add(new Funcionario("Ela", "programer", 12500.0));
  func.add(new Funcionario("Nós", "Analista", 12456.0));
  func.add(new Funcionario("Leo", "teste", 12983.0));

  int i = 0;
  for (var item in func) {
    item.cargo = "aaa";
    print(item.toString());
    print(func[i]);
    i++;
  }

  for (var item in func) {
    print(item.cargo);
    i++;
  }
}

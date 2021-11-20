import 'cliente.dart';

void main() {
  ClienteFisico cf = new ClienteFisico.vazio();

  cf.nome = "Gustavo";
  cf.email = "gustavo@pdvnet.com.br";
  cf.sexo = EnumSexo.masculino;
  cf.cpf = "093.355.247/51";
  cf.calcularDesconto();

  print(cf.toString());
  ClienteJuridico cj =
      new ClienteJuridico("coti", "coti@coti.com", "123-12/0001-12", "Inf");
  print(cj.toString());
  cj.calcularDesconto();
}

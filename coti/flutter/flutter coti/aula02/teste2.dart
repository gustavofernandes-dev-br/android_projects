import 'cliente.dart';

void main() {
  //Polimorfismo
  Cliente c = ClienteFisico.vazio();
  c.calcularDesconto();

  c = ClienteJuridico.vazio();
  c.calcularDesconto();
}
//Cons é variavel
//final é na classe

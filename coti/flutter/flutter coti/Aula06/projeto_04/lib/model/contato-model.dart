import 'package:projeto_04/util.dart';

class ContatoModel {
  int id;
  String nome;
  String email;
  String telefone;
  String img;

  ContatoModel();

  ContatoModel.fromMap(Map map) {
    id = map[idColumn];
    nome = map[nomeColumn];
    email = map[emailColumn];
    telefone = map[telefoneColumn];
    img = map[imgColumn];
  }

  Map toMap() {
/*     Map<String, dynamic> map = new Map<String, dynamic>();
    map[idColumn] = this.id.toString();
    map[nomeColumn] = this.nome.toString();
    map[emailColumn] = this.email.toString();
    map[telefoneColumn] = this.telefone.toString();
    map[imgColumn] = this.img.toString(); */

    Map<String, dynamic> map2 = {
      nomeColumn: nome,
      emailColumn: email,
      telefoneColumn: telefone,
      imgColumn: img
    };

    if (id != null) {
      map2[idColumn] = id;
    }

    return map2;
  }

  @override
  String toString() {
    return "$id , $nome , $email , $telefone , $img";
  }
}

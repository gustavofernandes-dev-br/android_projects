import 'package:flutter/material.dart';

void main() {
  runApp(Home());
}

class Home extends StatefulWidget {
  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {
  int limite = 20;
  int alunos = 0;
  int alunosEmSala = 0;
  int alunosOnline = 0;
  String infoTexto = "Ainda há vagas";

  void AtualizarAlunosEmSala(int valor) {
    if ((this.alunosEmSala + valor) < 0) return;
    if (this.alunos == limite && valor == 1) return;

    setState(() {
      alunosEmSala += valor;
      this.AtualizarTodosAlunos();
    });
  }

  void AtualizarTodosAlunos() {
    setState(() {
      this.alunos = this.alunosEmSala + this.alunosOnline;

      if (this.alunos >= this.limite) {
        this.infoTexto = "Turma lotada";
      } else {
        this.infoTexto = "Turma Aberta";
      }
    });
  }

  void AtualizarAlunosOnline(int valor) {
    if ((this.alunosOnline + valor) < 0) return;
    if (this.alunos == limite && valor == 1) return;

    setState(() {
      alunosOnline += valor;
      this.AtualizarTodosAlunos();
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: "Entrada de alunos",
        home: Stack(
          children: [
            Image.asset(
              "imagens/papel-parede.jpg",
              fit: BoxFit.cover,
              height: 100000,
            ),
            Container(
              color: Colors.grey[300],
            ),
            Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text(
                  "Alunos:$alunos",
                  style: TextStyle(
                      decoration: TextDecoration.none,
                      color: Colors.green,
                      fontWeight: FontWeight.bold,
                      fontSize: 25.0),
                ),
                Divider(),
                //ctrl + k + l
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    FlatButton(
                      child: Text(
                        "+",
                        style: TextStyle(fontSize: 25.0, color: Colors.white),
                      ),
                      onPressed: () {
                        AtualizarAlunosEmSala(1);
                      },
                    ),
                    FlatButton(
                      child: Text(
                        "-",
                        style: TextStyle(fontSize: 25.0, color: Colors.white),
                      ),
                      onPressed: () {
                        AtualizarAlunosEmSala(-1);
                      },
                    ),
                  ],
                ),
                Text(
                  "Alunos em sala:$alunosEmSala",
                  style: TextStyle(
                      decoration: TextDecoration.none,
                      color: Colors.blue,
                      fontWeight: FontWeight.bold,
                      fontSize: 25.0),
                ),
                Divider(),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    FlatButton(
                      padding: EdgeInsets.all(20.0),
                      child: Text(
                        "+",
                        style: TextStyle(fontSize: 25.0, color: Colors.white),
                      ),
                      onPressed: () {
                        AtualizarAlunosOnline(1);
                      },
                    ),
                    FlatButton(
                      padding: EdgeInsets.all(25.0),
                      child: Text(
                        "-",
                        style: TextStyle(fontSize: 25.0, color: Colors.white),
                      ),
                      onPressed: () {
                        AtualizarAlunosOnline(-1);
                      },
                    ),
                  ],
                ),
                Divider(),
                Text(
                  "Alunos on-line:$alunosOnline",
                  style: TextStyle(
                      decoration: TextDecoration.none,
                      color: Colors.blue,
                      fontWeight: FontWeight.bold,
                      fontSize: 25.0),
                ),
                Divider(),
                Text(
                  this.infoTexto,
                  style: TextStyle(
                      decoration: TextDecoration.none,
                      color: Colors.red,
                      fontSize: 25.0,
                      fontWeight: FontWeight.bold),
                )
              ],
            ),
          ],
        ));
  }
}

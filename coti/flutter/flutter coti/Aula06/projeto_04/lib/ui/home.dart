import 'dart:io';

import 'package:flutter/material.dart';
import 'package:projeto_04/model/contato-model.dart';
import 'package:projeto_04/persistence/contato-db.dart';
import 'package:projeto_04/ui/cadastro.dart';

class Home extends StatefulWidget {
  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {
  ContatoDB banco = new ContatoDB();

  Widget bottomSheetButton(String titulo, {Function f}) {
    return FlatButton(
      onPressed: () {
        f();
      },
      child: Text(
        titulo,
        style: TextStyle(
          color: Colors.green,
          fontSize: 20.0,
        ),
      ),
    );
  }

  void showOption() {
    showModalBottomSheet(
        context: context,
        builder: (context) {
          return Container(
            padding: EdgeInsets.all(10.0),
            child: Column(
              mainAxisSize: MainAxisSize.min,
              children: [
                this.bottomSheetButton("Ligar", f: () {
                  print("ligar");
                }),
                Divider(),
                this.bottomSheetButton("Excluir", f: () {
                  print("excluir");
                }),
                Divider(),
                this.bottomSheetButton("Editar", f: () {
                  print("editar");
                }),
              ],
            ),
          );
        });
  }

  List<ContatoModel> contatos = new List<ContatoModel>();
  void getContatos() {
    this.banco.getContatos().then((value) {
      this.contatos = value;
      print("contagem: ${contatos.length}");
      setState(() {});
    }).catchError((onError) => print(onError));
  }

  void showCadastroPage({ContatoModel contato}) async {
    Future<ContatoModel> retorno = Navigator.push(
      context,
      MaterialPageRoute(builder: (context) {
        return Cadastro(
          contatoUpdate: contato,
        );
      }),
    );
    retorno.then((value) {
      if (value != null) {
        if (contato != null) {
          //update
          banco.updateContato(value).then((value) => this.getContatos());
        } else {
          //insert
          banco.saveContato(value).then((value) => this.getContatos());
        }
      }
    }).catchError((error) {
      throw error;
    });
  }

  @override
  void initState() {
    super.initState();
    this.getContatos();
    print("contagem init: ${contatos.length}");

    //crtl + k c   ---  crtl + k u
    //shift + alt + a  - shift + alt + a

/*     ContatoModel contato = new ContatoModel.vazio();
    contato.nome = "Fernando";
    contato.email = "fernando@pdvnet.com.br";
    contato.telefone = "21 21590606";
    contato.img = "noimage.jpg";
    contato.id = 1;
    banco.saveContato(contato).then((value) {
      contato = value;
      print(value);
    }).catchError((onError) {
      print(onError);
    }); */
  }

  AppBar appBar() {
    return AppBar(
      title: Text("Contatos"),
      elevation: 0,
      centerTitle: true,
      backgroundColor: Colors.green,
    );
  }

  FloatingActionButton floatingActionButton() {
    return FloatingActionButton(
      onPressed: () {
        this.showCadastroPage();
      },
      elevation: 0,
      child: Icon(Icons.add),
      backgroundColor: Colors.green,
    );
  }

  Scaffold scaffold(
      AppBar appBar, FloatingActionButton botao, ListView listView) {
    return Scaffold(
      appBar: appBar,
      floatingActionButton: botao,
      body: listView,
    );
  }

  ListView listView() {
    return ListView.builder(
      itemCount: contatos.length,
      itemBuilder: (context, index) {
        return contatoCard(context, index);
      },
      padding: EdgeInsets.all(10.0),
    );
  }

  Widget contatoCard(BuildContext context, int index) {
    return GestureDetector(
      onTap: () {
        this.showOption();
        //showCadastroPage(contato: contatos[index]);
      },
      child: Card(
        elevation: 30,
        child: Padding(
          padding: EdgeInsets.all(10.0),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              this.containerImagem(context, index),
              Padding(
                padding: EdgeInsets.all(10.0),
                child: this.column(context, index),
              )
            ],
          ),
        ),
      ),
    );
  }

  Column column(BuildContext context, int index) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.end,
      children: [
        Text(
          "Id: ${contatos[index].id ?? ""}",
          style: TextStyle(
            fontSize: 14.0,
            fontWeight: FontWeight.bold,
          ),
        ),
        Text(
          "Nome: ${contatos[index].nome ?? ""}",
          style: TextStyle(
            fontSize: 14.0,
            fontWeight: FontWeight.bold,
          ),
        ),
        Text(
          "email: ${contatos[index].email ?? ""}",
          style: TextStyle(
            fontSize: 14.0,
            fontWeight: FontWeight.bold,
          ),
        ),
        Text(
          "telefone: ${contatos[index].telefone ?? ""}",
          style: TextStyle(
            fontSize: 14.0,
            fontWeight: FontWeight.bold,
          ),
        ),
      ],
    );
  }

  dynamic getFile(File file) {
    return file.existsSync()
        ? FileImage(file)
        : const AssetImage("images/pisca.gif");
  }

  Container containerImagem(BuildContext context, int index) {
    return Container(
      height: 80.0,
      width: 80.0,
      decoration: BoxDecoration(
        shape: BoxShape.circle,
        image: DecorationImage(
          image: contatos[index].img != null
              ? this.getFile(File(contatos[index].img))
              : AssetImage("images/pisca.gif"),
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return this
        .scaffold(this.appBar(), this.floatingActionButton(), this.listView());
  }
}

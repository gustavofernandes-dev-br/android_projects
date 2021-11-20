import 'dart:io';
import 'package:flutter/material.dart';
import 'package:projeto_04/model/contato-model.dart';

class Cadastro extends StatefulWidget {
  final ContatoModel contatoUpdate;
  Cadastro({this.contatoUpdate});

  @override
  _CadastroState createState() => _CadastroState();
}

class _CadastroState extends State<Cadastro> {
  int facao = 0;

  ContatoModel contato;
  TextEditingController nomeControler = TextEditingController();
  TextEditingController emailControler = TextEditingController();
  TextEditingController telefoneControler = TextEditingController();

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    this.contato = new ContatoModel();
  }

  void retornarContato() {
    if ((contato?.nome?.isNotEmpty ?? false) &&
        (contato?.email?.isNotEmpty ?? false) &&
        (contato?.telefone?.isNotEmpty ?? false)) {
      Navigator.pop(context, contato);
    } else {
      print("teste");
    }
  }

  Column column() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.stretch,
      children: [
        this.containerImagem(),
        TextField(
          controller: nomeControler,
          keyboardType: TextInputType.name,
          decoration: InputDecoration(
            labelText: "Nome",
          ),
          onChanged: (text) {
            contato.nome = text;
          },
          onSubmitted: (value) {
            print(value);
          },
          onEditingComplete: () {
            print("complete");
          },
        ),
        TextField(
          controller: emailControler,
          keyboardType: TextInputType.text,
          decoration: InputDecoration(
            labelText: "Email",
          ),
          onChanged: (text) {
            contato.email = text;
          },
        ),
        TextField(
          controller: telefoneControler,
          keyboardType: TextInputType.text,
          decoration: InputDecoration(
            labelText: "Telefone",
          ),
          onChanged: (text) {
            contato.telefone = text;
          },
          onSubmitted: (text) {
            retornarContato();
          },
        )
      ],
    );
  }

  dynamic getFile(File file) {
    return file.existsSync()
        ? FileImage(file)
        : const AssetImage("images/pisca.gif");
  }

  Container containerImagem() {
    return Container(
      height: 140.0,
      width: 140.0,
      decoration: BoxDecoration(
        shape: BoxShape.circle,
        image: DecorationImage(
          image: contato.img != null
              ? this.getFile(File(contato.img))
              : AssetImage("images/pisca.gif"),
        ),
      ),
    );
  }

  AppBar appBar() {
    return AppBar(
      title: Text("Novo contato"),
      elevation: 0,
      centerTitle: true,
      backgroundColor: Colors.green,
    );
  }

  FloatingActionButton floatingActionButton() {
    return FloatingActionButton(
      onPressed: () {
        retornarContato();
        /*  banco.saveContato(contato).then((value) {
          contato = value;
          print(value);
        }).catchError((onError) {
          print(onError);
        }); */
      },
      elevation: 0,
      child: Icon(Icons.save),
      backgroundColor: Colors.green,
    );
  }

  Scaffold scaffold(AppBar appBar, FloatingActionButton botao, Widget collumn) {
    return Scaffold(
      appBar: appBar,
      floatingActionButton: botao,
      body: SingleChildScrollView(
        child: Padding(
          padding: EdgeInsets.all(10.0),
          child: collumn,
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return this.scaffold(
      this.appBar(),
      this.floatingActionButton(),
      this.column(),
    );
  }
}

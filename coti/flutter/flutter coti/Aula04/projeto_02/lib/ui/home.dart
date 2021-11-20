import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import "package:projeto_02/services/home-service.dart";

class Home extends StatefulWidget {
  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {
  TextEditingController cepController = TextEditingController();

  Container _container2;
  set container2(Container container) => this._container2 = container;
  Container get container2 => this._container2;

  void getEnderecoFromCep() {
    print(this.cepController.text);
    setState(() {
      this.container2 = this.buildEndereco(this.cepController.text);
    });
  }

  @override
  void initState() {
    super.initState();
    this.container2 = Container(
      padding: EdgeInsets.all(10.0),
      child: Text(
        "Saída",
        style: TextStyle(color: Colors.green, fontSize: 20.0),
      ),
    );
  }

  Image getImage() {
    return Image.asset(
      'imagens/pin.png',
      width: 150.0,
      height: 150.0,
    );
  }

  Column column() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.stretch,
      children: [
        this.getImage(),
        this.textField(),
        Divider(),
        this.raisedButton(),
        this._container2
      ],
    );
  }

  Widget buildEndereco(String cep) {
    return Container(
      padding: EdgeInsets.all(10.0),
      child: FutureBuilder(
          future: HomeService.getEndereco(cep),
          builder: (context, snapshot) {
            switch (snapshot.connectionState) {
              case ConnectionState.none:
              case ConnectionState.waiting:
                return Text("Carregando");
                break;
              default:
                if (snapshot.hasError) {
                  return Text("Erro na consulta");
                } else {
                  if (snapshot.data['erro'] != null) {
                    return Text("Cep não encontrado.");
                  }

                  Map<String, String> endereco = Map.from(snapshot.data);
                  return Center(
                      child: Text(
                    endereco.toString(),
                    style: TextStyle(
                      color: Colors.green,
                      fontSize: 20.0,
                    ),
                  ));
                }
            }
          }),
    );
  }

  TextField textField() {
    return TextField(
      controller: cepController,
      keyboardType: TextInputType.number,
      inputFormatters: [FilteringTextInputFormatter.allow(RegExp("([0-9])"))],
      maxLength: 8,
      decoration: InputDecoration(
          labelText: "CEP",
          labelStyle: TextStyle(
            color: Colors.green[800],
            fontSize: 20.0,
          )),
    );
  }

  Container raisedButton() {
    return Container(
        height: 60.0,
        child: RaisedButton(
            onPressed: () {
              //passa referencia e não executando.
              getEnderecoFromCep();
            },
            color: Colors.green[700],
            child: Text(
              "Efetuar busca!",
              style: TextStyle(
                fontSize: 20.0,
                color: Colors.white,
              ),
            )));
  }

  //barra superior
  AppBar appBar() {
    return AppBar(
      title: Text(
        "Busca de endereço",
        style: TextStyle(color: Colors.white, fontSize: 24.0),
      ),
      backgroundColor: Colors.green[800],
      centerTitle: true,
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: this.appBar(),
      backgroundColor: Colors.white,
      body: SingleChildScrollView(
        padding: EdgeInsets.all(10.0),
        child: column(),
      ),
    );
  }
}

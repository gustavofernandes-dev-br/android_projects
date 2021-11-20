import 'package:flutter/material.dart';

class Home extends StatefulWidget {
  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {
  TextEditingController pesoController = TextEditingController();
  TextEditingController alturaController = TextEditingController();
  double imc = 0.0;
  double peso = 0.0;
  String resposta = "0.0";

  void calcularImc(peso, altura) {
    setState(() {
      this.imc = peso / (altura * altura);
      if (imc < 18.5) {
        this.resposta = "${imc.toStringAsFixed(2)} , MAGREZA";
      } else if (imc >= 18.5 && imc < 25.0) {
        this.resposta = "${imc.toStringAsFixed(2)} , NORMAL";
      } else if (imc >= 25.0 && imc < 30.0) {
        this.resposta = "${imc.toStringAsFixed(2)} , SOBRE PESO";
      } else if (imc >= 30.0 && imc < 40.0) {
        this.resposta = "${imc.toStringAsFixed(2)} , OBESIDADE";
      } else if (imc >= 40) {
        this.resposta = "${imc.toStringAsFixed(2)} , OBESIDADE GRAVE";
      }
    });
  }

  Container container() {
    return Container(
      padding: EdgeInsets.all(10.0),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.end,
        children: [
          Text(
            "Saída: $resposta",
            style: Theme.of(context).textTheme.headline1,
          )
        ],
      ),
    );
  }

  void pesoChanged(String texto) {
    this.peso = double.parse(this.pesoController.text);
  }

  void alturaChanged(String texto) {
    double altura = double.parse(this.alturaController.text);
    print(altura);

    if (this.pesoController.text.isNotEmpty &&
        this.alturaController.text.isNotEmpty &&
        this.peso > 0 &&
        altura > 0) {
      this.calcularImc(this.peso, altura);
    }
  }

  AppBar appBar() {
    return AppBar(
      title: Text("Calculadora IMC"),
      backgroundColor: Colors.amber,
      centerTitle: true,
    );
  }

  Column collumn() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.stretch,
      children: [
        this.buildTextField(
          "Peso",
          "Kg",
          pesoController,
          pesoChanged,
        ),
        Divider(),
        this.buildTextField(
          "Altura",
          "Cm",
          alturaController,
          alturaChanged,
        ),
        Divider(),
        container()
      ],
    );
  }

  TextField buildTextField(String label, String prefix,
      TextEditingController controller, Function f) {
    return TextField(
      controller: controller,
      decoration: InputDecoration(
        labelText: label,
        prefixText: prefix,
        labelStyle: TextStyle(
          fontSize: 25.0,
          color: Colors.amber,
        ),
        border: OutlineInputBorder(),
      ),
      keyboardType: TextInputType.number,
      onChanged: f,
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: appBar(),
      body: SingleChildScrollView(
        padding: EdgeInsets.all(10.0),
        child: this.collumn(),
      ),
    );
  }
}

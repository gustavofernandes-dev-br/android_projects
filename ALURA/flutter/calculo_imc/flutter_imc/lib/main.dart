import 'package:flutter/material.dart';

void main() {
  runApp(Home());
}

class Home extends StatefulWidget {
  @override
  _State createState() => _State();
}

class _State extends State<Home> {
  TextEditingController _Altura = TextEditingController();
  TextEditingController _Peso = TextEditingController();
  String _Texto = "Informe os dados";

  GlobalKey<FormState> _formKey = GlobalKey<FormState>();

  void _Reset() {
    _Altura.text = "";
    _Peso.text = "";
    _Texto = "Informe os dados";
    _formKey = GlobalKey<FormState>();
  }

  void _CalculaIMC() {
    double altura = double.parse(_Altura.text) /100;
    double peso = double.parse(_Peso.text) ;
    double imc = peso / (altura * altura);
    print(imc);
    setState(() {
      if (imc < 18.6) {
          _Texto = "Abaixo do peso, IMC: ${imc.toStringAsPrecision(2)}";
      }
      else if (imc >= 18.6 && imc < 24.9)
      {
          _Texto = "Peso ideal, IMC: ${imc.toStringAsPrecision(2)}";
      }
      else if (imc >= 24.9 && imc < 29.9)
      {
          _Texto = "Levemente acima do peso, IMC: ${imc.toStringAsPrecision(2)}";
      }
      else if (imc >= 29.9 && imc < 34.9)
      {
        _Texto = "Obesidade Grau 1, IMC: ${imc.toStringAsPrecision(2)}";
      }
      else if (imc >= 34.9 && imc < 39.9)
      {
        _Texto = "Obesidade Grau 2, IMC: ${imc.toStringAsPrecision(2)}";
      }
      else if(imc >= 40)
      {
        _Texto = "Obesidade Grau 3, IMC: ${imc.toStringAsPrecision(2)}";
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text("Calculadora IMC"),
          centerTitle: true,
          backgroundColor: Colors.green,
          actions: [
            IconButton(
                onPressed: () {
                  setState(() {
                    _Reset();
                  });
                },
                icon: Icon(Icons.refresh))
          ],
        ),
        body: Form(
          key: _formKey,
            child:  Padding(
              padding: const EdgeInsets.all(32.0),
              child: SingleChildScrollView(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.stretch,
                  children: [
                    Icon(
                      Icons.person,
                      size: 120.0,
                      color: Colors.green,
                    ),
                    TextFormField(
                      controller: _Peso,
                      keyboardType: TextInputType.number,
                      decoration: InputDecoration(
                        labelText: "Peso (kg)",
                        labelStyle: TextStyle(color: Colors.green, fontSize: 16.0),
                      ),
                      textAlign: TextAlign.center,
                      style: TextStyle(color: Colors.green, fontSize: 24.0),
                      validator: (value){
                        if(value!.isEmpty)
                          {
                            return "Preencha seu peso.";
                          }
                      },
                    ),
                    TextFormField(
                      controller: _Altura,
                      keyboardType: TextInputType.number,
                      decoration: InputDecoration(
                        labelText: "Altura (cm)",
                        labelStyle: TextStyle(color: Colors.green, fontSize: 16.0),
                      ),
                      textAlign: TextAlign.center,
                      style: TextStyle(color: Colors.green, fontSize: 24.0),
                        validator: (value){
                          if(value!.isEmpty)
                          {
                            return "Preencha sua altura.";
                          }
                        }
                    ),
                    Padding(
                      padding: EdgeInsets.only(top: 10.0, bottom: 10.0),
                      child: Container(
                        height: 50.0,
                        child: RaisedButton(
                          onPressed: (){
                            if(_formKey.currentState!.validate())
                              {
                                _CalculaIMC();
                              }
                          },
                          color: Colors.green,
                          child: Text(
                            "Calcular",
                            style: TextStyle(color: Colors.white, fontSize: 25.0),
                          ),
                        ),
                      ),
                    ),
                    Text(
                      "$_Texto",
                      textAlign: TextAlign.center,
                      style: TextStyle(color: Colors.green, fontSize: 16.0),
                    )
                  ],
                ),
              ),
            )
        ),
      ),
    );
  }
}



import 'package:flutter_app/Component/TextBox.dart';
import 'package:flutter/material.dart';
import 'package:flutter_app/Model/Transferencia.dart';

class FormularioTransferenciaState extends State<FormularioTransferencia>
{
  final TextEditingController _controllerConta = new TextEditingController();
  final TextEditingController _controllerValor = new TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: Text("Cadastrotransferência"),),
        body: SingleChildScrollView(
          child: Column(
            children: [
              TextBox(
                textoSuperior: "Conta",
                controlador: _controllerConta,
                dica_hint: "0000",
              ),
              TextBox(
                textoSuperior: "Valor",
                controlador: _controllerValor,
                dica_hint: "0,00",
                icone: Icons.monetization_on,
              )
              ,
              ElevatedButton(onPressed:  ()  {

                final double? valor = double.tryParse(_controllerValor.text);
                final int? conta = int.tryParse(_controllerConta.text);
                Transferencia tranfere;
                if(conta != null && valor != null) {
                  tranfere = new Transferencia(
                      valor, conta);
                  //debugPrint(tranfere.toString());
                  Navigator.pop(context, tranfere);

                  // Scaffold.of(context).showSnackBar(SnackBar(content: Text(tranfere.toString())));
                }
              } , child: Text("Confirmar"),)
            ],
          ),
        )
    );
  }

}

class FormularioTransferencia extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    // TODO: implement createState
    return FormularioTransferenciaState();
  }
}
import 'package:flutter/material.dart';

class TextBox extends StatelessWidget {

  final String? textoSuperior;
  final String? dica_hint;
  final TextEditingController? controlador;
  final IconData? icone;

  TextBox({this.textoSuperior, this.dica_hint, this.controlador, this.icone});

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Padding(
      padding: const EdgeInsets.all(16.0),
      child: TextField(
        controller: controlador,
        keyboardType: TextInputType.number,
        maxLength: 4,
        style: TextStyle(fontSize: 24.0),
        decoration: InputDecoration(
            labelText: textoSuperior,
            hintText: dica_hint,
            icon: icone != null ? Icon(icone): null
        ),
      ),
    );
  }
}


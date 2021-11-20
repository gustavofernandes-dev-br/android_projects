import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:projeto_05/ui/home.dart';

void main() {
  runApp(MaterialApp(
    home: Home(),
    debugShowCheckedModeBanner: false,
    title: "Calculadora de IMC",
    theme: ThemeData(
      hintColor: Colors.amber,
      primaryColor: Colors.white,
      inputDecorationTheme: InputDecorationTheme(
        focusedBorder: OutlineInputBorder(
            borderSide: BorderSide(
          color: Colors.amber,
        )),
        enabledBorder: OutlineInputBorder(
          borderSide: BorderSide(
            color: Colors.black,
          ),
        ),
      ),
      textTheme: const TextTheme(
        headline1: TextStyle(
            fontSize: 20.0, color: Colors.amber, fontWeight: FontWeight.bold),
        headline6: TextStyle(fontSize: 36.0, fontStyle: FontStyle.italic),
        bodyText2: TextStyle(fontSize: 14.0, fontFamily: 'Hind'),
      ),
    ),
  ));
}

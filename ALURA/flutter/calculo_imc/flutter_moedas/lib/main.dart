//flutter run --no-sound-null-safety
import 'dart:ffi';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'dart:async';

void main() async {
  //print(await BuscarDados());

  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _State createState() => _State();
}


class _State extends State<MyApp> {

  @override
  void initState()
  {
    super.initState();


  }

  double dolar = 0.0;
  double euro = 0.0;

  final controlerReal = TextEditingController();
  final controlerEuro = TextEditingController();
  final controlerDolar = TextEditingController();

  void _realChanged(String value)
  {

    double real = double.parse(value);
    double _euro = double.parse(euro.toStringAsPrecision(2));
    double _dolar = double.parse(dolar.toStringAsPrecision(2));

    print("$real $dolar $euro" );

      controlerEuro.text = ( real /_euro).toStringAsPrecision(2);
      controlerDolar.text = (real/_dolar).toStringAsPrecision(2);


  }
  void _euroChanged(String value)
  {
    print(value);
  }

  void _dolarChanged(String value)
  {
    print(value);
  }



  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
          hintColor: Colors.amber,
          primaryColor: Colors.white,
          inputDecorationTheme: InputDecorationTheme(
            enabledBorder:
                OutlineInputBorder(borderSide: BorderSide(color: Colors.white)),
            focusedBorder:
                OutlineInputBorder(borderSide: BorderSide(color: Colors.amber)),
            hintStyle: TextStyle(color: Colors.amber),
          )),
      home: Scaffold(
        backgroundColor: Colors.black,
        appBar: AppBar(
          centerTitle: true,
          title: Text("\$ Conversor de moeda \$"),
          backgroundColor: Colors.amber,
          actions: [
            IconButton(
                onPressed: () {
                  BuscarDados();
                },
                icon: Icon(Icons.account_circle_rounded))
          ],
        ),
        body: FutureBuilder<Map>(
          future: BuscarDados(),
          builder: (context, snapshot) {
            if (snapshot.connectionState == ConnectionState.none ||
                snapshot.connectionState == ConnectionState.waiting) {
              return Center(
                child: CircularProgressIndicator(),
              );
            } else {
              if (snapshot.hasError) {
                return Center(
                  child: Text(
                    "Erro na requisição",
                    style: TextStyle(color: Colors.amber, fontSize: 25.0),
                  ),
                );
              } else {

                if( snapshot.data != null) {
                   euro = snapshot
                      .data?["results"]["currencies"]["EUR"]["buy"] ?? 0.0;
                   dolar = snapshot
                       .data?['results']['currencies']['USD']["buy"] ?? 0.0;
                }


                return SingleChildScrollView(
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.stretch,
                    children: [
                      Icon(Icons.monetization_on,
                          size: 150.0, color: Colors.amber),
                      buildTextField("Reais","R\$",controlerReal,_realChanged),
                      Divider(),
                      buildTextField("Dolar","USD", controlerDolar,_dolarChanged),
                      Divider(),
                      buildTextField("Euro", "EUR", controlerEuro, _euroChanged)
                    ],
                  ),
                );
              }
            }
          },
        ),
      ),
    );
  }
}

Future<Map>? BuscarDados() async {
  var url = Uri.https('api.hgbrasil.com', '/finance', {'q': '{https}'});
  http.Response response = await http.get(url);
 // print(json.decode(response.body)['results']['currencies']['USD']);
  return json.decode(response.body);
}

Widget buildTextField(String label, String prefix
    , TextEditingController controlador, Function f)
{
  return TextField(
    keyboardType: TextInputType.numberWithOptions(decimal: true),
    controller: controlador,
    onChanged: (text)
    {
      f(text);
    },
    decoration: InputDecoration(
        labelText: label,
        labelStyle: TextStyle(color: Colors.amber),
        border: OutlineInputBorder(gapPadding: 2.0),
        prefixText: prefix),
    style: TextStyle(color: Colors.amber, fontSize: 25.0),
  );
}

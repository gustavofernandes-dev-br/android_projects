import 'package:flutter/material.dart';

import 'Screen/transferencia/Lista.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget
{
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return MaterialApp(
      theme:  ThemeData.from(
          colorScheme: ColorScheme.fromSwatch(primarySwatch: Colors.amber)
              .copyWith(
            secondary: Colors.green,
          ),
          textTheme: TextTheme(bodyText1: TextStyle(color: Colors.purple))
      )
      ,
      home: ListaTranferencia(),
    );
  }

}








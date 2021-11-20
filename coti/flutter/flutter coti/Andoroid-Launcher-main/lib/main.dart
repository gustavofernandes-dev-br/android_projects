import 'package:flutter/material.dart';
import 'package:launcher/home_page.dart';

void main() => runApp(MyApp(key: UniqueKey(),));

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: HomePage(key: UniqueKey(),),
    );
  }
}


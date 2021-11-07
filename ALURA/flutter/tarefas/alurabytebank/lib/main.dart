import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          centerTitle: true,
          backgroundColor: Colors.amber[500],
          title: Text(
            "DashBoard",
            style: TextStyle(color: Colors.grey[800]),
          ),
        ),
        body: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(
              "Hello World",
              style: TextStyle(
                  fontSize: 32.0,
                  letterSpacing: 10.8,
                  fontWeight: FontWeight.w100,
                  color: Colors.green[700]),
            ),
            Divider(
              color: Colors.transparent,
            ),
            Text(
              "Hello World",
              style: TextStyle(
                  fontSize: 24.0,
                  fontFamily: 'Roboto',
                  letterSpacing: 0.8,
                  fontWeight: FontWeight.w700,
                  color: Colors.green[700]),
            ),
            Divider(
              color: Colors.transparent,
            ),
            Divider(
              color: Colors.transparent,
            ),
            Divider(
              color: Colors.transparent,
            ),
            Image.network(
              "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
            ),
            Divider(),
            Divider(),
            Divider(),
    
          ],
        ),
      ),
    );
  }
}

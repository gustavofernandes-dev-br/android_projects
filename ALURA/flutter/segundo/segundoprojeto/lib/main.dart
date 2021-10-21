import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(home: Home());
  }
}

class Home extends StatefulWidget {
  @override
  _State createState() => _State();
}

class _State extends State<Home> {

  int _contador = 0;

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        Text(
          "Pessoas : $_contador",
          style: TextStyle(
              fontSize: 24.0, fontWeight: FontWeight.bold, color: Colors.white),
        ),
        Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: FlatButton(
                  onPressed: () {
                    setState(() {
                      _contador++;
                    });


                  },
                  child: Text(
                    "+1",
                    style: TextStyle(fontSize: 20.0, color: Colors.white),
                  )),
            ),
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: FlatButton(
                  onPressed: () {
                    setState(() {
                      _contador--;
                    });

                  },
                  child: Text(
                    "-1",
                    style: TextStyle(fontSize: 20.0, color: Colors.white),
                  )),
            )
          ],
        ),
        Text(
          "Pode entrar",
          style: TextStyle(
              fontSize: 20.0, fontWeight: FontWeight.bold, color: Colors.white),
        ),
      ],
    );
  }
}

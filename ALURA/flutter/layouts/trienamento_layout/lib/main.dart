import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        theme: ThemeData(),
        home: Scaffold(
            appBar: AppBar(
              centerTitle: true,
              title: Text("Treinamento layout"),
            ),
            body: Column(
              children: [
                Container(
                    height: 100.0,
                    color: Colors.amber[100],
                    padding: EdgeInsets.all(10.0),
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.end,
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: [
                        Expanded(flex: 4, child: Icon(Icons.home)),
                        Expanded(flex: 3, child: Icon(Icons.schedule)),
                        Expanded(flex: 3, child: Icon(Icons.http)),
                        Expanded(
                            flex: 2,
                            child: Column(
                              children: [
                                Expanded(
                                    flex: 2,
                                    child: Container(
                                      decoration: BoxDecoration(
                                        color: Colors.purple[800],
                                        border: Border.all(),
                                        borderRadius: BorderRadius.all(Radius.circular(9.0)),
                                      ),

                                    ))
                              ],
                            ))
                      ],
                    )
                ),
                Row(
                  children: [
                    Expanded(flex:7, child: Container(
                        height: 100.0,
                        color: Colors.green)),
                    Expanded(flex:3, child: Container(
                        height: 100.0,
                        color: Colors.yellow)),

                  ],
                ),
                Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: Stack(

                    children: <Widget>[

                      Align(
                        alignment: Alignment.topRight,
                        child: Container(
                          width: 700,
                          height: 100,
                          color: Colors.green,
                        ),
                      ),
                      Container(

                        width: 100,
                        height: 200,
                        color: Colors.red,
                      ),
                      Positioned(
                        left: 60.0,
                        top: 10.0,
                        child: Container(
                          width: 50,
                          height: 50,
                          color: Colors.blue,
                        ),
                      ),
                    ],
                  ),
                ),Stack(
                  children: <Widget> [
                    // primeiro filho na pilha
                    Image.network(
                        'https://pbs.twimg.com/media/D4FT8OZW4AAcmGc.jpg'),
                    // segundo filho na pilha
                    Padding(
                      padding: EdgeInsets.all(16.0),
                      child: Text(
                        'Imagem do buraco negro no centro da galáxia M87',
                        style: TextStyle(fontSize: 20, color: Colors.white),
                      ),
                    ),
                  ],
                )

              ],
            )));
  }
}

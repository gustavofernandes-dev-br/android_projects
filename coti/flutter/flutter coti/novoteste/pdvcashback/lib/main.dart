import 'package:flutter/material.dart';

void main() {
  runApp(PdvCashBack());
}

class PdvCashBack extends StatefulWidget {
  @override
  _PdvCashBackState createState() => _PdvCashBackState();
}

class _PdvCashBackState extends State<PdvCashBack> {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        home: Scaffold(
          appBar: AppBar(
            elevation: 0.0,
            backgroundColor: Colors.purple[700],
          ),
          drawer: Drawer(
              child: Text("")// Populate the Drawer in the next step.
          ),
      body: Container(
        color: Colors.white,
        alignment: Alignment.center,
        height: 250,
        width: 1000,
        child: Card(
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.only(bottomLeft: Radius.circular(35.0), bottomRight: Radius.circular(35.0)),
          ),
          elevation: 15,
          margin: EdgeInsets.all(0.0),
          color: Colors.purple[700],
          child: Center(
              child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Text("R\$",
                      style: TextStyle(
                          color: Colors.white,
                          fontSize: 20.0,
                          fontWeight: FontWeight.normal),
                      textDirection: TextDirection.ltr),
                  Text("235,00",
                      style: TextStyle(
                          color: Colors.white,
                          fontSize: 40.0,
                          fontWeight: FontWeight.normal),
                      textDirection: TextDirection.ltr),
                ],
              ),
              Text("Total de cashback",
                  style: TextStyle(
                      color: Colors.white,
                      fontSize: 15.0,
                      fontWeight: FontWeight.normal),
                  textDirection: TextDirection.ltr),
              Container(
                margin: EdgeInsets.all(5),
                child: FlatButton(
                  child: Text(
                    'Usar crédito',
                    style: TextStyle(fontSize: 20.0, fontWeight: FontWeight.bold, color: Colors.grey[300]),
                  ),
                  color: Colors.purple[600],
                  textColor: Colors.white,
                  onPressed: () {

                  },
                ),
              )
            ],
          )),
        ),
      ),
          bottomNavigationBar: BottomNavigationBar(
            backgroundColor: Colors.purple[700],
            items: const <BottomNavigationBarItem>[
              BottomNavigationBarItem(
                icon: Icon(Icons.home, color: Colors.white,),
                title: Text("Home", style: TextStyle(color: Colors.white),),
              ),
              BottomNavigationBarItem(
                icon: Icon(Icons.monetization_on,size: 50.0,color: Colors.white,),
                title: Text("", style: TextStyle(color: Colors.white),),
              ),
              BottomNavigationBarItem(
                icon: Icon(Icons.notifications,color: Colors.white,),
                title: Text("Alertas", style: TextStyle(color: Colors.white)),
              ),
            ],

            selectedItemColor: Colors.amber[800],
           // onTap: (){},
          ),
        )
    );
  }
}

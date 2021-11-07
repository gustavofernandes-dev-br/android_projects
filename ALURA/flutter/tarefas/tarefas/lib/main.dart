import 'dart:convert';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:path_provider/path_provider.dart';

void main() {
  runApp(Home());
}

class Home extends StatefulWidget {
  const Home({Key? key}) : super(key: key);

  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {
  Map<String, dynamic>? _ultimoRemovido;
  int? _ultimoRemovidoPos;

  Future<Null> _refresh() async{
    await Future.delayed(Duration(seconds: 1));
    setState(() {
      _toDoList.sort((a,b){
        if(a["ok"] && !b["ok"]) return 1;
        else if(!a["ok"] && b["ok"]) return -1;
        else return 0;
      });
      _saveDataFile();
    });

  }

  @override
  void initState() {
    super.initState();
    _readData().then((value) {
      setState(() {
        _toDoList = json.decode(value);
      });
    });
  }

  List _toDoList = [];
  final _toDoController = TextEditingController();

  void _addTodo() {
    setState(() {
      Map<String, dynamic> newTodo = Map();
      newTodo["title"] = _toDoController.text;
      newTodo["ok"] = false;
      _toDoList.add(newTodo);
      _saveDataFile();
      _toDoController.text = "";
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text("Lista tarefas"),
          centerTitle: true,
          backgroundColor: Colors.blueAccent,
        ),
        body: Column(
          children: [
            Container(
              padding: EdgeInsets.fromLTRB(17.0, 1.0, 7.0, 0.0),
              child: Row(
                children: [
                  Expanded(
                      child: TextField(
                    controller: _toDoController,
                    decoration: InputDecoration(
                        labelText: "Nova tarefa",
                        labelStyle: TextStyle(color: Colors.blueAccent)),
                  )),
                  RaisedButton(
                    onPressed: _addTodo,
                    color: Colors.blueAccent,
                    child: Text("ADD"),
                    textColor: Colors.white,
                  )
                ],
              ),
            ),
            Expanded(
                child: RefreshIndicator(
                  onRefresh: _refresh,
                  child:  ListView.builder(
                    itemBuilder: buildItem,
                    itemCount: _toDoList.length,
                    padding: EdgeInsets.only(top: 10.0),
                  ),
                )
            )
          ],
        ),
      ),
    );
  }

  Widget buildItem(context, index) {
    /*  return ListTile(
                  title: Text(_toDoList[index]),
                );*/
    return Dismissible(
        key: Key(DateTime.now().microsecondsSinceEpoch.toString()),
        direction: DismissDirection.startToEnd,
        onDismissed: (d) {
          setState(() {
            _ultimoRemovido = Map.from(_toDoList[index]);
            _ultimoRemovidoPos = index;
            _toDoList.removeAt(index);
            _saveDataFile();
            final snack = SnackBar(
              content: Text("Removido"),
              duration: Duration(seconds: 3),
              action: SnackBarAction(
                label: "Desfazer",
                onPressed: () {
                  setState(() {
                    _toDoList.insert(_ultimoRemovidoPos!, _ultimoRemovido);
                    _saveDataFile();
                  });
                },
              ),
            );
            Scaffold.of(context).removeCurrentSnackBar();
            Scaffold.of(context).showSnackBar(snack);
          });
        },
        background: Container(
            color: Colors.red,
            child: Align(
              alignment: Alignment(-9.0, 0.0),
              child: Icon(Icons.delete, color: Colors.white),
            )),
        child: CheckboxListTile(
            title: Text(_toDoList[index]["title"]),
            value: _toDoList[index]["ok"],
            secondary: CircleAvatar(
              child: Icon(_toDoList[index]["ok"] ? Icons.check : Icons.error),
            ),
            onChanged: (v) {
              setState(() {
                _toDoList[index]["ok"] = v;
                _saveDataFile();
              });
            }));
  }

  Future<File> _getFile() async {
    final diretorio = await getApplicationDocumentsDirectory();
    return File("${diretorio.path}/data.json");
  }

  Future<File> _saveDataFile() async {
    String data = json.encode(_toDoList);
    final file = await _getFile();
    return file.writeAsString(data);
  }

  Future<String> _readData() async {
    try {
      final file = await _getFile();
      return file.readAsString();
    } catch (e) {
      return "";
    }
  }
}

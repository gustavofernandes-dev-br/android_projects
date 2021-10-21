
import 'package:flutter/material.dart';
import 'package:flutter_app/Model/Transferencia.dart';
import 'package:flutter_app/Screen/transferencia/Transferencia.dart';

class TransferenciaItem extends StatelessWidget {
  final Transferencia _transferencia;
  TransferenciaItem(this._transferencia);
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Card(child: ListTile(
      leading: Icon(Icons.monetization_on),
      title: Text(_transferencia.valor.toString()),
      subtitle: Text(_transferencia.conta.toString()),
    ));
  }
}

class ListaTranferencia extends StatefulWidget {
  final List<Transferencia> _transferencias = [];
  @override
  State<StatefulWidget> createState() {
    // TODO: implement createState
    return ListaTransferenciaState();
  }

}

class ListaTransferenciaState extends State<ListaTranferencia>
{
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(

      body: ListView.builder(
        itemCount: widget._transferencias.length,
        itemBuilder: (context, posicao)
        {
          final Transferencia item = widget._transferencias[posicao];
          return TransferenciaItem(item);
        },
      ),
      appBar: AppBar(
        title: Text("Transferencias"),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          final Future<Transferencia?> future =
          Navigator.push(context, MaterialPageRoute(builder: (context) {
            return FormularioTransferencia();
          })
          );
          future.then((value) => {
            if(value != null)
              {
                setState(() {
                  widget._transferencias.add(value);
                })

              }
          });

        },
        child: Icon(Icons.add),
      ),
    );
  }
}
import 'package:flutter/material.dart';

class TreinamentoLayout extends StatefulWidget {
  @override
  _TreinamentoLayoutState createState() => _TreinamentoLayoutState();
}

class _TreinamentoLayoutState extends State<TreinamentoLayout> {
  var linha = Row(
    crossAxisAlignment: CrossAxisAlignment.start,
    children: [
      Column(
        children: [
          SizedBox(
            child: Image.asset("images\pisca.gif"),
            width: 440,
          )
        ],
      )
    ],
  );

  @override
  Widget build(BuildContext context) {
    return Container();
  }
}

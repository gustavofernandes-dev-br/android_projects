import 'dart:convert';
import 'dart:async';
import 'package:http/http.dart' as http;

class HomeService {
  static String path = "https://viacep.com.br/ws/";

  static Future<Map> getEndereco(String cep) async {
    http.Response response = await http.get(path + cep + "/json/");
    print(response.statusCode);
    if (response.statusCode == 200)
      return json.decode(response.body);
    else
      return null;
  }
}

import 'package:projeto_04/model/contato-model.dart';
import 'package:projeto_04/util.dart';
import 'package:sqflite/sqflite.dart';
import 'package:path/path.dart';

class ContatoDB {
  //Singleton
  static ContatoDB _instance = ContatoDB.internal();
  factory ContatoDB() => _instance;
  ContatoDB.internal();

  //SET AND GET
  Database _db;

  Future<Database> get db async {
    if (_db != null) {
      return _db;
    } else {
      _db = await initDb();
      return _db;
    }
  }

  Future<Database> initDb() async {
    final dataBasePath = await getDatabasesPath();
    final path = join(dataBasePath, "contatos.db");

    final String sSql =
        "CREATE TABLE $contatoTable($idColumn INTEGER PRIMARY KEY, $nomeColumn TEXT, " +
            " $emailColumn TEXT, $telefoneColumn TEXT, $imgColumn TEXT)";

    return await openDatabase(path, version: 1,
        onCreate: (Database db, int newerVersion) async {
      await db.execute(sSql);
    });
  }

  Future<ContatoModel> saveContato(ContatoModel contato) async {
    Database dbConexao = await db;
    //devolve o incremento
    contato.id = await dbConexao.insert(contatoTable, contato.toMap());
    return contato;
  }

  Future<List<ContatoModel>> getContatos() async {
    Database dbConexao = await db;
    List<Map> maps = await dbConexao.rawQuery("SELECT * FROM $contatoTable");
    List<ContatoModel> contatos = new List<ContatoModel>();
    for (var item in maps) {
      contatos.add(new ContatoModel.fromMap(item));
    }

    if (contatos.length > 0) {
      return contatos;
    } else {
      return null;
    }
  }

  Future<ContatoModel> getContato(int id) async {
    Database dbConexao = await db;
    List<Map> maps = await dbConexao.query(contatoTable,
        columns: [
          idColumn,
          nomeColumn,
          emailColumn,
          telefoneColumn,
          imgColumn,
        ],
        where: "$idColumn = ?",
        whereArgs: [id],
        orderBy: "$idColumn");

    if (maps.length > 0) {
      return ContatoModel.fromMap(maps.first);
    } else {
      return null;
    }
  }

  Future<int> deleteContato(int id) async {
    Database dbConexao = await db;
    //quantidade de registros modificada
    return await dbConexao.delete(
      contatoTable,
      where: "$idColumn = ?",
      whereArgs: [id],
    );
  }

  Future<int> updateContato(ContatoModel contato) async {
    Database dbConexao = await db;
    //quantidade de registros modificada
    return await dbConexao.update(contatoTable, contato.toMap(),
        where: "$idColumn = ?", whereArgs: [contato.id]);
  }
//encode do Json

  //fechar base
  Future close() async {
    Database dbConexao = await db;
    dbConexao.close();
  }

  Future<int> getQuantidadeContatos() async {
    Database dbConexao = await db;
    return Sqflite.firstIntValue(
      await dbConexao.rawQuery("SELECT COUNT(*) FROM $contatoTable"),
    );
  }
}

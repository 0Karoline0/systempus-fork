import 'package:app_flutter_horario_mobile/home.dart';
import 'package:app_flutter_horario_mobile/home_adicionar_disciplina.dart';
import 'package:app_flutter_horario_mobile/home_disciplina.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'SysTempus',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const HomePage(),
    );
  }
}

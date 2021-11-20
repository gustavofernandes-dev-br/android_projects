import 'dart:convert';
import 'dart:ui';

import 'package:device_apps/device_apps.dart';
import 'package:flutter/material.dart';

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  @override
  Widget build(BuildContext context) {
    return Stack(
      children: [
        Wallpaper(
          0,
          key: UniqueKey(),
        ),
        Scaffold(
            backgroundColor: Colors.transparent,
            body: Container(
              color: Colors.transparent,
              child: PageView(
                children: [
                  Principal(
                    key: UniqueKey(),
                  ),
                  Stack(
                    children: [
                      BackdropFilter(
                        filter: ImageFilter.blur(sigmaX: 5, sigmaY: 5),
                        child: Container(
                          color: Colors.black.withOpacity(0),
                        ),
                      ),
                      FutureBuilder(
                          future: DeviceApps.getInstalledApplications(
                            includeSystemApps: true,
                            onlyAppsWithLaunchIntent: true,
                            includeAppIcons: true,
                          ),
                          builder: (contex, snapshot) {
                            if (snapshot.connectionState ==
                                ConnectionState.done) {
                              List<Application> allApps =
                                  snapshot.data as List<Application>;
                              allApps.sort((a, b) => a.appName.toLowerCase().compareTo(b.appName.toLowerCase()));
                              return AppGrid(
                                allApps,
                                key: UniqueKey(),
                              );
                            } else {
                              return Loading(
                                key: UniqueKey(),
                              );
                            }
                          })
                    ],
                  )
                ],
              ),
            ))
      ],
    );
  }
}

class Wallpaper extends StatelessWidget {
  final double blur;
  const Wallpaper(this.blur, {Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return Center(
        child: ImageFiltered(
      imageFilter: ImageFilter.blur(sigmaX: blur, sigmaY: blur),
      child: Image.asset("images/img2.jpg"),
    ));
  }
}

class AppGrid extends StatelessWidget {
  final List<Application> allApps;
  const AppGrid(this.allApps, {Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return GridView.count(
      crossAxisCount: 3,
      children: List.generate(allApps.length, (index) {
        return GestureDetector(
          onTap: () => DeviceApps.openApp(allApps[index].packageName),
          child: Padding(
            padding: const EdgeInsets.all(8.0),
            child: Column(
              children: [
                Image.memory(
                  (allApps[index] as ApplicationWithIcon).icon,
                  width: 64,
                ),
                SizedBox(
                  key: UniqueKey(),
                  height: 15,
                ),
                Text(
                  allApps[index].appName,
                  overflow: TextOverflow.ellipsis,
                  style: TextStyle(color: Colors.white),
                )
              ],
            ),
          ),
        );
      }),
    );
  }
}

class Loading extends StatelessWidget {
  const Loading({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Center(
      key: UniqueKey(),
      child: CircularProgressIndicator(
        key: UniqueKey(),
      ),
    );
  }
}

class Principal extends StatelessWidget {
  const Principal({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
        /* child: Wallpaper(
        0,
        key: UniqueKey(),
      ), */
        );
  }
}

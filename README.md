# ijkplayer

[![pub package](https://img.shields.io/pub/v/flutter_ijkplayer.svg)](https://pub.dartlang.org/packages/flutter_ijkplayer)

ijkplayer,通过纹理的方式接入 [bilibili/ijkplayer](https://github.com/bilibili/ijkplayer)

使用前请完整阅读本 README 并参阅 example/lib/main.dart

有关 android 可能跑不起来的问题会详细解释

iOS 模拟器不显示图像,所以调试请使用真机(iOS10 iOS 12.1.4 亲测可用,其他版本有问题可反馈)
android 模拟器 mac android sdk 自带的 emulator(API28 android9)可用,其他类型的没有亲测不保证

- android: 我这里 sdk 自带的模拟器可用(音视频均正常)
- iOS: 库中包含了真机和模拟器的库文件,但是模拟器有声音,无图像

在正式使用前,可以先 star 一下仓库, download 代码跑一下 example 尝试 (clone 也可以)

- [ijkplayer](#ijkplayer)
  - [English Readme](#english-readme)
  - [安装](#%e5%ae%89%e8%a3%85)
  - [原生部分说明](#%e5%8e%9f%e7%94%9f%e9%83%a8%e5%88%86%e8%af%b4%e6%98%8e)
    - [自定义编译和原生部分源码](#%e8%87%aa%e5%ae%9a%e4%b9%89%e7%bc%96%e8%af%91%e5%92%8c%e5%8e%9f%e7%94%9f%e9%83%a8%e5%88%86%e6%ba%90%e7%a0%81)
    - [iOS](#ios)
      - [运行慢的问题](#%e8%bf%90%e8%a1%8c%e6%85%a2%e7%9a%84%e9%97%ae%e9%a2%98)
    - [Android](#android)
  - [入门示例](#%e5%85%a5%e9%97%a8%e7%a4%ba%e4%be%8b)
  - [使用](#%e4%bd%bf%e7%94%a8)
    - [设置](#%e8%ae%be%e7%bd%ae)
    - [关于销毁](#%e5%85%b3%e4%ba%8e%e9%94%80%e6%af%81)
    - [控制器的使用](#%e6%8e%a7%e5%88%b6%e5%99%a8%e7%9a%84%e4%bd%bf%e7%94%a8)
      - [设置资源](#%e8%ae%be%e7%bd%ae%e8%b5%84%e6%ba%90)
      - [播放器的控制](#%e6%92%ad%e6%94%be%e5%99%a8%e7%9a%84%e6%8e%a7%e5%88%b6)
      - [获取播放信息](#%e8%8e%b7%e5%8f%96%e6%92%ad%e6%94%be%e4%bf%a1%e6%81%af)
      - [截取视频帧](#%e6%88%aa%e5%8f%96%e8%a7%86%e9%a2%91%e5%b8%a7)
      - [资源监听](#%e8%b5%84%e6%ba%90%e7%9b%91%e5%90%ac)
      - [倍速播放](#%e5%80%8d%e9%80%9f%e6%92%ad%e6%94%be)
      - [IjkStatus 说明](#ijkstatus-%e8%af%b4%e6%98%8e)
      - [自定义 Option](#%e8%87%aa%e5%ae%9a%e4%b9%89-option)
        - [IjkOptionCategory](#ijkoptioncategory)
        - [设置软解码](#%e8%ae%be%e7%bd%ae%e8%bd%af%e8%a7%a3%e7%a0%81)
      - [释放资源](#%e9%87%8a%e6%94%be%e8%b5%84%e6%ba%90)
    - [自定义控制器 UI](#%e8%87%aa%e5%ae%9a%e4%b9%89%e6%8e%a7%e5%88%b6%e5%99%a8-ui)
    - [自定义纹理界面](#%e8%87%aa%e5%ae%9a%e4%b9%89%e7%ba%b9%e7%90%86%e7%95%8c%e9%9d%a2)
    - [根据当前状态构建一个 widget](#%e6%a0%b9%e6%8d%ae%e5%bd%93%e5%89%8d%e7%8a%b6%e6%80%81%e6%9e%84%e5%bb%ba%e4%b8%80%e4%b8%aa-widget)
  - [进度](#%e8%bf%9b%e5%ba%a6)
  - [LICENSE](#license)

## English Readme

https://github.com/CaiJingLong/flutter_ijkplayer/blob/master/README-EN.md

## 安装

[![pub package](https://img.shields.io/pub/v/flutter_ijkplayer.svg)](https://pub.dartlang.org/packages/flutter_ijkplayer)

最新版本请查看 pub

pubspec.yaml

```yaml
dependencies:
  flutter_ijkplayer: ${lastes_version}
```

## 原生部分说明

### 自定义编译和原生部分源码

自定义编译的主要目的是修改支持的格式, 因为默认包含了一些编解码器,解复用,协议等等, 这些格式可能你的项目用不到, 这时候可以修改 ffmpeg 的自定义编译选项, 以便于可以缩小库文件的体积, 以达到给 app 瘦身的目的

[当前的编译规则文件](https://gitee.com/kikt/ijkplayer_thrid_party/blob/master/config/module.sh),修改编译选项,这个参考 [bilibili/ijkplayer](https://github.com/bilibili/ijkplayer) 或 [ffmpeg](http://ffmpeg.org/),ffmpeg 的相关信息也可以通过搜索引擎获取

自定义编译选项的完整过程请看[文档](https://github.com/CaiJingLong/flutter_ijkplayer/blob/master/compile-cn.md), 否则不保证编译出来的代码不报错, 具体的更改方案也请查看编译文档, 本篇不再提及

### iOS

因为 iOS 部分代码的库文件比较大,为了方便管理版本, 所以创建了一个 pod 依赖托管 iOS 的 ijkplayer 库
pod 库托管在 github 仓库内 ,因为网速原因,源码托管在 [azure](https://dev.azure.com/cjlspy/_git/flutter_ijkplayer_pod)

因为 framework 文件的大小超过了 100M,所以采用了压缩的方式储存
没有采用通用的 tar.gz 或 zip,而是使用 tar.xz 的方式压缩,这个压缩格式压缩率高,但是压缩和解压缩的的速度慢,综合考虑使用高压缩率的方式来快速获取源文件并解压缩  
如果有朋友愿意提供 cdn 加速,可以联系我 😁

iOS 的原始代码来自于 https://github.com/jadennn/flutter_ijk 中提供的 iOS 代码, 但在这基础上有了修改, 不能直接使用这个仓库的源码, 修改后的项目源码托管在[gitee](https://gitee.com/kikt/ijkplayer_thrid_party)

#### 运行慢的问题

最新的 0.3.3 版本的 pod 库(版本号 0.1.0)库文件托管在 [azure](https://dev.azure.com/cjlspy/_git/flutter_ijkplayer_pod), 在美西下载速度可以达到 4~5M/s 只需要 20 秒左右就可以下载完, 10 多秒解压缩, 国内则会慢很多, 下载速度 1.5M/s 左右, 所以请耐心等待

0.3.2 以前的 pod 源码托管在 github, 国外下载速度能达到 5~6M/s, 国内速度则不足 100k, 所以可能需要 20 分钟, 建议没有用过这个库的人使用最新版本(0.3.3+)或使用代理

### Android

现在的 ffmpeg 编译基本是参考的 [GSYVideoPlayer](https://github.com/CarGuo/GSYVideoPlayer)中的 ex-so 的规则, 但当前项目的 c 语言源码有修改(截取视频帧), 所以你**不能**直接拿别的项目的 so 文件来用, 修改的内容可以在[gitee](https://gitee.com/kikt/ijkplayer_thrid_party)查到

## 入门示例

```dart
import 'package:flutter_ijkplayer/flutter_ijkplayer.dart';


class HomePageState extends State<HomePage> {
  IjkMediaController controller = IjkMediaController();

  @override
  void initState() {
    super.initState();
  }

  @override
  void dispose() {
    controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Plugin example app'),
        actions: <Widget>[
          IconButton(
            icon: Icon(Icons.videocam),
            onPressed: _pickVideo,
          ),
        ],
      ),
      body: Container(
        // width: MediaQuery.of(context).size.width,
        // height: 400,
        child: ListView(
          children: <Widget>[
            buildIjkPlayer(),
          ]
        ),
      ),
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.play_arrow),
        onPressed: () async {
          await controller.setNetworkDataSource(
              'https://www.sample-videos.com/video123/mp4/720/big_buck_bunny_720p_20mb.mp4',
              // 'rtmp://172.16.100.245/live1',
              // 'https://www.sample-videos.com/video123/flv/720/big_buck_bunny_720p_10mb.flv',
//              "https://www.sample-videos.com/video123/mp4/720/big_buck_bunny_720p_1mb.mp4",
              // 'http://184.72.239.149/vod/smil:BigBuckBunny.smil/playlist.m3u8',
              // "file:///sdcard/Download/Sample1.mp4",
              autoPlay: true);
          print("set data source success");
          // controller.playOrPause();
        },
      ),
    );
  }

  Widget buildIjkPlayer() {
    return Container(
      // height: 400, // 这里随意
      child: IjkPlayer(
        mediaController: controller,
      ),
    );
  }
}
```

## 使用

### 设置

每个 ijkplayer 对应一个 IjkMediaController;

```dart
IjkMediaController controller = IjkMediaController();
```

将 controller 设置给 ijkplayer

```dart
  var ijkplayer = IjkPlayer(
    mediaController: controller,
  );
```

### 关于销毁

用户在确定不再使用 controller 时,必须自己调用 dispose 方法以释放资源,如果不调用,则会造成资源无法释放(后台有音乐等情况),一般情况下,在 ijkplayer 所属的页面销毁时同步销毁

因为一个`controller`可能被多个`IjkPlayer`附着, 导致一个`controller`同时控制多个`IjkPlayer`,所以原则上不能与`IjkPlayer`的`dispose`达成一致,所以这里需要调用者自行 dispose,

```dart
controller.dispose();
```

### 控制器的使用

#### 设置资源

```dart
// 根据你的资源类型设置,设置资源本身是耗时操作,建议await

// 网络
await controller.setNetworkDataSource("https://www.sample-videos.com/video123/mp4/720/big_buck_bunny_720p_20mb.mp4");

// 设置请求头, 使用headers参数
await controller.setNetworkDataSource(url, headers: <String,String>{});

// 应用内资源
await controller.setAssetDataSource("assets/test.mp4");

// 文件
await controller.setFileDataSource(File("/sdcard/1.mp4"));

// 通过数据源的方式
var dataSource = DataSource.file(File("/sdcard/1.mp4"));
var dataSource = DataSource.network("https://www.sample-videos.com/video123/mp4/720/big_buck_bunny_720p_20mb.mp4", headers:<String,String>{});
var dataSource = DataSource.asset("assets/test.mp4");
await controller.setDataSource(dataSource);

// 还可以添加autoplay参数,这样会在资源准备完成后自动播放
await controller.setNetworkDataSource("https://www.sample-videos.com/video123/mp4/720/big_buck_bunny_720p_20mb.mp4",autoPlay : true);

//或者也可以在设置资源完毕后自己调用play
await controller.setNetworkDataSource("https://www.sample-videos.com/video123/mp4/720/big_buck_bunny_720p_20mb.mp4");
await controller.play();
```

#### 播放器的控制

```dart
// 播放或暂停
await controller.playOrPause();

// 不管当前状态,直接play
await controller.play();

// 不管当前状态,直接pause
await controller.pause();

// 停止
// 这里要说明,ijkplayer的stop会释放资源,导致play不能使用,需要重新准备资源,所以这里其实采用的是回到进度条开始,并暂停
await controller.stop();

// 进度跳转
await controller.seekTo(0.0); //这里是一个double值, 单位是秒, 如1秒100毫秒=1.1s 1分钟10秒=70.0

// 进度跳转百分比
await controller.seekToProgress(0.0); //0.0~1.0

// 暂停其他所有的播放器(适用于ListView滚出屏幕或界面上有多个播放器的情况)
await controller.pauseOtherController();

// 设置媒体音量,这个可以用于做视频静音而不影响系统音量
controller.volume = 100; //范围0~100

// 设置系统音量
await controller.setSystemVolume(100); // 范围0~100
```

#### 获取播放信息

```dart
  // 包含了一些信息,是否在播放,视频宽,高,视频角度,当前播放进度,总长度等信息
  VideoInfo info = await controller.getVideoInfo();
```

#### 截取视频帧

视频帧的截图

以`Uint8List`的格式导出,可以使用`Image`控件查看

```dart
var uint8List = await controller.screenShot();
var provider = MemoryImage(uint8List);
Widget image = Image(image:provider);
```

但是有 2 个问题:

1. 这个和显示中的视频不总完全一样, 这个是因为截取的是解码后的完整视频帧, 可能比当前播放的**略快 1~2 帧**. 如果你不能接受这种不同步,请不要使用这个功能,或提交可行的 PR
2. 硬解的情况下不支持截图, 因为只有软解码才有视频帧(这个问题我会在后面尝试解决, 但不保证实现时间)

**在设置播放资源前[设置软解码](#%e8%ae%be%e7%bd%ae%e8%bd%af%e8%a7%a3%e7%a0%81)**

#### 资源监听

使用 stream 的形式向外广播一些信息的变化,原则上以 stream 结尾的属性都是可监听的

```dart
// 当纹理id发生变化时的回调,这个对于用户不敏感
Stream<int> textureIdStream = controller.textureIdStream;

// 播放状态的监听,true为正在播放,false为暂停
Stream<bool> playingStream = controller.playingStream;

// 当有调用controller.refreshVideoInfo()时,这个方法会回调,一般用于controllerUI的自定义,以便于监听当前信息(播放进度,播放状态,宽,高,方向变化等)
Stream<VideoInfo> videoInfoStream = controller.videoInfoStream;

// 音量的变化,这里需要注意,这个变化指的是当前媒体的音量变化,而不是系统的音量变化
Stream<bool> volumeStream = controller.playingStream;

// 当前Controller状态的监听,取值范围可以查看
Stream<IjkStatus> ijkStatusStream = controller.ijkStatusStream;
```

#### 倍速播放

调用代码:

```dart
controller.setSpeed(2.0);
```

支持的倍率默认为 1.0, 上限不明,下限请不要小于等于 0,否则可能会 crash

变调的问题:
由于变速变调的问题, 如果需要不变调, 需要一个 option 的支持, 这个 option **默认开启**, 如果要关闭这个, 可以使用如下代码

```dart
IjkMediaController(needChangeSpeed: false); // 这个设置为false后, 则变速时会声音会变调的情况发生
```

#### IjkStatus 说明

| 名称              | 说明                     |
| ----------------- | ------------------------ |
| noDatasource      | 初始状态/调用`reset()`后 |
| preparing         | 设置资源中               |
| setDatasourceFail | 设置资源失败             |
| prepared          | 准备好播放               |
| pause             | 暂停                     |
| error             | 发生错误                 |
| playing           | 播放中                   |
| complete          | 播放完毕后               |
| disposed          | 调用 dispose 后的状态    |

#### 自定义 Option

**本功能可能会出问题,导致不能播放等等情况,**如果发现设置选项后不能使用或出现异常,请停止使用此功能

支持自定义 IJKPlayer 的 option,这个 option 会直接传输至 android/iOS 原生,具体的数值和含义你需要查看[bilibili/ijkplayer](https://github.com/bilibili/ijkplayer)的设置选项

但这个设置后的选项不是即时生效的
只有在你重新 setDataSource 以后才会生效

设置方法`setIjkPlayerOptions`

```dart
void initIjkController() async {
  var option1 = IjkOption(IjkOptionCategory.format, "fflags", "fastseek");// category, key ,value

  controller.setIjkPlayerOptions(
    [TargetPlatform.iOS, TargetPlatform.android],
    [option1].toSet(),
  );

  await controller.setDataSource(
    DataSource.network(
        "http://img.ksbbs.com/asset/Mon_1703/05cacb4e02f9d9e.mp4"),
    autoPlay: true,
  );
}
```

第一个参数是一个数组,代表了你 option 目标设备的类型(android/iOS)

第二个参数是一个`Set<IjkOption>`,代表了 Option 的集合,因为 category 和 key 均相同的情况下会覆盖,所以这里使用了 set

与`setIjkPlayerOptions`对应的,还有一个`addIjkPlayerOptions`方法,区别在于 set 是会清空以前所有的配置选项, add 是添加, 只覆盖 category 和 key 均相同的配置选项

##### IjkOptionCategory

含义请查看 bilibili/ijkplayer 的说明或自行搜索

| name   |
| ------ |
| format |
| codec  |
| sws    |
| player |

##### 设置软解码

android 软解码的开启方式:
通过设置分类为 player, key 为 mediacodec, 值为 0

```dart
mediaController.setIjkPlayerOptions(
  [
    TargetPlatform.android,
  ],
  [
    IjkOption(IjkOptionCategory.player, "mediacodec", 0),
  ],
);
```

ios 对应的 key 则是 videotoolbox

```dart
mediaController.setIjkPlayerOptions(
  [
    TargetPlatform.ios,
  ],
  [
    IjkOption(IjkOptionCategory.player, "videotoolbox", 0),
  ],
);
```

#### 释放资源

```dart
await controller.reset(); // 这个方法调用后,会释放所有原生资源,但重新设置dataSource依然可用

await controller.dispose(); //这个方法调用后,当前控制器理论上不再可用,重新设置dataSource无效,且可能会抛出异常,确定销毁这个controller时再调用
```

### 自定义控制器 UI

使用`IJKPlayer`的`controllerWidgetBuilder`属性可以自定义控制器的 UI,默认使用`defaultBuildIjkControllerWidget`方法构建

签名如下: `typedef Widget IJKControllerWidgetBuilder(IjkMediaController controller);`

返回的 Widget 会被覆盖在 Texture 上

```dart
IJKPlayer(
  mediaController: IjkMediaController(),
  controllerWidgetBuilder: (mediaController){
    return Container(); // 自定义
  },
);
```

内置的播放器 UI 使用的类为: `DefaultIJKControllerWidget`

这个类提供了一些属性进行自定义, 除`controller`外所有属性均为可选:

|               name                |            type            |      default      |                              desc                              |
| :-------------------------------: | :------------------------: | :---------------: | :------------------------------------------------------------: |
|           doubleTapPlay           |            bool            |       false       |                          双击播放暂停                          |
|          verticalGesture          |            bool            |       true        |                            纵向手势                            |
|         horizontalGesture         |            bool            |       true        |                            横向手势                            |
|            volumeType             |         VolumeType         | VolumeType.system |               纵向手势改变的声音类型(系统,媒体)                |
|        playWillPauseOther         |            bool            |       true        |                    播放当前是否暂停其他媒体                    |
|      currentFullScreenState       |            bool            |       false       |        **如果你是自定义全屏界面, 这个必须设置为 true**         |
|       showFullScreenButton        |            bool            |       true        |                        是否显示全屏按钮                        |
| fullscreenControllerWidgetBuilder | IJKControllerWidgetBuilder |                   |                      可以自定义全屏的界面                      |
|          fullScreenType           |       FullScreenType       |                   |            全屏的类型(旋转屏幕,或是使用 RotateBox)             |
|     hideSystemBarOnFullScreen     |            bool            |       true        |         进入全屏时,是否自动隐藏状态栏(请看额外说明 1)          |
|           onFullScreen            |    void Function(bool)     |       null        | 全屏状态改变时的回调, 参数为 true 是全屏状态, false 为普通状态 |

**额外说明 1**: ios 需要向 Info.plist 添加一条属性

```xml
<key>UIViewControllerBasedStatusBarAppearance</key>
<true/>
```

### 自定义纹理界面

使用`textureBuilder`属性自定义纹理界面,在 0.1.8 和之前的版本该属性名是`playerBuilder`

默认的方法`buildDefaultIjkPlayer`接受 `context,controller,videoInfo` 参数返回 Widget

```dart
IJKPlayer(
  mediaController: IjkMediaController(),
  textureBuilder: (context,mediaController,videoInfo){
    return Texture(); // 自定义纹理界面
  },
);
```

### 根据当前状态构建一个 widget

根据 Controller 当时 IjkStatus 的值构建 Widget,这个 Widget 会根据当前 status 变化而呈现出不同的界面

```dart

Widget buildIjkPlayer() {
  return IjkPlayer(
    mediaController: mediaController,
    stateWidgetBuilder: _buildStatusWidget,
  );
}

Widget _buildStatusWidget(
  BuildContext context,
  IjkMediaController controller,
  IjkStatus status,
) {
  if (status == IjkStatus.noDatasource) {
    return Center(
      child: Text(
        "no data",
        style: TextStyle(color: Colors.white),
      ),
    );
  }

  // you can custom your self status widget
  return IjkStatusWidget.buildStatusWidget(context, controller, status);
}
```

## 进度

目前正处于初始开发阶段,可能有各种问题,欢迎提出,但不一定会实现,也不一定会修改 😌

最初准备参考官方 video_player 的 api 方式进行开发,但是觉得调用的方式比较奇怪

需要自定义 LifeCycle 进行管理,而且自定义控制器不太方便,遂决定重写 api 的代码结构,同时清晰逻辑

目前属于公开测试使用阶段,不保证不出 bug,也不保证今后 api 不发生重大变更

目前的进度可以查看[TODOLIST](https://github.com/CaiJingLong/flutter_ijkplayer/blob/master/TODOLIST.md)

UI 控制功能包含常见的播放停止,手势拖动

## LICENSE

MIT

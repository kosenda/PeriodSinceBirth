# 🐨 概要
誕生日からどれくらいの期間が経っているか知ることができるシンプルなアプリです。  
ReduxとJetpack Composeを使用している点が特徴だと思います。  
アプリに関しては、Google Playで公開する予定です。  

# 🐶 代表的なライブラリ
|名前|簡単な説明|
|:--|:--|
|redux-kotlin-compose|ReduxとJetpackComposeを合わせてアプリを作るためのライブラリ|
|Jetpack Compose|UIアプリ開発ツールキット|
|Material3|デザイン|
|datastore-preferences|永続化データ|
|Robolectric|単体テストフレームワーク|
|Truth|アサーション|
|Hilt|依存性注入|
|Timber|ログ出力|
|googlefonts|ダウンロード可能なGoogleフォント|
|play-services-oss-licenses|OSSライセンス|

# 🐱 パッケージ
|パッケージ名|簡単な説明|
|:--|:--|
|action|ReduxのActionを格納|
|di|HiltでDIするモジュールを格納|
|middleware|非同期処理を行う処理等を行うReduxのMiddlewareを格納|
|repository|Repository（DataStoreを読み込む処理を行うため）を格納|
|reducer|ReduxのReducerを格納|
|state|ReduxのStateを格納|
|store|ReduxのStoreを格納|
|ui|画面に関する部品を格納|
|util|日時に関する処理を行うTimeUtilを格納|

# 🐹　スクリーンショット
<details>
<summary>ライトモード</summary>

|入力画面|メイン画面|設定画面|インフォ画面|
|:-:|:-:|:-:|:-:|
|<img width="320" alt="input_screen_light" src="https://user-images.githubusercontent.com/60963155/220823677-af14bd35-5b63-456c-89c3-5584d3249628.PNG">|<img width="320" alt="main_screen_light" src="https://user-images.githubusercontent.com/60963155/220823683-1d30bc93-d646-4217-a918-0a59cd357d7a.PNG">|<img width="320" alt="setting_screen_light" src="https://user-images.githubusercontent.com/60963155/220823686-d3d5de92-b9d2-493b-b98b-6c06e3fcdd64.PNG">|<img width="320" alt="info_screen_light" src="https://user-images.githubusercontent.com/60963155/220823689-bfc98257-7e26-4820-b9b3-e1147e194c7a.PNG">|
</details>

<details>
<summary>ダークモード</summary>

|入力画面|メイン画面|設定画面|インフォ画面|
|:-:|:-:|:-:|:-:|
|<img width="320" alt="input_screen_dark" src="https://user-images.githubusercontent.com/60963155/220823680-51eaebdf-2ab4-4325-9b1d-d8229475f172.PNG">|<img width="320" alt="main_screen_dark" src="https://user-images.githubusercontent.com/60963155/220823685-fa8942f1-afa8-487d-9e32-d036fc6f7527.PNG">|<img width="320" alt="setting_screen_dark" src="https://user-images.githubusercontent.com/60963155/220823687-68e829e0-62d1-4119-9067-9797fc4553a9.PNG">|<img width="320" alt="info_screen_dark" src="https://user-images.githubusercontent.com/60963155/220823690-46c04803-4d19-4d9e-8054-62dd6039c49d.PNG">|
</details>

# 🐰 主な参考サイト
- [redux-kotlin-compose](https://github.com/reduxkotlin/redux-kotlin-compose)
- [ReduxKotlin](https://reduxkotlin.org)
- [Redux Essentials, Part 1: Redux Overview and Concepts](https://redux.js.org/tutorials/essentials/part-1-overview-concepts)  
- [Android + Reduxアーキテクチャで状態管理が容易になった話](https://qiita.com/Urotea/items/8cbc8f55406b6ff32bbd)  

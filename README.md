# 🐤 概要
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

# 🐱 パッケージの簡単な説明
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

# 🐰 主な参考サイト
- [redux-kotlin-compose](https://github.com/reduxkotlin/redux-kotlin-compose)
- [ReduxKotlin](https://reduxkotlin.org)
- [Redux Essentials, Part 1: Redux Overview and Concepts](https://redux.js.org/tutorials/essentials/part-1-overview-concepts)  
- [Android + Reduxアーキテクチャで状態管理が容易になった話](https://qiita.com/Urotea/items/8cbc8f55406b6ff32bbd)  

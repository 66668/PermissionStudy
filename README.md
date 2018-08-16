权限详解及Application相关权限问题
===================================

(一)AndroidManifest.xml的所有权限
------------

    <!--====================================最全权限及注释 ===========================================-->
    <!--TODO 说明：权限按字母排序 之后会总结6.0+的 动态权限 权限组 及 常用权限 -->

    <!--=========================A(12个)===========================-->
    <!--TODO 访问登记属性-->
    <!--01 允许读/写 在checkin数据库中 的properties表-->
    <uses-permission android:name="android.permission.ACCESS_CHECKIN_PROPERTIES" />

    <!--TODO 粗略定位-->
    <!--02 wifi/基站粗略定位-->
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />

    <!--TODO 精确定位-->
    <!--03()-(02) 手机GPS芯片定位-->
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />

    <!--TODO 访问定位额外命令-->
    <!--04()-(02) 允许访问额外的定位提供者指令-->
    <uses-permission android:name="android.permission.ACCESS_LOCATION_EXTRA_COMMANDS" />

    <!--TODO 模拟定位-->
    <!--05()-(02) 开发者测试使用 模拟定位(该权限需要写在src/debug/AndroidManifest.xml下)-->
    <uses-permission android:name="android.permission.ACCESS_MOCK_LOCATION" />

    <!--TODO 获取网络状态-->
    <!--06()-(06) 获取网络信息状态-->
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

    <!--TODO (level 23)-->
    <!--07# APP通知显示在状态栏 -->
    <uses-permission android:name="android.permission.ACCESS_NOTIFICATION_POLICY" />

    <!--TODO -->
    <!--08 允许程序使用SurfaceFlinger底层特性 一般用于游戏或照相机预览界面和底层模式的屏幕截图-->
    <uses-permission android:name="android.permission.ACCESS_SURFACE_FLINGER" />

    <!--TODO 获取wifi状态-->
    <!--09()-(06) 获取当前WiFi接入的状态以及WLAN热点的信息-->
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />

    <!--TODO 账户管理 (系统)-->
    <!--10* 获取账户验证信息，主要为GMail账户信息，只有系统级进程才能访问的权限-->
    <uses-permission android:name="android.permission.ACCOUNT_MANAGER" />

    <!--TODO 验证账户-->
    <!--11()-(10) 允许一个程序通过账户验证方式访问账户管理ACCOUNT_MANAGER相关信息-->
    <uses-permission android:name="android.permission.AUTHENTICATE_ACCOUNTS" />

    <!--TODO (level 26)-->
    <!--12# 允许该应用接听来电。  in API level 26-->
    <uses-permission android:name="android.permission.ANSWER_PHONE_CALLS" />

    <!--=========================B（36个）===========================-->


    <!--13 电量统计-->
    <uses-permission android:name="android.permission.BATTERY_STATS " />

    <!--TODO (系统)-->
    <!--14* 请求accessibilityservice服务，以确保只有系统可以绑定到它-->
    <uses-permission android:name="android.permission.BIND_ACCESSIBILITY_SERVICE" />

    <!--TODO (系统)-->
    <!--15* 请求accessibilityservice服务，以确保只有系统可以绑定到它-->
    <uses-permission android:name="android.permission.BIND_ACCESSIBILITY_SERVICE" />

    <!--TODO 绑定小插件-->
    <!--16* Android Laucher(android桌面UI) 之窗口小部件权限 eg:时间表 音乐窗口 日历-->
    <uses-permission android:name="android.permission.BIND_APPWIDGET" />

    <!--TODO （level 26）(系统)-->
    <!--17*# 必须由AutofillService来确保只有系统可以绑定到它。in API level 26-->
    <uses-permission android:name="android.permission.BIND_AUTOFILL_SERVICE" />

    <!--TODO -->
    <!--18* API>23时使用BIND_CARRIER_MESSAGING_SERVICE,API<23时 使用BIND_CARRIER_SERVICES-->
    <uses-permission android:name="android.permission.BIND_CARRIER_MESSAGING_SERVICE" />

    <!--TODO -->
    <!--19()-(18)* API<=23时 使用BIND_CARRIER_SERVICES 允许绑定到运营商应用程序中的服务的系统进程将具有此权限。 运营商应用程序应该使用此权限来保护只允许系统绑定的服务-->
    <uses-permission android:name="android.permission.BIND_CARRIER_SERVICES" />

    <!--TODO (系统)-->
    <!--20* 必须由ChooserTargetService要求，确保只有系统可以绑定到它-->
    <uses-permission android:name="android.permission.BIND_CHOOSER_TARGET_SERVICE" />

    <!--TODO (level 24) (系统)-->
    <!--21* 必须由ConditionProviderService要求，确保只有系统可以绑定到它  added in API level 24-->
    <uses-permission android:name="android.permission.BIND_CONDITION_PROVIDER_SERVICE" />

    <!--TODO 绑定设备管理 (系统)-->
    <!--22* DeviceAdminReceiver+DevicePolicyManager要求，只有系统才能使用-->
    <uses-permission android:name="android.permission.BIND_DEVICE_ADMIN" />

    <!--TODO (level 21) (系统)-->
    <!--23* DreamService要求 ，只有系统才能使用 added in API level 21-->
    <uses-permission android:name="android.permission.BIND_DREAM_SERVICE" />

    <!--TODO (level 23) (系统)-->
    <!--24* 由InCallService所要求，以确保只有系统可以绑定到它added in API level 23-->
    <uses-permission android:name="android.permission.BIND_INCALL_SERVICE" />

    <!--TODO 绑定输入法 (系统)-->
    <!--25* 自定义输入法 InputMethodService必须被要求，以确保只有系统可以绑定到它-->
    <uses-permission android:name="android.permission.BIND_INPUT_METHOD" />

    <!--TODO (系统)-->
    <!--26* 必须由MidiDeviceService来确保只有系统可以绑定到它 added in API level 23-->
    <uses-permission android:name="android.permission.BIND_MIDI_DEVICE_SERVICE" />

    <!--TODO (系统)-->
    <!--27* 必须由HostApduService或OffHostApduService来确保只有系统可以绑定到它。added in API level 19 -->
    <uses-permission android:name="android.permission.BIND_NFC_SERVICE" />

    <!--TODO (系统)-->
    <!--28* NotificationListenerService必须要求，以确保只有系统可以绑定到它 -->
    <uses-permission android:name="android.permission.BIND_NOTIFICATION_LISTENER_SERVICE" />

    <!--TODO 自定义打印 (level 19) (系统)-->
    <!--29* 自定义打印 必须是PrintService所必需的，以确保只有系统可以绑定到它 框架代码位于android.printservice包 added in API level 19-->
    <uses-permission android:name="android.permission.BIND_PRINT_SERVICE" />

    <!--TODO (level 24) (系统)-->
    <!--30* 允许应用绑定到第三方快速设置图块。  只应由系统请求，应由TileService声明所要求。added in API level 24 -->
    <uses-permission android:name="android.permission.BIND_QUICK_SETTINGS_TILE" />

    <!--TODO 绑定RemoteView (系统)-->
    <!--31* RemoteViewsService必须要求，以确保只有系统可以绑定到它-->
    <uses-permission android:name="android.permission.BIND_REMOTEVIEWS" />

    <!--TODO (level 24)(系统)-->
    <!--32* Call Screening(来电过滤） CallScreeningService必须要求，以确保只有系统可以绑定到它。 added in API level 24-->
    <uses-permission android:name="android.permission.BIND_SCREENING_SERVICE" />

    <!--TODO (level 23)(系统)-->
    <!--33* ConnectionService必须要求，以确保只有系统可以绑定到它 added in API level 23-->
    <uses-permission android:name="android.permission.BIND_TELECOM_CONNECTION_SERVICE" />

    <!--TODO (系统)-->
    <!--34* 必须由TextService（例如SpellCheckerService）来确保只有系统可以绑定到它-->
    <uses-permission android:name="android.permission.BIND_TEXT_SERVICE" />

    <!--TODO (系统)-->
    <!--35* TvInputService必须要求它确保只有系统可以绑定到它 added in API level 21-->
    <uses-permission android:name="android.permission.BIND_TV_INPUT" />

    <!--TODO (level 26)(系统)-->
    <!--36* 必须通过VisualVoicemailService链接来确保只有系统可以绑定到它。added in API level 26 -->
    <uses-permission android:name="android.permission.BIND_VISUAL_VOICEMAIL_SERVICE" />

    <!--TODO (level 21)(系统)-->
    <!--37*VoiceInteractionService必须要求，以确保只有系统可以绑定到它 added in API level 21-->
    <uses-permission android:name="android.permission.BIND_VOICE_INTERACTION" />

    <!--TODO VPN (系统)-->
    <!--38* VpnService用于应用扩展和构建自己的VPN解决方案  以确保只有系统可以绑定到它-->
    <uses-permission android:name="android.permission.BIND_VPN_SERVICE" />

    <!--TODO (level 26)(系统)-->
    <!--39* VrListenerService必须要求，以确保只有系统可以绑定到它 added in API level 26-->
    <uses-permission android:name="android.permission.BIND_VR_LISTENER_SERVICE" />

    <!--TODO (系统)-->
    <!--40 WallpaperService必须要求,以确保只有系统可以绑定到它-->
    <uses-permission android:name="android.permission.BIND_WALLPAPER" />

    <!--TODO 蓝牙-->
    <!--41 蓝牙-->
    <uses-permission android:name="android.permission.BLUETOOTH" />

    <!--TODO 蓝牙管理-->
    <!--42 蓝牙管理-->
    <uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />

    <!--TODO (系统)-->
    <!--43 允许应用程序配对蓝牙设备-->
    <uses-permission android:name="android.permission.BLUETOOTH_PRIVILEGED" />

    <!--TODO 传感器-->
    <!--44 传感器  允许应用程序访问用户使用的传感器来测量他/她的身体内发生了什么，如心率仪-->
    <uses-permission android:name="android.permission.BODY_SENSORS" />

    <!--TODO 手机变砖-->
    <!--45 禁用手机，非常危险，让手机变成砖头-->
    <uses-permission android:name="android.permission.BRICK" />

    <!--TODO 安装卸载广播-->
    <!--45 监听应用卸载的广播提示-->
    <uses-permission android:name="android.permission.BROADCAST_PACKAGE_REMOVED" />

    <!--TODO 短信广播-->
    <!--46 允许程序当收到短信时触发一个广播-->
    <uses-permission android:name="android.permission.BROADCAST_SMS" />

    <!--TODO  连续广播-->
    <!--47 允许程序收到广播后快速收到下一个广播-->
    <uses-permission android:name="android.permission.BROADCAST_STICKY" />

    <!--TODO WAP PUSH广播-->
    <!--48 WAP PUSH服务收到后触发一个广播-->
    <uses-permission android:name="android.permission.BROADCAST_WAP_PUSH" />

    <!--=========================C（14个）===========================-->

    <!--TODO 拍照-->
    <!--49@+ 使用相机 必要权限 -->
    <uses-permission android:name="android.permission.CAMERA" />

    <!--TODO 通话-->
    <!--50@+ 许应用拨打电话，而无需通过拨号程式使用者介面让使用者确认通话-->
    <uses-permission android:name="android.permission.CALL_PHONE" />

    <!--TODO (系统)-->
    <!--51@p 允许应用程式拨打任何电话号码（包括紧急电话号码），而无需通过拨号程式使用者界面让使用者确认所拨的电话。
    不适用于第三方应用程序(系统专属) 是个private API，程序员几乎不用-->
    <uses-permission android:name="android.permission.CALL_PRIVILEGED" />

    <!--TODO (系统)-->
    <!--52@p 允许应用程序捕获音频输出。不适用于第三方应用程序。added in API level 19-->
    <uses-permission android:name="android.permission.CAPTURE_AUDIO_OUTPUT" />

    <!--TODO (系统)-->
    <!--53@p允许应用程序捕获安全的视频输出。不适用于第三方应用程序。added in API level 19-->
    <uses-permission android:name="android.permission.CAPTURE_SECURE_VIDEO_OUTPUT" />

    <!--TODO (系统)-->
    <!--54@p 允许应用程序捕捉视频输出。   不适用于第三方应用程序。added in API level 19-->
    <uses-permission android:name="android.permission.CAPTURE_VIDEO_OUTPUT" />


    <!--TODO (系统)-->
    <!--55@p 允许应用程序更改是否启用应用程序组件（非自己的应用程序组件）。    不适用于第三方应用程序。-->
    <uses-permission android:name="android.permission.CHANGE_COMPONENT_ENABLED_STATE" />

    <!--TODO 修改配置-->
    <!--56 允许应用程序修改当前配置，例如语言环境，定位-->
    <uses-permission android:name="android.permission.CHANGE_CONFIGURATION" />

    <!--TODO 改变网络状态-->
    <!--57 允许应用程序更改网络连接状态-->
    <uses-permission android:name="android.permission.CHANGE_NETWORK_STATE" />

    <!--TODO 改变WiFi多播状态-->
    <!--58 ()-57 允许应用程序进入Wi-Fi多播模式-->
    <uses-permission android:name="android.permission.CHANGE_WIFI_MULTICAST_STATE" />

    <!--TODO 改变WiFi状态-->
    <!--59 ()-57 改变WiFi状态-->
    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />

    <!--TODO 清除应用缓存-->
    <!--60 允许应用程序清除设备上所有已安装应用程序的缓存区 -->
    <uses-permission android:name="android.permission.CLEAR_APP_CACHE" />

    <!--TODO 清除用户数据-->
    <!--61 允许应用程序清除设备上所有已安装应用程序的数据区-->
    <uses-permission android:name="android.permission.CLEAR_APP_USER_DATA" />

    <!--TODO 控制定位更新-->
    <!--62* 可通过位置更新-->
    <uses-permission android:name="android.permission.CONTROL_LOCATION_UPDATES" />


    <!--=========================D（6个）===========================-->

    <!--TODO 闪促缓存文件-->
    <!--63* 允许应用程序删除缓存文件-->
    <uses-permission android:name="android.permission.DELETE_CACHE_FILES" />

    <!--TODO 删除应用-->
    <!--64* 允许应用程序删除软件包。不适用于第三方应用程序。 从N开始，
    当删除软件包的应用程序与安装该软件包的应用程序不是同一个应用程序时，请求用户确认。-->
    <uses-permission android:name="android.permission.DELETE_PACKAGES" />

    <!--TODO 电源管理-->
    <!--65* 允许访问底层电源管理-->
    <uses-permission android:name="android.permission.DEVICE_POWER" />

    <!--TODO 应用诊断-->
    <!--66* 允许应用程序读取RW到诊断资源-->
    <uses-permission android:name="android.permission.DIAGNOSTIC" />

    <!--TODO 禁用键盘锁-->
    <!--67 允许应用程序在不安全的情况下禁用键盘锁-->
    <uses-permission android:name="android.permission.DISABLE_KEYGUARD" />

    <!--TODO 转存系统信息-->
    <!--68* 允许应用程序从系统服务中检索状态转储信息。-->
    <uses-permission android:name="android.permission.DUMP" />

    <!--=========================E（1个）===========================-->

    <!--TODO 状态栏控制-->
    <!--69 允许应用程序展开或折叠状态栏/允许程序扩展或收缩状态栏-->
    <uses-permission android:name="android.permission.EXPAND_STATUS_BAR" />


    <!--=========================F（3个）===========================-->

    <!--TODO 工厂测试模式 (系统)-->
    <!--70 不适用于第三方应用程序。 added in API level 1-->
    <uses-permission android:name="android.permission.FACTORY_TEST" />

    <!--TODO 闪光灯-->
    <!--71 使用闪光灯-->
    <uses-permission android:name="android.permission.FLASHLIGHT" />

    <!--TODO 强制back键-->
    <!--72 允许程序强制使用back后退按键，无论Activity是否在顶层-->
    <uses-permission android:name="android.permission.FORCE_BACK" />


    <!--=========================G（6个）===========================-->

    <!--TODO 访问账户Gmail列表-->
    <!--73 允许访问帐户服务中的帐户列表。 注意：API>=23，则不需要“GET_ACCOUNTS”权限即可读取有关该帐户的信息。
     API<23，所有应用都需要“GET_ACCOUNTS”权限才能阅读有关任何帐户的信息。 -->
    <uses-permission android:name="android.permission.GET_ACCOUNTS" />

    <!--TODO (level 23)-->
    <!--74 允许访问帐户服务中的帐户列表。 added in API level 23-->
    <uses-permission android:name="android.permission.GET_ACCOUNTS_PRIVILEGED" />

    <!--TODO 获取应用大小-->
    <!--75 允许应用程序查找任何包的大小。-->
    <uses-permission android:name="android.permission.GET_PACKAGE_SIZE" />

    <!-- TODO 获取任务信息 -->
    <!--76 允许一个应用程序检索私有信息是当前最顶级的活动，不被第三方应用使用-->
    <uses-permission android:name="android.permission.GET_TOP_ACTIVITY_INFO" />

    <!--TODO 允许程序全局搜索 -->
    <!--77 此权限可用于内容提供商以允许全局搜索系统访问其数据。 通常， 当提供者具有保护它的某些权限时（全局搜索将不被期望保持） ，并且作为只读权限被添加到执行全局搜索查询的提供者中的路径时使用。 这种许可不能由正常的应用程序来保存; 除了全局搜索外， 应用程序还是使用它来保护自己免受其他人的攻击 added in API level 4-->
    <uses-permission android:name="android.permission.GLOBAL_SEARCH" />


    <!--=========================H（1个）===========================-->
    <!--TODO 硬件测试-->
    <!--78 访问硬件辅助设备，用于硬件测试。 仅当设备在制造商测试模式下运行时才可用。-->
    <uses-permission android:name="android.permission.HARDWARE_TEST" />


    <!--=========================I（6个）===========================-->

    <!--TODO 访问网络-->
    <!--79@- 允许应用程序打开网络套接字。 -->
    <uses-permission android:name="android.permission.INTERNET" />

    <!--TODO 注射事件-->
    <!--80允许访问本程序的底层事件，获取按键、轨迹球的事件流-->
    <uses-permission android:name="android.permission.INJECT_EVENTS" />

    <!--TODO 安装定位提供-->
    <!--81 允许应用程序将location provider安装到Location Manager中。-->
    <uses-permission android:name="android.permission.INSTALL_LOCATION_PROVIDER" />

    <!--TODO 安装应用程序 -->
    <!--82允许程序安装 包应用-->
    <uses-permission android:name="android.permission.INSTALL_PACKAGES" />

    <!--TODO 创建快捷方式 /对应有删除快捷方式 UNINSTALL_SHORTCUT-->
    <!--83 允许应用程序在Launcher中安装快捷方式。 在Android O（API级别26）及更高版本中，INSTALL_SHORTCUT广播不再对您的应用程序有任何影响，
     因为它是私有的隐式广播。 相反，您应该使用ShortcutManager类中的requestPinShortcut（） 方法创建应用程序快捷方式。
     added in API level 19-->
    <uses-permission android:name="android.permission.INSTALL_SHORTCUT" />

    <!--TODO 内部系统窗口-->
    <!--84 允许程序打开内部窗口，不对第三方应用程序开放此权限-->
    <uses-permission android:name="android.permission.INTERNAL_SYSTEM_WINDOW" />


    <!--=========================K（1个）===========================-->

    <!--TODO 结束后台进程-->
    <!--85 允许程序调用killBackgroundProcesses(String)方法结束后台进程-->
    <uses-permission android:name="android.permission.KILL_BACKGROUND_PROCESSES" />

    <!--=========================L（1个）===========================-->

    <!--TODO （系统）-->
    <!--86 允许应用程序在硬件中使用位置功能，例如geofencing api。不适用于第三方应用程序-->
    <uses-permission android:name="android.permission.LOCATION_HARDWARE" />

    <!--=========================M（9个）===========================-->

    <!--TODO 管理账户-->
    <!--87 允许程序管理AccountManager中的账户列表-->
    <uses-permission android:name="android.permission.MANAGE_ACCOUNTS" />

    <!--TODO 管理程序引用 （系统）-->
    <!--88* 管理创建、摧毁、Z轴顺序，仅用于系统-->
    <uses-permission android:name="android.permission.MANAGE_APP_TOKENS" />

    <!--TODO （系统）-->
    <!--89 允许应用程序管理对文档的访问，通常作为文档选择器的一部分。
     此权限只能由平台文档管理应用程序请求。 此权限不能授予第三方应用程序。
     保护级别：签名 added in API level 19-->
    <uses-permission android:name="android.permission.MANAGE_DOCUMENTS" />

    <!--TODO 软格式化-->
    <!--90 允许程序执行软格式化，删除系统配置信息 不能授予第三方应用程序。-->
    <uses-permission android:name="android.permission.MASTER_CLEAR" />


    <!--91 允许应用知道正在播放的内容并控制其播放,不能授予第三方应用程序-->
    <uses-permission android:name="android.permission.MEDIA_CONTENT_CONTROL" />

    <!--TODO 修改声音设置-->
    <!--92 允许应用修改全局音频设置-->
    <uses-permission android:name="android.permission.MODIFY_AUDIO_SETTINGS" />

    <!--TODO 修改电话状态-->
    <!--93 允许修改电话状态 - 开机，mmi等。不包括拨打电话。不适用于第三方应用程序。-->
    <uses-permission android:name="android.permission.MODIFY_PHONE_STATE" />

    <!--TODO 格式化文件系统-->
    <!--94 允许将文件系统格式化为可移动存储。不适用于第三方应用程序-->
    <uses-permission android:name="android.permission.MOUNT_FORMAT_FILESYSTEMS" />

    <!--TODO 挂载文件系统-->
    <!--95 允许安装和卸载可移动存储的文件系统/挂载、反挂载外部文件系统。-->
    <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />

    <!--=========================N（1个）===========================-->

    <!--TODO NFC-->
    <!--96 允许应用程序通过NFC执行I / O操作   -->
    <uses-permission android:name="android.permission.NFC" />


    <!--=========================P（1个）===========================-->
    <!--TODO 处理拨出电话-->
    <!--97 允许程序监视，修改或放弃播出电话-->
    <uses-permission android:name="android.permission.PROCESS_OUTGOING_CALLS" />


    <!--=========================R（28个）===========================-->

    <!--TODO 读写权限-->
    <!--98程序可以读取设备外部存储空间（内置SDcard和外置SDCard）的文件，如果您的App已经添加了
    “WRITE_EXTERNAL_STORAGE ”权限 ，则就没必要添加读的权限了，写权限已经包含了读权限了-->
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />

    <!--TODO 读取日程提醒-->
    <!--99读取日程提醒-->
    <uses-permission android:name="android.permission.READ_CALENDAR" />

    <!--TODO 读取通话记录-->
    <!--100 读取通话记录-->
    <uses-permission android:name="android.permission.READ_CALL_LOG" />

    <!--TODO 读取通讯录-->
    <!--101 允许程序访问联系人通讯录信息-->
    <uses-permission android:name="android.permission.READ_CONTACTS" />

    <!--TODO 屏幕截图-->
    <!--102允许程序读取帧缓存用于屏幕截图-->
    <uses-permission android:name="android.permission.READ_FRAME_BUFFER" />

    <!--TODO 读取收藏夹和历史记录-->
    <!--103 读取浏览器收藏夹和历史记录-->
    <uses-permission android:name="android.permission.READ_HISTORY_BOOKMARKS" />

    <!--TODO 读取输入状态 (系统)-->
    <!--104 读取当前键的输入状态，仅用于系统-->
    <uses-permission android:name="android.permission.READ_INPUT_STATE" />

    <!--TODO 读取系统日志-->
    <!--105 读取系统底层日志-->
    <uses-permission android:name="android.permission.READ_LOGS" />

    <!--TODO 读取电话状态-->
    <!--106 读取电话状态-->
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />

    <!--107 访问用户个人资料-->
    <uses-permission android:name="android.permission.READ_PROFILE" />

    <!--TODO 读取短信内容-->
    <!--108 读取短信内容-->
    <uses-permission android:name="android.permission.READ_SMS" />

    <!--109 读取用户的社交信息流-->
    <uses-permission android:name="android.permission.READ_SOCIAL_STREAM" />

    <!--TODO 读取同步设置-->
    <!--110 读取同步设置，读取Google在线同步设置-->
    <uses-permission android:name="android.permission.READ_SYNC_SETTINGS" />

    <!--TODO 读取同步状态-->
    <!--111读取同步状态，获得Google在线同步状态-->
    <uses-permission android:name="android.permission.READ_SYNC_STATS" />

    <!--112 从一个提供器中获取数据，针对对应的提供器，应用程序需要“读访问权限”-->
    <uses-permission android:name="android.permission.READ_USER_DICTIONARY" />

    <!--TODO (系统)-->
    <!--113 读取语音邮件-->
    <uses-permission android:name="android.permission.READ_VOICEMAIL" />

    <!--TODO 重启设备-->
    <!--114 重启设备-->
    <uses-permission android:name="android.permission.REBOOT" />

    <!--TODO 开机自动运行程序-->
    <!--115 允许程序开机自动运行-->
    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />

    <!--TODO 接受彩信-->
    <!--116 接受彩信-->
    <uses-permission android:name="android.permission.RECEIVE_MMS" />

    <!--TODO 接受短信-->
    <uses-permission android:name="android.permission.RECEIVE_SMS" />

    <!--TODO 接收Wap Push-->
    <!--118 接收Wap Push-->
    <uses-permission android:name="android.permission.RECEIVE_WAP_PUSH" />

    <!--TODO 录音-->
    <!--119 录音-->
    <uses-permission android:name="android.permission.RECORD_AUDIO" />

    <!--120 重新排序系统Z轴运行中的任务-->
    <uses-permission android:name="android.permission.REORDER_TASKS" />

    <!--121 -->
    <uses-permission android:name="android.permission.REQUEST_COMPANION_RUN_IN_BACKGROUND" />

    <!--122-->
    <uses-permission android:name="android.permission.REQUEST_COMPANION_USE_DATA_IN_BACKGROUND" />

    <!--123 允许应用程序请求删除安装包-->
    <uses-permission android:name="android.permission.REQUEST_DELETE_PACKAGES" />

    <!--124 一个应用程序请求它将永远被授予权限，而不需要用户批准或看到它-->
    <uses-permission android:name="android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS" />

    <!--TODO 请求安装包-->
    <!--125 允许应用程序请求安装包。针对API>level22,必须持有该许可使用ACTION_INSTALL_PACKAGE应用。-->
    <uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />


    <!--=========================S（21个）===========================-->
    <!--TODO 程序回复来电-->
    <!--126 允许用户在来电的时候用你的应用进行即时的短信息回复-->
    <uses-permission android:name="android.permission.SEND_RESPOND_VIA_MESSAGE" />

    <!--TODO 发送短信-->
    <!--127 发送短信-->
    <uses-permission android:name="android.permission.SEND_SMS" />
    <!---->
    <!--128 允许程序设置Activity观察器一般用于monkey测试-->
    <uses-permission android:name="android.permission.SET_ACTIVITY_WATCHER" />

    <!--129 设置闹铃提醒-->
    <uses-permission android:name="android.permission.SET_ALARM" />

    <!--130 设置程序在后台是否总是退出-->
    <uses-permission android:name="android.permission.SET_ALWAYS_FINISH" />

    <!--TODO 设置动画缩放-->
    <!--131设置全局动画缩放-->
    <uses-permission android:name="android.permission.SET_ANIMATION_SCALE" />

    <!--TODO -->
    <!--132设置调试程序，一般用于开发-->
    <uses-permission android:name="android.permission.SET_DEBUG_APP" />

    <!--TODO 设置屏幕方向-->
    <!--133 设置屏幕方向为横屏或标准方式显示，不用于普通应用-->
    <uses-permission android:name="android.permission.SET_ORIENTATION" />

    <!--TODO (系统)-->
    <uses-permission android:name="android.permission.SET_POINTER_SPEED" />

    <!--TODO 设置进程限制-->
    <!--135 允许程序设置最大的进程数量的限制-->
    <uses-permission android:name="android.permission.SET_PROCESS_LIMIT" />

    <!--TODO 设置系统时间-->
    <!--136 设置系统时间-->
    <uses-permission android:name="android.permission.SET_TIME" />

    <!--TODO 设置系统时区-->
    <!--137 设置系统时区-->
    <uses-permission android:name="android.permission.SET_TIME_ZONE" />

    <!--TODO 设置桌面壁纸-->
    <!--138 设置桌面壁纸-->
    <uses-permission android:name="android.permission.SET_WALLPAPER" />

    <!--TODO发送永久进程信号-->
    <!--139 发送永久进程信号-->
    <uses-permission android:name="android.permission.SIGNAL_PERSISTENT_PROCESSES" />

    <!--TODO 状态栏控制-->
    <!--140 允许程序打开、关闭、禁用状态栏-->
    <uses-permission android:name="android.permission.STATUS_BAR" />

    <!--TODO 访问订阅内容-->
    <!--141 访问订阅信息的数据库-->
    <uses-permission android:name="android.permission.SUBSCRIBED_FEEDS_READ" />

    <!--TODO 写入订阅内容-->
    <!--142写入或修改订阅内容的数据库-->
    <uses-permission android:name="android.permission.SUBSCRIBED_FEEDS_WRITE" />

    <!--TODO 显示系统窗口-->
    <!--143 显示系统窗口-->
    <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />


    <!--=========================T（1个）===========================-->

    <!--TODO 红外发射器-->
    <!--144允许使用设备的红外发射器，如果可用-->
    <uses-permission android:name="android.permission.TRANSMIT_IR" />

    <!--=========================U（4个）===========================-->

    <!-- TODO 删除快捷方式-->
    <!-- 145 删除快捷方式/83 创建-->
    <uses-permission android:name="android.permission.UNINSTALL_SHORTCUT" />

    <!--TODO 更新设备状态-->
    <!--146 更新设备状态-->
    <uses-permission android:name="android.permission.UPDATE_DEVICE_STATS" />

    <!--TODO  使用证书-->
    <!--147 允许程序请求验证从AccountManager-->
    <uses-permission android:name="android.permission.USE_CREDENTIALS" />

    <!--TODO 使用SIP视频-->
    <!--148 允许程序使用SIP视频服务-->
    <uses-permission android:name="android.permission.USE_SIP" />

    <!--=========================V（1个）===========================-->

    <!--TODO 振动-->
    <!--149 振动-->
    <uses-permission android:name="android.permission.VIBRATE" />

    <!--=========================W（17个）===========================-->

    <!--TODO 读写权限-->
    <!--150 读写权限-->
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

    <!--TODO 唤醒锁定-->
    <!--151 允许程序在手机屏幕关闭后后台进程仍然运行-->
    <uses-permission android:name="android.permission.WAKE_LOCK" />

    <!--TODO 写入GPRS接入点设置-->
    <!--152 写入GPRS接入点设置-->
    <uses-permission android:name="android.permission.WRITE_APN_SETTINGS" />

    <!--TODO 写入日程提醒-->
    <!--153 写入日程提醒-->
    <uses-permission android:name="android.permission.WRITE_CALENDAR" />

    <!--TODO 写入联系人数据，但不可读取-->
    <!--154写入联系人数据，但不可读取-->
    <uses-permission android:name="android.permission.WRITE_CALL_LOG" />

    <!--TODO 写入联系人到通讯录，但不可读取-->
    <!--154写入联系人，但不可读取-->
    <uses-permission android:name="android.permission.WRITE_CONTACTS" />

    <!--155 允许程序修改Google服务地图-->
    <uses-permission android:name="android.permission.WRITE_GSERVICES" />

    <!--TODO 写入收藏夹和历史记录-->
    <!--156 写入浏览器历史记录或收藏夹，但不可读取-->
    <uses-permission android:name="android.permission.WRITE_HISTORY_BOOKMARKS" />

    <!--157写入个人资料数据-->
    <uses-permission android:name="android.permission.WRITE_PROFILE" />

    <!--TODO 读写系统敏感设置-->
    <uses-permission android:name="android.permission.WRITE_SECURE_SETTINGS" />

    <!--TODO 读写系统设置-->
    <!--159 读写系统设置-->
    <uses-permission android:name="android.permission.WRITE_SETTINGS" />

    <!--160 编写短信-->
    <uses-permission android:name="android.permission.WRITE_SMS" />

    <!--161 读取用户的社交信息流-->
    <uses-permission android:name="android.permission.WRITE_SOCIAL_STREAM" />

    <!--162 写入Google在线同步设置-->
    <uses-permission android:name="android.permission.WRITE_SYNC_SETTINGS" />

    <!--163 允许应用程序向用户词典中写入新词-->
    <uses-permission android:name="android.permission.WRITE_USER_DICTIONARY" />

    <!--164 写语音邮件-->
    <uses-permission android:name="android.permission.WRITE_VOICEMAIL" />



(二)AndroidManifest.xml的9个危险权限组
-------------------------------

    <!--TODO 说明：危险权限需要在6.0+中（动态）申请，所有的危险权限都由权限组划分，如下：-->

    <!--==================1-日历 android.permission-group.CALENDAR 2个==================-->
    <uses-permission android:name="android.permission.READ_CALENDAR" />
    <uses-permission android:name="android.permission.WRITE_CALENDAR" />

    <!--==================2-相机 android.permission-group.CAMERA 1个==================-->
    <uses-permission android:name="android.permission.CAMERA" />

    <!--==================3-通讯录 android.permission-group.CONTACTS 3个==================-->

    <uses-permission android:name="android.permission.READ_CONTACTS" />
    <uses-permission android:name="android.permission.WRITE_CONTACTS" />
    <uses-permission android:name="android.permission.GET_ACCOUNTS" />

    <!--==================4-定位 android.permission-group.LOCATION 2个==================-->
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />

    <!--==================5-录音 android.permission-group.MICROPHONE 1个==================-->
    <uses-permission android:name="android.permission.RECORD_AUDIO" />

    <!--==================6-电话 android.permission-group.PHONE 7个==================-->
    <uses-permission android:name="android.permission.ADD_VOICEMAIL" />
    <uses-permission android:name="android.permission.CALL_PHONE" />
    <uses-permission android:name="android.permission.PROCESS_OUTGOING_CALLS" />
    <uses-permission android:name="android.permission.PROCESS_OUTGOING_CALLS" />
    <uses-permission android:name="android.permission.READ_CALL_LOG" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.USE_SIP" />
    <uses-permission android:name="android.permission.WRITE_CALL_LOG" />

    <!--==================7-传感器 android.permission-group.SENSORS 1个==================-->
    <uses-permission android:name="android.permission.BODY_SENSORS" />

    <!--==================8-短信 android.permission-group.SMS 6个==================-->
    <uses-permission android:name="android.permission.READ_CELL_BROADCASTS" />
    <uses-permission android:name="android.permission.SEND_SMS" />
    <uses-permission android:name="android.permission.RECEIVE_MMS" />
    <uses-permission android:name="android.permission.RECEIVE_SMS" />
    <uses-permission android:name="android.permission.RECEIVE_WAP_PUSH" />
    <uses-permission android:name="android.permission.SEND_SMS" />

    <!--==================9-存储 android.permission-group.STORAGE 2个==================-->
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />


(三) 单个权限申请的方法（SingleAct）
------------

注意：targetSdkVersion和Build.VERSION.SDK_INT都大于23时，才起作用

1.判断是否需要申请权限的判断,说明：>23不可以使用ActivityCompat.checkSelfPermission,应使用ContextCompat.checkSelfPermission

     //1步骤
    //判断是否需要申请权限
    if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
    &&this.getApplicationInfo().targetSdkVersion>=Build.VERSION_CODES.M){
        //需要申请权限
        //2步骤
    }else {
          //权限已经申请，可以使用功能
    }
    
    


2.是否需要向用户解释为何申请权限:该处代码可自定义,比如用弹窗，snackbar等方式

     if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)){
        //解释为何申请权限的代码
        
        
     }else{
        //3步骤
     }
     
     

3.申请权限

     //申请权限
     ActivityCompat.requestPermissions(SingleAct.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE);
     
     
     
4.重写onRequestPermissionsResult方法，权限处理结果

     @Override
       public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            switch (requestCode) {
                case REQUEST_CODE_SIGNLE:
                         
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        //单个权限申请 通过的处理
                                
                    } else {
                        //5.单个权限申请拒绝的处理
                               
                    }
           
                           break;
                }
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
       }
       
5.特殊情况:如果一个权限第一次拒绝后，第二次申请时，点击不再询问+拒绝，想第三次时使用，则只有一种方式打开，代码如下：

 （1）Snackbar/弹窗，触发判断if (!ActivityCompat.shouldShowRequestPermissionRationale(SingleAct.this, permissions[0]))
  
 （2）通过Intent打开设置 手动打开权限设置
 
 （3）通过startActivityForResult+onActivityResult的方式再次申请权限
  
  

    //单个权限申请拒绝的处理
    Snackbar.make(layout, "申请权限拒绝！", Snackbar.LENGTH_SHORT)
        .setAction("设置", new View.OnClickListener() {//TODO
            @Override
            public void onClick(View view) {
                //(1)
                if (!ActivityCompat.shouldShowRequestPermissionRationale(SingleAct.this, permissions[0])) {
                    //(2).手动打开权限设置
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", SingleAct.this.getPackageName(), null);
                    intent.setData(uri);
                    startActivityForResult(intent, REQUEST_SECOND_OPEN);
                }
            }
        })
    .show();
    
    //(3)
     @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            switch (resultCode) {
                case REQUEST_SECOND_OPEN://永久拒绝后，手动打开权限的返回处理
                    //3.再次申请
                    ActivityCompat.requestPermissions(SingleAct.this, new String[]{permissionStr}, REQUEST_CODE_SIGNLE);
    
                    break;
            }
    
        }



(四) 多个权限申请的方法（MultiAct）
------------
说明：原生申请方式和单个权限方式相同，需要注意的地方便是：

1.判断是否需要申请权限的判断，必须包含所有要申请的权限判断，用||隔开，比如有两个，就要写两个，不可以写1个

2.是否需要向用户解释为何申请权限，也要写全要申请的权限判断，用||隔开

4.重写onRequestPermissionsResult方法可如下(和单个申请没有区别)：

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull final String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_MULTI:
                boolean isGranted = true;
                StringBuilder builder = new StringBuilder();
                deniedArray = new String[grantResults.length];
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] < 0) {
                        isGranted = false;
                        builder.append(permissions[i]);
                        builder.append(" ");
                        deniedArray[i] = permissions[i];
                    }
                }

                if (isGranted) {
                    //多个权限申请 通过的处理
                    Snackbar.make(layout, "申请权限通过",
                            Snackbar.LENGTH_SHORT)
                            .show();
                    //可以执行功能了
                    for (DataBean item : selectList) {
                        for (DataBean bean : lists) {
                            if (item.getPermission().equals(bean.getPermission())) {
                                bean.setUsed(true);
                            }
                        }

                    }

                } else {
                    //权限申请拒绝的处理
                    Snackbar.make(layout, "权限" + builder.toString() + "被拒绝！", Snackbar.LENGTH_SHORT)
                            .setAction("设置", new View.OnClickListener() {//TODO
                                @Override
                                public void onClick(View view) {
                                    //不询问，直接打开设置界面
                                    if (!ActivityCompat.shouldShowRequestPermissionRationale(MultiAct.this, deniedArray[0])) {
                                        //3.手动打开权限
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        Uri uri = Uri.fromParts("package", MultiAct.this.getPackageName(), null);
                                        intent.setData(uri);
                                        startActivityForResult(intent, REQUEST_SECOND_OPEN);
                                    }
                                }
                            })
                            .show();
                }

                break;
        }

    }
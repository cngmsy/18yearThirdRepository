﻿#版本更新（兼容Android 7.0）
#效果图gif
![](aaaa.gif)

#实现的核心代码是
#步骤一:

      # 1.进入应用时,首先获取文件读写权限.
	  # 2.请求服务器,判断当前的Code与服务器的Code是否相等
	  # 3.如果版本号相等,则直接跳转到主页面,如果不相等,弹出对话框,点击更新,请求服务器的ApkUrl
	  # 4.调用Android下载器DownloadManager 下载Apk
	  # 5.下载完成,使用 Intent隐式跳转,安装Apk包
	  #   注意：7.0需要配置权限, 效果注释，都在Demo里。

#步骤二:




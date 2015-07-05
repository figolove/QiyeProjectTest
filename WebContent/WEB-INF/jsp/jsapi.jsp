<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SpringMVC</title>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
function checkJsApi(){
	alert("进入判断当前客户端版本是否支持指定JS接口");
	wx.checkJsApi({
	    jsApiList: ['chooseImage'],
	    success: function(res) {
	    	 // 以键值对的形式返回，可用的api值true，不可用为false
	    	 // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
	    	 alert(res);
	    }
	});
}
function onMenuShareTimeline(){
	wx.onMenuShareTimeline({
	    title: 'JS-SDK', // 分享标题
	    link: 'http://www.omsapp.cn', // 分享链接
	    imgUrl: 'http://www.omsapp.cn/a.jpg', // 分享图标
	    success: function () {
	        // 用户确认分享后执行的回调函数
	        alert("我分享了");
	    },
	    cancel: function () {
	        // 用户取消分享后执行的回调函数
	    	 alert("我取消分享了");
	    }
	});
}
function chooseImage(){
	wx.chooseImage({
	    success: function (res) {
	        var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
	        alert(localIds);
	    }
	});
}

function scanQRCode(){
	alert("进入扫一扫...");
	wx.scanQRCode({
	    desc: 'scanQRCode desc',
	    needResult: 0, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
	    scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
	    success: function (res) {
	   		var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
	   		alert(result);
		}
	});
}
</script>
</head>
<body>
	<h1>微信JS-SDK</h1>
	<button onclick="checkJsApi()">判断当前客户端版本是否支持指定JS接口</button><br/>
	<button onclick="onMenuShareTimeline()">分享接口</button><br/>
	<button onclick="chooseImage()">图像接口</button><br/>
	<button onclick="previewImage()">预览图片接口</button><br/>
	<button onclick="uploadImage()">上传图片接口</button><br/>
	<button onclick="downloadImage()">下载图片接口</button><br/>
	<hr/><h2>音频接口</h2><br/>
	<button onclick="startRecord()">开始录音接口</button><br/>
	<button onclick="stopRecord()">停止录音接口</button><br/>
	<button onclick="onVoiceRecordEnd()">监听录音自动停止接口</button><br/>
	<button onclick="playVoice()">播放语音接口</button>  <br/>
	<button onclick="pauseVoice()">暂停播放接口</button><br/>
	<button onclick="stopVoice()">停止播放接口</button><br/>
	<button onclick="onVoicePlayEnd()">监听语音播放完毕接口</button><br/>
	<button onclick="uploadVoice()">上传语音接口</button><br/>
	<button onclick="downloadVoice()">下载语音接口</button><br/>
	<hr/><h2>智能接口</h2><br/>
	<button onclick="translateVoice()">识别音频并返回识别结果接口</button><br/>
	<hr/><h2>地理位置</h2><br/>
	<button onclick="openLocation()">使用微信内置地图查看位置接口</button><br/>
	<button onclick="getLocation()">获取地理位置接口</button><br/>
	<hr/><h2>界面操作</h2>
	<button onclick="honclickeOptionMenu()">隐藏右上角菜单接口</button><br/>
	<button onclick="showOptionMenu()">显示右上角菜单接口</button><br/>
	<button onclick="closeWindow()">关闭当前网页窗口接口</button>  <br/>
	<button onclick="honclickeMenuItems()">批量隐藏功能按钮接口</button>  <br/>
	<button onclick="showMenuItems()">批量显示功能按钮接口</button>  <br/>
	<button onclick="honclickeAllNonBaseMenuItem()">隐藏所有非基础按钮接口</button>  <br/>
	<button onclick="showAllNonBaseMenuItem()">显示所有功能按钮接口</button>  <br/>
	<hr/><h2>微信扫一扫</h2>
	<button onclick="scanQRCode()">调起微信扫一扫接口</button>  <br/>
</body>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
  /*
   * 注意：
   * 1. 所有的JS接口只能在公众号绑定的域名下调用，公众号开发者需要先登录微信公众平台进入“公众号设置”的“功能设置”里填写“JS接口安全域名”。
   * 2. 如果发现在 Android 不能分享自定义内容，请到官网下载最新的包覆盖安装，Android 自定义分享接口需升级至 6.0.2.58 版本及以上。
   * 3. 常见问题及完整 JS-SDK 文档地址：http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html
   *
   * 开发中遇到问题详见文档“附录5-常见错误及解决办法”解决，如仍未能解决可通过以下渠道反馈：
   * 邮箱地址：weixin-open@qq.com
   * 邮件主题：【微信JS-SDK反馈】具体问题
   * 邮件内容说明：用简明的语言描述问题所在，并交代清楚遇到该问题的场景，可附上截屏图片，微信团队会尽快处理你的反馈。
   */
   alert("JS-SDk即将初始化！");
  wx.config({
    debug: true,
    appId: '${appid}',
    timestamp: '${timestamp}',
    nonceStr: '${nonceStr}',
    signature: '${signature}',
    jsApiList: [
      // 所有要调用的 API 都要加到这个列表中
      'onMenuShareTimeline',
      'onMenuShareAppMessage',
      'onMenuShareQQ',
      'onMenuShareWeibo',
      'startRecord',
      'stopRecord',
      'onVoiceRecordEnd',
      'playVoice',
      'pauseVoice',
      'stopVoice',
      'onVoicePlayEnd',
      'uploadVoice',
      'downloadVoice',
      'chooseImage',
      'previewImage',
      'uploadImage',
      'downloadImage',
      'translateVoice',
      'getNetworkType',
      'openLocation',
      'getLocation',
      'hideOptionMenu',
      'showOptionMenu',
      'hideMenuItems',
      'showMenuItems',
      'hideAllNonBaseMenuItem',
      'showAllNonBaseMenuItem',
      'closeWindow',
      'scanQRCode'
    ]
  });
  wx.ready(function () {
	  wx.getNetworkType({
		    success: function (res) {
		        var networkType = res.networkType; // 返回网络类型2g，3g，4g，wifi
		        alert(networkType);
		    }
		});
    
  });
  wx.error(function(res){
		alert("进入wx.error="+res);
	    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。

	});
</script>
</html>
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  // bindtest: function(res) {
  //   //请求code
  //   wx.login({
  //     success(res) {
  //       if (res.code) {
  //         //发起网络请求
  //         wx.request({
  //           url: 'http://101.200.39.35:8080/New_war/wx',
  //           data: {
  //             //这里的data就是request对象携带的的请求参数
  //             code: res.code
  //           }
  //         })
  //       } else {
  //         console.log('登录失败！' + res.errMsg)
  //       }
  //     }
  //   })
  // },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (res) {
    var that = this;
    //查看是否授权
    wx.getSetting({
      success: function (res) {
        if (res.authSetting['scope.userInfo']) {
          console.log("用户授权了");
        } else {
          //用户没有授权
          console.log("用户没有授权");
        }
      }
    });
  },

  bindGetUserInfo: function (res) {
    if (res.detail.userInfo) {
      //用户按了允许授权按钮
      var that = this;
      wx.request({
        url: 'http://101.200.39.35:8080/New_war/wxUserInfo',
        data: {
          rawData: res.detail.userInfo,
        },
        header: {
          'content-type': 'application/json', // 默认值
          'cookie': 'JSESSIONID=' + wx.getStorageSync("sessionId")
        },
        success: function (res) {
          console.log(wx.getStorageSync("sessionId"));
          console.log(res);
        },
      })
      wx.redirectTo({
        url: '../homepage/homepage',
      })
    } else {
      //用户按了拒绝按钮
      wx.showModal({
        title: 'Tip',
        content: '您点击了拒绝授权，请授权之后再进入!!!',
        showCancel: false,
        confirmText: '好吧',
        success: function (res) {
          // 用户没有授权成功，不需要改变 isHide 的值
          if (res.confirm) {
            console.log('用户点击了“返回授权”');
          }
        }
      });
    }
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    wx.hideHomeButton()
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})
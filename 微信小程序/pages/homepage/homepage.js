const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    nickName: "",
    avatarUrl: "",
    image:"",
  },

  clicknew: function() {
    wx.navigateTo({
      url: '../startnew/startnew',
    })
  },
  clickcheck: function() {
    wx.navigateTo({
      url: '../checkexist/checkexist',
    })
  },

  /*老式的获取权限方式废弃掉了
    getUserInfo: function(e) {
      let that = this;
      //console.log(e)
      wx.getSetting({
        success(res) {
          if (res.authSetting['scope.userInfo']) {
            console.log("已授权====")
            wx.getUserInfo({
              success(res) {
                console.log("获取用户信息成功", res)
                that.setData({
                  name: res.userInfo.nickName //用户昵称
                })
              },
              fail(res) {
                console.log("获取用户信息失败", res)
              }
            })
          } else {
            console.log("未授权====")
            that.showSettingToast("请授权")
          }
        }
      })
    },

    //权限设置提示
    showSettingToast: function(e) {
      wx.showModal({
        title: '必须授权',
        confirmText: '好吧',
        content: '拒绝授权将无法使用功能，请重新授权',
        success: function(res) {
          if (res.confirm) {
            wx.openSetting({
              success: (res) => {
                if (res.authSetting["scope.userInfo"]) { ////如果用户重新同意了授权登录
                  wx.getUserInfo({
                    success: function(res) {
                      var userInfo = res.userInfo;
                      that.setData({
                        nickName: userInfo.nickName,
                        avatarUrl: userInfo.avatarUrl,
                      })
                    }
                  })
                }
              },
              fail: function(res) {

              }
            })
          }
        }
      })
    },*/

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    //查看是否授权
    wx.getSetting({
      success: function (res) {
        if (res.authSetting['scope.userInfo']) {
          console.log("用户授权了");
          /*注意此处使用app（在js头部getApp()获取）.globalData.xxx获取全局变量 */
          if (app.globalData.userInfo != undefined) {
            console.log("显示用户头像")
              that.data.image = app.globalData.userInfo.avatarUrl
          }
        } else {
          //用户没有授权
          /*wx.navigateTo({
            url: '../index/index',
          })*/
          console.log("用户没有授权");
        }
      }
    });
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})
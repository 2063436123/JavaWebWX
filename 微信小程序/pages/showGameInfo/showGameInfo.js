// pages/showGameInfo/showGameInfo.js
var util = require('../../utils/util.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    isSponsor: '',
    participants: "",
    onSigning: false, //是否在报名阶段
    notSignedUp: true, //是否已报名
    gameId: "",
    time:"",
    name:"",
    content:"",
    site:"",
    deadline:"",
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    //console.log(wx.getStorageSync("sessionId"));
    var that = this;
    console.log(options);
    that.setData({
      gameId: options.gameId,
      time:options.time,
      name:options.name,
      content:options.content,
      site:options.site,
      deadline:options.deadline,
    })
    if ((options.deadline + options.time) >= util.formatTimeWithoutSecond(new Date())) {
      console.log("onsigning has been true;");
      that.setData({
        onSigning: true,
      })
    }
    wx.request({
      url: 'http://101.200.39.35:8080/New_war/wxShowGameInfo',
      data: {
        gameId: options.gameId,
      },
      header: {
        'content-type': 'application/json', // 默认值
        'cookie': 'JSESSIONID=' + wx.getStorageSync("sessionId")
      },
      success(res) { //回调函数，应对response返回值(game信息及参与人员)进行处理
        console.log(res.data);
        // console.log(res.data.isSponsor);
        that.setData({
          participants: res.data.participants,
          isSponsor: res.data.isSponser,
          notSignedUp: !res.data.isSignedUp, //WAITING...!!!
        })
        console.log(res.data);
      }
    })
  },

  cancel: function() {
    var that = this;
    //先提示是否取消
    wx.showModal({
      title: 'Tip',
      content: '确定要取消比赛吗?此操作不可逆！',
      showCancel: true,
      success(res) {
        if (res.confirm) {
          console.log('用户点击确定')
          wx.request({
            url: 'http://101.200.39.35:8080/New_war/wxCancelGame',
            data: {
              gameId: that.data.gameId,
            },
            header: {
              'content-type': 'application/json', // 默认值
              'cookie': 'JSESSIONID=' + wx.getStorageSync("sessionId")
            },
            success(res) {
              wx.showToast({
                title: '取消成功',
                icon: 'success',
                duration: 1500,
                mask: true,
                success: function(res) {
                  setTimeout(that.bridgeMethod, 1500);
                },
              })
            }
          })
        }
      }
    });
  },

  bridgeMethod: function() {
    wx.redirectTo({
      url: '/pages/homepage/homepage',
    })
    },

  signUp: function() {
    var that = this;
    //后台关联显示报名成功
    wx.request({
      url: 'http://101.200.39.35:8080/New_war/wxSignUpGame',
      data: {
        gameId: that.data.gameId,
      },
      header: {
        'content-type': 'application/json', // 默认值
        'cookie': 'JSESSIONID=' + wx.getStorageSync("sessionId")
      },
      success(res) {
        wx.showToast({
          title: '报名成功',
          icon: 'success',
          duration: 1500,
          success(res) {
            that.onReady;
            that.data.notSignup = false;
            setTimeout(that.bridgeMethod, 1500);
          }
        })
        console.log("待测试,报名成功后是否出现正确提示并刷新界面")
        // wx.showModal({
        //   title: 'Tip',
        //   content: '报名成功！',
        //   showCancel: false,
        //   success(res) {
        //     wx.navigateBack({
        //       //返回比赛列表
        //     })
        //     that.data.notSignup = false;
        //   }
        // });
      }
    })
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
    return {
      title: '一起来玩呀，滴！',
      // path: '/pages/showGameInfo/showGameInfo?gameId=' + this.data.gameId + "&deadline=" + this.data.time,
      path: '/pages/showGameInfo/showGameInfo?gameId=' + this.data.gameId + "&deadline=" + this.data.deadline + "&time=" + this.data.time + "&content=" + this.data.content + "&name=" + this.data.name + "&site=" + this.data.site,
      imageUrl: '/image/ball.png',
      success(e) {
        wx.showToast({
          title: '转发成功',
          icon: 'success',
          duration: 2000,
          mask: true,
          success: function(res) {},
          fail: function(res) {},
          complete: function(res) {},
        })
      }
    }
  }
})
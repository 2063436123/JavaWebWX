const app = getApp()
var util = require('../../utils/util.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    now: util.formatTimeWithoutSecond(new Date()),
    myGames: "",
    bottomHidden: 'none',
  },

  transfer: function (games) {
    //console.log(games);
    var that = this;
    var i;
    for (i = 0; i < this.data.myGames.length; i++) {
      if (games[i].name == "篮球") games[i].name = "basketball";
      else if (games[i].name == "足球") games[i].name = "football";
      else if (games[i].name == "乒乓球") games[i].name = "tabletennis";
      else if (games[i].name == "羽毛球") games[i].name = "badminton";
      else if (games[i].name == "网球") games[i].name = "tennis";
      else if (games[i].name == "排球") games[i].name = "volleyball";
    }
    return games;
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //console.log(options);
    this.setData({})
    //从servlet与用户有关的获取比赛信息
    var that = this;
    //console.log(wx.getStorage("sessionId"));
    wx.request({
      url: 'http://101.200.39.35:8080/New_war/wxRelevantGame', //接口地址
      data: {},
      header: {
        'content-type': 'application/json', // 默认值
        'Cookie': 'JSESSIONID=' + wx.getStorageSync("sessionId")
      },
      success(res) {
        //回调函数，应对response返回值(使用res.data调用整个返回的JSON字符串)进行处理
        that.setData({
          //直接把games数组传给变量即可，变量myGames自动成为对象数组
          myGames: that.transfer(res.data.games),
        })
      }
    }) 
  },

  //跳转到比赛参与人员界面
  showGame: function (e) {
    wx.navigateTo({
      url: '../showGameInfo/showGameInfo?gameId=' + e.currentTarget.dataset.c + "&deadline=" + e.currentTarget.dataset.deadline + "&time=" + e.currentTarget.dataset.time + "&content=" + e.currentTarget.dataset.content + "&name=" + e.currentTarget.dataset.name + "&site=" + e.currentTarget.dataset.site,
    })
  },

  //选择开关
  switch1Change: function (res) {
    //console.log(res.detail.value);
    if (this.data.bottomHidden == 'run-in')
      this.setData({
        bottomHidden: 'none'
      })
    else
      this.setData({
        bottomHidden: 'run-in'
      })
    //console.log(this.data.bottomHidden);
  },

  cancel: function (e) {
    wx.showToast({
      title: '删除成功',
      icon: 'success',
      duration: 1500,
    })
    var that = this;
    wx.request({
      url: 'http://101.200.39.35:8080/New_war/wxCancelGame',
      data: {
        gameId: e.currentTarget.dataset.id,
      },
      header: {
        'content-type': 'application/json', // 默认值
        'cookie': 'JSESSIONID=' + wx.getStorageSync("sessionId")
      },
      success(res) {
        setTimeout(that.bridgeMethod, 1500);
      }
    })
  },

  //桥方法
  bridgeMethod: function () {
    //重定向当前界面
    wx.reLaunch({
      url: '../checkexist/checkexist',
    })
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
    this.onLoad();
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
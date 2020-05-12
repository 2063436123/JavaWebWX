// pages/setgameinfo/setgameinfo.js
var util = require('../../utils/util.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    nowDate: util.formatNowDate(new Date()),
    nowTime: util.formatNowTime(new Date()),
    date: util.formatNowDate(new Date()),
    time: util.formatNowTimeExtraTen(new Date()),
    name: "",
    array: ['一号场', '二号场', '体育馆', '待定'],
    objectArray: [{
      id: 0,
      name: '一号场'
    },
    {
      id: 1,
      name: '二号场'
    },
    {
      id: 2,
      name: '体育馆'
    },
    {
      id: 3,
      name: '待定'
    }
    ],
    index: 0,
    content: "", //比赛名称
    hasContent: false,
    tempGameId: "",
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      name: options.name
    })
    //拿到传过来的球类name信息
    console.log(this.data.name)
  },

  //输入比赛名称
  getContent: function (e) {
    this.setData({
      content: e.detail.value
    })
  },

  //检查比赛名是否为空
  checkContent: function () {
    if (this.data.content != "") {
      this.setData({
        hasContent: true,
      })
    } else {
      this.setData({
        hasContent: false,
      })
    }
  },

  submit: function () {
    var that = this;
    //console.log(wx.getStorageSync("sessionId"));
    wx.request({
      url: 'http://101.200.39.35:8080/New_war/wxGameInfo', //接口地址
      data: {
        name: that.data.name,
        date: that.data.date,
        time: that.data.time,
        site: that.data.array[that.data.index],
        content: that.data.content
      },
      header: {
        'content-type': 'application/json', // 默认值
        'cookie': 'JSESSIONID=' + wx.getStorageSync("sessionId")
      },
      success(res) {
        that.setData({
          tempGameId: res.data,
        })
        wx.showToast({
          title: '成功创建比赛',
          icon: 'success',
          duration: 2000,
          success(res) {
            setTimeout(that.bridgeMethod, 1500);
          }
        })
      }
    })
  },

  bridgeMethod: function () {
    wx.redirectTo({
      // url: '../showGameInfo/showGameInfo?gameId=' + this.data.tempGameId + "&deadline=" + this.data.date+' ' + this.data.time, //跳转到此比赛信息界面
      url: '../showGameInfo/showGameInfo?gameId=' + this.data.tempGameId + "&deadline=" + this.data.date + "&time=" + this.data.time + "&content=" + this.data.content + "&name=" + this.data.name + "&site=" + "",
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

  bindDateChange: function (e) {
    //console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      date: e.detail.value
    })
  },
  bindTimeChange: function (e) {
    //console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      time: e.detail.value
    })
  },
  bindPickerChange: function (e) {
    //console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      index: e.detail.value
    })
  },
  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})
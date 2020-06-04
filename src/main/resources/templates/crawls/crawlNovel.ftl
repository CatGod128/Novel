<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../html/assets/css/layui.css">
    <link rel="stylesheet" href="../html/assets/css/view.css"/>
    <link rel="icon" href="/favicon.ico">
    <title>管理后台</title>
</head>
<body class="layui-view-body">
    <div class="layui-content">
        <div class="layui-page-header">
            <div class="pagewrap">
                <span class="layui-breadcrumb">
                  <a href="">首页</a>
                  <a href="">爬虫</a>
                  <a><cite>爬取图片</cite></a>
                </span>
                <h2 class="title">爬取图片</h2>
            </div>
        </div>
       <form class="layui-form" a>
           <div class="layui-form-item">
               <div class="layui-inline">
                   <label class="layui-form-label">图片地址</label>
                   <div class="layui-input-inline">
                       <input type="text" name="url" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input">
                   </div>
               </div>
           </div>
           <div class="layui-form-item">
               <div class="layui-input-block">
                   <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">开始爬取</button>
                   <button type="reset" class="layui-btn layui-btn-primary">重置</button>
               </div>
           </div>
           <div class="layui-form-item">
               <div class="layui-input-block">
                  <input id="message"/>
               </div>
           </div>
       </form>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
            <legend>动态改变进度</legend>
        </fieldset>

        <div class="layui-progress layui-progress-big" lay-showpercent="true" lay-filter="prodemo">
            <div id="prodemo" class="layui-progress-bar layui-bg-red" lay-percent="0%"></div>
        </div>

        <div class="site-demo-button" style="margin-top: 20px; margin-bottom: 0;">
            <button class="layui-btn site-demo-active" data-type="setPercent">设置50%</button>
            <o class="layui-btn site-demo-active" data-type="loading" id="loading">模拟loading</o>
        </div>
    </div>
    <script src="../html/assets/layui.all.js"></script>
    <script src="../static/js/jquery-1.11.3.min.js"></script>
    <script src="../static/publicjs.js"></script>
    <script>
        layui.use('element', function(){
            var $ = layui.jquery
                ,element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块

            //触发事件
            var active = {
                setPercent: function(){
                    //设置50%进度
                    element.progress('prodemo', '50%')
                }
                ,loading: function(othis){
                    $.ajax({
                        type:'get',
                        datatype:'JSON',
                        async:false,
                        url:'/testProcess',
                        contenType:'application/json;charset=UTF-8',
                        success:function (r) {
                            console.log(r);
                            element.progress('prodemo', r+'%');
                            if(r<100){
                                //进行进度条渲染
                                $('#loading').click();
                            }
                        }
                    })
                }
            };

            $('.site-demo-active').on('click', function(){
                var othis = $(this), type = $(this).data('type');
                active[type] ? active[type].call(this, othis) : '';
            });
        });
    </script>
    <script>
        layui.use(['form','jquery'],function(){

            //var form = layui.form(),
            var form = layui.form
            $ = layui.jquery ;
            form.on('submit(demo1)',function(data){
                $.ajax({
                    url:'/Spider',
                    type:'post',
                    data:data.field,
                    dataType:'json',
                    success:function(message){
                        console.log(message);
                        alert(message);
                    }
                })
                return false;
            });
        });
    </script>
</body>
</html>
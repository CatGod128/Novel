<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0,maximum-scale=1.0,user-scalable=no,">
<title> ${ChapterName}
</title>
<link href="../static/css/bootstrap.css" rel="stylesheet" type="text/css">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" href="../static/css/chapter.css" type="text/css">
</head>
<body>
<div class="container-fluid" id="mainDiv" >
<div class="row">
  <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-lg-8 col-lg-offset-2">
    <!--主面板-->
    <div class="panel panel-success">
      <div class="panel-body table-bordered">
        <!--页头-->
        <div class="row" data-toggle="modal" data-target="#btnGroup" data-backdrop="true">
          <div class="col-xs-12 text-center">
            <h3><strong><em>﻿ ${ChapterName}
</em></strong></h3>
          </div>
        </div>
        <!--页头 end-->
        <hr> 
        <!--内容框-->
        <div class="row" id="content">
          <div id="col-xs-10 col-xs-offset-1">
        ${Content}
          </div>
        </div>
        <!--内容框 end-->
      </div>
    </div>
    <!--主面板 end-->
  </div>
  
</div>
<div class="row">
  <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-lg-8 col-lg-offset-2">
      <nav aria-label="...">
        <ul class="pager">
            <li><a  class="btn btn-lg" onclick="javascript:refreshto('${privousId!""}')"><span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span> 上一章</a></li>
            <li><a  class="btn btn-lg" onclick="javascript:Home('${NovelId}')"><span class="glyphicon glyphicon-home" aria-hidden="true" ></span> 目 录</a></li>
            <li><a  class="btn btn-lg" onclick="javascript:refreshto('${nextId!""}')">下一章<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span></a></li>
        </ul>
      </nav>
  </div>
</div>
<hr>

<!-- 字体加减 段落加减 默认 护眼 仿真-->
<div id="btnGroup" class="modal fade">
  <div class="row">
    <div>
      <hr>
      <div class="row">
        <button id="subfont" class="btn col-xs-3 col-xs-offset-2 col-sm-1 col-sm-offset-5"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span></button>
        <div class="col-xs-2 col-sm-1"><img src="../../../img/字体.png" alt="字体"></div>
        <button id="plusfont" class="btn col-xs-3 col-sm-1"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
      </div>
      <hr>
      <div class="row">
        <button id="subpara" class="btn col-xs-3 col-xs-offset-2 col-sm-1 col-sm-offset-5"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span></button>
        <div class="col-xs-2  col-sm-1"><img src="../../../img/段落.png" alt="段落" class="img-rounded"></div>
        <button id="pluspara" class="btn col-xs-3 col-sm-1 "><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
      </div>
      <hr>
      <div class="row">
        <button id="bookkind" class="btn col-xs-3 col-xs-offset-2 col-sm-1 col-sm-offset-5">护眼</button>
        <div class="col-xs-2  col-sm-1"><img src="../../../img/颜色.png" alt="段落" class="img-rounded"></div>
        <button id="bookdefault" class="btn col-xs-3 col-sm-1 ">仿真</button>
      </div>
      <hr>
      <div class="row">
        <button id="defaultinit" class="btn col-xs-8 col-xs-offset-2 col-sm-3 col-sm-offset-5">默认</button>
      </div>
      <hr>
    </div>
  </div>
</div>
<!-- 字体加减 段落加减 默认 护眼 仿真 end-->
</div>
<script src="../static/js/jquery-1.11.3.min.js"></script>
<script src="../static/js/bootstrap.js"></script>
<script src="../static/js/chapter.js"></script>
<script>
    function refreshto(id){
         var url ='queryChapterInfo?id='+id;
         window.location.href=url;
    }
    function Home(id){
        var url ='queryNovelInfo?id='+id;
        window.location.href=url;
    }
</script>
</body>
</html>

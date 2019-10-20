<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no,minimum-scale=1.0,maximum-scale=1.0">
<title>章节选择</title>
<link href="./static/css/bootstrap.css" rel="stylesheet" type="text/css">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" href="./static/css/chaptersInfo.css">
</head>
<body >
<div class="container-fluid">
<div class="row">
  <div class="col-xs-12 col-sm-10 col-sm-offset-1">
    <div class="panel panel-success">
      <div class="row">
        <div class="col-xs-12 text-center">
          <h3><strong> ${NovelName} </strong></h3>
          <hr>
        </div>
      </div>
      <div class="panel-body ">
        <div class="row">
        <#list ChapterList as Chapter>
          <div class=" col-xs-12 col-sm-6 col-md-4"><a onclick="refreshto('${Chapter.id}')">${Chapter.chapterName}</a></div>
        </#list>
        </div>
      </div>
    </div>
    
  </div>
</div>
</div>
<script>
    function refreshto(id){
        var url ='queryChapterInfo?id='+id;
        window.location.href=url;
    }

</script>
</body>
</html>

// JavaScript Document
  var fontSize=1.2;
  var lineHeight=12;
  var bgColor="default";
  var ptags=document.getElementsByTagName("p");
  var divs=document.getElementsByTagName("div");
  var content=document.getElementById("content");
  var atags=document.getElementsByTagName("a");
  var pageBtns=document.getElementsByClassName("btn-lg");
  /*字体增大*/
  function getUrlStr(){
    "use strict";
    return "?"+fontSize+"?"+lineHeight+"?"+bgColor;
  }
  document.getElementById("plusfont").onclick=function() {
    "use strict";
    fontSize+=0.1;
    content.style.fontSize=fontSize+"em";
    for(var i=0;i<pageBtns.length;i++){
      pageBtns[i].href=pageBtns[i].href.split(".html")[0]+".html"+getUrlStr();
    }
  };
  /*字体减小*/
  document.getElementById("subfont").onclick=function() {
    "use strict";
    fontSize-=0.1;
    content.style.fontSize=fontSize+"em";
    for(var i=0;i<pageBtns.length;i++){
      pageBtns[i].href=pageBtns[i].href.split(".html")[0]+".html"+getUrlStr();
    }
  };
  document.getElementById("subpara").onclick=function() {
    "use strict";
    lineHeight-=1;
    for(var i=0;i<ptags.length;i++){
      ptags[i].style.marginBottom=lineHeight+"px";
    }
    for( i=0;i<pageBtns.length;i++){
      pageBtns[i].href=pageBtns[i].href.split(".html")[0]+".html"+getUrlStr();
    }
  };
  document.getElementById("pluspara").onclick=function() {
    "use strict";
    lineHeight+=1;
    for(var i=0;i<ptags.length;i++){
      ptags[i].style.marginBottom=lineHeight+"px";
    }
    for( i=0;i<pageBtns.length;i++){
      pageBtns[i].href=pageBtns[i].href.split(".html")[0]+".html"+getUrlStr();
    }
  };
  document.getElementById("defaultinit").onclick=function() {
    "use strict";
    document.location.href=document.location.href.split(".html")[0]+".html";
  };
  document.getElementById("bookkind").onclick=function() {
    "use strict";
    for(var i=0;i<divs.length;i++){
      divs[i].style.background="#d7e0cb";
    }
    for(i=0;i<atags.length;i++){
      atags[i].style.background="#d7e0cb";
    }
    document.getElementById("mainDiv").style.background="#d7e0cb";
    bgColor="bookkind";
    for(i=0;i<pageBtns.length;i++){
      pageBtns[i].href=pageBtns[i].href.split(".html")[0]+".html"+getUrlStr();
    }
  };
  document.getElementById("bookdefault").onclick=function() {
    "use strict";
    for(var i=0;i<divs.length;i++){
      divs[i].style.background="#ecdabb";
    }
    for(i=0;i<atags.length;i++){
      atags[i].style.background="#ecdabb";
    }
    document.getElementById("mainDiv").style.background="#ecdabb";
    bgColor="bookdefault";
    for(i=0;i<pageBtns.length;i++){
      pageBtns[i].href=pageBtns[i].href.split(".html")[0]+".html"+getUrlStr();
    }
  };

  function reloadPage(){
    "use strict";
    var parms= document.location.href.split(".html")[1];
    if(parms!==""){
      parms=parms.split("?");
      fontSize= parseFloat(parms[1])
      content.style.fontSize=parms[1]+"em";
      lineHeight=parseInt(parms[2]);
      for(var i=0;i<ptags.length;i++){
        ptags[i].style.marginBottom=lineHeight+"px";
      }
      if(parms[3]==="bookdefault"){
         document.getElementById("bookdefault").click();
      }
      else if(parms[3]==="bookkind"){
        document.getElementById("bookkind").click();
      }
      else {
        for(i=0;i<pageBtns.length;i++){
            pageBtns[i].href=pageBtns[i].href.split(".html")[0]+".html"+getUrlStr();
        }
      }
        
    }
  }

  function advertisement(){
    var time= new Date();
    if(time.getHours()<6){
        for(var i=0;i<1;i++){
            alert(time.toString()+"\n"+"媳妇,还不睡,小心你妈打你!\n对了,姨妈还有一天,注意了啊");  
        }
    }
    
  }

  reloadPage();
  advertisement();
  
   

  

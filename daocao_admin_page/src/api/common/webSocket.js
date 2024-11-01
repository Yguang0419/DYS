var webSocket = new WebSocket("ws://127.0.0.1:80/websocket");
    webSocket.onopen = function(event){
        console.log("连接成功");
        //console.log(event);
    };
    webSocket.onerror = function(event){
        console.log("连接失败");
        //console.log(event);
    };
    webSocket.onclose = function(event){
        console.log("Socket连接断开");
        //console.log(event);
    };
    webSocket.onmessage = function(event){
        //接受来自服务器的消息
        var data = event.data;
        var usercode = "${usercode}";
        if(data!="1"){
        	var subdata = data.substring(0, 4);
        	 if("jiab"== subdata){//加班登记(工作日加班)
	        	//判断当前是否登记过加班，登记过则不弹提示框
	        	$.post("${ctx}//addworktable/findAddWork",function(data){
	        		if(data=="1"){
	        			return;
	        		}else{
	        			$("#swdiv2").empty();
	                	var newuri="${ctx}";
	        				 var val="	<div class=\"swiper-slide\">\n" + 
	        				    		" 		       <a href=\"javascript:void(0)\"   class=\"addTabPage\"data-tab-id=\"tabpanel-10qaw53482\" data-title=\"加班登记提醒\"\n" + 
	        				    		"					data-href=\""+newuri+"/addworktable/form\">\n" + 				    		"				 <div class=\"img_div\">  \n" + 
	        				    		"				        <div class=\"img_att2\" style='border-radius:0'>  \n" + 
	        				    		"				            <img src=\"/images/jbdj.png"+"\" class=\"img_size\" />  \n" + 
	        				    		"				        </div> \n" + 
	        				    		"				 </div> \n" + 
	        				    		"		         <div class=\"content_dive\">\n" + 
	        				    		"		         	请您及时登记今天的加班情况！" + 
	        				    		"		         </div>\n" + 
	        				    		"		        </a>\n" + 
	        				    		"		      </div>";
	        				 $("#swdiv2").append(val);
	        				 //弹出窗口
	        				 $(".fdbody2").animate({height:"show"},800);
	                		bf();//提示音播放
	        		}
	        	})
		
        	}
        	else if("zmji"== subdata){//加班登记(节假日加班)
            	//判断当前是否登记过加班，登记过则不弹提示框
            	$.post("${ctx}/addworktable/findzmAddWork",function(data){
            		console.log(data);
            		if(data.type=="1"){
	        			return;
	        		}else{
	        			$("#swdiv2").empty();
	                	var newuri="${ctx}";
	        				 var val="	<div class=\"swiper-slide\">\n" + 
	        				    		" 		       <a href=\"javascript:void(0)\"   class=\"addTabPage\"data-tab-id=\"tabpanel-10qaw53482\" data-title=\"加班登记提醒\"\n" + 
	        				    		"					data-href=\""+newuri+"/addworktable/form?id="+data.id+"\">\n" + 				    		"				 <div class=\"img_div\">  \n" + 
	        				    		"				        <div class=\"img_att2\" style='border-radius:0'>  \n" + 
	        				    		"				            <img src=\"/static/images/jbdj.png"+"\" class=\"img_size\" />  \n" + 
	        				    		"				        </div> \n" + 
	        				    		"				 </div> \n" + 
	        				    		"		         <div class=\"content_dive\">\n" + 
	        				    		"		         	请您及时登记今天的加班情况！" + 
	        				    		"		         </div>\n" + 
	        				    		"		        </a>\n" + 
	        				    		"		      </div>";
	        				 $("#swdiv2").append(val);
	        				 //弹出窗口
	        				 $(".fdbody2").animate({height:"show"},800);
	                		bf();//提示音播放
	        		}
            	})
            	}
 
        }
        //myAuto.stop();
       // myAuto.play();
 
    }
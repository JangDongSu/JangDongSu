$(document).ready(function(){
						/*startTimer();
						function startTimer(){
							timer = setInterval(
									function(){
										$.getJSON("/notice/getIotState",
												function(iot){
												console.log(iot);
										});
										showList();
									},3000);
							}*/
							
						/*function showList(){
							$.getJSON("/notice/list",
									function(list){
										var str="";
										for(var i=0, len = list.length || 0; i<len; i++){
											str += "<li data-no='"+list[i].n_bno+"'>";
											//str ="</li>"
										}
										$(".chat").html(str);
							}).fail(function(xhr, status, err){
								if(error){
									console.log(error);
								}
							});
						}
						function showList() {
							$.getJSON("/notice/list",
							    function(list) {
									var str="";
								    if(list == null || list.length == 0){
								    	replyUL.html(str);
								    	return;
								    }
								    for (var i = 0, len = list.length || 0; i < len; i++) {
								    	str +="<li data-no='"+list[i].n_bno+"'>";
										str +=" <a href='/notice/'" +list[i].n_bno+ "'><span class='latest-title'>"+list[i].n_title+"</span>";
										str +="<span class='latest-date'>" + noticeService.displayTime(list[i].regdate) + "</span>";
										str +="</a></li>";
					    			}
								    $(".notice-latest").html(str);
							}).fail(function(xhr, status, err) {
								if (error) {
									error();
								}
							});
						}*/

					function displayTime(timeValue) {
						var today = new Date();
						var gap = today.getTime() - timeValue;
						var dateObj = new Date(timeValue);
						var str = "";
						var yy = dateObj.getFullYear();
						var mm = dateObj.getMonth() + 1; // getMonth() is zero-based
						var dd = dateObj.getDate();
						/*var hh = dateObj.getHours();
						var mi = dateObj.getMinutes();
						var ss = dateObj.getSeconds();*/
						return [ yyyy, '-', (mm > 9 ? '' : '0') + mm, '-', (dd > 9 ? '' : '0') + dd ].join('');
					}
					});
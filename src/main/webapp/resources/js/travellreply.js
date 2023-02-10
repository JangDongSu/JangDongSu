console.log("Reply module....");
var travellReplyService = (function(){
	function add(reply, callback, error){
		console.log("add  reply..............");
		
		$.ajax({
			type : 'post',
			url : '/travellreplies/new',
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error : function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		})
	}
	
	function getList(param, callback, error){
		console.log("getList.111");
		var t_bno = param.t_bno;
		var page = param.page || 1;
		
		$.getJSON("/travellreplies/pages/" + t_bno +"/" + page,
		function(data){
			if(callback){
				callback(data.replyCnt, data.list);
			}
		}).fail(function(xhr, status, err){
			if(error){
				error();
			}
		});
	}
	
	function remove(t_rno, replyer, callback, error){
		console.log("remove");
		$.ajax({
			type : 'delete',
			url : '/travellreplies/' + t_rno,
			data : JSON.stringify({t_rno:t_rno, replyer:replyer}),
			contentType : "application/json; charset=utf-8",
			success : function(deleteResult, status, xhr){
				if(callback){
					callback(deleteResult);
				}
			},
			error : function(xhr, status, er){
				if(error){
					error(er);
				}				
			}
		});
	}
	
	function update(reply, callback, error){
		console.log("T_RNO: "+ reply.t_rno);
		
		$.ajax({
			type : 'put',
			url : '/travellreplies/' + reply.t_rno,
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error : function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		});
	}
		function get(t_rno, callback, error){
			$.get("/travellreplies/" + t_rno,
			function(result) {
				if(callback){
					callback(result);
				}
			}).fail(function(xhr, status, err){
				if(error){
					error();
				}
				
			});
		
	}
	
	function displayTime(timeValue){
		var today = new Date();
		var gap = today.getTime() - timeValue;
		var dataObj = new Date(timeValue);
		var str = "";
		if(gap < (1000 * 60 * 60 * 24)){
			var hh = dataObj.getHours();
			var mi = dataObj.getMinutes();
			var ss = dataObj.getSeconds();
			return[(hh > 9 ? '' : '0') + hh, ':',(mi > 9 ? '' : '0') + mi, ':', (ss > 9 ? '' : '0') + ss ].join('');
		}else{
			var yy = dataObj.getFullYear();
			var mm = dataObj.getMonth() + 1; // getMonth() is zero-based
			var dd = dataObj.getDate();
			return[yy, '/', (mm > 9 ? '' : '0') + mm, '/', (dd > 9 ? '' : '0') + dd].join('');
		}
	}
	
	return {
		add:add, getList : getList, remove : remove, update : update, get : get, displayTime : displayTime
		};
})();
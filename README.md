# JSON2SQL connects JSON with SQL.
This is a project designed to connect json with SQL. Welcome to pull it and modify it. I belive we can make it more flexible.
```json
{
	"database": "dm",
	"table": "dm_total_user_profile",
	"type": "bar",
	"type_val": 3,
	"dimension": [{
		"expression": "age",
		"name": "d0",
		"verbose": "年龄"
	}],
	"metric": [{
		"expression": "count(member_no)",
		"name": "m0",
		"verbose": "人数"
	}],
	"filter": [{
		"randomKey": "15562716019695445",
		"expression": "age",
		"name": "c4955",
		"type": 0,
		"optionText": "",
		"operation": "<",
		"verbose": "年龄",
		"sql": "",
		"option": "fun:70"
	}],
	"order": [{
		"expression": "age",
		"name": "c4955",
		"type": 0,
		"operation": "asc",
		"verbose": "年龄",
		"sql": ""
	}],
	"limit": "100",
	"join":[{
	  "join_method":"left join",
	  "slice":{"database":"dm","table_id":351,"table":"dm_total_user_profile","type":"not_paginate_table","type_val":2,"dimension":[{"expression":"telphone_no","name":"d0","verbose":"会员手机号"},{"expression":"last_seq_days","name":"d1","verbose":"离最后一次购物间隔天数"}],"metric":[],"filter":[{"expression":"substring(register_time,1,10)","name":"c5008","type":0,"operation":">=","verbose":"注册日期","sql":"","option":"2019-03-01"},{"expression":"register_time","name":"c5006","type":0,"operation":"<=","verbose":"注册时间","sql":"","option":"2019-04-15"},{"expression":"register_store","name":"c4963","type":0,"operation":">=","verbose":"注册门店代码","sql":"","option":"3000"}],"order":[{"name":"d0","verbose":"","expression":"telphone_no","operation":"asc"}],"limit":5000,"title":"新会员","href":{},"appID":"1658","appType":"slice"},
	  "associate": {
	    "left":"1",
	    "op":"=",
	    "right":"1"
	  }
	}] 
}
```

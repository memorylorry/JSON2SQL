# JSON2SQL connects JSON with SQL.
This is a project designed to connect json with SQL. Welcome to pull it and modify it. I belive we can make it more flexible.

formart:
```json

{
	"database": "dbName",
	"table": "tableName",
	"dimension": [{
		"name": "create_time_day",
		"verbose": "创建时间(天)",
		"expression": "DATE_FORMAT(create_time,'%Y-%m-%d')",
		"type": "1"
	}],
	"metric": [{
		"name": "user_number",
		"verbose": "用户数",
		"expression": "count(DISTINCT user_code)"
	}],
	"filter": [{
		"name": "create_time_day",
		"verbose": "创建时间(天)",
		"expression": "DATE_FORMAT(create_time,'%Y-%m-%d')",
		"operation": "in",
		"type": 0,
		"option": "['2018-03-22']",
		"optionText": "11,3,4"
	}, {
		"name": "user_number",
		"verbose": "用户数",
		"expression": "count(DISTINCT user_code)",
		"operation": "in",
		"type": 1,
		"option": ["1", "2"],
		"optionText": "11,2"
	}],
	"order": [{
		"name": "create_time_day",
		"verbose": "创建时间(天)",
		"expression": "DATE_FORMAT(create_time,'%Y-%m-%d')",
		"operation": "asc"
	}],
	"limit": "1000",
	"title": "用户创建日期分析",
	"type": "table",
	"type_val": 2
}
```
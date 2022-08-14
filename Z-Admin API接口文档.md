# Z-Admin


**简介**:Z-Admin


**HOST**:localhost:18078


**联系人**:


**Version**:2.1


**接口路径**:/v2/api-docs


[TOC]






# 功能模块相关接口


## 删除模块接口


**接口地址**:`/system/module/delete`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids|选中的模块id集合|query|true|array|integer|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"map": {},
	"msg": ""
}
```


## 修改后台用户接口


**接口地址**:`/system/module/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "children": [
    {
      "children": [],
      "created": "",
      "ctype": 0,
      "curl": "",
      "deleted": 0,
      "icon": "",
      "id": 0,
      "module": "",
      "name": "",
      "operatorId": 0,
      "operatorName": "",
      "parentId": 0,
      "remark": "",
      "req": "",
      "sort": "",
      "state": 0,
      "updated": "",
      "version": 0
    }
  ],
  "created": "",
  "ctype": 0,
  "curl": "",
  "deleted": 0,
  "icon": "",
  "id": 0,
  "module": {
    "children": [],
    "created": "",
    "ctype": 0,
    "curl": "",
    "deleted": 0,
    "icon": "",
    "id": 0,
    "module": "",
    "name": "",
    "operatorId": 0,
    "operatorName": "",
    "parentId": 0,
    "remark": "",
    "req": "",
    "sort": "",
    "state": 0,
    "updated": "",
    "version": 0
  },
  "name": "",
  "operatorId": 0,
  "operatorName": "",
  "parentId": 0,
  "remark": "",
  "req": "",
  "sort": "",
  "state": 0,
  "updated": "",
  "version": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|module|修改的模块|body|true|功能模块|功能模块|
|&emsp;&emsp;children|子模块||false|array|功能模块|
|&emsp;&emsp;created|新增时间||false|string(date-time)||
|&emsp;&emsp;ctype|类型0 主菜单/1 左侧菜单/2按钮/3 链接/4 状态||false|integer(int64)||
|&emsp;&emsp;curl|静态页链接地址||false|string||
|&emsp;&emsp;deleted|逻辑删除字段||false|integer(int32)||
|&emsp;&emsp;icon|图标||false|string||
|&emsp;&emsp;id|主键id||false|integer(int64)||
|&emsp;&emsp;module|本类的自关联对象||false|功能模块|功能模块|
|&emsp;&emsp;name|模块名称||false|string||
|&emsp;&emsp;operatorId|操作用户Id||false|integer(int64)||
|&emsp;&emsp;operatorName|操作用户名称||false|string||
|&emsp;&emsp;parentId|父模块id||false|integer(int64)||
|&emsp;&emsp;remark|备注||false|string||
|&emsp;&emsp;req|页面请求地址||false|string||
|&emsp;&emsp;sort|排序字段||false|string||
|&emsp;&emsp;state|状态1启用0停用||false|integer(int64)||
|&emsp;&emsp;updated|修改时间||false|string(date-time)||
|&emsp;&emsp;version|乐观锁字段||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"map": {},
	"msg": ""
}
```


## 根据id查询单条数据接口


**接口地址**:`/system/module/findById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|选中的模块id|query|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«功能模块»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|功能模块|功能模块|
|&emsp;&emsp;children|子模块|array|功能模块|
|&emsp;&emsp;created|新增时间|string(date-time)||
|&emsp;&emsp;ctype|类型0 主菜单/1 左侧菜单/2按钮/3 链接/4 状态|integer(int64)||
|&emsp;&emsp;curl|静态页链接地址|string||
|&emsp;&emsp;deleted|逻辑删除字段|integer(int32)||
|&emsp;&emsp;icon|图标|string||
|&emsp;&emsp;id|主键id|integer(int64)||
|&emsp;&emsp;module|本类的自关联对象|功能模块|功能模块|
|&emsp;&emsp;name|模块名称|string||
|&emsp;&emsp;operatorId|操作用户Id|integer(int64)||
|&emsp;&emsp;operatorName|操作用户名称|string||
|&emsp;&emsp;parentId|父模块id|integer(int64)||
|&emsp;&emsp;remark|备注|string||
|&emsp;&emsp;req|页面请求地址|string||
|&emsp;&emsp;sort|排序字段|string||
|&emsp;&emsp;state|状态1启用0停用|integer(int64)||
|&emsp;&emsp;updated|修改时间|string(date-time)||
|&emsp;&emsp;version|乐观锁字段|integer(int32)||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"children": [],
		"created": "",
		"ctype": 0,
		"curl": "",
		"deleted": 0,
		"icon": "",
		"id": 0,
		"module": "",
		"name": "",
		"operatorId": 0,
		"operatorName": "",
		"parentId": 0,
		"remark": "",
		"req": "",
		"sort": "",
		"state": 0,
		"updated": "",
		"version": 0
	},
	"map": {},
	"msg": ""
}
```


## 查询全部模块接口


**接口地址**:`/system/module/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«List«功能模块»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|array|功能模块|
|&emsp;&emsp;children|子模块|array|功能模块|
|&emsp;&emsp;created|新增时间|string(date-time)||
|&emsp;&emsp;ctype|类型0 主菜单/1 左侧菜单/2按钮/3 链接/4 状态|integer(int64)||
|&emsp;&emsp;curl|静态页链接地址|string||
|&emsp;&emsp;deleted|逻辑删除字段|integer(int32)||
|&emsp;&emsp;icon|图标|string||
|&emsp;&emsp;id|主键id|integer(int64)||
|&emsp;&emsp;module|本类的自关联对象|功能模块|功能模块|
|&emsp;&emsp;name|模块名称|string||
|&emsp;&emsp;operatorId|操作用户Id|integer(int64)||
|&emsp;&emsp;operatorName|操作用户名称|string||
|&emsp;&emsp;parentId|父模块id|integer(int64)||
|&emsp;&emsp;remark|备注|string||
|&emsp;&emsp;req|页面请求地址|string||
|&emsp;&emsp;sort|排序字段|string||
|&emsp;&emsp;state|状态1启用0停用|integer(int64)||
|&emsp;&emsp;updated|修改时间|string(date-time)||
|&emsp;&emsp;version|乐观锁字段|integer(int32)||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": [
		{
			"children": [],
			"created": "",
			"ctype": 0,
			"curl": "",
			"deleted": 0,
			"icon": "",
			"id": 0,
			"module": "",
			"name": "",
			"operatorId": 0,
			"operatorName": "",
			"parentId": 0,
			"remark": "",
			"req": "",
			"sort": "",
			"state": 0,
			"updated": "",
			"version": 0
		}
	],
	"map": {},
	"msg": ""
}
```


## 查询模块列表接口


**接口地址**:`/system/module/page`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|当前页码|query|true|integer(int32)||
|pageSize|每页数据长度|query|true|integer(int32)||
|name|模块名称|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«Page»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|Page|Page|
|&emsp;&emsp;countId||string||
|&emsp;&emsp;current||integer(int64)||
|&emsp;&emsp;hitCount||boolean||
|&emsp;&emsp;maxLimit||integer(int64)||
|&emsp;&emsp;optimizeCountSql||boolean||
|&emsp;&emsp;orders||array|OrderItem|
|&emsp;&emsp;&emsp;&emsp;asc||boolean||
|&emsp;&emsp;&emsp;&emsp;column||string||
|&emsp;&emsp;pages||integer(int64)||
|&emsp;&emsp;records||array|object|
|&emsp;&emsp;searchCount||boolean||
|&emsp;&emsp;size||integer(int64)||
|&emsp;&emsp;total||integer(int64)||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"countId": "",
		"current": 0,
		"hitCount": true,
		"maxLimit": 0,
		"optimizeCountSql": true,
		"orders": [
			{
				"asc": true,
				"column": ""
			}
		],
		"pages": 0,
		"records": [],
		"searchCount": true,
		"size": 0,
		"total": 0
	},
	"map": {},
	"msg": ""
}
```


## 新增模块接口


**接口地址**:`/system/module/save`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "children": [
    {
      "children": [],
      "created": "",
      "ctype": 0,
      "curl": "",
      "deleted": 0,
      "icon": "",
      "id": 0,
      "module": "",
      "name": "",
      "operatorId": 0,
      "operatorName": "",
      "parentId": 0,
      "remark": "",
      "req": "",
      "sort": "",
      "state": 0,
      "updated": "",
      "version": 0
    }
  ],
  "created": "",
  "ctype": 0,
  "curl": "",
  "deleted": 0,
  "icon": "",
  "id": 0,
  "module": {
    "children": [],
    "created": "",
    "ctype": 0,
    "curl": "",
    "deleted": 0,
    "icon": "",
    "id": 0,
    "module": "",
    "name": "",
    "operatorId": 0,
    "operatorName": "",
    "parentId": 0,
    "remark": "",
    "req": "",
    "sort": "",
    "state": 0,
    "updated": "",
    "version": 0
  },
  "name": "",
  "operatorId": 0,
  "operatorName": "",
  "parentId": 0,
  "remark": "",
  "req": "",
  "sort": "",
  "state": 0,
  "updated": "",
  "version": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|module|新增的模块|body|true|功能模块|功能模块|
|&emsp;&emsp;children|子模块||false|array|功能模块|
|&emsp;&emsp;created|新增时间||false|string(date-time)||
|&emsp;&emsp;ctype|类型0 主菜单/1 左侧菜单/2按钮/3 链接/4 状态||false|integer(int64)||
|&emsp;&emsp;curl|静态页链接地址||false|string||
|&emsp;&emsp;deleted|逻辑删除字段||false|integer(int32)||
|&emsp;&emsp;icon|图标||false|string||
|&emsp;&emsp;id|主键id||false|integer(int64)||
|&emsp;&emsp;module|本类的自关联对象||false|功能模块|功能模块|
|&emsp;&emsp;name|模块名称||false|string||
|&emsp;&emsp;operatorId|操作用户Id||false|integer(int64)||
|&emsp;&emsp;operatorName|操作用户名称||false|string||
|&emsp;&emsp;parentId|父模块id||false|integer(int64)||
|&emsp;&emsp;remark|备注||false|string||
|&emsp;&emsp;req|页面请求地址||false|string||
|&emsp;&emsp;sort|排序字段||false|string||
|&emsp;&emsp;state|状态1启用0停用||false|integer(int64)||
|&emsp;&emsp;updated|修改时间||false|string(date-time)||
|&emsp;&emsp;version|乐观锁字段||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"map": {},
	"msg": ""
}
```


# 后台日志相关接口


## 查询日志列表接口


**接口地址**:`/system/logger/page`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|当前页码|query|true|integer(int32)||
|pageSize|每页数据长度|query|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«Page»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|Page|Page|
|&emsp;&emsp;countId||string||
|&emsp;&emsp;current||integer(int64)||
|&emsp;&emsp;hitCount||boolean||
|&emsp;&emsp;maxLimit||integer(int64)||
|&emsp;&emsp;optimizeCountSql||boolean||
|&emsp;&emsp;orders||array|OrderItem|
|&emsp;&emsp;&emsp;&emsp;asc||boolean||
|&emsp;&emsp;&emsp;&emsp;column||string||
|&emsp;&emsp;pages||integer(int64)||
|&emsp;&emsp;records||array|object|
|&emsp;&emsp;searchCount||boolean||
|&emsp;&emsp;size||integer(int64)||
|&emsp;&emsp;total||integer(int64)||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"countId": "",
		"current": 0,
		"hitCount": true,
		"maxLimit": 0,
		"optimizeCountSql": true,
		"orders": [
			{
				"asc": true,
				"column": ""
			}
		],
		"pages": 0,
		"records": [],
		"searchCount": true,
		"size": 0,
		"total": 0
	},
	"map": {},
	"msg": ""
}
```


# 后台用户相关接口


## 加载用户权限菜单接口


**接口地址**:`/system/user/author`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«List«功能模块»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|array|功能模块|
|&emsp;&emsp;children|子模块|array|功能模块|
|&emsp;&emsp;created|新增时间|string(date-time)||
|&emsp;&emsp;ctype|类型0 主菜单/1 左侧菜单/2按钮/3 链接/4 状态|integer(int64)||
|&emsp;&emsp;curl|静态页链接地址|string||
|&emsp;&emsp;deleted|逻辑删除字段|integer(int32)||
|&emsp;&emsp;icon|图标|string||
|&emsp;&emsp;id|主键id|integer(int64)||
|&emsp;&emsp;module|本类的自关联对象|功能模块|功能模块|
|&emsp;&emsp;name|模块名称|string||
|&emsp;&emsp;operatorId|操作用户Id|integer(int64)||
|&emsp;&emsp;operatorName|操作用户名称|string||
|&emsp;&emsp;parentId|父模块id|integer(int64)||
|&emsp;&emsp;remark|备注|string||
|&emsp;&emsp;req|页面请求地址|string||
|&emsp;&emsp;sort|排序字段|string||
|&emsp;&emsp;state|状态1启用0停用|integer(int64)||
|&emsp;&emsp;updated|修改时间|string(date-time)||
|&emsp;&emsp;version|乐观锁字段|integer(int32)||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": [
		{
			"children": [],
			"created": "",
			"ctype": 0,
			"curl": "",
			"deleted": 0,
			"icon": "",
			"id": 0,
			"module": "",
			"name": "",
			"operatorId": 0,
			"operatorName": "",
			"parentId": 0,
			"remark": "",
			"req": "",
			"sort": "",
			"state": 0,
			"updated": "",
			"version": 0
		}
	],
	"map": {},
	"msg": ""
}
```


## 删除后台用户接口


**接口地址**:`/system/user/delete`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids|选中的课程id集合|query|true|array|integer|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"map": {},
	"msg": ""
}
```


## 修改后台用户接口


**接口地址**:`/system/user/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "adminId": 0,
  "created": "",
  "deleted": 0,
  "dept": {
    "created": "",
    "deleted": 0,
    "deptId": 0,
    "deptName": "",
    "operatorId": 0,
    "operatorName": "",
    "remark": "",
    "updated": "",
    "version": 0
  },
  "deptId": "",
  "name": "",
  "operatorId": 0,
  "operatorName": "",
  "password": "",
  "state": 0,
  "updated": "",
  "userName": "",
  "version": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|admin|修改的后台用户|body|true|后台用户|后台用户|
|&emsp;&emsp;adminId|主键id||false|integer(int64)||
|&emsp;&emsp;created|新增时间||false|string(date-time)||
|&emsp;&emsp;deleted|逻辑删除字段||false|integer(int32)||
|&emsp;&emsp;dept|关联的部门类型||false|部门|部门|
|&emsp;&emsp;&emsp;&emsp;created|新增时间||false|string||
|&emsp;&emsp;&emsp;&emsp;deleted|逻辑删除字段||false|integer||
|&emsp;&emsp;&emsp;&emsp;deptId|主键id||false|integer||
|&emsp;&emsp;&emsp;&emsp;deptName|部门名称||false|string||
|&emsp;&emsp;&emsp;&emsp;operatorId|操作用户Id||false|integer||
|&emsp;&emsp;&emsp;&emsp;operatorName|操作用户名称||false|string||
|&emsp;&emsp;&emsp;&emsp;remark|备注||false|string||
|&emsp;&emsp;&emsp;&emsp;updated|修改时间||false|string||
|&emsp;&emsp;&emsp;&emsp;version|乐观锁字段||false|integer||
|&emsp;&emsp;deptId|部门id||false|string||
|&emsp;&emsp;name|用户姓名||false|string||
|&emsp;&emsp;operatorId|操作用户Id||false|integer(int64)||
|&emsp;&emsp;operatorName|操作用户名称||false|string||
|&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;state|用户状态||false|integer(int64)||
|&emsp;&emsp;updated|修改时间||false|string(date-time)||
|&emsp;&emsp;userName|用户登录名||false|string||
|&emsp;&emsp;version|乐观锁字段||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"map": {},
	"msg": ""
}
```


## 根据id查询单条数据接口


**接口地址**:`/system/user/findById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|选中的用户id|query|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«后台用户»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|后台用户|后台用户|
|&emsp;&emsp;adminId|主键id|integer(int64)||
|&emsp;&emsp;created|新增时间|string(date-time)||
|&emsp;&emsp;deleted|逻辑删除字段|integer(int32)||
|&emsp;&emsp;dept|关联的部门类型|部门|部门|
|&emsp;&emsp;&emsp;&emsp;created|新增时间|string||
|&emsp;&emsp;&emsp;&emsp;deleted|逻辑删除字段|integer||
|&emsp;&emsp;&emsp;&emsp;deptId|主键id|integer||
|&emsp;&emsp;&emsp;&emsp;deptName|部门名称|string||
|&emsp;&emsp;&emsp;&emsp;operatorId|操作用户Id|integer||
|&emsp;&emsp;&emsp;&emsp;operatorName|操作用户名称|string||
|&emsp;&emsp;&emsp;&emsp;remark|备注|string||
|&emsp;&emsp;&emsp;&emsp;updated|修改时间|string||
|&emsp;&emsp;&emsp;&emsp;version|乐观锁字段|integer||
|&emsp;&emsp;deptId|部门id|string||
|&emsp;&emsp;name|用户姓名|string||
|&emsp;&emsp;operatorId|操作用户Id|integer(int64)||
|&emsp;&emsp;operatorName|操作用户名称|string||
|&emsp;&emsp;password|用户密码|string||
|&emsp;&emsp;state|用户状态|integer(int64)||
|&emsp;&emsp;updated|修改时间|string(date-time)||
|&emsp;&emsp;userName|用户登录名|string||
|&emsp;&emsp;version|乐观锁字段|integer(int32)||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"adminId": 0,
		"created": "",
		"deleted": 0,
		"dept": {
			"created": "",
			"deleted": 0,
			"deptId": 0,
			"deptName": "",
			"operatorId": 0,
			"operatorName": "",
			"remark": "",
			"updated": "",
			"version": 0
		},
		"deptId": "",
		"name": "",
		"operatorId": 0,
		"operatorName": "",
		"password": "",
		"state": 0,
		"updated": "",
		"userName": "",
		"version": 0
	},
	"map": {},
	"msg": ""
}
```


## 后台用户登录接口


**接口地址**:`/system/user/login`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|map|用户信息 userName-用户名 password-密码|body|true|||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«后台用户»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|后台用户|后台用户|
|&emsp;&emsp;adminId|主键id|integer(int64)||
|&emsp;&emsp;created|新增时间|string(date-time)||
|&emsp;&emsp;deleted|逻辑删除字段|integer(int32)||
|&emsp;&emsp;dept|关联的部门类型|部门|部门|
|&emsp;&emsp;&emsp;&emsp;created|新增时间|string||
|&emsp;&emsp;&emsp;&emsp;deleted|逻辑删除字段|integer||
|&emsp;&emsp;&emsp;&emsp;deptId|主键id|integer||
|&emsp;&emsp;&emsp;&emsp;deptName|部门名称|string||
|&emsp;&emsp;&emsp;&emsp;operatorId|操作用户Id|integer||
|&emsp;&emsp;&emsp;&emsp;operatorName|操作用户名称|string||
|&emsp;&emsp;&emsp;&emsp;remark|备注|string||
|&emsp;&emsp;&emsp;&emsp;updated|修改时间|string||
|&emsp;&emsp;&emsp;&emsp;version|乐观锁字段|integer||
|&emsp;&emsp;deptId|部门id|string||
|&emsp;&emsp;name|用户姓名|string||
|&emsp;&emsp;operatorId|操作用户Id|integer(int64)||
|&emsp;&emsp;operatorName|操作用户名称|string||
|&emsp;&emsp;password|用户密码|string||
|&emsp;&emsp;state|用户状态|integer(int64)||
|&emsp;&emsp;updated|修改时间|string(date-time)||
|&emsp;&emsp;userName|用户登录名|string||
|&emsp;&emsp;version|乐观锁字段|integer(int32)||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"adminId": 0,
		"created": "",
		"deleted": 0,
		"dept": {
			"created": "",
			"deleted": 0,
			"deptId": 0,
			"deptName": "",
			"operatorId": 0,
			"operatorName": "",
			"remark": "",
			"updated": "",
			"version": 0
		},
		"deptId": "",
		"name": "",
		"operatorId": 0,
		"operatorName": "",
		"password": "",
		"state": 0,
		"updated": "",
		"userName": "",
		"version": 0
	},
	"map": {},
	"msg": ""
}
```


## 后台用户退出接口


**接口地址**:`/system/user/logout`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"map": {},
	"msg": ""
}
```


## 查询后台用户列表接口


**接口地址**:`/system/user/page`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|当前页码|query|true|integer(int32)||
|pageSize|每页数据长度|query|true|integer(int32)||
|name|根据用户姓名查询|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«Page»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|Page|Page|
|&emsp;&emsp;countId||string||
|&emsp;&emsp;current||integer(int64)||
|&emsp;&emsp;hitCount||boolean||
|&emsp;&emsp;maxLimit||integer(int64)||
|&emsp;&emsp;optimizeCountSql||boolean||
|&emsp;&emsp;orders||array|OrderItem|
|&emsp;&emsp;&emsp;&emsp;asc||boolean||
|&emsp;&emsp;&emsp;&emsp;column||string||
|&emsp;&emsp;pages||integer(int64)||
|&emsp;&emsp;records||array|object|
|&emsp;&emsp;searchCount||boolean||
|&emsp;&emsp;size||integer(int64)||
|&emsp;&emsp;total||integer(int64)||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"countId": "",
		"current": 0,
		"hitCount": true,
		"maxLimit": 0,
		"optimizeCountSql": true,
		"orders": [
			{
				"asc": true,
				"column": ""
			}
		],
		"pages": 0,
		"records": [],
		"searchCount": true,
		"size": 0,
		"total": 0
	},
	"map": {},
	"msg": ""
}
```


## 新增后台用户接口


**接口地址**:`/system/user/save`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "adminId": 0,
  "created": "",
  "deleted": 0,
  "dept": {
    "created": "",
    "deleted": 0,
    "deptId": 0,
    "deptName": "",
    "operatorId": 0,
    "operatorName": "",
    "remark": "",
    "updated": "",
    "version": 0
  },
  "deptId": "",
  "name": "",
  "operatorId": 0,
  "operatorName": "",
  "password": "",
  "state": 0,
  "updated": "",
  "userName": "",
  "version": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|admin|新增的后台用户|body|true|后台用户|后台用户|
|&emsp;&emsp;adminId|主键id||false|integer(int64)||
|&emsp;&emsp;created|新增时间||false|string(date-time)||
|&emsp;&emsp;deleted|逻辑删除字段||false|integer(int32)||
|&emsp;&emsp;dept|关联的部门类型||false|部门|部门|
|&emsp;&emsp;&emsp;&emsp;created|新增时间||false|string||
|&emsp;&emsp;&emsp;&emsp;deleted|逻辑删除字段||false|integer||
|&emsp;&emsp;&emsp;&emsp;deptId|主键id||false|integer||
|&emsp;&emsp;&emsp;&emsp;deptName|部门名称||false|string||
|&emsp;&emsp;&emsp;&emsp;operatorId|操作用户Id||false|integer||
|&emsp;&emsp;&emsp;&emsp;operatorName|操作用户名称||false|string||
|&emsp;&emsp;&emsp;&emsp;remark|备注||false|string||
|&emsp;&emsp;&emsp;&emsp;updated|修改时间||false|string||
|&emsp;&emsp;&emsp;&emsp;version|乐观锁字段||false|integer||
|&emsp;&emsp;deptId|部门id||false|string||
|&emsp;&emsp;name|用户姓名||false|string||
|&emsp;&emsp;operatorId|操作用户Id||false|integer(int64)||
|&emsp;&emsp;operatorName|操作用户名称||false|string||
|&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;state|用户状态||false|integer(int64)||
|&emsp;&emsp;updated|修改时间||false|string(date-time)||
|&emsp;&emsp;userName|用户登录名||false|string||
|&emsp;&emsp;version|乐观锁字段||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"map": {},
	"msg": ""
}
```


## 根据用户查询角色接口


**接口地址**:`/system/user/updateRole`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|map|adminId-后台用户id roles-选中的角色集合|body|true|object||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"map": {},
	"msg": ""
}
```


## 根据用户查询角色接口


**接口地址**:`/system/user/userRoleList`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|后台用户id|query|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«List«后台用户角色»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|array|后台用户角色|
|&emsp;&emsp;checked|用户与角色关联回显数据|string||
|&emsp;&emsp;created|新增时间|string(date-time)||
|&emsp;&emsp;deleted|逻辑删除字段|integer(int32)||
|&emsp;&emsp;name|角色名称|string||
|&emsp;&emsp;operatorId|操作用户Id|integer(int64)||
|&emsp;&emsp;operatorName|操作用户名称|string||
|&emsp;&emsp;remark|描述信息|string||
|&emsp;&emsp;roleId|主键id|integer(int64)||
|&emsp;&emsp;updated|修改时间|string(date-time)||
|&emsp;&emsp;version|乐观锁字段|integer(int32)||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": [
		{
			"checked": "",
			"created": "",
			"deleted": 0,
			"name": "",
			"operatorId": 0,
			"operatorName": "",
			"remark": "",
			"roleId": 0,
			"updated": "",
			"version": 0
		}
	],
	"map": {},
	"msg": ""
}
```


# 后台部门相关接口


## 删除部门接口


**接口地址**:`/system/dept/delete`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids|选中的部门id集合|query|true|array|integer|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"map": {},
	"msg": ""
}
```


## 修改部门接口


**接口地址**:`/system/dept/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "created": "",
  "deleted": 0,
  "deptId": 0,
  "deptName": "",
  "operatorId": 0,
  "operatorName": "",
  "remark": "",
  "updated": "",
  "version": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|dept|修改的部门|body|true|部门|部门|
|&emsp;&emsp;created|新增时间||false|string(date-time)||
|&emsp;&emsp;deleted|逻辑删除字段||false|integer(int32)||
|&emsp;&emsp;deptId|主键id||false|integer(int64)||
|&emsp;&emsp;deptName|部门名称||false|string||
|&emsp;&emsp;operatorId|操作用户Id||false|integer(int64)||
|&emsp;&emsp;operatorName|操作用户名称||false|string||
|&emsp;&emsp;remark|备注||false|string||
|&emsp;&emsp;updated|修改时间||false|string(date-time)||
|&emsp;&emsp;version|乐观锁字段||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"map": {},
	"msg": ""
}
```


## 根据id查询单条数据接口


**接口地址**:`/system/dept/findById`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|选中的部门id|query|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«部门»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|部门|部门|
|&emsp;&emsp;created|新增时间|string(date-time)||
|&emsp;&emsp;deleted|逻辑删除字段|integer(int32)||
|&emsp;&emsp;deptId|主键id|integer(int64)||
|&emsp;&emsp;deptName|部门名称|string||
|&emsp;&emsp;operatorId|操作用户Id|integer(int64)||
|&emsp;&emsp;operatorName|操作用户名称|string||
|&emsp;&emsp;remark|备注|string||
|&emsp;&emsp;updated|修改时间|string(date-time)||
|&emsp;&emsp;version|乐观锁字段|integer(int32)||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"created": "",
		"deleted": 0,
		"deptId": 0,
		"deptName": "",
		"operatorId": 0,
		"operatorName": "",
		"remark": "",
		"updated": "",
		"version": 0
	},
	"map": {},
	"msg": ""
}
```


## 查询全部部门接口


**接口地址**:`/system/dept/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«List«部门»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|array|部门|
|&emsp;&emsp;created|新增时间|string(date-time)||
|&emsp;&emsp;deleted|逻辑删除字段|integer(int32)||
|&emsp;&emsp;deptId|主键id|integer(int64)||
|&emsp;&emsp;deptName|部门名称|string||
|&emsp;&emsp;operatorId|操作用户Id|integer(int64)||
|&emsp;&emsp;operatorName|操作用户名称|string||
|&emsp;&emsp;remark|备注|string||
|&emsp;&emsp;updated|修改时间|string(date-time)||
|&emsp;&emsp;version|乐观锁字段|integer(int32)||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": [
		{
			"created": "",
			"deleted": 0,
			"deptId": 0,
			"deptName": "",
			"operatorId": 0,
			"operatorName": "",
			"remark": "",
			"updated": "",
			"version": 0
		}
	],
	"map": {},
	"msg": ""
}
```


## 查询部门列表接口


**接口地址**:`/system/dept/page`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|当前页码|query|true|integer(int32)||
|pageSize|每页数据长度|query|true|integer(int32)||
|deptName|部门名称|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«Page»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|Page|Page|
|&emsp;&emsp;countId||string||
|&emsp;&emsp;current||integer(int64)||
|&emsp;&emsp;hitCount||boolean||
|&emsp;&emsp;maxLimit||integer(int64)||
|&emsp;&emsp;optimizeCountSql||boolean||
|&emsp;&emsp;orders||array|OrderItem|
|&emsp;&emsp;&emsp;&emsp;asc||boolean||
|&emsp;&emsp;&emsp;&emsp;column||string||
|&emsp;&emsp;pages||integer(int64)||
|&emsp;&emsp;records||array|object|
|&emsp;&emsp;searchCount||boolean||
|&emsp;&emsp;size||integer(int64)||
|&emsp;&emsp;total||integer(int64)||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"countId": "",
		"current": 0,
		"hitCount": true,
		"maxLimit": 0,
		"optimizeCountSql": true,
		"orders": [
			{
				"asc": true,
				"column": ""
			}
		],
		"pages": 0,
		"records": [],
		"searchCount": true,
		"size": 0,
		"total": 0
	},
	"map": {},
	"msg": ""
}
```


## 新增部门接口


**接口地址**:`/system/dept/save`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "created": "",
  "deleted": 0,
  "deptId": 0,
  "deptName": "",
  "operatorId": 0,
  "operatorName": "",
  "remark": "",
  "updated": "",
  "version": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|dept|新增的部门|body|true|部门|部门|
|&emsp;&emsp;created|新增时间||false|string(date-time)||
|&emsp;&emsp;deleted|逻辑删除字段||false|integer(int32)||
|&emsp;&emsp;deptId|主键id||false|integer(int64)||
|&emsp;&emsp;deptName|部门名称||false|string||
|&emsp;&emsp;operatorId|操作用户Id||false|integer(int64)||
|&emsp;&emsp;operatorName|操作用户名称||false|string||
|&emsp;&emsp;remark|备注||false|string||
|&emsp;&emsp;updated|修改时间||false|string(date-time)||
|&emsp;&emsp;version|乐观锁字段||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"map": {},
	"msg": ""
}
```


# 校区管理相关接口


## 根据id删除数据的方法


**接口地址**:`/school/campus/delete`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids|选中的加盟校id集合|query|true|array|string|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"map": {},
	"msg": ""
}
```


## 修改加盟校数据


**接口地址**:`/school/campus/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "campusId": "",
  "coopTime": "",
  "created": "",
  "deleted": 0,
  "name": "",
  "operatorId": 0,
  "operatorName": "",
  "remark": "",
  "sale": "",
  "updated": "",
  "version": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|campus|修改的加盟校数据|body|true|校区|校区|
|&emsp;&emsp;campusId|主键id||false|string||
|&emsp;&emsp;coopTime|合作时间||false|string(date)||
|&emsp;&emsp;created|新增时间||false|string(date-time)||
|&emsp;&emsp;deleted|逻辑删除字段||false|integer(int32)||
|&emsp;&emsp;name|学校名称||false|string||
|&emsp;&emsp;operatorId|操作用户Id||false|integer(int64)||
|&emsp;&emsp;operatorName|操作用户名称||false|string||
|&emsp;&emsp;remark|校区备注||false|string||
|&emsp;&emsp;sale|所属销售||false|string||
|&emsp;&emsp;updated|修改时间||false|string(date-time)||
|&emsp;&emsp;version|乐观锁字段||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"map": {},
	"msg": ""
}
```


## 根据id查询单条数据


**接口地址**:`/school/campus/findById`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|选中的校区id|query|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«校区»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|校区|校区|
|&emsp;&emsp;campusId|主键id|string||
|&emsp;&emsp;coopTime|合作时间|string(date)||
|&emsp;&emsp;created|新增时间|string(date-time)||
|&emsp;&emsp;deleted|逻辑删除字段|integer(int32)||
|&emsp;&emsp;name|学校名称|string||
|&emsp;&emsp;operatorId|操作用户Id|integer(int64)||
|&emsp;&emsp;operatorName|操作用户名称|string||
|&emsp;&emsp;remark|校区备注|string||
|&emsp;&emsp;sale|所属销售|string||
|&emsp;&emsp;updated|修改时间|string(date-time)||
|&emsp;&emsp;version|乐观锁字段|integer(int32)||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"campusId": "",
		"coopTime": "",
		"created": "",
		"deleted": 0,
		"name": "",
		"operatorId": 0,
		"operatorName": "",
		"remark": "",
		"sale": "",
		"updated": "",
		"version": 0
	},
	"map": {},
	"msg": ""
}
```


## 查看加盟校列表


**接口地址**:`/school/campus/page`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|当前页码|query|true|integer(int32)||
|pageSize|每页数据长度|query|true|integer(int32)||
|address|根据校区地址查询|query|false|string||
|campusName|根据校区名称查询|query|false|string||
|endData|根据结束时间查询，和开始时间共用|query|false|string||
|startData|根据开始时间查询，和结束时间共用|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«Page»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|Page|Page|
|&emsp;&emsp;countId||string||
|&emsp;&emsp;current||integer(int64)||
|&emsp;&emsp;hitCount||boolean||
|&emsp;&emsp;maxLimit||integer(int64)||
|&emsp;&emsp;optimizeCountSql||boolean||
|&emsp;&emsp;orders||array|OrderItem|
|&emsp;&emsp;&emsp;&emsp;asc||boolean||
|&emsp;&emsp;&emsp;&emsp;column||string||
|&emsp;&emsp;pages||integer(int64)||
|&emsp;&emsp;records||array|object|
|&emsp;&emsp;searchCount||boolean||
|&emsp;&emsp;size||integer(int64)||
|&emsp;&emsp;total||integer(int64)||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"countId": "",
		"current": 0,
		"hitCount": true,
		"maxLimit": 0,
		"optimizeCountSql": true,
		"orders": [
			{
				"asc": true,
				"column": ""
			}
		],
		"pages": 0,
		"records": [],
		"searchCount": true,
		"size": 0,
		"total": 0
	},
	"map": {},
	"msg": ""
}
```


## 新增加盟校数据


**接口地址**:`/school/campus/save`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "campusId": "",
  "coopTime": "",
  "created": "",
  "deleted": 0,
  "name": "",
  "operatorId": 0,
  "operatorName": "",
  "remark": "",
  "sale": "",
  "updated": "",
  "version": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|campus|新增的加盟校数据|body|true|校区|校区|
|&emsp;&emsp;campusId|主键id||false|string||
|&emsp;&emsp;coopTime|合作时间||false|string(date)||
|&emsp;&emsp;created|新增时间||false|string(date-time)||
|&emsp;&emsp;deleted|逻辑删除字段||false|integer(int32)||
|&emsp;&emsp;name|学校名称||false|string||
|&emsp;&emsp;operatorId|操作用户Id||false|integer(int64)||
|&emsp;&emsp;operatorName|操作用户名称||false|string||
|&emsp;&emsp;remark|校区备注||false|string||
|&emsp;&emsp;sale|所属销售||false|string||
|&emsp;&emsp;updated|修改时间||false|string(date-time)||
|&emsp;&emsp;version|乐观锁字段||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"map": {},
	"msg": ""
}
```


# 测试相关接口


## 测试接口


**接口地址**:`/hello`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


暂无


**响应示例**:
```javascript

```


# 自定义设置相关接口


## 删除后台用户接口


**接口地址**:`/system/setting/delete`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids|选中的设置id集合|query|true|array|integer|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"map": {},
	"msg": ""
}
```


## 修改设置接口


**接口地址**:`/system/setting/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "created": "",
  "deleted": 0,
  "id": 0,
  "name": "",
  "operatorId": 0,
  "operatorName": "",
  "param": "",
  "remark": "",
  "sort": "",
  "updated": "",
  "version": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|setting|修改的设置|body|true|后台自定义设置|后台自定义设置|
|&emsp;&emsp;created|新增时间||false|string(date-time)||
|&emsp;&emsp;deleted|逻辑删除字段||false|integer(int32)||
|&emsp;&emsp;id|主键id||false|integer(int64)||
|&emsp;&emsp;name|参数名称||false|string||
|&emsp;&emsp;operatorId|操作用户Id||false|integer(int64)||
|&emsp;&emsp;operatorName|操作用户名称||false|string||
|&emsp;&emsp;param|具体参数||false|string||
|&emsp;&emsp;remark|备注||false|string||
|&emsp;&emsp;sort|排序字段||false|string||
|&emsp;&emsp;updated|修改时间||false|string(date-time)||
|&emsp;&emsp;version|乐观锁字段||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"map": {},
	"msg": ""
}
```


## 根据id查询单条数据接口


**接口地址**:`/system/setting/findById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|选中的设置id|query|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«后台自定义设置»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|后台自定义设置|后台自定义设置|
|&emsp;&emsp;created|新增时间|string(date-time)||
|&emsp;&emsp;deleted|逻辑删除字段|integer(int32)||
|&emsp;&emsp;id|主键id|integer(int64)||
|&emsp;&emsp;name|参数名称|string||
|&emsp;&emsp;operatorId|操作用户Id|integer(int64)||
|&emsp;&emsp;operatorName|操作用户名称|string||
|&emsp;&emsp;param|具体参数|string||
|&emsp;&emsp;remark|备注|string||
|&emsp;&emsp;sort|排序字段|string||
|&emsp;&emsp;updated|修改时间|string(date-time)||
|&emsp;&emsp;version|乐观锁字段|integer(int32)||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"created": "",
		"deleted": 0,
		"id": 0,
		"name": "",
		"operatorId": 0,
		"operatorName": "",
		"param": "",
		"remark": "",
		"sort": "",
		"updated": "",
		"version": 0
	},
	"map": {},
	"msg": ""
}
```


## 查询设置列表接口


**接口地址**:`/system/setting/page`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|当前页码|query|true|integer(int32)||
|pageSize|每页数据长度|query|true|integer(int32)||
|name|设置名称|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«Page»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|Page|Page|
|&emsp;&emsp;countId||string||
|&emsp;&emsp;current||integer(int64)||
|&emsp;&emsp;hitCount||boolean||
|&emsp;&emsp;maxLimit||integer(int64)||
|&emsp;&emsp;optimizeCountSql||boolean||
|&emsp;&emsp;orders||array|OrderItem|
|&emsp;&emsp;&emsp;&emsp;asc||boolean||
|&emsp;&emsp;&emsp;&emsp;column||string||
|&emsp;&emsp;pages||integer(int64)||
|&emsp;&emsp;records||array|object|
|&emsp;&emsp;searchCount||boolean||
|&emsp;&emsp;size||integer(int64)||
|&emsp;&emsp;total||integer(int64)||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"countId": "",
		"current": 0,
		"hitCount": true,
		"maxLimit": 0,
		"optimizeCountSql": true,
		"orders": [
			{
				"asc": true,
				"column": ""
			}
		],
		"pages": 0,
		"records": [],
		"searchCount": true,
		"size": 0,
		"total": 0
	},
	"map": {},
	"msg": ""
}
```


## 新增设置接口


**接口地址**:`/system/setting/save`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "created": "",
  "deleted": 0,
  "id": 0,
  "name": "",
  "operatorId": 0,
  "operatorName": "",
  "param": "",
  "remark": "",
  "sort": "",
  "updated": "",
  "version": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|setting|新增的设置参数|body|true|后台自定义设置|后台自定义设置|
|&emsp;&emsp;created|新增时间||false|string(date-time)||
|&emsp;&emsp;deleted|逻辑删除字段||false|integer(int32)||
|&emsp;&emsp;id|主键id||false|integer(int64)||
|&emsp;&emsp;name|参数名称||false|string||
|&emsp;&emsp;operatorId|操作用户Id||false|integer(int64)||
|&emsp;&emsp;operatorName|操作用户名称||false|string||
|&emsp;&emsp;param|具体参数||false|string||
|&emsp;&emsp;remark|备注||false|string||
|&emsp;&emsp;sort|排序字段||false|string||
|&emsp;&emsp;updated|修改时间||false|string(date-time)||
|&emsp;&emsp;version|乐观锁字段||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"map": {},
	"msg": ""
}
```


# 角色相关接口


## 查询角色授权接口


**接口地址**:`/system/role/author`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|选中的角色id|query|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«Map»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|object||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"map": {},
	"msg": ""
}
```


## 删除后台用户接口


**接口地址**:`/system/role/delete`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids|选中的角色id集合|query|true|array|integer|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"map": {},
	"msg": ""
}
```


## 修改角色接口


**接口地址**:`/system/role/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "checked": "",
  "created": "",
  "deleted": 0,
  "name": "",
  "operatorId": 0,
  "operatorName": "",
  "remark": "",
  "roleId": 0,
  "updated": "",
  "version": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|role|修改的角色|body|true|后台用户角色|后台用户角色|
|&emsp;&emsp;checked|用户与角色关联回显数据||false|string||
|&emsp;&emsp;created|新增时间||false|string(date-time)||
|&emsp;&emsp;deleted|逻辑删除字段||false|integer(int32)||
|&emsp;&emsp;name|角色名称||false|string||
|&emsp;&emsp;operatorId|操作用户Id||false|integer(int64)||
|&emsp;&emsp;operatorName|操作用户名称||false|string||
|&emsp;&emsp;remark|描述信息||false|string||
|&emsp;&emsp;roleId|主键id||false|integer(int64)||
|&emsp;&emsp;updated|修改时间||false|string(date-time)||
|&emsp;&emsp;version|乐观锁字段||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"map": {},
	"msg": ""
}
```


## 根据id查询单条数据接口


**接口地址**:`/system/role/findById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|选中的角色id|query|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«后台用户角色»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|后台用户角色|后台用户角色|
|&emsp;&emsp;checked|用户与角色关联回显数据|string||
|&emsp;&emsp;created|新增时间|string(date-time)||
|&emsp;&emsp;deleted|逻辑删除字段|integer(int32)||
|&emsp;&emsp;name|角色名称|string||
|&emsp;&emsp;operatorId|操作用户Id|integer(int64)||
|&emsp;&emsp;operatorName|操作用户名称|string||
|&emsp;&emsp;remark|描述信息|string||
|&emsp;&emsp;roleId|主键id|integer(int64)||
|&emsp;&emsp;updated|修改时间|string(date-time)||
|&emsp;&emsp;version|乐观锁字段|integer(int32)||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"checked": "",
		"created": "",
		"deleted": 0,
		"name": "",
		"operatorId": 0,
		"operatorName": "",
		"remark": "",
		"roleId": 0,
		"updated": "",
		"version": 0
	},
	"map": {},
	"msg": ""
}
```


## 查询角色列表接口


**接口地址**:`/system/role/page`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|当前页码|query|true|integer(int32)||
|pageSize|每页数据长度|query|true|integer(int32)||
|name|角色名称|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«Page»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|Page|Page|
|&emsp;&emsp;countId||string||
|&emsp;&emsp;current||integer(int64)||
|&emsp;&emsp;hitCount||boolean||
|&emsp;&emsp;maxLimit||integer(int64)||
|&emsp;&emsp;optimizeCountSql||boolean||
|&emsp;&emsp;orders||array|OrderItem|
|&emsp;&emsp;&emsp;&emsp;asc||boolean||
|&emsp;&emsp;&emsp;&emsp;column||string||
|&emsp;&emsp;pages||integer(int64)||
|&emsp;&emsp;records||array|object|
|&emsp;&emsp;searchCount||boolean||
|&emsp;&emsp;size||integer(int64)||
|&emsp;&emsp;total||integer(int64)||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"countId": "",
		"current": 0,
		"hitCount": true,
		"maxLimit": 0,
		"optimizeCountSql": true,
		"orders": [
			{
				"asc": true,
				"column": ""
			}
		],
		"pages": 0,
		"records": [],
		"searchCount": true,
		"size": 0,
		"total": 0
	},
	"map": {},
	"msg": ""
}
```


## 新增角色接口


**接口地址**:`/system/role/save`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "checked": "",
  "created": "",
  "deleted": 0,
  "name": "",
  "operatorId": 0,
  "operatorName": "",
  "remark": "",
  "roleId": 0,
  "updated": "",
  "version": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|role|新增的角色|body|true|后台用户角色|后台用户角色|
|&emsp;&emsp;checked|用户与角色关联回显数据||false|string||
|&emsp;&emsp;created|新增时间||false|string(date-time)||
|&emsp;&emsp;deleted|逻辑删除字段||false|integer(int32)||
|&emsp;&emsp;name|角色名称||false|string||
|&emsp;&emsp;operatorId|操作用户Id||false|integer(int64)||
|&emsp;&emsp;operatorName|操作用户名称||false|string||
|&emsp;&emsp;remark|描述信息||false|string||
|&emsp;&emsp;roleId|主键id||false|integer(int64)||
|&emsp;&emsp;updated|修改时间||false|string(date-time)||
|&emsp;&emsp;version|乐观锁字段||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"map": {},
	"msg": ""
}
```


## 保存角色授权接口


**接口地址**:`/system/role/updateRoleModule`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|map|st-选中的角色id checkedArray-选中的模块集合|body|true|object||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|web请求返回«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应编码，1-成功，0或者其他数字-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|map|响应动态数据|object||
|msg|错误信息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"map": {},
	"msg": ""
}
```
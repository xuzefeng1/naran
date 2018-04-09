package com.naran.foundation.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.naran.foundation.system.ErrCode;
import com.naran.foundation.system.NaranCode;

/**
 * @author zefeng.xu
 */
public class ResultMessageBuilder {

	public static ResultMessage build() {
		return new ResultMessage();
	}
	
	public static ResultMessage build(Object data) {
		if (data instanceof ErrCode) {
			ErrCode code = (ErrCode) data;
			return new ResultMessage(code.getCode(), code.getMsg());
		}
		
		return new ResultMessage(NaranCode.SUCCESS_CODE.getCode(), NaranCode.SUCCESS_CODE.getMsg(), data);
	}
	
	public static ResultMessage build(ErrCode code, String msg) {
		return new ResultMessage(code.getCode(), msg);
	}
	
	public static ResultMessage build(ErrCode code, Object data) {
		return new ResultMessage(code.getCode(), code.getMsg(), data);
	}
	
	public static ResultMessage build(ErrCode code, String msg, Object data) {
		return new ResultMessage(code.getCode(), msg, data);
	}

	public static ResultMessageRaw buildRaw(String jsonStr) {
		return new ResultMessageRaw(jsonStr);
	}

	public static ResultMessageRaw buildRawWithDateFormat(Object obj, String dateFormat) {
		return new ResultMessageRaw(JSON.toJSONStringWithDateFormat(obj,
				dateFormat, SerializerFeature.DisableCircularReferenceDetect));
	}
	
	public static class ResultMessage {
		
		private String code;
		private String msg;
		private Object data;
		
		public ResultMessage() {
			this.code = NaranCode.SUCCESS_CODE.getCode();
			this.msg = NaranCode.SUCCESS_CODE.getMsg();
		}

		public ResultMessage(String code) {
			this.code = code;
		}
		
		public ResultMessage(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}
		
		public ResultMessage(String code, Object data) {
			this.code = code;
			this.data = data;
		}

		public ResultMessage(String code, String msg, Object data) {
			this.code = code;
			this.msg = msg;
			this.data = data;
		}
		
		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public ResultMessage setMsg(String msg) {
			this.msg = msg;
			return this;
		}

		public Object getData() {
			return data;
		}

		public ResultMessage setData(Object data) {
			this.data = data;
			return this;
		}

		public String toJSONString() {
			return JSON.toJSONString(this, new SerializerFeature[] { SerializerFeature.DisableCircularReferenceDetect });
		}

	}

	public static class ResultMessageRaw {

		private String jsonStr = null;

		public ResultMessageRaw() {
			
		}

		public ResultMessageRaw(String jsonStr) {
			this.jsonStr = jsonStr;
		}

		public String getJsonStr() {
			return jsonStr;
		}

		public void setJsonStr(String jsonStr) {
			this.jsonStr = jsonStr;
		}

		public String toJSONString() {
			StringBuilder buff = new StringBuilder("{\"data\": ");
			buff.append(jsonStr).append("}");
			return buff.toString();
		}

	}

}

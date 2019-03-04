package com.yjy.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * web层统一返回对象
 * @author leo  下午5:31:11
 */
public class ResponseStatus<T> {

	private Integer code; //0：成功 1：失败
	
	private String msg;
	
	private T data;
	
	public enum ResultEnum{
		SUCCESS(0),
		FAILURE(1);
		Integer code;
		private ResultEnum() {
		}

		private ResultEnum(Integer code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}
	}
	
	public ResponseStatus(){
		
	}
	
	public ResponseStatus(Integer code, String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public ResponseStatus(Integer code, String msg, T data){
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public ResponseStatus<T> success(){
		this.code = 0;
		this.msg = "操作成功";
		return this;
	}
	
	public ResponseStatus<T> success(T data){
		this.code = 0;
		this.msg = "操作成功";
		this.data = data;
		return this;
	}
	
	public ResponseStatus<T> failure(){
		this.code = 1;
		this.msg = "操作失败";
		return this;
	}
	
	public ResponseStatus<T> failure(String msg){
		this.code = 1;
		this.msg = msg;
		return this;
	}
	
//	public ResponseStatus<T> failure(T data){
//		this.code = 1;
//		this.msg = "操作失败";
//		this.data = data;
//		return this;
//	}
	
	public ResponseStatus<T> failure(Integer code, String msg){
		this.code = code;
		this.msg = msg;
		return this;
	}
	
	public ResponseStatus<T> successHalf(T data){
		this.code = 2;
		this.msg = "部分成功";
		this.data=data;
		return this;
	}

	public int getCode() {		//0:成功 1：失败
		return code;
	}

	public ResponseStatus<T> setCode(Integer code) {
		this.code = code;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public ResponseStatus<T> setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public T getData() {
		return data;
	}

	public ResponseStatus<T> setData(T data) {
		this.data = data;
		return this;
	}

	@Override
    public String toString() {
        return JSON.toJSONString(this, new SerializerFeature[]{SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.UseISO8601DateFormat });
    }
	
//	@Override
//	public String toString() {
//		return "ResponseStatus [code=" + code + ", msg=" + msg + ", data=" + data + "]";
//	}

}

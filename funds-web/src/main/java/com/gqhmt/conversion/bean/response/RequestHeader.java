package com.gqhmt.conversion.bean.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="requestHeader")
@XmlType(name = "", propOrder = {})
public class RequestHeader {

	private String sys_node_flag;
	private String is_actual;
	private String req_sys_id;
	private String serv_sys_id;
	private String service_id;
	private String receive_time;

	public String getSys_node_flag() {
		return sys_node_flag;
	}

	public void setSys_node_flag(String sys_node_flag) {
		this.sys_node_flag = sys_node_flag;
	}

	public String getIs_actual() {
		return is_actual;
	}

	public void setIs_actual(String is_actual) {
		this.is_actual = is_actual;
	}

	public String getReq_sys_id() {
		return req_sys_id;
	}

	public void setReq_sys_id(String req_sys_id) {
		this.req_sys_id = req_sys_id;
	}

	public String getServ_sys_id() {
		return serv_sys_id;
	}

	public void setServ_sys_id(String serv_sys_id) {
		this.serv_sys_id = serv_sys_id;
	}

	public String getReceive_time() {
		return receive_time;
	}

	public void setReceive_time(String receive_time) {
		this.receive_time = receive_time;
	}

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}
}

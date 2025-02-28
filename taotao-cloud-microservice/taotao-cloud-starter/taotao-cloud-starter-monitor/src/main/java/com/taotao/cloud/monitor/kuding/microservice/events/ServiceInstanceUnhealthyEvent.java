package com.taotao.cloud.monitor.kuding.microservice.events;

import java.util.List;

import com.taotao.cloud.monitor.kuding.pojos.servicemonitor.ServiceHealth;
import org.springframework.context.ApplicationEvent;


public class ServiceInstanceUnhealthyEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private final String serviceName;

	private final List<ServiceHealth> serviceHealths;

	public ServiceInstanceUnhealthyEvent(Object source, String serviceName, List<ServiceHealth> serviceHealths) {
		super(source);
		this.serviceName = serviceName;
		this.serviceHealths = serviceHealths;
	}

	public String getServiceName() {
		return serviceName;
	}

	public List<ServiceHealth> getServiceHealths() {
		return serviceHealths;
	}

}

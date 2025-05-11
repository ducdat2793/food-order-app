package com.example.order_service.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import com.food.grpc.menu.MenuRequest;
import com.food.grpc.menu.MenuResponse;
import com.food.grpc.menu.MenuServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Service
public class MenuGrpcClient {

	private final MenuServiceGrpc.MenuServiceBlockingStub stub;

	@Autowired
	public MenuGrpcClient(DiscoveryClient discoveryClient) {
		MenuServiceGrpc.MenuServiceBlockingStub tempStub = null;

		List<ServiceInstance> instances = discoveryClient.getInstances("menu-service");
		// System.out.println("====> "+ instances);
		if (instances != null && !instances.isEmpty()) {
			ServiceInstance instance = instances.get(0);
			String host = instance.getHost();
			// int port = instance.getPort();
			
			Map<String, String> metadata = instance.getMetadata();
			int grpcPort = Integer.parseInt(metadata.get("grpc.port"));

			ManagedChannel channel = ManagedChannelBuilder.forAddress(host, grpcPort).usePlaintext().build();

			tempStub = MenuServiceGrpc.newBlockingStub(channel);
		}

		this.stub = tempStub;
	}

	public MenuResponse getMenuById(long id) {
		if (stub == null) {
			throw new IllegalStateException("gRPC stub is not initialized (menu-service not available)");
		}
		MenuRequest request = MenuRequest.newBuilder().setId(id).build();
		return stub.getMenuItem(request);
	}

}

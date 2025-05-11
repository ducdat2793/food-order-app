package com.example.menu_service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.menu_service.model.Menu;
import com.example.menu_service.repository.MenuRepository;
import com.food.grpc.menu.MenuRequest;
import com.food.grpc.menu.MenuResponse;
import com.food.grpc.menu.MenuServiceGrpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class MenuGrpcService extends MenuServiceGrpc.MenuServiceImplBase {

	@Autowired
	private MenuRepository repo ;

	@Override
	public void getMenuItem(MenuRequest request, StreamObserver<MenuResponse> responseObserver) {
		Optional<Menu> menu = repo.findById(request.getId());
		MenuResponse response = MenuResponse.newBuilder()
				.setId(request.getId())
				.setName(menu.get().getName())
				.setPrice(menu.get().getPrice())
				.build();

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

}

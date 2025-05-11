package com.example.menu_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuRequest {

	private String name;
	private String description;
	private double price;
	private boolean available;
	private String imgUrl;
	private int category;
	
}

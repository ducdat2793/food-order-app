package com.example.menu_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.menu_service.model.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long>{

}

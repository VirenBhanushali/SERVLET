package com.dev.bbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.bss.beans.Admin;
import com.dev.bss.beans.Bus;
import com.dev.bss.sevice.ServiceAdmin;

@RestController
public class AdminController {
	@Autowired
	ServiceAdmin services;
	
	@RequestMapping(value = "/rest/bus/create", method = RequestMethod.POST)
	public Bus createBus(@RequestBody Bus bus) {
		Bus check = services.createBus(bus);
		if(check != null) {
			return check;
		}
		return null;
	}
	
	@RequestMapping(value = "/rest/bus/delete/{id}/{password}", method = RequestMethod.DELETE)
	public Bus deleteBus(@PathVariable("id") int busId,@PathVariable("password") String password) {
		 Bus bus = services.searchBus(busId);
		 Boolean check = services.deletebus(busId, password);
		 if(check) {
		
				return bus;
			}
			return null;
	}
	
	@RequestMapping(value = "/rest/bus/{id}", method = RequestMethod.GET)
	public Bus getBus(@PathVariable("id") int busId) {
		
		Bus bus = services.searchBus(busId);
		if(bus != null) {
			return bus;
		}
		return null;
	}
	
	@RequestMapping(value = "/rest/bus/update", method = RequestMethod.PUT)
	public Bus updateBus(@RequestBody Bus bus) {
		Boolean check = services.updateBus(bus);
		if(check) {
			return bus;
		}
		return null;
	}
	
	@RequestMapping(value = "/rest/admin/login", method = RequestMethod.POST)
	public Admin loginAdmin(@RequestBody Admin admin) {
		Boolean check = services.adminLogin(admin.getAdminId(),admin.getPassword());
		if(check) {
			return admin;
		}
		return null;
	}
	
	
}
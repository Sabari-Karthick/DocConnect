package com.Batman.dto.user;

import lombok.Data;

@Data
public class UpgradeResponse {
	private Integer id;
	private String name;
	private String email;
	private  final String status = "SuccessFully Upgraded";

}

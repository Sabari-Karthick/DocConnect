package com.Batman.error;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error {
	private String msg;
	private LocalDateTime time;
	private String status;

}

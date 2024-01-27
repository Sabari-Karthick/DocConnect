package com.Batman.exceptions;

public class SlotNotAvailableException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public SlotNotAvailableException(String msg)
	{
		super(msg);
	}

}

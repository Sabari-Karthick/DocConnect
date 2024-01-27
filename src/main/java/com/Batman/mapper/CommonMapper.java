package com.Batman.mapper;

/**
 * 
 * 
 * @author karthick
 * @date 17-10-2023
 *
 */
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CommonMapper {
	
	private final ModelMapper mapper;
	
	public <T, S> S convertToEntity(T data, Class<S> type) {
        return mapper.map(data, type);
    }

    public <T, S> S convertToResponse(T data, Class<S> type) {
        return mapper.map(data, type);
    }

   


}

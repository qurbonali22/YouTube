package com.example.YouTube;

import com.example.YouTube.dto.channel.ChannelCreationDTO;
import com.example.YouTube.dto.channel.ChannelUpdateDTO;
import com.example.YouTube.enums.Language;
import com.example.YouTube.service.ChannelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

@SpringBootTest
class YouTubeApplicationTests {

	@Autowired
	ChannelService service;

	@Test
	void contextLoads() {
		ChannelCreationDTO dto = new ChannelCreationDTO();
		dto.setName("code_uz");
		dto.setDescription("for programmer");
		dto.setBannerId("c19f90d9-3264-4b02-ba99-b53333fdfa97");
		dto.setPhotoId("c19f90d9-3264-4b02-ba99-b53333fdfa97");
		//System.out.println(service.create(dto, 2));

	}

	@Test
	void updateChannel() {
		ChannelUpdateDTO dto = new ChannelUpdateDTO();
		dto.setName("code_uz");
		dto.setDescription("for children");

		System.out.println(service.updateChannel("bfec74fe-5fdb-4a0b-83f1-40ccdc6c1d6a", dto, Language.EN));
	}


}

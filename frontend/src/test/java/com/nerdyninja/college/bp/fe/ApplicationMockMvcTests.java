/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nerdyninja.college.bp.fe;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ApplicationMockMvcTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void checkBloodPressureWithDiastolicMissingNameThenFailure() throws Exception {
		MockHttpServletRequestBuilder createPerson = post("/")
				.param("systolic", "90").param("agerange","0").param("email","testmvc@local.ie");

		mockMvc.perform(createPerson)
			.andExpect(model().hasErrors());
	}

	@Test
	void checkBloodPressureWithSystolicMissingNameThenFailure() throws Exception {
		MockHttpServletRequestBuilder createPerson = post("/")
				.param("diastolic", "50").param("agerange","0").param("email","testmvc@local.ie");

		mockMvc.perform(createPerson)
			.andExpect(model().hasErrors());
	}

	@Test
	void checkBloodPressure() throws Exception {
		MockHttpServletRequestBuilder createPerson = post("/")
				.param("systolic", "90")
				.param("diastolic", "50")
				.param("agerange","0").param("email","testmvc@local.ie");

		mockMvc.perform(createPerson)
				.andExpect(model().hasNoErrors());
	}
}

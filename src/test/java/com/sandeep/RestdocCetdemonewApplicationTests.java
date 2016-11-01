package com.sandeep;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestdocCetdemonewApplicationTests {

	@Rule
    public final JUnitRestDocumentation restDocumentation = 
    new JUnitRestDocumentation("target/generated-snippets");

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
	            .apply(documentationConfiguration(this.restDocumentation)
	            		.uris().withScheme("https").
	            		withHost("rest-docssandeep.apps-np.homedepot.com").
	            		withPort(443))
	            .build();
	}
	
	
	@Test
	public void contextLoads() throws Exception {
		this.mockMvc.perform(get("/strList")) 
		.andExpect(status().isOk()).andDo(document("store-get", 
				preprocessResponse(prettyPrint())));
	}

}

package com.sandeep;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
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
		this.mockMvc.perform(get("/str?storeNbr=1")) 
		.andExpect(status().isOk()).andDo(document("store-get" ,
				preprocessResponse(prettyPrint()),
				responseFields( 
						fieldWithPath("storeNbr").description("The Store Number"),
						fieldWithPath("strName").description("The Store Name"),
						fieldWithPath("strAddress").description("The Store Address"))
				));
		
	}

	@Test
	public void contextLoads_2() throws Exception {
		this.mockMvc.perform(get("/strList?page=0&size=100")) 
		.andExpect(status().isOk()).andDo(document("store-get-page" ,
				preprocessResponse(prettyPrint()),
				requestParameters(parameterWithName("page").description("The page to retrieve"), 
						parameterWithName("size").description("Entries per page") 
						)
				));
		
	}
}

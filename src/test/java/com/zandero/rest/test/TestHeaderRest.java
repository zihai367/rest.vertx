package com.zandero.rest.test;

import com.zandero.rest.annotation.RequestReader;
import com.zandero.rest.reader.DummyBodyReader;
import com.zandero.rest.reader.NpeReader;
import com.zandero.rest.reader.TokenReader;
import com.zandero.rest.test.data.Token;
import com.zandero.rest.test.json.Dummy;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 *
 */
@Path("header")
public class TestHeaderRest {

	@GET
	@Path("/dummy")
	public String getExtendedDummyFromHeader(@HeaderParam("dummy") Dummy dummy) {

		return dummy.name + "=" + dummy.value;
	}

	// utilize different readers for different params
	@POST
	@Path("/dummy")
	@RequestReader(DummyBodyReader.class)
	public String getExtendedDummyFromHeader(Dummy dummy,
	                                         @HeaderParam("token") @RequestReader(TokenReader.class) Token token,
											 @HeaderParam("other") String other) {

		return dummy.name + "=" + dummy.value + ", " + token.token + " " + other;
	}

	@GET
	@Path("/npe")
	public String npeException(@HeaderParam("dummy") @RequestReader(NpeReader.class) String dummy) {

		return dummy;
	}
}

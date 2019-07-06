package com.zandero.rest.test.data;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;

public class MyBean {

    @HeaderParam("header")
    private String header;

    @PathParam("path")
    private String path;

    @QueryParam("query")
    private String query;

 /*   @Context
    private Request request;*/

    @Override
    public String toString() {
        return "Header: " + header + ", Path: " + path + ", Query: " + query;
    }
}

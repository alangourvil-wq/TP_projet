package com.example.service_entreprise;

import java.io.IOException;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CorsResponseFilter
        implements ContainerRequestFilter, ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if ("OPTIONS".equalsIgnoreCase(requestContext.getMethod())) {
            Response.ResponseBuilder response = Response.ok();
            ajouterEntetesCors(response.getHeaders(), requestContext);
            requestContext.abortWith(response.build());
        }
    }

    @Override
    public void filter(
            ContainerRequestContext requestContext,
            ContainerResponseContext responseContext
    ) throws IOException {
        ajouterEntetesCors(responseContext.getHeaders(), requestContext);
    }

    private void ajouterEntetesCors(
            MultivaluedMap<String, Object> headers,
            ContainerRequestContext requestContext
    ) {
        headers.putSingle("Access-Control-Allow-Origin", "*");
        headers.putSingle("Access-Control-Allow-Methods", "GET, POST, OPTIONS");

        String requestedHeaders = requestContext.getHeaderString(
                "Access-Control-Request-Headers"
        );
        headers.putSingle(
                "Access-Control-Allow-Headers",
                requestedHeaders == null ? "Content-Type" : requestedHeaders
        );
        headers.putSingle("Access-Control-Max-Age", "3600");
    }
}

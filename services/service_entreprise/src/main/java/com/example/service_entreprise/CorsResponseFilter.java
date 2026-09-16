package com.example.service_entreprise;

import java.io.IOException;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;

public class CorsResponseFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
        if ("GET".equals(request.getMethod())) {
            response.getHeaders().putSingle("Access-Control-Allow-Origin", "*");
        }
    }
}

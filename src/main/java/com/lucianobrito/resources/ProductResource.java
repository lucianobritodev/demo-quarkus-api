package com.lucianobrito.resources;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lucianobrito.entities.Product;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {
	
    @GET
    public List<Product> findAll() {
        return Product.listAll();
    }
    
    @GET
    @Path("/{id}")
    public Product findById(@PathParam("id") Long id) {
        return Product.findById(id);
    }
    
    @POST
    @Transactional
    public Response save(Product product) {
    	product.persist();
        return Response.status(Response.Status.CREATED)
        		.entity(product)
        		.build();
    }
    
    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
    	Product.deleteById(id);
    	return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Product product) {
    	Product productUpdate = Product.findById(id);
    	productUpdate.setName(product.getName());
    	return Response.ok(productUpdate)
    			.build();
    }
}
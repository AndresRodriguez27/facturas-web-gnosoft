package com.gnosoft.facturacion;

import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Fachada.Fachada;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import Vo.FacturaVo;

@Path("factura")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class FacturacionResource {

    private final Fachada fachada;

    public FacturacionResource() {
        fachada = new Fachada();
    }

    @GET
    public List<FacturaVo> listarFacturas() {
        List<FacturaVo> facturas = fachada.ListarFacturas();
        return facturas;
    }

    @GET
    @Path("/{id}")
    public Response obtenerFactura(@PathParam("id") int id) {
        FacturaVo factura = fachada.obtenerFactura(id);
        if (factura == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("la factura #" + id + " no existe").build();
        }
        return Response.ok(factura).build();
    }

    @POST
    public Response crearFactura(FacturaVo factura) {
        FacturaVo facturaR = fachada.crearFactura(factura);
        if (facturaR == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("No fue posible crear la factura").build();
        }
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminarFactura(@PathParam("id") int id) {
        boolean eliminado = fachada.eliminarFactura(id);
        if (!eliminado) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Factura #" + id + " no encontrada").build();
        }
        return Response.ok().build();
    }

    @PUT
    public Response actualizarFactura(FacturaVo factura) {
        boolean actualizado = fachada.actualizarFactura(factura);
        if(!actualizado) {
           return Response.ok("No fue posible actualizar la factura").build();
        }
        return Response.ok().build();
    }

}
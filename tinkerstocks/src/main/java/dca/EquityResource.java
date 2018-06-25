package dca;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/equity")
@Produces(MediaType.APPLICATION_JSON)
public class EquityResource {
    StockDataHandler stockDataHandler;
    Simulation simulation;

    public EquityResource(StockDataHandler handler) {
        this.stockDataHandler = handler;

    }

    @GET
    public Response get(){
        return Response.ok().entity("Welcome to Investment Strategies & Equity Yield Figures").build();
    }

    @GET
    @Path("/yield/{scrip}")
    public Response getYieldForScrip( @PathParam("scrip") String scrip){

        try {
            List<Stock> stocks = stockDataHandler.loadAll(scrip);
            List<Stock> monthly = stockDataHandler.loadMonthly( scrip, stocks);
            Simulation simulation = new Simulation();
            List<Yield> yield = simulation.runningYield( monthly, 2004, 2012);
            return Response.ok()
                    .entity( yield)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.ok()
                    .entity(String.format("Something went wrong : %s", e.getMessage()))
                    .build();
        }
    }

    @GET
    @Path("/prices/{scrip}")
    public Response getPricesForScrip( @PathParam("scrip") String scrip){

        try {
            List<Stock> stocks = stockDataHandler.loadAll(scrip);
            return Response.ok()
                    .entity( stocks)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.ok()
                    .entity( String.format("Something went wrong fetching prices : %s", e.getMessage()))
                    .build();
        }
    }
    @GET
    @Path("/prices/monthly/{scrip}")
    public Response getMonthlyPricesForScrip( @PathParam("scrip") String scrip){

        try {
            List<Stock> stocks = stockDataHandler.loadAll(scrip);
            List<Stock> monthly = stockDataHandler.loadMonthly( scrip, stocks);
            return Response.ok()
                    .entity( monthly)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.ok()
                    .entity( String.format("Something went wrong fetching prices : %s", e.getMessage()))
                    .build();
        }
    }

    @GET
    @Path("/scrips")
    public Response getScrips( ){

        return Response.ok().entity(StockDataHandler.stockNames).build();
    }
}

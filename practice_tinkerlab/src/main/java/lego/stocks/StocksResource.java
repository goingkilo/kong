package lego.stocks;

import io.dropwizard.jersey.params.NonEmptyStringParam;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

@Path("/stock")
@Produces(MediaType.APPLICATION_JSON)
public class StocksResource {

	public StocksResource() { }

	@GET
	@Path("/{ticker}")
	@Consumes("application/x-www-form-urlencoded")
	public Stock tick ( @PathParam("ticker") NonEmptyStringParam ticker ) throws IOException {
		logTimestamp();
		return YahooFinance.get(ticker.get().get(),true);

	}

	@GET
	@Path("/local/{ticker}")
	@Produces(MediaType.TEXT_PLAIN)
	public String localData ( @PathParam("ticker") NonEmptyStringParam ticker ) throws IOException {
		return loadCSV(ticker.get().get());
	}

	private String[] qc(String fileContents) {
		String[] lines = fileContents.split("\n");
		int c = lines[0].split(",").length;
		return lines;
	}

	private String loadCSV(String scrip) throws IOException {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream( "data/"+ scrip + ".csv");
		byte[] b = new byte[in.available()];
		in.read(b);
		String s = new String(b);
		return s;
	}

	private void logTimestamp(){
		long timeStamp = 1438194600000l;
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timeStamp);

		int y = calendar.get(Calendar.YEAR);
		int m = calendar.get(Calendar.MONTH);
		int d = calendar.get(Calendar.DAY_OF_MONTH);

		System.out.println( m +"/" + d +"/" + y );
	}


}

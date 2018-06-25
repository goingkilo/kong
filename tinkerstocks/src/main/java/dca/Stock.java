package dca;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Data
public class Stock {

    public static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private Date date;
    private float open;
    private float high;
    private float low;
    private float close;
    private double volume;
    private float adjClose;

    public Stock( String[] row) throws ParseException {
        date = FORMAT.parse(row[0]);
        open = Float.valueOf(row[1]);
        high  = Float.valueOf(row[2]);
        low = Float.valueOf(row[3]);
        close = Float.valueOf(row[4]);
        volume  = Double.valueOf(row[5]);
        adjClose = Float.valueOf(row[6]);
    }


}

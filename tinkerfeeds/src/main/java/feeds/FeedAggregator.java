package feeds;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FeedAggregator {

    /*
     * This could be a good generics exercize
     */
    public static SyndFeed aggregate() {
        try {
            String outputType = "rss_2.0";

            SyndFeed feed = new SyndFeedImpl();
            feed.setFeedType(outputType);

            feed.setTitle("Aggregated Feed");
            feed.setDescription("Anonymous Aggregated Feed");
            feed.setAuthor("anonymous");
            feed.setLink("http://www.anonymous.com");

            List entries = new ArrayList();
            feed.setEntries(entries);

            String[] args = {"http://www.stevepavlina.com/blog/feed/", "http://www.dadhacker.com/blog/?feed=rss2"};

            for (int i = 1; i < args.length; i++) {
                URL inputUrl = new URL(args[i]);

                SyndFeedInput input = new SyndFeedInput();
                SyndFeed inFeed = input.build(new XmlReader(inputUrl));

                entries.addAll(inFeed.getEntries());

            }

            return feed;
//				

        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        return null;
//			System.out.println();
//			System.out.println("feeds.FeedAggregator aggregates different feeds into a single one.");
//			System.out.println("The first parameter must be the feed type for the aggregated feed.");
//			System.out.println(" [valid values are: rss_0.9, rss_0.91U, rss_0.91N, rss_0.92, rss_0.93, ]");
//			System.out.println(" [                  rss_0.94, rss_1.0, rss_2.0 & atom_0.3  ]");
//			System.out.println("The second to last parameters are the URLs of feeds to aggregate.");
//			System.out.println();
    }

}

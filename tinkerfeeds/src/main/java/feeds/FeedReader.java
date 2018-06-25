package feeds;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class FeedReader {

	public static void get() {

		URL feedUrl;
		try {
			feedUrl = new URL("http://www.stevepavlina.com/blog/feed/");
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(feedUrl));
			List<SyndEntryImpl> entries = feed.getEntries();
			
			for( SyndEntryImpl a : entries ) {
				System.out.println(  a.getAuthor()   );
				System.out.println(  a.getTitle()     );
				//System.out.println(  a.getContents().get(0)      );
			}
		
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (FeedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
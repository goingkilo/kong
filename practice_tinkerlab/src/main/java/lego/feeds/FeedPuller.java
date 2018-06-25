package lego.feeds;

import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kraghunathan on 8/8/16.
 */
public class FeedPuller {

    public List<Article> pullAll() {
        List<Article> ret = new ArrayList<Article>();
        for( String url : Feeds.URLs) {
            ret.addAll( pull(url));
        }
        return ret;
    }

    public List<Article> pull(int i) {
        return pull( Feeds.URLs[i]);
    }

    public List<Article> pull(String urlStr) {
        URL url = null;
        List<Article> list = new ArrayList<Article>();
        try {
            url = new URL( urlStr);

            HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(httpcon));
            List entries = feed.getEntries();
            Iterator itEntries = entries.iterator();

            while (itEntries.hasNext()) {
                try {
                    Article x = new Article();
                    SyndEntry entry = (SyndEntry) itEntries.next();
                    x.setTitle(entry.getTitle());
                    x.setLink(entry.getLink());
                    x.setAuthor(entry.getAuthor());
                    x.setPublishedDate(entry.getPublishedDate());
                    x.setDescription(entry.getDescription().getValue());

                    List<SyndContentImpl> contents = (List<SyndContentImpl>) entry.getContents();
                    StringBuilder contentBuf = new StringBuilder();
                    for (SyndContentImpl content : contents) {
                        contentBuf.append(content.getValue());
                        contentBuf.append("<p>");
                    }
                    x.setContents(contentBuf.toString());
                    list.add(x);
                }
                catch(Exception e) {
                    //continue;
                    // need logger
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FeedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println( "pulled from : " + url);
        return list;
    }
}

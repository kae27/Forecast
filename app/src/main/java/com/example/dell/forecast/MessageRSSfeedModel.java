package com.example.dell.forecast;



import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dell on 5/9/2017.
 * ส่วนของการเก็บข้อมูลที่ทำการ Feed มาแล้ว
 */

public class MessageRSSfeedModel {

        static SimpleDateFormat FORMATTER =new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
        private String title;
        private URL link;
        private String description;
        private Date date;



        public MessageRSSfeedModel copy()
        {
            MessageRSSfeedModel copy = new MessageRSSfeedModel();
            copy.title = title;
            copy.link = link;
            copy.description = description;
            copy.date = date;
            return copy;
        }

        public String getTitle()
        {
            return title;
        }

        public void setTitle(String description_title) {
            this.title = title.trim();
        }

        public URL getLink()
        {
                return link;
        }



        public void setLink(String url)
        {
            try
            {
                this.link = new URL(url);
            }
            catch (MalformedURLException e)
            {
                throw new RuntimeException(e);
            }
       }

        public String getDescription()
        {
            return description;
        }

        public void setDescription(String description) {
            this.description = description.trim();
        }

        public String getDate() {
            return  FORMATTER.format(this.date);
        }

        public void setDate(String date)
        {
            while (!date.endsWith("00"))
            {
                date += "0";
            }
            try
            {
                this.date = FORMATTER.parse(date.trim());
            } catch (ParseException e)
            {
                throw new RuntimeException(e);
            }
        }


}

package MainPackage;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		  long diffInMillisec=0;
          long diffInDays=0;
         Calendar firstDate =null;
         Calendar secondDate =null;
          try{
                   // Create two calendars instances
                   firstDate = Calendar.getInstance();
                   secondDate = Calendar.getInstance();
                
                  //Set the dates
                  firstDate.set(2009, 8, 1);       
                  secondDate.set(2009, 7, 31);

                  // Get the difference between two dates in milliseconds    
                 diffInMillisec = firstDate.getTimeInMillis() - secondDate.getTimeInMillis();     

                 // Get difference between two dates in days
                 diffInDays = diffInMillisec / (24 * 60 * 60 );       
                 System.out.println(diffInDays);
              }catch(Exception ex)
              {
                          ex.printStackTrace();
               }             
     

		
		
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-mm-dd");
		String inputString1 = "2015-01-24";
		String inputString2 = "2015-02-04";

		try {
		    Date date1 = myFormat.parse(inputString1);
		    Date date2 = myFormat.parse(inputString2);
		    
		    GregorianCalendar c1 = new GregorianCalendar();
			GregorianCalendar c2 = new GregorianCalendar();

			c1.setTime(date1);
			c2.setTime(date2);

			
			int d1 = c1.get(Calendar.DAY_OF_YEAR);
			int d2 = c2.get(Calendar.DAY_OF_YEAR);

			
			int diffs = d2 - d1;
		    
		    System.out.println(diffs);
		    long diff = date2.getTime() - date1.getTime();
		    System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
		} catch (ParseException e) {
		    e.printStackTrace();
		}
	}

}

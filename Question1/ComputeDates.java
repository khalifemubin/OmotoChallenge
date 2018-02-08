import java.util.Scanner; 
import java.text.SimpleDateFormat;  
import java.util.Date; 
import java.text.ParseException;
import java.time.LocalDate;
import java.lang.Exception;
import java.util.Calendar;
import java.time.YearMonth;


class ComputeDates {
	
	public static void computeDates(Date fromdate, Date todate){
		Calendar cal = Calendar.getInstance();
		YearMonth yearMonth = null;
		int year = 0, month = 0;
		
		cal.setTime(fromdate);
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);		
		yearMonth = YearMonth.of( year, (month==0)? ++month : month );
		LocalDate firstOfMonth = yearMonth.atDay( 1 );
		System.out.println("fromdate :"+java.sql.Date.valueOf(firstOfMonth));
		
		
		cal.setTime(todate);		
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);		
		yearMonth = YearMonth.of( year, (month==0)? ++month : month );
		LocalDate lastOfMonth = yearMonth.atEndOfMonth();
		
		Date currDate = new Date();
		cal.setTime(currDate);
		
		if(java.sql.Date.valueOf(lastOfMonth).compareTo(currDate) < 0){
			System.out.println("todate :"+java.sql.Date.valueOf(lastOfMonth));
		}else{
			cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -1);
			Date resultDate = cal.getTime();
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH);		
			yearMonth = YearMonth.of( year, (month==0)? ++month : month );
			lastOfMonth = yearMonth.atEndOfMonth();
			System.out.println("todate :"+java.sql.Date.valueOf(lastOfMonth));
		}
	}
	
	public static void main(String args[]) {
		System.out.println("\n\n\nThis program takes 'From Date' and 'To Date' and returns the first day of the 'From Date\'s' month and last day of 'To Date\'s' month");
		
		// To take the fromdate input
		Scanner scanner = new Scanner(System.in);		
		boolean validDate = false;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date fromdate=null,todate=null;
		
		do{
			System.out.println("\n\n\nPlease enter the 'From Date' in yyyy-MM-dd format");
			String strFromDate = scanner.next();
			
			try {							
				String arrFromDate[]= strFromDate.split("-");
				int y = Integer.parseInt( arrFromDate[0] );
				int m = Integer.parseInt( arrFromDate[1]);
				int d = Integer.parseInt( arrFromDate[2]);
				LocalDate ldFrom = LocalDate.of( y , m , d );
				fromdate = java.sql.Date.valueOf(ldFrom);
				validDate = true;				
			} catch (Exception e) {
				System.out.println("Invalid Date format");
				//e.printStackTrace();
				validDate = false;
			}
			
		}while (!validDate);
		
		do{
			
			try {
				
				boolean correctOrder = false;
				
				do{
					System.out.println("\n\n\nPlease enter the 'To Date' in yyyy-MM-dd format");
					String strToDate = scanner.next();
					String arrToDate[]= strToDate.split("-");
					int y = Integer.parseInt( arrToDate[0] );
					int m = Integer.parseInt( arrToDate[1]);
					int d = Integer.parseInt( arrToDate[2]);
					LocalDate ldTo = LocalDate.of( y , m , d );
					todate = java.sql.Date.valueOf(ldTo);
					
					if(fromdate.compareTo(todate) < 0){
						correctOrder = true;
					}else{					
						System.out.println("\n\n\n\'From Date' cannot be greater than 'To Date'");
					}
				}while(!correctOrder);
				
				
				validDate = true;
				
			//} catch (ParseException e) {
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Invalid Date format");
				//e.printStackTrace();
				validDate = false;
			}
			
		}while (!validDate);
		
		
		//System.out.printf("Dates entered are %1$td %1$tm %1$tY,%2$td %2$tm %2$tY",fromdate,todate);
		scanner.close();
		computeDates(fromdate,todate);
	}

}
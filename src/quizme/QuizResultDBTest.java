package quizme;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class QuizResultDBTest {
	static DBConnection db;
	static QuizResultsDB resultDB;
	
	@BeforeClass
	public static void oneTimeSetUp() {
		db = new DBConnection();
		resultDB = new QuizResultsDB(db);
		resultDB.removeResult(1); /* keep replacing result 1 in basic test */
	}
	
	@AfterClass
	public static void oneTimeTearDown() {
		db.closeDatabase();
	}
	
	@Test
	public void basictest() {
		Date today = new Date();
		int resultid = resultDB.addResult(300, 20, 90.5, 60, today);
		assertEquals(resultid, 1);
		
		int quizid = resultDB.getQuizID(resultid);
		assertEquals(quizid, 300);
		
		int userid = resultDB.getUserID(resultid);
		assertEquals(userid, 20);
		
		double score = resultDB.getScore(resultid);
		assertEquals(score, 90.5, 0.0001);
		
		long time = resultDB.getTime(resultid);
		assertEquals(time, 60);
		
		DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		Date date = resultDB.getDate(resultid);
		String dateString = df.format(date);
		String todayString = df.format(today);
		assertTrue(dateString.equals(todayString));
	}

}

/**
*
* Opprydder.java  - "Programmering i Java", 4.utgave - 2009-07-01
* Metoder for å rydde opp etter databasebruk
*/
//package mittBibliotek;

import java.sql.*;

public class Opprydder {
	
	Opprydder(Connection con){
		lukkForbindelse(con);
	}
	
	public static void skrivMelding(Exception e, String melding) {
		System.err.println("*** Feil oppstått: " + melding + ". ***");
		e.printStackTrace(System.err);
	}
		
	 public static void lukkResSet(ResultSet res) {
	   try {
	     if (res != null) {
	       res.close();
	     }
	   } catch (SQLException e) {
	     skrivMelding(e, "lukkResSet()");
	   }
	 }
	
	 public static void lukkSetning(Statement stm) {
	   try {
	     if (stm != null) {
	       stm.close();
	     }
	   } catch (SQLException e) {
	     skrivMelding(e, "lukkSetning()");
	   }
	 }
	
	 public static void lukkForbindelse(Connection forbindelse) {
	   try {
	     if (forbindelse != null) {
	       forbindelse.close();
	     }
	   } catch (SQLException e) {
	     skrivMelding(e, "lukkForbindelse()");
	   }
	 }
	
	 public static void rullTilbake(Connection forbindelse) {
	   try {
	     if (forbindelse != null && !forbindelse.getAutoCommit()) {
	       forbindelse.rollback();
	     }
	   } catch (SQLException e) {
	     skrivMelding(e, "rollback()");
	   }
	 }
	
	 public static void autoCommit(Connection forbindelse, boolean on_off) {
		 
		 try {
			 if(forbindelse.getAutoCommit() == on_off) { //returnerer dersom autoCommit allerede er i stadiet det skal endres til, uten å gjøre noe
				 return;
			 }
			 if (forbindelse != null && on_off) {
				  forbindelse.setAutoCommit(true);
		     }else if(forbindelse != null && !on_off) {
		    	 forbindelse.setAutoCommit(false);
		    	 forbindelse.setSavepoint();
		     }
		   }catch (SQLException e) {
			   skrivMelding(e, "autoCommit()");
		   }
		}
	 
	public static void lukkRessurser(Object obj1, Object obj2, Object obj3, Object obj4, Object obj5) {
		Object[] ressurser = {obj1, obj2, obj3, obj4, obj5};
		for(int i = 0; i < ressurser.length; i++) {
			lukkRessurs(ressurser[i]);
		}
	}
	public static void lukkRessurser(Object obj1, Object obj2, Object obj3, Object obj4) {
		Object[] ressurser = {obj1, obj2, obj3, obj4};
		for(int i = 0; i < ressurser.length; i++) {
			lukkRessurs(ressurser[i]);
		}
	}
	public static void lukkRessurser(Object obj1, Object obj2, Object obj3) {
		Object[] ressurser = {obj1, obj2, obj3};
		for(int i = 0; i < ressurser.length; i++) {
			lukkRessurs(ressurser[i]);
		}
	}
	public static void lukkRessurser(Object obj1, Object obj2) {
		Object[] ressurser = {obj1, obj2};
		for(int i = 0; i < ressurser.length; i++) {
			lukkRessurs(ressurser[i]);
		}
	}
	public static void lukkRessurs(Object obj) {
		if(obj != null && obj instanceof ResultSet) {
			ResultSet res = (ResultSet)obj;
			lukkResSet(res);
		}else if(obj != null && obj instanceof Statement) {
			Statement stm = (Statement)obj;
			lukkSetning(stm);
		}else if(obj != null && obj instanceof PreparedStatement) {
			PreparedStatement pstm = (PreparedStatement)obj;
			lukkSetning(pstm);
		}else if(obj != null && obj instanceof Connection) {
			Connection con = (Connection)obj;
			lukkForbindelse(con);
		}
	}
}
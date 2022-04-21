import java.sql.*;

public class Database{
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet result;
	
	public Database(String database, String username, String password){
		try{
			this.connection = DriverManager.getConnection("jdbc:mysql://mysql.stud.idi.ntnu.no:3306/" + database, username, password);
		}catch(SQLException e) {
			System.out.println("	Konstruktør: " + e);
		}
	}
	
	public void lukkForbindelse() {
		try {
			connection.close();
			Opprydder.lukkRessurser(result,  preparedStatement, connection);
		}catch(SQLException e){
			Opprydder.skrivMelding(e,  "lukkForbindelse(");
		}
	}
	
	public boolean regNyBok(Bok nyBok) {
		if(bokFins(nyBok.getIsbn())) {
			return false;
		}
		Opprydder.autoCommit(connection,  false);
		try {
			preparedStatement = connection.prepareStatement("insert into boktittel(isbn, forfatter, tittel) values('"+nyBok.getIsbn()+"', '"+nyBok.getForfatter()+"', '"+nyBok.getTittel()+"')");
			preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement("insert into eksemplar(isbn, eks_nr) values ('"+nyBok.getIsbn()+"', 1)");
			preparedStatement.executeUpdate();
			connection.commit();
		}catch(SQLException e) {
			Opprydder.rullTilbake(connection);
			Opprydder.skrivMelding(e,  "regNyBok()");
		}finally{
			Opprydder.autoCommit(connection, true);
			Opprydder.lukkRessurs(preparedStatement);
		}
		return true;
	}
	
	public int regNyttEksemplar(String isbn) {
		int eksemplar = 0;
		Opprydder.autoCommit(connection,  false);
		try {
			preparedStatement = connection.prepareStatement("SELECT MAX(eks_nr) AS tidligere_eksemplar FROM eksemplar WHERE isbn = ?");
			preparedStatement.setString(1, isbn);
			
			result = preparedStatement.executeQuery();
			if(result.next()) {
				int tidligere_eksemplar = result.getInt("tidligere_eksemplar");
				if(tidligere_eksemplar == 0) {
					return 0;
				}else {
					eksemplar = tidligere_eksemplar + 1;
					preparedStatement.executeUpdate("insert into eksemplar(isbn, eks_nr) values ('"+isbn+"'," + eksemplar + ")");
				}
			}
			connection.commit();
		}catch(Exception e) {
			Opprydder.rullTilbake(connection);
			Opprydder.skrivMelding(e,  "regNyttEksemplar()");
		}finally {
			Opprydder.autoCommit(connection, true);
			Opprydder.lukkRessurser(preparedStatement,  result);
		}
		return eksemplar;
	}
	
	public boolean lånUtEksemplar(String isbn, String navn, int eks_nr) {
		
		try {
	    	preparedStatement = connection.prepareStatement("update eksemplar set laant_av = '" + navn + "' where isbn = '" + isbn + "' and eks_nr = " + eks_nr);
		    if(0 == preparedStatement.executeUpdate()) {
		    	return false;
		    }
		    return true;
	    	
	    }catch (SQLException e) {
	    	Opprydder.rullTilbake(connection);
	    	Opprydder.skrivMelding(e,  "lånUtEksemplar()");
	    }finally{
			Opprydder.lukkRessurs(preparedStatement);
		}
		return false;
	}
	
	public boolean slettBok(String isbn) {
		
	    Opprydder.autoCommit(connection,  false);
		try {
	    	preparedStatement = connection.prepareStatement("delete from eksemplar where isbn = ?");
//	    	preparedStatement.setString(1,  "eksemplar");
	    	preparedStatement.setString(1,  isbn);
	    	preparedStatement.executeUpdate();
	    	
	    	preparedStatement = connection.prepareStatement("delete from boktittel where isbn = ?");
//	    	preparedStatement.setString(1,  "boktittel");
	    	preparedStatement.setString(1,  isbn);
	    	preparedStatement.executeUpdate();
	    	connection.commit();
	    	return true;
	    }catch (SQLException e) {
	    	Opprydder.rullTilbake(connection);
	    	Opprydder.skrivMelding(e,  "slettBok()");
	    }finally{
	    	Opprydder.autoCommit(connection, true);
			Opprydder.lukkRessurser(result, preparedStatement);
		}
	    return false;
	}
	
	public boolean bokFins(String isbn) {
		try {
			preparedStatement = connection.prepareStatement("SELECT isbn FROM boktittel");
			result = preparedStatement.executeQuery();
			while(result.next()) {
				if(isbn.contentEquals(result.getString("isbn"))){
					return true;
				}
			}
		}catch(SQLException e) {
			Opprydder.skrivMelding(e,  "bokFins(): ");
		}finally {
			Opprydder.lukkRessurser(result, preparedStatement);
		}
		return false;
	}
	
	
	public static void main(String[] args){
		System.out.println("Antall tester: 5");
		Database test = new Database("sivertut", "sivertut", "BFy970Wo");
		Bok nyBok = new Bok("1-12-12345-1", "Test utg. 1", "Sivert Utne");
		
		if(	!test.bokFins(nyBok.getIsbn()) &&
			test.bokFins("0-07-241163-5")){
			System.out.println("1 - bokFins(): OK");
		}else {System.out.println("1 - bokFins(): IKKE OK");}
		
		if(	test.regNyBok(nyBok) &&
			!test.regNyBok(nyBok)) {
			System.out.println("2 - regNyBok(): OK");
		}else {System.out.println("2 - regNyBok(): IKKE OK");}
		
		if( test.regNyttEksemplar(nyBok.getIsbn()) == 2 &&
			test.regNyttEksemplar(nyBok.getIsbn()) == 3 &&
			test.regNyttEksemplar("1337-420") == 0 &&
			test.regNyttEksemplar(nyBok.getIsbn()) == 4){
			System.out.println("3 - regNyttEksemplar(): OK");
		}else {System.out.println("3 - regNyttEksemplar(): IKKE OK");}
		
		if(	test.lånUtEksemplar(nyBok.getIsbn(), "Sebastian", 2) &&
			test.lånUtEksemplar(nyBok.getIsbn(), "Sivert", 3) &&
			!test.lånUtEksemplar("blablabla", "Sebbi", 1) &&
			!test.lånUtEksemplar(nyBok.getIsbn(), "Sivvi", 7)) {
			System.out.println("4 - lånUtEksemplar(): OK");
		}else {System.out.println("4 - lånUtEksemplar(): IKKE OK");}
		
		if(test.slettBok(nyBok.getIsbn())){
			System.out.println("5 - slettBok(): OK");
		}else {System.out.println("5 - slettBok(): IKKE OK");}
		
		test.lukkForbindelse();
	}
}

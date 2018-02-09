import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class HangmanModel {
	String odabraniFilm;
	List<String> records;
	char code[] = new char[1024];
	String codeMovie;
	int mistakes = 0;
	
	public void readFile()
	{
	  records = new ArrayList<String>();
	  try {
	    BufferedReader reader = new BufferedReader(new FileReader("movies.txt"));
	    String line;
	    
	    while ((line = reader.readLine()) != null)
	    {
	      records.add(line);
	    }
	    reader.close();

	    /*System.out.println("Lista fimova:");
		    for(int i = 0; i < records.size(); i++)
		    	System.out.println(records.get(i));*/
	  }catch (Exception e) {
	    System.err.format("Exception occurred trying to read '%s'.", "movies.txt");
	    e.printStackTrace();
	  }
	}
	
	void setOdabrani() {
		Random random = new Random();
		odabraniFilm = records.get(random.nextInt(records.size()));
	}

	String getOdabrani() {
		return odabraniFilm;
	}
	
	void setCode() { // postavlja zvjezdice sa slova, za sve ostalo ispise original
		for (int i = 0; i < (int)odabraniFilm.length(); i++) {
			if (!Character.isLetter(odabraniFilm.charAt(i))) 
				code[i] = odabraniFilm.charAt(i);
			else
				code[i] = '*';
		}
		codeMovie = new String(code, 0, (int)odabraniFilm.length());
	}
	
	public String getCode(){
		return codeMovie;
	}
}
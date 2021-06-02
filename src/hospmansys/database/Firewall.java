package hospmansys.database;

import java.util.ArrayList;

public class Firewall {

	ArrayList<String> badInput = new ArrayList<String>();
	
	public boolean scanData(String data)
	{
		badInput.add(";");
		
		for (int x = 0; x < badInput.size(); x++)
		{
			if (data.contains(badInput.get(x)))
			{
				System.out.println("Data is bad");
				return false;
			}
		}
		
		System.out.println("Data is clean");
		return true;
	}
	
}

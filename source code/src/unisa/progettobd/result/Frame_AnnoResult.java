package unisa.progettobd.result;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import unisa.progettobd.service.DatabaseManager;

public class Frame_AnnoResult extends JFrame{

	private static final long serialVersionUID = 1L;

	public Frame_AnnoResult(String tipo, String anno) {
		super();

		String[] colNames = {
				"Anno",
				"Codice Università",
				"Ateneo",
				"Numero studenti"
		};

		DefaultTableModel dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(colNames);

		JTable table = new JTable();
		table.setSize(this.getWidth(), this.getHeight());
		table.setModel(dtm);

		JScrollPane scrollText = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(scrollText);



		Document docQuery = new Document().append("ANNO", anno);

		MongoCollection<Document> coll = null; 

		if(tipo.equals("iscritti")) {

			dtm.addRow(new Object[] {"ISCRITTI:", "--", "--", "--", "--"});
			coll = DatabaseManager.getIscritti();
		}else if(tipo.equals("laureati")) { 

			dtm.addRow(new Object[] {"LAUREATI:", "--", "--", "--", "--"});
			coll = DatabaseManager.getIscritti();

		}

		if(coll != null) {
			FindIterable<Document> results = coll.find(docQuery);
			
			Boolean found = false;
			for(Document temp : results) {
				found = true;
				dtm.addRow(new Object[] {temp.getString("ANNO"), temp.getString("ATENEO_CODICE"), temp.getString("ATENEO_NOME"), temp.getInteger("STUDENTI")});
			}
			
			if(!found) dtm.addRow(new Object[] {"NOT FOUND"});
			
			
		}



		DatabaseManager.close();

	}
}

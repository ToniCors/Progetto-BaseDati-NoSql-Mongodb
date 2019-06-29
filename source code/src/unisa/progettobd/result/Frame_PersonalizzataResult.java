package unisa.progettobd.result;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import unisa.progettobd.service.DatabaseManager;

public class Frame_PersonalizzataResult extends JFrame{

	private static final long serialVersionUID = 1L;

	public Frame_PersonalizzataResult(String tipo, String tipoAggregazione, Vector<String> vectUni, Boolean anno[], String queryNumero, int numero) {
		super();


		Vector<String> vectNewUni = new Vector<String>();

		if(vectUni != null) {
			//getting the uni
			for(int i = 0; i<vectUni.size(); i++) {
				String uni = vectUni.get(i);
				String code = uni.split(",")[0];
				vectNewUni.add(code);

			}
		}


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

		BasicDBObject query = new BasicDBObject();
		List<BasicDBObject> queryTotal = new ArrayList<BasicDBObject>();

		BasicDBObject annoObjQuery = new BasicDBObject();
		List<BasicDBObject> annoQuery = new ArrayList<BasicDBObject>();

		if(anno[0]) annoQuery.add(new BasicDBObject("ANNO", "2014"));
		if(anno[1]) annoQuery.add(new BasicDBObject("ANNO", "2015"));
		if(anno[2]) annoQuery.add(new BasicDBObject("ANNO", "2016"));

		annoObjQuery.put("$or", annoQuery);
		queryTotal.add(annoObjQuery);




		if(vectUni != null) {
			BasicDBObject uniObjQuery = new BasicDBObject();
			List<BasicDBObject> uniQuery = new ArrayList<BasicDBObject>();

			for(int i = 0; i<vectNewUni.size(); i++) {
				uniQuery.add(new BasicDBObject("ATENEO_CODICE", vectNewUni.get(i)));

			}

			uniObjQuery.put("$or", uniQuery);
			queryTotal.add(uniObjQuery);

		}




		if(queryNumero.equals("maggiore")) queryTotal.add(new BasicDBObject("STUDENTI", new BasicDBObject("$gt", numero)));
		else if(queryNumero.equals("minore")) queryTotal.add(new BasicDBObject("STUDENTI", new BasicDBObject("$lt", numero)));
		else if(queryNumero.equals("uguale")) queryTotal.add(new BasicDBObject("STUDENTI", new BasicDBObject("$eq", numero)));



		query.put("$and", queryTotal);


		MongoCollection<Document> coll_laureati = null, coll_iscritti = null;
		FindIterable<Document> resultsIscritti = null, resultsLaureati = null;

		if(tipo.equals("iscritti")) {
			coll_iscritti = DatabaseManager.getIscritti();

			resultsIscritti = coll_iscritti.find(query);

		}else if(tipo.equals("laureati")) {
			coll_laureati = DatabaseManager.getLaureati();
			resultsLaureati = coll_laureati.find(query);

		}else if(tipo.equals("entrambi")){
			coll_iscritti = DatabaseManager.getIscritti();
			coll_laureati = DatabaseManager.getLaureati();

			resultsIscritti = coll_iscritti.find(query);
			resultsLaureati = coll_laureati.find(query);

		}

		int somma, count, minimo, massimo;



		if(resultsIscritti != null) {
			dtm.addRow(new Object[] {"ISCRITTI:", "--", "--", "--", "--"});
			somma = 0;
			count = 0;
			massimo = 0;
			minimo = 999999;



			if(tipoAggregazione.equals("Nessuno")){
			
				for(Document temp : resultsIscritti) {
					dtm.addRow(new Object[] {temp.getString("ANNO"), temp.getString("ATENEO_CODICE"), temp.getString("ATENEO_NOME"), temp.getInteger("STUDENTI")});
					count++;
				}
			}else {
				for(Document temp : resultsIscritti) {

					if(temp.getInteger("STUDENTI") < minimo) minimo = temp.getInteger("STUDENTI");
					if(temp.getInteger("STUDENTI") > massimo) massimo = temp.getInteger("STUDENTI");
					somma += temp.getInteger("STUDENTI");
					count++;

				}
			}

			if(count!=0) {
				if(!tipoAggregazione.equals("Nessuno")) {
					if(tipoAggregazione.equals("Somma")) dtm.addRow(new Object[] {"SOMMA:", "--", "--", somma}); 
					else if(tipoAggregazione.equals("Media")) dtm.addRow(new Object[] {"MEDIA:", "--", "--", somma/count}); 
					else if(tipoAggregazione.equals("Minimo")) dtm.addRow(new Object[] {"MINIMO:", "--", "--", minimo}); 
					else if(tipoAggregazione.equals("Massimo")) dtm.addRow(new Object[] {"MASSIMO:", "--", "--", massimo}); 
				}
			}else dtm.addRow(new Object[] {"NOT FOUND"});
		}





		if(resultsLaureati != null) {
			dtm.addRow(new Object[] {"LAUREATI:", "--", "--", "--", "--"});
			somma = 0;
			count = 0;
			massimo = 0;
			minimo = 999999;

			if(tipoAggregazione.equals("Nessuno")) {
				for(Document temp : resultsLaureati) {
					dtm.addRow(new Object[] {temp.getString("ANNO"), temp.getString("ATENEO_CODICE"), temp.getString("ATENEO_NOME"), temp.getInteger("STUDENTI")});
					count++;
				}
			}else {
				for(Document temp : resultsLaureati) {
					if(temp.getInteger("STUDENTI") < minimo) minimo = temp.getInteger("STUDENTI");
					if(temp.getInteger("STUDENTI") > massimo) massimo = temp.getInteger("STUDENTI");
					somma += temp.getInteger("STUDENTI");
					count++;

				}
			}

			if(count!=0) {
				if(!tipoAggregazione.equals("Nessuno")){
					if(tipoAggregazione.equals("Somma")) dtm.addRow(new Object[] {"SOMMA:", "--", "--", somma}); 
					else if(tipoAggregazione.equals("Media")) dtm.addRow(new Object[] {"MEDIA:", "--", "--", somma/count}); 
					else if(tipoAggregazione.equals("Minimo")) dtm.addRow(new Object[] {"MINIMO:", "--", "--", minimo}); 
					else if(tipoAggregazione.equals("Massimo")) dtm.addRow(new Object[] {"MASSIMO:", "--", "--", massimo}); 
				}
			}else dtm.addRow(new Object[] {"NOT FOUND"});



		}



		DatabaseManager.close();
	}
}

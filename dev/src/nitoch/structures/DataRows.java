package nitoch.structures;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DataRows {
	LinkedList<DataRow> lst = new LinkedList<>();
	Map<Integer,String> cols = new HashMap<>();
	int iter=-1;
	public DataRows(ResultSet rs) throws SQLException {
		ResultSetMetaData rmd=rs.getMetaData();
		int len = rmd.getColumnCount();
		for (int i = 1; i <= len; i++)
			cols.put(i, rmd.getColumnName(i));
		
		while(rs.next())
			lst.add(new DataRow(rs));
	}
	private DataRows(LinkedList<DataRow> lst,Map<Integer,String> cols) {
		this.lst = lst;
		this.cols = cols;
	}
	public boolean next() {
		return ++iter<lst.size();
	}

	public Object get(String column) {
		return lst.get(iter).get(column);
	}
	public String getString(String column) {
		return lst.get(iter).getString(column);
	}
	public int getInt(String column) {
		return lst.get(iter).getInt(column);
	}
	public Object get(int i) {
		return get(cols.get(i));
	}
	public String getString(int i) {
		return getString(cols.get(i));
	}
	public int getInt(int i) {
		return getInt(cols.get(i));
	}
	
	public DataRow getAt(int i) {
		return lst.get(i);
	}
	
	public DataRows select(String colName,String value) {
		LinkedList<DataRow> lst2=new LinkedList<>();
		for(DataRow dr : lst)
			if(dr.getString(colName).equals(value))
				lst.add(dr.clone());
		DataRows drs = new DataRows(lst2,cols);
		return drs;
	}
	//use this function any time you return DataRows instance from a function or after iterate it.
	public void resetIterator() {
		iter=-1;
	}
}

package nitoch.structures;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DataRow {
	Map<String, Object> dic = new HashMap<>();
	Map<Integer,String> cols = new HashMap<>();
	public DataRow(ResultSet rs) throws SQLException{
		ResultSetMetaData rmd=rs.getMetaData();
		int len = rmd.getColumnCount();
		for (int i = 1; i <= len; i++) {
			String col=rmd.getColumnName(i);
			cols.put(i, col);
			dic.put(col,rs.getString(col));
		}
	}
	private DataRow(DataRow dr) {
		for(Map.Entry<String, Object> entry : dic.entrySet())
			this.dic.put(entry.getKey(), entry.getValue());
		for(Map.Entry<Integer, String> entry : cols.entrySet())
			this.cols.put(entry.getKey(), entry.getValue());
	}
	public DataRow clone() {
		return new DataRow(this);
	}
	public Object get(String column) {
		return dic.get(column);
	}
	public String getString(String column) {
		return dic.get(column).toString();
	}
	public int getInt(String column) {
		return Integer.parseInt(getString(column));
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
	
}

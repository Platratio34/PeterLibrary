package dataManagment;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import inputs.Files;

public class YAMLSection {

	private Map<String, YAMLSection> sections;
	private Map<String, String> values;
	private List<String> list;
	
	public YAMLSection() {
		clear();
	}
	public YAMLSection(File file) {
		clear();
		load(file);
	}
	
	public void clear() {
		sections = new HashMap<String, YAMLSection>();
		values = new HashMap<String, String>();
		list = new ArrayList<String>();
	}
	
	public void load(File file) {
		String[] cont = Files.fileAsArray(file);
		int l = 0;
		for(int i = 0; i < cont.length; i++) {
			
		}
	}
	
	public void setValue(String key, Object value) {
		values.put(key, value.toString());
	}
	
	public void setSection(String key, YAMLSection section) {
		sections.put(key, section);
	}
}

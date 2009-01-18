package org.jpericia.core.components;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;

public class ListExtended extends List
{

	private HashMap itens = new HashMap();
	
	public ListExtended(Composite parent, int style) {
		super(parent, style);
	}
	
	public void setMap(HashMap map)
	{
		this.itens = map;
		
		Set keyMap = itens.keySet();
		for (Iterator iterator = keyMap.iterator(); iterator.hasNext();) {
			Long key = (Long) iterator.next();
			String value = (String)itens.get(key);
			add(value);
		}
	}
	
	public String getValue()
	{
		String returnValue = "";
		
		int selectedKey = getSelectionIndex();
		String selectedValue = getItem(selectedKey);
		
		//Obtem a chave do valor
		Set keyMap = itens.keySet();
		for (Iterator iterator = keyMap.iterator(); iterator.hasNext();) {
			Long key = (Long) iterator.next();
			String value = (String)itens.get(key);
			
			if (value.equals(selectedValue))
			{
				returnValue = key.toString();
				break;
			}
			else
			{
				continue;
			}
		}
		
		return returnValue;
	}
	
	public String[] getKeys()
	{	
		int[] selectedKeys = getSelectionIndices();
		String[] selectedValues = getSelection();
		String[] returnValue = new String[selectedKeys.length];
		
		//Obtem a chave do valor
		Set keyMap = itens.keySet();
		
		for (Iterator iterator = keyMap.iterator(); iterator.hasNext();)
		{
			Long key = (Long) iterator.next();
			String value = (String)itens.get(key);
			
			for(int i=0;i<selectedKeys.length;i++)
			{
				if (value.equals(selectedValues[i]))
				{
					returnValue[i] = key.toString();
				}
			}
		}
		
		return returnValue;
	}	
	
	public String[] getValues()
	{	
		int[] selectedKeys = getSelectionIndices();
		String[] selectedValues = getSelection();
		String[] returnValue = new String[selectedKeys.length];
		
		//Obtem a chave do valor
		Set keyMap = itens.keySet();
		
		for (Iterator iterator = keyMap.iterator(); iterator.hasNext();)
		{
			Long key = (Long) iterator.next();
			String value = (String)itens.get(key);
			
			for(int i=0;i<selectedKeys.length;i++)
			{
				if (value.equals(selectedValues[i]))
				{
					returnValue[i] = value.toString();
				}
			}
		}
		
		return returnValue;
	}
	
	protected void checkSubclass() {
		//Disable the check that prevents subclassing of SWT components
	} 
	
}

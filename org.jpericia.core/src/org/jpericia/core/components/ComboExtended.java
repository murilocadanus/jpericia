package org.jpericia.core.components;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

public class ComboExtended extends Combo
{

	private HashMap itens = new HashMap();
	
	public ComboExtended(Composite parent, int style) {
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
	
	protected void checkSubclass() {
		//Disable the check that prevents subclassing of SWT components
	} 
	
}

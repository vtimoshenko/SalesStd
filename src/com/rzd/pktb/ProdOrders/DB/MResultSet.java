package com.rzd.pktb.ProdOrders.DB;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.LinkedList;

/**
 * Класс для выборки данных из БД.
 * Позволяет выбрать все строки, закрыть соединение и только потом получать результаты.
 * Эмитирует работу ResultSet.
 * 
 * @author vtimoshenko
 *
 */
public class MResultSet
{
	//			//			//										//
	public  	LinkedList<LinkedList<String>> results = new LinkedList<LinkedList<String>>();//Набор строк
	private 	int			pointer;											//Указатель на текущую строку
	public 		LinkedList<String> Colnames = new LinkedList<String>();			//Набор имён столбцов
	private		int			cols;									//Количество колонок 
	
	//******************************************************************
	/**
	 * Передвинуть курсор на следующую строку
	 * 
	 * @return	возвращает true если ещё есть строки
	 */
	public		boolean		next()
	{
		pointer++;
		if (pointer < results.size()) { return true; }
		else return false;
	}
	/**
	 * получить строку данных здесь обратное преобразование в null. Все работает адекватно.
	 * 
	 * @param index номер столбца начиная с 1
	 * @return содержимое поля
	 */
	public		String		getString(int index)
	{
		index--;
		if (index < results.get(pointer).size())
		{
			if (results.get(pointer).get(index).equals("NULL")) return null;
			else return results.get(pointer).get(index);
		}
		else return null;
	}
	/**
	 * Получение данных из стандартного набора при чтении с базы
	 * 
	 * @param rs набор данных
	 * @return статус чтения
	 */
	public		boolean		getFromRS (ResultSet rs)
	{
		results.clear();
		pointer = -1;
		try
		{
			ResultSetMetaData rsmd = rs.getMetaData();
			cols = rsmd.getColumnCount();
			for (int i=1;i<=cols;i++)
			{
				Colnames.add(rsmd.getColumnLabel(i));
			}
			int cind=0;
			while (rs.next())
			{
				results.add(new LinkedList<String>());
				for (int i=0;i<cols;i++)
				{
					if (rs.getString(i+1) == null) results.get(cind).add("NULL");	//Если встречаем null записываем строковый эквивалент дабы избежать вылетов
					else results.get(cind).add(rs.getString(i+1).trim());
				}
				cind++;
			}
		}
		catch (Exception e)
		{
			System.out.println("Extracting data from Result Set FAILURE!");
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * узнать размеры результирующего набора
	 * 
	 * @return количество строк
	 */
	public		int			getSize()
	{
		return results.size();
	}
	/**
	 * По умолчанию курсор выставляется в начало
	 */
	MResultSet ()
	{
		pointer = -1;
	}
	/**
	 * получить перечень колонок
	 * 
	 * @return связанный список с именами колонок
	 */
	public		LinkedList<String>	getCols()
	{
		return Colnames;
	}
	//******************************************************************
}

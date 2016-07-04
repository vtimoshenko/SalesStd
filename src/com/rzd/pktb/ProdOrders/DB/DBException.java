package com.rzd.pktb.ProdOrders.DB;

import java.util.LinkedList;

public class DBException extends Exception
{
	private		String		Message;
	private		LinkedList<String> Transaction;
	private		Exception	ex;

	/**
	 * простой конструктор с сообщением
	 * 
	 * @param nMessage сообщение о ошибке
	 */
	public 		DBException (String nMessage)
	{
		Message = nMessage;
		Transaction = new LinkedList<String>();
		ex = null;
	}
	/**
	 * конструктор с сообщением и внутренним исключением
	 * 
	 * @param nMessage	сообщение об ошибке
	 * @param nex		причинное исключение
	 */
	public 		DBException (String nMessage, Exception nex)
	{
		Message = nMessage;
		Transaction = new LinkedList<String>();
		ex = nex;
	}
	/**
	 * полный конструктор для транзакции
	 * 
	 * @param nMessage	сообщение об ошибке
	 * @param nex		причинное исключение
	 * @param TransComs	команды транзакции
	 */
	public 		DBException (String nMessage, Exception nex, LinkedList<String> TransComs)
	{
		Message = nMessage;
		Transaction = TransComs;
		ex = nex;
	}
	/**
	 * конструктор для одной команды
	 * 
	 * @param nMessage	сообщение
	 * @param nex		причинное исключение
	 * @param TransCom	команда
	 */
	public 		DBException (String nMessage, Exception nex, String TransCom)
	{
		Message = nMessage;
		Transaction = new LinkedList<String>();
		Transaction.add(TransCom);
		ex = nex;
	}
	/**
	 * Сообщение об ошибке
	 */
	public 		String 		getMessage ()
	{
		return Message;
	}
	/**
	 * Список команд транзакции
	 * 
	 * @return
	 */
	public		String		getTransaction()
	{
		String str = "";
		for (int i=0;i<Transaction.size();i++) str += Transaction.get(i) + "\n";
		return str;
	}
	/**
	 * Причинное исключение
	 * 
	 * @return исключение вызвавшее отказ
	 */
	public		Exception	getInnerException()
	{
		return ex;
	}
	/**
	 * печать сообщения об ошибке
	 */
	public 		void 		printErrorReport()
	{
		System.out.println(getErrorReport());
		this.printStackTrace();
	}
	public		String		getErrorReport()
	{	
		String msg = "Ошибка при обращении к БД (" + Message + ")\n";
		if (ex!=null) msg += " Исключение:" + ex.getMessage();
		if (Transaction.size()>0)
		{
			msg += " Транзакция:";
			for (int i=0;i<Transaction.size();i++) msg += "\n  " + Transaction.get(i);
		}
		return msg;
	}
}

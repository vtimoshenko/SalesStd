package com.rzd.pktb.ProdOrders.DB;

public class DBAssist
{
	/**
	 * Вставка в ДБ2
	 * 
	 * @param input
	 * @return
	 */
	public static String InsertStr(String input)
	{
		StringBuffer buf = new StringBuffer("");
		for (int i=0;i<input.length();i++)
		{
			char app = input.charAt(i);
			if (app == '“') app = '"';	//замена экзотических символов
			else if (app == '”') app = '"';
			else if (app == '‘') app = '\'';
			else if (app == '’') app = '\'';
			else if (app == '«') app = '"';
			else if (app == '»') app = '"';
			buf.append(app);
			if (app=='\'') buf.append("'"); //удвоение одиночных кавычек
		}
		String ret = ConcatStr (buf.toString()); //конкат
		return ret;
	}
	/**
	 * При инсерте в ДБ2 необходимо конкатить длинные строки
	 * 
	 * @param ishod
	 * @return
	 */
	private static String ConcatStr(String ishod)
	{
		int maxlength = 255; //максимальная длина строки для конката
		
		if (ishod==null) return "";	//если вход пустой возвращаем пустую строку
		StringBuffer result = new StringBuffer("");
		do
		{
			if (ishod.length()<=maxlength) //если строка не длиннее максимальной, просто записываем в выход
			{
				result.append(ishod);
				ishod = "";
			}
			else
			{
				int end = maxlength;
				//если слева от конката будет одиночная кавычка может случится косяк, сдвинем конкат левее
				while (ishod.charAt(end-1)=='\'') end--; 
				result.append(ishod.substring(0, end));
				result.append("'concat'");
				ishod = ishod.substring(end, ishod.length());
			}
		}
		while (ishod.length()>0);
		return result.toString();
	}
}

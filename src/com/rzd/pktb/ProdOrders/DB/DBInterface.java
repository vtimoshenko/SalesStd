package com.rzd.pktb.ProdOrders.DB;

import com.rzd.pktb.JSONCluster.ClusterException;
import com.rzd.pktb.JSONCluster.ClusterOne;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

/**
 * Интерфейс работы с СУБД.
 * Заточен под DB2. Однако легко адаптируется к любым JDBC поддерживающим СУБД.
 * Включает конфигурируемый исполнитель хранимыъх процедур
 * 
 * @author vtimoshenko
 *
 */
public class DBInterface
{
	//			//				//						//							//
	//Базовые настройки интерфейса
	private		String			user;					//Пользователь от чьего имени происходит доступ к базе
	private		String			password;				//Пароль пользователя
	private		String			URL;					//УРЛ базы
	//Контроль ошибок
	
	//Базовые процедуры интерфейса**************************************
	/**
	 * Выполнение хранимой процедуры
	 * 
	 * 
	 * @param ProcedureID идентификатор процедуры (текстовый)
	 * @param ParamTypes типы параметров (i - input o - output.) Пример: iiiioo / ioo / oo
	 * @param ParamVals значения параметров
	 * @throws DBException
	 */
	public		void			ExecuteProcedure (String ProcedureID, String ParamTypes ,String[] ParamVals)
	throws DBException
	{
		if (ProcedureID==null || ParamTypes==null) throw new DBException("Не указан идентификатор процедуры или список параметров");
		if (ParamTypes.length() != ParamVals.length) throw new DBException("Не совпадает количество указанных параметров и их набор");
		try
		{
			//Формирование строки вызова
			String Cll = "call " + ProcedureID + "(";
			for (int i=0;i<ParamTypes.length();i++) Cll += "?,";
			Cll = Cll.substring(0, Cll.length()-1);
			Cll += ")";
			//Создание подключения
			Connection con = DriverManager.getConnection(URL, user, password);
			CallableStatement cstmt = con.prepareCall(Cll);
			//Установка параметров
			for (int i=0;i<ParamTypes.length();i++)
			{
				if (ParamTypes.charAt(i)=='i' || ParamTypes.charAt(i)=='I')
				{//Входной параметр
					cstmt.setString(i+1, ParamVals[i]);
				}
				if (ParamTypes.charAt(i)=='o' || ParamTypes.charAt(i)=='O')
				{//выходной параметр
					cstmt.registerOutParameter(i+1, java.sql.Types.VARCHAR);
				}
			}
			cstmt.execute();
			for (int i=0;i<ParamTypes.length();i++)
			{
				if (ParamTypes.charAt(i)=='i' || ParamTypes.charAt(i)=='I')
				{//Входной параметр
				}
				if (ParamTypes.charAt(i)=='o' || ParamTypes.charAt(i)=='O')
				{//выходной параметр
					ParamVals[i] = cstmt.getString(i+1);
				}
			}
            cstmt.close();
            con.close();
		}
		catch (SQLException sqle)
		{
			throw new DBException("Не удалось выполнить процедуру " + ProcedureID + "", sqle);
		}
	}
	/**
	 * Тест соединения
	 * @throws DBException
	 */
	public		void			ConnectionTest ()
	throws DBException
	{
		try
		{
			Connection con = DriverManager.getConnection(URL, user, password);
			DatabaseMetaData dma = con.getMetaData();
			System.out.println("URL: " + dma.getURL());
			System.out.println("UserID: " + dma.getUserName());
			System.out.println("Ver: " + dma.getDatabaseProductVersion());
			con.close();
		}
		catch (SQLException e)
		{
			throw new DBException("Не удалось проверить соединение!", e);
		}
	}
	/**
	 * Выполнить транзакцию модификации данных
	 * 
	 * @param quer набор транзакций
	 * @return статус выполнения
	 * @throws DBException
	 */
	public		boolean			MakeUpdate (LinkedList<String> quer)
	throws DBException
	{
		try
		{
			Connection con = DriverManager.getConnection(URL, user, password);
			try
			{//Попытка внести изменения в информацию
				con.setAutoCommit(false);									//Все команды в одну транзакцию
				Statement sm = con.createStatement();
				for (int i=0;i<quer.size();i++)								//Перебор команд
				{
					sm.executeUpdate(quer.get(i));
				}
				con.commit();
				sm.close();
			}
			catch (SQLException QuerrsFail)
			{//Если не получилось внести изменения
			    try
			    {//Попытка отката
			       con.rollback();											//Откатить изменения
			       throw new DBException("Не удалось выполнить транзакцию", QuerrsFail, quer);
			    }
			    catch (SQLException RollbackFail)
			    {//Если откат не удался. Совсем плохо.
			    	throw new DBException("Не удалось выполнить транзакцию и даже откатить изменения!!!", RollbackFail, quer);
			    }
			}
			finally
			{
				con.close();	//Закрыть соединение в любом случае
			}										
		}
		catch (SQLException ConnectionFail)
		{
			throw new DBException("Не удалось установить подключение", ConnectionFail);
		}
		return true;
	}
	/**
	 * Выполнить транзакцию модификации данных
	 * 
	 * @param quer транзакция
	 * @return статус выполнения
	 * @throws DBException
	 */
	public		boolean			MakeUpdate (String quer)
	throws DBException
	{
		LinkedList<String> querrs = new LinkedList<String>();
		querrs.add(quer);
		return MakeUpdate(querrs);
	}
	/**
	 * Выполнить выборку данных
	 * 
	 * @param select строка запроса
	 * @return набор результирующих данных
	 * @throws DBException
	 */
	public		MResultSet		MakeSelect (String select)
	throws DBException
	{
		try
		{
			Connection con = DriverManager.getConnection(URL, user, password);
			Statement sm = con.createStatement();
			MResultSet mrs = new MResultSet();
			ResultSet rs = sm.executeQuery(select/* + " with ur"*/);
			mrs.getFromRS(rs);
			rs.close();
			sm.close();
			con.close();
			return mrs;
		}
		catch (SQLException SelectFail)
		{
			throw new DBException("Не удалось выполнить запрос", SelectFail, select);
		}
	}
	/**
	 * Выполнить запрос и получить результат в JSON
	 * 
	 * @param select строка запроса 
	 * @return набор данных JSON
	 * @throws DBException
	 */
	public ClusterOne MakeJSelect (String select)							//Выборка данных
	throws DBException
	{
		try
		{
			Connection con = DriverManager.getConnection(URL, user, password);
			Statement sm = con.createStatement();
			ClusterOne result = new ClusterOne();
			ResultSet rs = sm.executeQuery(select/* + " with ur"*/);
			result.GetFromRS(rs);
			rs.close();
			sm.close();
			con.close();
			return result;
		}
		catch (SQLException SelectFail)
		{	
			throw new DBException("Не удалось выполнить запрос", SelectFail, select);
		}
		catch (ClusterException ClusterFail)
		{
			throw new DBException("Не удалось сформировать JSON", ClusterFail, select);
		}
	}
	//******************************************************************
	
	
	/**
	 * Инициализация интерфейса
	 * 
	 * @param driver	драйвер
	 * @param nURL		УРЛ базы
	 * @param nuser		пользователь
	 * @param npassword	пароль
	 * @throws DBException
	 */
	public		DBInterface(String driver, String nURL, String nuser, String npassword)
	throws DBException
	{
		try
		{
			user = nuser;
			password = npassword;
			URL = nURL;
			Class.forName(driver);
		}
		catch (Exception e)
		{
			throw new DBException("Не удалось инициализировать интерфейс с БД");
		}
	}
}

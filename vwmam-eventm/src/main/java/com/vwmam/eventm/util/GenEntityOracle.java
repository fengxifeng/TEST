package com.vwmam.eventm.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class GenEntityOracle {

	private String packageOutPath = "com.vwmam.eventm.entity";// 指定实体生成所在包的路径
	// （根据业务方法名称定义修改package名称）
	private String authorName = "chuxunfeng";// 作者名字
	// TODO
	private String tablename = "EventLevel";// 表名
	private String[] colnames; // 列名数组
	private String[] colTypes; // 列名类型数组
	private int[] colReads; // 列名是否为只读
	private int[] colSizes; // 列名大小数组
	private int[] colScale; // 后补位数
	private boolean f_util = false; // 是否需要导入包java.util.*
	private boolean f_sql = false; // 是否需要导入包java.sql.*
	private boolean f_BigDecimal = false; // 是否需要导入包java.math.BigDecimal

	// 数据库连接
	private static final String URL = "jdbc:mysql://192.168.2.158:3306/VWMA?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	private static final String NAME = "root";
	private static final String PASS = "123456";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	
	
	/*
	 * 构造函数
	 */
	public GenEntityOracle() {
		// 创建连接
		Connection con;
		// 查要生成实体类的表
		String sql = "select * from " + tablename;
		Statement pStemt = null;
		try {
			try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			con = DriverManager.getConnection(URL, NAME, PASS);
			pStemt = (Statement) con.createStatement();
			ResultSet rs = pStemt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int size = rsmd.getColumnCount(); // 统计列
			colnames = new String[size];
			colTypes = new String[size];
			colSizes = new int[size];
			colReads = new int[size];
			colScale = new int[size];
			for (int i = 0; i < size; i++) {
				colnames[i] = rsmd.getColumnName(i + 1);
				colTypes[i] = rsmd.getColumnTypeName(i + 1);

				if (colTypes[i].equalsIgnoreCase("date") || colTypes[i].equalsIgnoreCase("timestamp")) {
					f_util = true;
				}
				if (colTypes[i].equalsIgnoreCase("blob") || colTypes[i].equalsIgnoreCase("char")) {
					f_sql = true;
				}
				int scale = rsmd.getScale(i + 1);
				if (scale > 0) {
					f_BigDecimal = true;
				}
				colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
				colReads[i] = rsmd.isNullable(i + 1);
				colScale[i] = rsmd.getScale(i + 1);
			}

			String content = parse(colnames, colTypes, colSizes, colReads, colScale);

			try {
				File directory = new File("");
				String outputPath = directory.getAbsolutePath() + "/src/main/java/" + this.packageOutPath.replace(".", "/") + "/" + initcap(chaged(tablename)) + ".java";
				FileWriter fw = new FileWriter(outputPath);
				PrintWriter pw = new PrintWriter(fw);
				pw.println(content);
				pw.flush();
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 功能：生成实体类主体代码
	 * 
	 * @param colnames
	 * @param colTypes
	 * @param colSizes
	 * @return
	 */
	private String parse(String[] colnames, String[] colTypes, int[] colSizes, int[] colReads, int[] colScale) {
		StringBuffer sb = new StringBuffer();

		// 判断是否导入工具包
		sb.append("package " + this.packageOutPath + ";\r\n\r\n");
		if (f_util) {
			sb.append("import java.util.Date;\r\n");
		}
		if (f_sql) {
			sb.append("import java.sql.*;\r\n");
		}
		if (f_BigDecimal) {
			sb.append("import java.math.BigDecimal;\r\n");
		}
		sb.append("import static javax.persistence.GenerationType.SEQUENCE;\r\n");
		sb.append("import javax.persistence.Id;\r\n");
		sb.append("import javax.persistence.GeneratedValue;\r\n");
		sb.append("import javax.persistence.SequenceGenerator;\r\n");
		sb.append("import javax.persistence.Column;\r\n");
		sb.append("import javax.persistence.Entity;\r\n");
		sb.append("import javax.persistence.Table;\r\n");
		sb.append("\r\n");
		// 注释部分
		sb.append("/**\r\n");
		sb.append(" * " + tablename + " 实体类\r\n");
		sb.append(" * @author " + authorName + "\r\n");
		sb.append(" * " + new Date() + "\r\n");
		sb.append("**/\r\n\r");
		// 实体部分
		sb.append("@Entity\r\n");
		sb.append("@Table(name = \"" + initcap(tablename) + "\")");

		sb.append("\r\npublic class " + initcap(chaged(tablename)) + " implements java.io.Serializable {\r\n\n");
		processAllAttrs(sb);// 属性
		processAllMethod(sb);// get set方法
		sb.append("}\r\n");

		return sb.toString();
	}

	/**
	 * 功能：生成所有属性
	 * 
	 * @param sb
	 */
	private void processAllAttrs(StringBuffer sb) {

		sb.append("\tprivate static final long serialVersionUID = " + System.currentTimeMillis() + "L;\r\n");
		for (int i = 0; i < colnames.length; i++) {
			sb.append("\tprivate " + sqlType2JavaType(colTypes[i], colScale[i]) + " " + chaged(colnames[i].toLowerCase()) + ";\r\n");
		}

	}

	/**
	 * 去"_",下个字母大写
	 * 
	 * @param srcStr
	 * @return
	 */
	public String chaged(String srcStr) {
		String str = srcStr.toLowerCase();
		if (str.contains("_")) {
			str = replaceUnderlineAndfirstToUpper(str, "_", "");
		}
		return str;
	}

	/**
	 * 首字母大写
	 * 
	 * @param srcStr
	 * @return
	 */
	public String firstCharacterToUpper(String srcStr) {
		return srcStr.substring(0, 1).toUpperCase() + srcStr.substring(1);
	}

	/**
	 * 替换字符串并让它的下一个字母为大写
	 * 
	 * @param srcStr
	 * @param org
	 * @param ob
	 * @return
	 */
	public String replaceUnderlineAndfirstToUpper(String srcStr, String org, String ob) {
		String newString = "";
		int first = 0;
		while (srcStr.indexOf(org) != -1) {
			first = srcStr.indexOf(org);
			if (first != srcStr.length()) {
				newString = newString + srcStr.substring(0, first) + ob;
				srcStr = srcStr.substring(first + org.length(), srcStr.length());
				srcStr = firstCharacterToUpper(srcStr);
			}
		}
		newString = newString + srcStr;
		return newString;
	}

	/**
	 * 功能：生成所有方法
	 * 
	 * @param sb
	 */
	private void processAllMethod(StringBuffer sb) {
		for (int i = 0; i < colnames.length; i++) {
			sb.append("\t\r\n");
			//
			String colString = chaged(colnames[i].toLowerCase());

			sb.append("\tpublic void set" + initcap(colString) + "(" + sqlType2JavaType(colTypes[i], colScale[i]) + " " + colString + "){\r\n");
			sb.append("\t\tthis." + colString + " = " + colString + ";\r\n");
			sb.append("\t}\r\n");

			if (i == 0) {
				sb.append("\t@Id\r\n");
			}
			String nullable = "false";
			if (colReads[i] == 1) {
				nullable = "true";
			}
			sb.append("\t@Column(name = \"" + colnames[i] + "\", nullable = " + nullable + ", precision = " + colSizes[i] + ", scale = " + colScale[i] + ")\r\n");
			sb.append("\tpublic " + sqlType2JavaType(colTypes[i], colScale[i]) + " get" + initcap(colString) + "(){\r\n");
			sb.append("\t\treturn this." + colString + ";\r\n");
			sb.append("\t}\r\n");
		}
	}

	/**
	 * 功能：将输入字符串的首字母改成大写
	 * 
	 * @param str
	 * @return
	 */
	private String initcap(String str) {
		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}

		return new String(ch);
	}

	/**
	 * 功能：获得列的数据类型
	 * 
	 * @param sqlType
	 * @return
	 */
	private String sqlType2JavaType(String sqlType, int colScale) {

		if (sqlType.equalsIgnoreCase("binary_double")) {
			return "double";
		} else if (sqlType.equalsIgnoreCase("binary_float")) {
			return "float";
		} else if (sqlType.equalsIgnoreCase("blob")) {
			return "byte[]";
		} else if (sqlType.equalsIgnoreCase("blob")) {
			return "byte[]";
		} else if (sqlType.equalsIgnoreCase("char") || sqlType.equalsIgnoreCase("nvarchar2") || sqlType.equalsIgnoreCase("varchar2")) {
			return "String";
		} else if (sqlType.equalsIgnoreCase("date") || sqlType.equalsIgnoreCase("timestamp") || sqlType.equalsIgnoreCase("timestamp with local time zone") || sqlType.equalsIgnoreCase("timestamp with time zone")) {
			return "Date";
		} else if (sqlType.equalsIgnoreCase("number")) {
			if (colScale  > 0) {
				return "BigDecimal";
			}
			return "Integer";
		}
		return "String";
	}

	/**
	 * 出口 TODO
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("数据bean开始自动生成！");
		new GenEntityOracle();
		System.out.println("自动生成完成！");
	}

}
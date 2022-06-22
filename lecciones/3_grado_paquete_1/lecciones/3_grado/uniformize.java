import java.io.*;
import java.util.*;

public class uniformize {
	
	static final String inicio(String title) {
		return 
			"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n"+
			"<html>\r\n"+
			"<head>\r\n"+
			"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">\r\n"+
			"   <title>"+title+"</title>\r\n"+
			"   <link href=\"css/lecciones_3.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n"+
			"</head>\r\n"+
			"<body>\r\n"+
			"<p align=\"center\">\r\n"+title+"\r\n<br/><br/>\r\n";
	}
	
	static final String finalstr(String title) {
		return
			"\r\n<br/><br/>\r\n"+	title+"\r\n</p>\r\n"+
			"</body>\r\n"+
			"</html>\r\n";
	}
	
	static final String png_html(String title) {
		return 
			"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n"+
			"<html>\r\n"+
			"<head>\r\n"+
			"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">\r\n"+
			"   <title>"+title+"</title>\r\n"+
			"   <link href=\"../css/lecciones_3.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n"+
			"</head>\r\n"+
			"<body>\r\n"+
			"<p align=\"center\">\r\n"+	title+"\r\n<br/><br/>\r\n"+
			"<a href=\"../"+title+".html\">\r\n"+
			"<img style=\"border: 3px solid red;\"  src=\""+title+".png\" alt=\"Ver la lecci&oacute;n interactiva\">\r\n"+
			"</a>\r\n"+
			"\r\n<br/><br/>\r\n"+	title+"\r\n</p>\r\n"+
			"</body>\r\n"+
			"</html>\r\n";
	}
	
	static String menu_png_head=
			"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n"+
			"<html>\r\n"+
			"<head>\r\n"+
			"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">\r\n"+
			"   <title>menu</title>\r\n"+
			"   <link href=\"css/menu_3.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n"+
			"</head>\r\n"+
			"<body>\r\n"+
			"<h2 align=\"center\">Paquete 1</h2>\r\n"+
			"<p align=\"left\"><small><a href=\"menu.html\" target=\"_self\">Ir a Interactivos</a></small></p>\r\n"+
			"<h2>Impresiones</h2>\r\n"+
			"<p align=\"left\">\r\n";

	static String menu_head=
			"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n"+
			"<html>\r\n"+
			"<head>\r\n"+
			"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">\r\n"+
			"   <title>menu</title>\r\n"+
			"   <link href=\"css/menu_3.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n"+
			"</head>\r\n"+
			"<body>\r\n"+
			"<h2 align=\"center\">Paquete 1</h2>\r\n"+
			"<p align=\"left\"><small><a href=\"menu_png.html\" target=\"_self\">Ir a Impresiones</a></small></p>\r\n"+
			"<h2>Interactivos</h2>\r\n"+
			"<p align=\"left\">\r\n";

	static String menu_end=
			"</p>\r\n"+
			"</body>\r\n"+
			"</head>\r\n";
	
	
	static String readAll(File f) {
		try {
			BufferedReader BR= new BufferedReader(new FileReader(f));;
			Vector V=new Vector();
			do {
				String str=BR.readLine();
				if (str==null) {
					break;
				}
				if (str.length()>0) {
					V.addElement(str);
				}
			} while (true);
			BR.close();
			String content="";
			for (int i=0;i<V.size();i++) {
				if (i>0) {
					content+="\r\n";
				}
				content+=(String) V.elementAt(i);
			}
			return content;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		File dir=new File(".");
		File[] files=dir.listFiles();
		
		File backups=new File ("backups");
		if (!backups.exists()) {
			backups.mkdirs();
		}
		
		File oldmenu=new File("menu.html");
		if (oldmenu.exists()) {
			oldmenu.delete();
		}
		File oldmenupng=new File("menu_png.html");
		if (oldmenupng.exists()) {
			oldmenupng.delete();
		}
		try {
			BufferedWriter mBW=new BufferedWriter(new FileWriter(oldmenu));
			BufferedWriter mpBW=new BufferedWriter(new FileWriter(oldmenupng));
			mBW.write(menu_head);
			mpBW.write(menu_png_head);
			String t4a="";
			String fn_ant="";
			String t4t3a="";
			for (int i=0;i<files.length;i++) {
				String fn=files[i].getName();
				if (fn.endsWith(".html") && fn.startsWith("130_")) {
					StringTokenizer st=new StringTokenizer(fn,"_.");
					String t1=st.nextToken();
					String t2=st.nextToken();
					String t3=st.nextToken();
					String t4=st.nextToken();
					if (!t4a.equals(t4)) {
						if (!t4a.equals("")) {
							mBW.write("<br>\r\n");
							mpBW.write("<br>\r\n");
						}
						t4a=t4;
					}
					String t5=st.nextToken();
					if (t5.length()<2) {
						t5="0"+t5;
					}
					String t6=st.nextToken();
					String t7=st.nextToken();
					String t8=st.nextToken();
					fn=t1+"_"+t2+"_"+t3+"_"+t4+"_"+t5+"_"+t6+"_"+t7+"_"+t8+".html";
					System.out.println("cleaning "+fn);
					String content=readAll(files[i]);
					int appletix=content.toLowerCase().indexOf("<applet ");
					int endappletix=content.toLowerCase().indexOf("</applet>");
					if (appletix>=0 && endappletix>appletix) {
						mpBW.write("&nbsp; <a href=\"png/png_"+fn+"\" target=\"main\">"+t4+" &nbsp;"+t3+"</a><br/>\r\n");
						mBW.write("&nbsp; <a href=\""+fn+"\" target=\"main\">"+t4+" &nbsp;"+t3+"</a><br/>\r\n");
						String applet=content.substring(appletix,endappletix+9);
						int bix=applet.indexOf(">");
						String applettail=applet.substring(bix+1);
						while (applettail.startsWith("\r")||applettail.startsWith("\n")) {
							applettail=applettail.substring(1);
						}
						if (bix>0) {
							applet="<applet code=\"Arquimedes\" codebase=\"../../\" archive=\"Arquimedes.jar\" width=\"790\" height=\"565\" align=\"top\">\r\n"
								+applettail+"\r\n";
						}
						try {
							// protección
							String oldfn="backups"+File.separator+fn;
							File oldfile=new File(oldfn);
							if (oldfile.exists()) {
								oldfile.delete();
							}
							BufferedWriter BW=new BufferedWriter(new FileWriter(oldfn));
							BW.write(content,0,content.length());
							BW.flush();
							BW.close();
	
							String title=fn.substring(0,fn.indexOf(".html"));
	
							// png
							String pngfn="png"+File.separator+"png_"+fn;
							File pngfile=new File(pngfn);
							if (pngfile.exists()) {
								pngfile.delete();
							}
							BW=new BufferedWriter(new FileWriter(pngfn));
							String pngstr=png_html(title);
							BW.write(pngstr,0,pngstr.length());
							BW.flush();
							BW.close();
	
							// escritura del nuevo uniformizado
							File newfile=new File(fn);
							if (newfile.exists()) {
								newfile.delete();
							}
							String headstr=inicio(title);
							String closestr=finalstr(title);
							BW=new BufferedWriter(new FileWriter(fn));
							BW.write(headstr,0,headstr.length());
							BW.write(applet,0,applet.length());
							BW.write(closestr,0,closestr.length());
							BW.flush();
							BW.close();
							fn_ant=fn;
							t4t3a=t4+"_"+t3;
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						if (fn_ant.length()>0)
						mpBW.write("&nbsp; <a href=\"png/png_"+fn_ant+"\" target=\"main\">"+t4+" &nbsp;"+t3+"</a> - "+t4t3a+"<br/>\r\n");
						mBW.write("&nbsp; <a href=\""+fn_ant+"\" target=\"main\">"+t4+" &nbsp;"+t3+"</a> - "+t4t3a+"<br/>\r\n");
//						mpBW.write("&nbsp;&nbsp; "+t4+" &nbsp;"+t3+"<br/>\r\n");
//						mBW.write("&nbsp;&nbsp; "+t4+" &nbsp;"+t3+"<br/>\r\n");
					}
				}
			}
			mBW.write(menu_end);
			mBW.flush();
			mBW.close();
			mpBW.write(menu_end);
			mpBW.flush();
			mpBW.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
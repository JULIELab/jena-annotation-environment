package de.julielab.annoenv.utils;

public class ExceptionUtils {
	public static  String getCausesFromException(Throwable t) {
		String text = "";
		if (t!=null) {
			text = addText(text,t.getMessage());
			String newText = getCausesFromException(t.getCause());
			text = addText(text,newText);
		}
		
		return text;
	}
	
	private static String addText (String beforeText, String newText) {
		if (newText!=null) {
			if (beforeText.length()>0)
				beforeText += "\n";
			beforeText += newText;
		}
		return beforeText;
	}
}
